import java.util.Scanner;
import java.lang.Math;
public class app {

    public static void main (String[] args) {
        Scanner scanner;
        player p = new player(5, "john", 0, 0);
        int level = 0;        
        while(true)
        {
        mapGenerator mg = new mapGenerator(20, 40);
        char[][] screen = mg.map;
        p.setX(screen.length/2);
        p.setY(3);
        System.out.println("Level " + level);
        screen[p.yPos][p.xPos] = '@';
        System.out.println("Health: " + p.getHealth() + "/" + p.getHealthCap() + " Weapon: " + p.getWeapon().getName());
        screen[(int)(Math.random() * 18) + 1][(int)(Math.random() * 38) + 1] = 'h';
        int weaponGen = (int)(Math.random() *4) +1;
        if(weaponGen == 1)
        {
        screen[(int)(Math.random() * 18) + 1][(int)(Math.random() * 38) + 1] = 'D';
        }
        if(weaponGen == 2)
        {
        screen[(int)(Math.random() * 18) + 1][(int)(Math.random() * 38) + 1] = 'T';
        }

        Enemies enemies = new Enemies();

        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[0].length; j++) {
                System.out.print(screen[i][j]);
            }
            System.out.print("\n");
        }
        
        while (p.getHealth() > 0) {
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
                
                if(screen[p.yPos][p.xPos] == 'D')
                  p.setWeapon(new weapon("Bow", 2, 1));
                
                if(screen[p.yPos][p.xPos] == 'T')
                  p.setWeapon(new weapon("Sword", 1, 2));
                 
                
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
        if(p.getHealth() < 1)
        {
          System.out.println("Game Over");
          break;
        }
        }

    }
}