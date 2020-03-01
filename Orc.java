// depreciated (just use Enemy 4head)

public class Orc extends Enemy
{
 public Orc(int x, int y)
 {
   super('t',1,x,y);
   super.attack = 1;
   super.hp = 1;
   super.character = 't';
 }
}