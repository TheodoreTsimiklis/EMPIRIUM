import greenfoot.*;  

/**
 * Backbutton allows the player to return to a previous menu
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class BackButton extends Actor
{
    /**
     * Act method runs constantly
     */
    public void act() 
    {
        backButton();
    }
    
    /**
     * does the action of returning to a prior menu
     */
    private void backButton() {
        if  (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new StartMenu());
        }
    }
}
