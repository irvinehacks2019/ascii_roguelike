import java.lang.Math;

public class MapGenerator {

    private char[][] map;
    private int[][] walls;
    private boolean[][] mapChecker;
    private char wall = '#';
    private char space = ' ';

    public char getWall() { return wall; } 
    public char getSpace() { return space; }
    public char[][] getMap() { return map; }

    public MapGenerator(int y, int x) {
        map = new char[y][x];
        mapChecker = new boolean[y][x];
        walls = new int[map.length / 3][ map[0].length / 3];
        fillMap();
    }

    //DEBUGGING ONLY
    public String wallsToString() {
        String output = "";
        for (int y = 0; y < walls.length; y++) {
            for (int x = 0; x < walls[y].length; x++) {
                output += walls[y][x] + ", ";
            }
            output += "\n";
        }
        return output;        
    }

    public String toString() {
        String output = "";
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                output += map[y][x];
            }
            output += "\n";
        }
        return output;
    }
    private void fillMap() {
        boolean isXEdge = false;
        boolean isYEdge = false;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                isXEdge = x == 0 || x == map[y].length - 1;
                isYEdge = y == 0 || y == map.length - 1;
                if (isXEdge || isYEdge) {
                    map[y][x] = wall;
                } else {
                    map[y][x] = space;
                }

                mapChecker[y][x] = map[y][x] == space;

            }
        }

        if (!allSquaresTraversable()) {
            //System.out.println("A botched map!");
            fillMap();
        }
    }

    private boolean allSquaresTraversable() {
        int yValue = map.length / 2;
        int xValue = map[yValue].length / 2;
        boolean canReachExits = 
        findPath(xValue, yValue, xValue, 0) && 
        findPath(xValue, yValue, 0, yValue) && 
        findPath(xValue, yValue, xValue, map.length - 1) && 
        findPath(xValue, yValue, map[yValue].length - 1, yValue);

        boolean allSquaresUsed = true;
        for (int y = 0; y < mapChecker.length; y++) {
            for (int x = 0; x < mapChecker[y].length; x++) {
                if (!mapChecker[y][x] && map[y][x] == space)
                    allSquaresUsed = false;
            }
        }
        //System.out.println("Traversable: " + canReachExits + "\nAll squares used: " + allSquaresUsed);

        return canReachExits && allSquaresUsed;
    }

    private boolean findPath(int x1, int y1, int x2, int y2) {
        mapChecker[y1][x1] = true;
        boolean output = true;

        if (x1 == x2 && y1 == y2)
            return true;
        else if (x1 == 0 || y1 == 0 || x1 == map[y2].length - 1 || y1 == map.length - 1)
            return false;

        if (!mapChecker[y1][x1+1])
            output = output && findPath(x1+1, y1, x2, y2);
        if (!mapChecker[y1][x1-1])
            output = output && findPath(x1-1, y1, x2, y2);
        if (!mapChecker[y1+1][x1])
            output = output && findPath(x1, y1+1, x2, y2);
        if (!mapChecker[y1-1][x1])
            output = output && findPath(x1, y1-1, x2, y2);

        return output;
    }



    public static void main(String[] args) {
        MapGenerator mg = new MapGenerator(Integer.parseInt(args[0]), Integer.parseInt((args[1])));
        System.out.println(mg);
        System.out.println(mg.wallsToString());
    }

} 