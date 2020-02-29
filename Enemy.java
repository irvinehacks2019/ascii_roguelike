public class Enemy
{
  protected int hp;
  protected int attack;
  protected boolean isDead;
  protected int xPos;
  protected int yPos;
  protected int range;
  protected char character;
  
  public Enemy()
  {
    hp = 0;
    attack = 0;
    isDead = false;
    xPos = 0;
    yPos = 0;
    range = 1;
    character = 'e';
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