package org.irvinehacks.ascii_rougelike;

import java.util.ArrayList;

public class Enemies {

    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public Enemy getEnemyByPosition(int xPos, int yPos) {
        for (Enemy e : this.enemies) {
            if (e.xPos == xPos && e.yPos == yPos) {
                return e;
            }
        }
        return null;
    }

}