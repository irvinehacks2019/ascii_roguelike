import java.util.Scanner;
import java.lang.Math;
public class app {

    public static void main (String[] args) {
        Scanner scanner;
        mapGenerator mg = new mapGenerator(10, 20);

        mapGenerator mg = new mapGenerator(20, 40);
        char[][] screen = mg.map;
        p.setX(screen.length/2);
        p.setY(3);
        System.out.println("Level " + level);
        screen[p.yPos][p.xPos] = '@';
        screen[(int)(Math.random() * 18) + 1][(int)(Math.random() * 38) + 1] = 'h';
        
        

        Enemies enemies = new Enemies();

        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[0].length; j++) {
                System.out.print(screen[i][j]);
            }
            System.out.print("\n");
        }
        
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
                
                if(screen[p.yPos][p.xPos] == '-')
                  break;
                
                if(screen[p.yPos][p.xPos] == 'h')
                 if(p.getHealth() < p.getHealthCap())
                {
                   p.increaseHealth();
                }
                 
                
                if (screen[p.yPos][p.xPos] != mg.getWall()) {
                    screen[p.yPos][p.xPos] = '@';
                    if (dir != -1) {
                        screen[lastY][lastX] = ' ';
                    }
                } else {
                    p.yPos = lastY;
                    p.xPos = lastX;
                }


                // printing press
                for (int i = 0; i < screen.length; i++) {
                    for (int j = 0; j < screen[0].length; j++) {
                        System.out.print(screen[i][j]);
                    }
                    System.out.print("\n");
                }
                System.out.println("Health: " + p.getHealth() + "/" + p.getHealthCap() + " Weapon: " + p.getWeapon().getName());

            }
        }
        }

    }
}