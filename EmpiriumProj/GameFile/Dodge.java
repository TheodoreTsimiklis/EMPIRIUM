import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Dodge object acts as a cooldown timer in the MainWorld, to allow the player to visually see when they will be able to roll next
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class Dodge extends Actor
{   
    // Dodge animation array
    private String[] dodgeA = {"DodgeWidget1.png", "DodgeWidget2.png", "DodgeWidget3.png", "DodgeWidget4.png", "DodgeWidget5.png", "DodgeWidget6.png", "DodgeWidget7.png"};
    private int rollImg = 0;
    private int counter = 0;
    
    /**
     * Constructor that sets the image of the object when it is instatiated
     */
    public Dodge() {
        GreenfootImage initialImg = new GreenfootImage(dodgeA[0]);
        setImage(initialImg);
    }
    
    /**
     * Act method runs constantly
     */
    public void act() 
    {   
        if (getWorld() instanceof MainWorld) {
            counter++;
            cooldownAnimation();
        }
        else if (getWorld() instanceof BuyScreen) {
            getWorld().showText("Not Upgradeable", getX(), getY() - 150);
            getWorld().showText("Not Upgradeable", getX(), getY() + 100);
        }
        if (counter >= 35) {
            counter = 0;
        }
    } 
    
    /**
     * Animation for the cooldown timer
     * 
     */
    private void cooldownAnimation() {
        if (Bobius.rollTimer < 4 ||rollImg != 0) {
            if (counter % 20  == 0)
                rollImg++;
            if (rollImg >= dodgeA.length) {
                rollImg = 0;
            }
            GreenfootImage newImage = new GreenfootImage(dodgeA[rollImg]);
            newImage.scale(newImage.getWidth() / 2, newImage.getHeight() / 2);
            setImage(newImage);
        }
    }
    
    /**
     * Override method that changes the size of the object depending on what world it is in
     */
    @Override
    protected void addedToWorld(World world) {
        if (world instanceof MainWorld)
            getImage().scale(getImage().getWidth() / 2, getImage().getHeight() / 2);
    }
}    
