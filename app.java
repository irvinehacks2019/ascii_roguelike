import java.util.Scanner;
import java.lang.Math;
public class app {

    public static void main (String[] args) {
        Scanner scanner;
        player p = new player(5, "john", 0, 0);
        int level = 0;
        int numOfTypesOfMonsters = 2;

        mapGenerator mg = new mapGenerator(20, 40);
        char[][] screen = mg.map;
        p.setX(screen.length/2);
        p.setY(3);
        System.out.println("Level " + level);
        screen[p.yPos][p.xPos] = '@';
        screen[(int)(Math.random() * 18) + 1][(int)(Math.random() * 38) + 1] = 'h';
        

        Enemies enemies = new Enemies();

        for (int i = 0; i < level; i++) {
            int xPs = ((int)Math.random()*12*3)+2;
            int yPs = ((int)Math.random()*6*3)+2;
            int type = (int)Math.random()*numOfTypesOfMonsters;
            if (type == 1) {
                Enemy enemy = new Enemy('O', 1, 1, xPs, yPs); // orc
                enemies.addEnemy(enemy);
            } else if (type == 2) {
                Enemy enemy = new Enemy('G', 1, 2, xPs, yPs); // goblin
                enemies.addEnemy(enemy);
            } else if (type == 3) {
                Enemy enemy = new Enemy('B', 3, 1, xPs, yPs); // behemoth
                enemies.addEnemy(enemy);
            }
        }

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
