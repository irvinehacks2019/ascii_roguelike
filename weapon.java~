public class weapon {

    private int durability;
    private int range;
    private int damage;

    public weapon(int dr, int r, int da) {
        durability = dr;
        range = r;
        damage = da;
    }


    /////GETTER METHODS\\\\\
    public int GetDurability() { return durability; }
    public int GetRange() { return range; }
    public int GetDamage() { return damage; }

    public int DecreaseDurability() {
        if (--durability == 0)
            damage = 0;
        return durability; 
    }

}