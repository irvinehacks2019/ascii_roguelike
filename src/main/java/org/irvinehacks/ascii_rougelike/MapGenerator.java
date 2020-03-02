package org.irvinehacks.ascii_rougelike;

public class MapGenerator {

    public char[][] map;
    private int[][] walls;
    private boolean[][] mapChecker;
    //private int[][] navigator;
    private char wall = '#';
    private char space = ' ';
    private char door = '-';

    public MapGenerator(int y, int x) {
        map = new char[y][x];
        mapChecker = new boolean[y][x];
        walls = new int[map.length / 3][map[0].length / 3];
        fillMap();
        for (int y0 = 0; y0 < map.length; y0++) {
            for (int x0 = 0; x0 < map.length; x0++) {
                //navigator[y0][x0] = (map[y0][x0] == '#' ? -1 : 0);
            }
        }
        //navigator = PathFinder.findPath(navigator, map[0].length / 2, 3, map[0].length / 2, map.length - 1);
    }

    public char getWall() {
        return wall;
    }

    public char getSpace() {
        return space;
    }

    public char[][] getMap() {
        return map;
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
                walls[y][x] = (int) (Math.random() * 4);
                if (walls[y][x] > 5)
                    walls[y][x] = 5;
            }
        }

        //SAFE SPACE
        //walls[0]
        for (int x = 0; x < walls[0].length; x++) {
            if (x > walls[0].length * 2 / 5 && x < walls[0].length * 3 / 5) {
                walls[0][x] = 5;
                walls[walls.length - 1][x] = 5;
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
                    if (x > map[y].length * 2 / 5 && x < map[y].length * 3 / 5 && y != 0)
                        map[y][x] = door;
                }

            }
        }


        //Filling (navigator) with proper values
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map.length; x++) {
                if (mapChecker[y][x] != true)
                    mapChecker[y][x] = false;
            }
        }
    }

    private int getRelativeCoordinate(int num) {
        return 2 + 3 * num;
    }

    //0 = space
    //-1 = wall


}