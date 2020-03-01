public class Enemy
{
  protected int hp;
  protected int attack;
  protected boolean isDead;
  protected int xPos;
  protected int yPos;
  protected int range;
  protected char character;
  
  public Enemy(char c, int h, int xP, int yP)
  {
    hp = h;
    attack = 0;
    isDead = false;

    xPos = xP;
    yPos = yP;

    range = 1;
    character = c;
  }
  
  public int attack()
  {
   return attack; 
  }
  
  public int getHP()
  {
   return hp; 
  }
  
  public boolean checkDead()
  {
   return isDead; 
  }
  
  public void takeDamage(int d)
  {
    if(isDead = true)
    {
      return;
    }
    hp = hp - d;
    isDead = (hp <= 0);
  }
  
  public void move(int dir)
  {
    if(dir == 1)
    {
      yPos++;
    }
    
    if(dir == 2)
    {
     yPos--; 
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