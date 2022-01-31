import greenfoot.*;

/**
 * Healthbar floats above every character and changes depending on if damage was taken or not
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class HealthBar extends Actor
{
    // Variables to create the healthbar and make it start green
    int health = 100;
    int barWidth = 100;
    int barHeight = 10;
    public Hitbox hitbox;
    
    /**
     * Constructor calling the update method
     */
    public HealthBar() {
        update();
    }
    
    /**
     * Act method runs constantly
     */
    public void act() 
    {
        update();
        if (health <= 0) {
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Changes the health status when damage is taken
     */
    public void update() {
        setImage(new GreenfootImage(barWidth + 2, barHeight + 2));
        getImage().setColor(Color.BLACK);
        getImage().drawRect(0, 0, barWidth + 1, barHeight + 1);
        getImage().setColor(Color.GREEN);
        getImage().fillRect(1, 1, health, barHeight);
    }
}
