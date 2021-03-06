package org.irvinehacks.ascii_rougelike;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner;
        Player p = new Player(5, "john", 0, 0);
        int level = 0;
        System.out.println("|----CONTROLS----|\n| W - move up    |\n| A - move left  |\n| S - move down  |\n| D - move right |\n| Space - attack |\n| Press ENTER to confirm action |\n|-------LEGEND-------|\n| D - bow            |\n| T - sword          |\n| h - health potion  |\n| O - orc            |\n| G - goblin         |\n|B - behemoth        |");

        //RUNS EVERY LEVEL RESET
        while (true) {
            MapGenerator mg = new MapGenerator(20, 40);
            char[][] screen = mg.map;
            p.setX(screen.length / 2);
            p.setY(3);
            System.out.println("Level: " + ++level);
            screen[p.yPos][p.xPos] = '@';
            screen[(int) (Math.random() * 18) + 1][(int) (Math.random() * 38) + 1] = 'h';
            int weaponGen = (int) (Math.random() * 4) + 1;
            if (weaponGen == 1) {
                screen[(int) (Math.random() * 18) + 1][(int) (Math.random() * 38) + 1] = 'D';
            }
            if (weaponGen == 2) {
                screen[(int) (Math.random() * 18) + 1][(int) (Math.random() * 38) + 1] = 'T';
            }
            System.out.println("Health: " + p.getHealth() + "/" + p.getHealthCap() + " ascii_rougelike.Weapon: " + p.getWeapon().getName());

            Enemies enemies = new Enemies();

            for (int i = 0; i < level; i++) {
                int xPs = ((int) Math.random() * 12 * 3) + 2;
                int yPs = ((int) Math.random() * 6 * 3) + 2;
                int type = (int) (Math.random() * 3) + 1;
                if (type == 1) {
                    Enemy enemy = new Enemy('O', 1, 1, 2, xPs, yPs); // orc
                    enemies.addEnemy(enemy);
                    screen[yPs][xPs] = 'O';
                } else if (type == 2) {
                    Enemy enemy = new Enemy('G', 1, 2, 1, xPs, yPs); // goblin
                    enemies.addEnemy(enemy);
                    screen[yPs][xPs] = 'G';
                } else if (type == 3) {
                    Enemy enemy = new Enemy('B', 3, 1, 1, xPs, yPs); // behemoth
                    enemies.addEnemy(enemy);
                    screen[yPs][xPs] = 'B';
                }
            }

            for (int i = level; i > 0; i--) {
                int xPs = (int) (Math.random() * 12 * 3) + 2; //x-position
                int yPs = (int) (Math.random() * 6 * 3) + 2;  //y-position
                int type = (int) (Math.random() * 3) + 1;   //randomizes enemy type

                Enemy enemy;
                if (type == 1) {
                    enemy = new Enemy('O', 1, 1, 2, xPs, yPs); // orc
                    screen[yPs][xPs] = 'O';
                    enemies.addEnemy(enemy);
                } else if (type == 2) {
                    enemy = new Enemy('G', 1, 2, 1, xPs, yPs); // goblin
                    screen[yPs][xPs] = 'G';
                    enemies.addEnemy(enemy);
                } else if (type == 3) {
                    enemy = new Enemy('B', 3, 1, 1, xPs, yPs); // behemoth
                    screen[yPs][xPs] = 'B';
                    enemies.addEnemy(enemy);
                }
            }

            for (int i = 0; i < screen.length; i++) {
                for (int j = 0; j < screen[0].length; j++) {
                    System.out.print(screen[i][j]);
                }
                System.out.print("\n");
            }

            //RUNS EVERY TURN
            while (p.getHealth() > 0) {
                scanner = new Scanner(System.in);
                String input = scanner.nextLine();

                boolean enemyIsNearPlayer;
                int deltaX, deltaY;

                for (Enemy en : enemies.enemies) {
                    deltaX = Math.abs(p.xPos - en.xPos);
                    deltaY = Math.abs(p.yPos - en.yPos);
                    enemyIsNearPlayer = deltaX <= en.range && deltaY <= en.range;

                    if (enemyIsNearPlayer) {
                        p.decreaseHealth(en.attack);
                        System.out.println(en + " delt " + en.attack + " damage to you!");
                    } else if (p.xPos > en.xPos && mg.map[en.yPos][en.xPos + 1] != mg.getWall()) {
                        mg.map[en.yPos][en.xPos] = mg.getSpace();
                        en.move('a');
                        mg.map[en.yPos][en.xPos] = en.character;
                    } else if (p.xPos < en.xPos && mg.map[en.yPos][en.xPos - 1] != mg.getWall()) {
                        mg.map[en.yPos][en.xPos] = mg.getSpace();
                        en.move('d');
                        mg.map[en.yPos][en.xPos] = en.character;
                    } else if (p.yPos > en.yPos && mg.map[en.yPos + 1][en.xPos] != mg.getWall()) {
                        mg.map[en.yPos][en.xPos] = mg.getSpace();
                        en.move('w');
                        mg.map[en.yPos][en.xPos] = en.character;
                    } else if (p.yPos < en.yPos && mg.map[en.yPos - 1][en.xPos] != mg.getWall()) {
                        mg.map[en.yPos][en.xPos] = mg.getSpace();
                        en.move('s');
                        mg.map[en.yPos][en.xPos] = en.character;
                    }
                }

                screen[p.yPos][p.xPos] = '@';

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
                        p.attack(screen, enemies);
                    }
                    p.move(dir);

                    if (screen[p.yPos][p.xPos] == '-')
                        break;

                    if (screen[p.yPos][p.xPos] == 'h')
                        if (p.getHealth() < p.getHealthCap()) {
                            p.increaseHealth();
                        }

                    if (screen[p.yPos][p.xPos] == 'D')
                        p.setWeapon(new Weapon("Bow", 2, 1));

                    if (screen[p.yPos][p.xPos] == 'T')
                        p.setWeapon(new Weapon("Sword", 1, 2));


                    for (Enemy enemy : enemies.enemies) {
                        if (enemy.isDead) {
                            enemy.attack = 0;
                            enemy.character = ' ';
                            screen[enemy.yPos][enemy.xPos] = enemy.character;
                        }
                    }

                    if (screen[p.yPos][p.xPos] != mg.getWall() && screen[p.yPos][p.xPos] != 'O' && screen[p.yPos][p.xPos] != 'G' && screen[p.yPos][p.xPos] != 'B') {
                        screen[p.yPos][p.xPos] = '@';
                        if (dir != -1) {
                            screen[lastY][lastX] = ' ';
                        }
                    } else {
                        p.yPos = lastY;
                        p.xPos = lastX;
                    }


                    ////   ////   //  //   //  //////
                    // //  // //  //  //// //    //
                    ////   ////   //  // / //    //
                    //     // //  //  // ////    //
                    //     //  // //  //   //    //
                    for (int i = 0; i < screen.length; i++) {
                        for (int j = 0; j < screen[0].length; j++) {
                            System.out.print(screen[i][j]);
                        }
                        System.out.print("\n");
                    }
                    System.out.println("Health: " + p.getHealth() + "/" + p.getHealthCap() + " ascii_rougelike.Weapon: " + p.getWeapon().getName());

                }
            }
            if (p.getHealth() < 1) {
                String end = "";
                end += "  _____                         ____                 \n";
                end += " / ____|                       / __ \\                \n";
                end += "| |  __  __ _ _ __ ___   ___  | |  | |_   _____ _ __ \n";
                end += "| | |_ |/ _` | '_ ` _ \\ / _ \\ | |  | \\ \\ / / _ \\ '__| \n";
                end += "| |__| | (_| | | | | | |  __/ | |__| |\\ V /  __/ |   \n";
                end += " \\_____|\\__,_|_| |_| |_|\\___|  \\____/  \\_/ \\___|_|   ";
                System.out.println(end);
                break;
            }
        }

    }
}
