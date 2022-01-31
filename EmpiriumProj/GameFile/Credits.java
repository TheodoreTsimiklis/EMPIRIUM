import greenfoot.*;  
import java.util.List;

/**
 * Credits button changes the world to the CreditWorld
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class Credits extends Actor
{
    //Images for the animation
    private GreenfootImage animation = new GreenfootImage("CreditsFP.png");
    private GreenfootImage credits = new GreenfootImage("CreditsF.png");
    
    /**
     * Act method runs constantly
     */
    public void act() {
        if (getWorld() instanceof StartMenu) {
            creditButton();
            animation();
        }
        else
            getWorld().removeObject(this);
    }
    
    /**
     * Sets the world to the CreditWorld
     */
    private void creditButton() {
        if  (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new CreditWorld());
        }
    }

    /**
     * Changes the image when the mouse hovers over the button
     */
    public void animation(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            setImage(credits);
            List objects = getWorld().getObjectsAt(mouse.getX(), mouse.getY(), Credits.class);
            for(Object object : objects) {
                if (object == this) {
                    setImage(animation);
                }
            }
        }
    }
}