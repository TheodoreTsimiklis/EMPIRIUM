import greenfoot.*;  

/**
 * QRImage shows up when the player loses, and allows the player to reset the game 
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class QRImage extends Actor
{
    /**
     * Act method runs constantly
     */
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new StartMenu());
        }
    }    
}
