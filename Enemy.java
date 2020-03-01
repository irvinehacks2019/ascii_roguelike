public class Enemy
{
  public int hp;
  public int attack;
  public boolean isDead;
  public int xPos;
  public int yPos;
  public int range;
  public char character;
  
  public Enemy(char c, int h, int atk, int r, int xP, int yP)
  {
    hp = h;
    attack = atk;
    isDead = false;
    range = r;
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

  public void setDamage(int atk) {
    attack = atk;
  }

  public void setChar(char c) {
    character = c;
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
  
  public void move(char c)
  {
    if(c == 'w')
    {
      yPos++;
    }
    
    if(c == 's')
    {
     yPos--; 
    }
    
    if(c == 'a')
    {
      xPos++;
    }
    
    if(c == 'd')
    {
     xPos--; 
    }
  }
}