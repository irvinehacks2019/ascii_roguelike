package org.irvinehacks.ascii_rougelike;

public class Player {

    public int yPos;
    public int xPos;
    private int health;
    private int healthCap;
    private String name;
    private Weapon playerWeapon;
    public Player(int h, String n, int x, int y) {
        name = n;
        healthCap = h;
        health = h;
        playerWeapon = new Weapon("Fists", 1, 1);
        yPos = y;
        xPos = x;
    }

    public String getName() {
        return name;
    }

    public void setName(String str) {
        name = str;
    }

    public Weapon getWeapon() {
        return playerWeapon;
    }

    public void setWeapon(Weapon newWeapon) {
        playerWeapon = newWeapon;
    }

    public int getHealthCap() {
        return healthCap;
    }

    public void setHealthCap(int num) {
        healthCap = num;
    }

    public void decreaseHealth(int num) {
        health -= num;
    }

    public int increaseHealth() {
        return ++health;
    }

    public void setX(int x) {
        xPos = x;
    }

    public void setY(int y) {
        yPos = y;
    }

    public int getHealth() {
        return health;
    }

    public void move(int dir) {
        if (dir == 1) {
            yPos--;
        }

        if (dir == 2) {
            yPos++;
        }

        if (dir == 3) {
            xPos++;
        }

        if (dir == 4) {
            xPos--;
        }
    }

    public void attack(char[][] screen, Enemies enemies) {
        boolean attacked = false;

        for (int i = yPos - playerWeapon.getRange(); i <= yPos + playerWeapon.getRange(); i++) {
            for (int j = xPos - playerWeapon.getRange(); j <= xPos + playerWeapon.getRange(); j++) {
                try {
                    if (screen[i][j] == 'O' || screen[i][j] == 'G' || screen[i][j] == 'B') {
                        Enemy enemy = enemies.getEnemyByPosition(j, i);
                        System.out.println("Delt " + playerWeapon.getDamage() + " to " + enemy);
                        enemy.takeDamage(playerWeapon.getDamage());
                        attacked = true;
                    }
                } catch (Exception e) {
                    System.out.println("Error - null attack");
                }
            }
        }
        if (!attacked)
            System.out.println("You attacked thin air!");
    }
}