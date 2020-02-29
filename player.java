public class player {

    private int health;
    private int healthCap;
    private String name;
    private weapon playerWeapon;

    public void setName(String str) { name = str; }
    public void setHealthCap(int num) { healthCap = num; }
    public void setWeapon(weapon newWeapon) { playerWeapon = newWeapon; }

    public String getName() { return name; }
    public weapon getWeapon() { return playerWeapon; }
    public int getHealthCap() { return healthCap; }

    public int decreaseHealth() { return health--;}
}