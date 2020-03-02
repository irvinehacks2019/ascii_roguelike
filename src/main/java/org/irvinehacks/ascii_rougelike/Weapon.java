package org.irvinehacks.ascii_rougelike;

public class Weapon {
    private String name;
    private int range;
    private int damage;

    public Weapon(String n, int r, int da) {
        name = n;
        range = r;
        damage = da;
    }


    /////GETTER METHODS\\\\\
    public int getRange() {
        return range;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

}