import java.lang.Math;

public class mapGenerator {

    public char[][] map;
    private int[][] walls;
    private boolean[][] mapChecker;
    private char wall = '#';
    private char space = ' ';
    private char door = '-';

    public char getWall() { return wall; } 
    public char getSpace() { return space; }
    public char[][] getMap() { return map; }

    public mapGenerator(int y, int x) {
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

        for (int y = 0; y < walls.length; y++) {
            for (int x = 0; x < walls[y].length; x++) {
                walls[y][x] = (int)(Math.random() * 7);
                if (walls[y][x] > 5)
                    walls[y][x] = 5;
            }
        }

        

        int xCoordinate, yCoordinate;
        for (int y = 0; y < walls.length; y++) {
            yCoordinate = getRelativeCoordinate(y);
            for (int x = 0; x < walls[y].length; x++) {
                xCoordinate = getRelativeCoordinate(x);
                map[yCoordinate][xCoordinate] = space;
                switch (walls[y][x]) {
                    case 1:
                        map[yCoordinate + 1][xCoordinate - 1] = wall; //LOW-LEFT
                        map[yCoordinate][xCoordinate - 1] = wall; //LEFT
                        map[yCoordinate - 1][xCoordinate - 1] = wall; //UP-LEFT          
                        break;
                    case 2:
                        map[yCoordinate - 1][xCoordinate + 1] = wall; //UP-RIGHT
                        map[yCoordinate - 1][xCoordinate - 1] = wall; //UP-LEFT
                        map[yCoordinate - 1][xCoordinate] = wall; //UP                         
                        break;
                    case 3:
                        map[yCoordinate - 1][xCoordinate + 1] = wall; //UP-RIGHT
                        map[yCoordinate][xCoordinate + 1] = wall; //RIGHT
                        map[yCoordinate + 1][xCoordinate + 1] = wall; //LOW-RIGHT
                        break;
                    case 4:
                        map[yCoordinate + 1][xCoordinate + 1] = wall; //LOW-RIGHT
                        map[yCoordinate + 1][xCoordinate] = wall; //LOW
                        map[yCoordinate + 1][xCoordinate - 1] = wall; //LOW-LEFT
                        break;
                    case 5:
                    default:
                        break;
                }
            }
        }
        
        //DOOR
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                isXEdge = x == 0 || x == map[y].length - 1;
                isYEdge = y == 0 || y == map.length - 1;
                if (isXEdge || isYEdge) {
                    map[y][x] = wall;
                    if (x > map[y].length * 2/5 && x < map[y].length * 3/5 && y != 0)
                        map[y][x] = door;
                }

                //mapChecker[y][x] = map[y][x] != space;

            }
        }

        //SAFE SPACE
        //for ()
    }

    private int getRelativeCoordinate(int num) {
        return 2 + 3 * num;
    }

    private boolean findPath(int x1, int y1, int x2, int y2) {
        mapChecker[y1][x1] = true;
        boolean output = true;

        if (x1 == x2 && y1 == y2)
            return true;
        else if (x1 == 0 || y1 == 0 || x1 == map[y2].length - 1 || y1 == map.length - 1)
            return false;

        if (map[y1][x1+1] == space && !mapChecker[y1][x1+1])
            output = output && findPath(x1+1, y1, x2, y2);
        if (map[y1][x1-1] == space && !mapChecker[y1][x1-1])
            output = output && findPath(x1-1, y1, x2, y2);
        if (map[y1+1][x1] == space && !mapChecker[y1+1][x1])
            output = output && findPath(x1, y1+1, x2, y2);
        if (map[y1-1][x1] == space && !mapChecker[y1-1][x1])
            output = output && findPath(x1, y1-1, x2, y2);

        return output;
    }



    public static void main(String[] args) {
        mapGenerator mg = new mapGenerator(Integer.parseInt(args[0]), Integer.parseInt((args[1])));
        System.out.println(mg);
        System.out.println(mg.wallsToString());
    }

} 