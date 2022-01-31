import greenfoot.*;  

/**
 * Spartan kick functions both as a cooldown timer and an upgrade button
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class SpartanKick extends Actor
{
    private String[] spartanKickA = {"SpartanKick1.png", "SpartanKick2.png", "SpartanKick3.png", "SpartanKick4.png", "SpartanKick5.png", "SpartanKick6.png", "SpartanKick7.png"};
    
    //Counters for the cooldown
    private int kickImg = 0;
    private int counter = 0;
    
    // Level and cost for upgrades
    public static int level = 1;
    private static int cost = 140;
    
    /**
     * Default Constructor
     */
    public SpartanKick() {
        GreenfootImage initialImg = new GreenfootImage(spartanKickA[0]);
        setImage(initialImg);
    }
    
    /**
     * Act method runs constantly
     */
    public void act() {
        if (getWorld() instanceof BuyScreen) {
            getWorld().showText("Level: " + level, getX(), getY() - 150);
            getWorld().showText("Cost: " + cost, getX(), getY() + 100);
            if (Greenfoot.mouseClicked(this) && MainWorld.points > 0) {
                if (MainWorld.points - cost > 0) {
                    level++;
                    Bobius.kickDamage += 3;
                    MainWorld.points -= cost;
                    cost += 25;
                }
            }
        }
        if (getWorld() instanceof MainWorld) {
            counter++;
            cooldownAnimation();
        }
        if (counter == 135)
            counter = 0;
    } 
    
    /**
     * Animation for the cooldown that displays when it can be used again
     */
    private void cooldownAnimation() {
        if (Bobius.kickTimer < 15 || kickImg != 0) {
            if (counter % 130 == 0)
                kickImg++;
            if (kickImg >= spartanKickA.length) {
                kickImg = 0;
            }
            GreenfootImage newImage = new GreenfootImage(spartanKickA[kickImg]);
            newImage.scale(newImage.getWidth() / 2, newImage.getHeight() / 2);
            setImage(newImage);
        }
    }
    
    /**
     * Changes the size of the image depending on the world it is in
     */
    @Override
    protected void addedToWorld(World world) {
        if (world instanceof MainWorld)
            getImage().scale(getImage().getWidth() / 2, getImage().getHeight() / 2);
    }
}
