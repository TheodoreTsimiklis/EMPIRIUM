import greenfoot.*;

/**
 * Hitbox gets hit and updates health
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class Hitbox extends Actor
{
    GreenfootImage hitbox = new GreenfootImage("Hitbox rect.png");
    // Objects being created to be associated to the hitbox
    public Prisoner prisoner;
    public Champion champion;
    public HealthBar healthbar;
    
    //HealthValues used to update the healthbar
    public final int maxHealthValue;
    public int healthValue;
    
    //Invincibility frames
    public int invincibility = 0;
    
    /**
     * Default Constructor
     */
    public Hitbox() {
        this(0, 0);
    }
    
    /**
     * Constructor that assigns values to the hitbox
     */
    public Hitbox(int healthValue, int maxHealthValue) {
        hitbox.scale(hitbox.getWidth() - 200, hitbox.getHeight());
        this.healthValue = healthValue;
        this.maxHealthValue = maxHealthValue;
        this.healthbar = new HealthBar();
    }
   
    /**
     * Constructor that assigns a prisoner to the hitbox
     */
    public Hitbox(Prisoner prisoner) {
        this();
        this.prisoner = prisoner;
        this.champion = null;
    }
    
    /**
     * Constructor that assigns a champion to the hitbox
     */
    public Hitbox(Champion champion) {
        this();
        this.prisoner = null;
        this.champion = champion;
    }
   
    /**
     * Act method runs constantly
     */
    public void act() 
    {  
         BoxOfHit();
         Bobius bobius = getWorld().getObjects(Bobius.class).get(0);
         if (bobius.hitbox == this) {
             setLocation(bobius.getX(), bobius.getY());
         }
         if (healthValue <= 0) {
            getWorld().removeObject(this);
         } 
         if (getWorld() != null) {
           healthbar.setLocation(this.getX() - 5, this.getY() - 175);
         } 
    }
    
    /**
     * "disappears" in the sense that it cannot be damaged
     */
    public void disappear() {       
        invincibility = 1;
    }
    
    /**
     * Updates the healthbar
     */
    public void lowerHealth() {
        healthbar.health = (int) Math.ceil((double) healthValue / maxHealthValue * 100);
    }
    
    /**
     * Sets the image to the hitbox image in the data members
     */
    public void BoxOfHit() {
        setImage(hitbox);
    }
}
