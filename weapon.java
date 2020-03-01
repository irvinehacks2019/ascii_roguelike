public class weapon {
    private String name;
    private int range;
    private int damage;

    public weapon(String n, int r, int da) {
        name = n;
        range = r;
        damage = da;
    }


    /////GETTER METHODS\\\\\
    public int GetRange() { return range; }
    public int GetDamage() { return damage; }
    public String getName()
    {
     return name; 
    }

}