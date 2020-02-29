public class mapGenerator {

    public char[][] map;
    private char border = '*';
    private char wall = '#';
    private char space = ' ';

    public char getBorder() { return border; }
    public char getWall() { return wall; } 
    public char getSpace() { return space; }

    public mapGenerator(int y, int x) {
        map = new char[y][x];
        fillMap();
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
            }
        }
    }

    // public static void main(String[] args) {
    //     System.out.println(new mapGenerator(Integer.parseInt(args[0]), Integer.parseInt((args[1]))));
    // }

}