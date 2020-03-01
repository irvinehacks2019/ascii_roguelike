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
  
  public String toString()
  {
    switch(character) {
      case 'O':
        return "Orc";
      case 'G':
        return "Goblin";
      case 'B':
        return "Behemoth";
      default:
        return "monster";
    }
  }

  public int attack()
  {
   return attack; 
  }
  
  public int getHP()
  {
   return hp; 
  }
  
  public void takeDamage(int d)
  {
    if(isDead = true)
    {
      return;
    }
    hp -= d;

    isDead = (hp <= 0);

    if (isDead) {
      attack = 0;
      character = ' ';
    }
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