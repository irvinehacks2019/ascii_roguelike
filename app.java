import java.util.Scanner;

public class app {

    public static void main (String[] args) {
        Scanner scanner;
        mapGenerator mg = new mapGenerator(20, 20);

        player p = new player(5, "john");
        char[][] screen = mg.map;


        // for (int i = 0; i < screen.length; i++) {
        //     for (int j = 0; j < screen[0].length; j++) {
        //         System.out.print(screen[i][j]);
        //     }
        //     System.out.print("\n");
        // }



        while (true) {
            scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("w") || input.equals("a") || input.equals("s") || input.equals("d") || input.equals(" ")) {
                int lastX = p.xPos;
                int lastY = p.yPos;

                int dir = 0;
                if (input.equals("w")) {
                    dir = 1;
                } else if (input.equals("s")) {
                    dir = 2;
                } else if (input.equals("d")) {
                    dir = 3;
                } else if (input.equals("a")) {
                    dir = 4;
                } else if (input.equals(" ")) {
                    dir = -1; 
                }
                p.move(dir);
                
                if (screen[p.yPos][p.xPos] != mg.getWall()) {
                    screen[p.yPos][p.xPos] = '@';
                    if (dir != -1) {
                        screen[lastY][lastX] = ' ';
                    }
                } else {
                    p.yPos = lastY;
                    p.xPos = lastX;
                }

                // minecraft monster mover
                



                // printing press
                for (int i = 0; i < screen.length; i++) {
                    for (int j = 0; j < screen[0].length; j++) {
                        System.out.print(screen[i][j]);
                    }
                    System.out.print("\n");
                }
                System.out.println(p.xPos);
                System.out.println(p.yPos);
                System.out.println(dir);
            }
        }

    }
}