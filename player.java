public class player {

    public player(int h, String n, int x, int y) {
        name = n;
        healthCap = h;
        health = h;
        playerWeapon = new weapon("Fists", 1, 1);
        yPos = y;
        xPos = x;
    }

    private int health;
    private int healthCap;
    private String name;
    private weapon playerWeapon;
    public int yPos;
    public int xPos;


    public void setName(String str) { name = str; }
    public void setHealthCap(int num) { healthCap = num; }
    public void setWeapon(weapon newWeapon) { playerWeapon = newWeapon; }

    public String getName() { return name; }
    public weapon getWeapon() { return playerWeapon; }
    public int getHealthCap() { return healthCap; }

    public void decreaseHealth(int num) { health -= num;}
    public int increaseHealth() { return ++health;}
    
    public void setX(int x)
    {
     xPos = x; 
    }
    
    public void setY(int y)
    {
     yPos = y; 
    }
    
    public int getHealth()
    {
      return health;
    }

    public void move (int dir) {
        if(dir == 1)
        {
            yPos--;
        }
        
        if(dir == 2)
        {
            yPos++; 
        }
        
        if(dir == 3)
        {
            xPos++;
        }
        
        if(dir == 4)
        {
            xPos--; 
        }
    }

    public void attack(char[][] screen, Enemies enemies) {
        boolean attacked = false;

        for (int i = yPos - playerWeapon.GetRange(); i <= yPos + playerWeapon.GetRange(); i++) {
            for (int j = xPos - playerWeapon.GetRange(); j <= xPos + playerWeapon.GetRange(); j++) {
                    try {   
                        if (screen[i][j] == 'O' || screen[i][j] == 'G' || screen[i][j] == 'B') {                     
                            Enemy enemy = enemies.getEnemyByPosition(j, i);
                            System.out.println("Delt " + playerWeapon.GetDamage() + " to " + enemy);
                            enemy.takeDamage(playerWeapon.GetDamage());
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