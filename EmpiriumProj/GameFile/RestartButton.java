import greenfoot.*;

/**
 * Restart button allows player to fully reset the game at any time without making it stop
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class RestartButton extends Actor {
    private GreenfootImage restart = new GreenfootImage("RestartButton.png");
    
    /**
     * Constructor sets the image
     */
    public RestartButton() {
       setImage(restart);
    }
    
    /**
     * Act method runs constantly
     */
    public void act() 
    {
        restartButton();
    }
    
    /**
     * resets the game when it is clicked
     */
    private void restartButton() {
        if  (Greenfoot.mouseClicked(this)) {
            MainWorld.music.stop();
            Greenfoot.setWorld(new StartMenu());
        }
    }
}
