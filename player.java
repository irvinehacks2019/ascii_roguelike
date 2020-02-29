public class player {

    public player(int h, String n) {
        name = n;
        healthCap = h;
        health = h;
        playerWeapon = new weapon(-1, 1, 1);
        yPos = 4;
        xPos = 4;
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

    public int decreaseHealth() { return health--;}

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
}