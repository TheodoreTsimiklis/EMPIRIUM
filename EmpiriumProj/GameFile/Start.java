import greenfoot.*;  
import java.util.List;

/**
 * Start button starts the run of the game
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class Start extends Actor
{
    // Images for the animation
    private GreenfootImage animation = new GreenfootImage("StartFP.png");
    private GreenfootImage startButton = new GreenfootImage("StartF.png");
    
    /**
     * Act method runs constantly
     */
    public void act() 
    {
        startButton();
        animation();
    }
    
    /**
     * Sets the world to the MainWorld 
     */
    private void startButton() {
        if  (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new MainWorld());
        }
    }
    
    /**
     * Changes the image when your mouse hovers over the button
     */
    public void animation(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            setImage(startButton);
            List objects = getWorld().getObjectsAt(mouse.getX(), mouse.getY(), Start.class);
            for(Object object : objects) {
                if (object == this) {
                    setImage(animation);
                }
            }
        }

    }
}