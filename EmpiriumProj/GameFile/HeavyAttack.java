import greenfoot.*; 

/**
 * Acts as a cooldown timer in the MainWorld, and an upgrade button on the BuyScreen
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class HeavyAttack extends Actor 
{
    // Array for animation
    private String[] heavyAttackA = {"HeavyWidget1.png", "HeavyWidget2.png", "HeavyWidget3.png", "HeavyWidget4.png", "HeavyWidget5.png", "HeavyWidget6.png", "HeavyWidget7.png"};
 
    // Level and cost for upgrades
    public static int level = 1;
    private int heavyAttackImg = 0;
    private static int cost = 60;
    
    /**
     * Constructor that sets the image 
     */
    public HeavyAttack() {
        GreenfootImage initialImg = new GreenfootImage(heavyAttackA[0]);     
        setImage(initialImg);
    }
    
    /**
     * Act Method runs constantly
     */
    public void act() {
        if (getWorld() instanceof BuyScreen) {
            getWorld().showText("Level: " + level, getX(), getY() - 150);
            getWorld().showText("Cost: " + cost, getX(), getY() + 100);
            if (Greenfoot.mouseClicked(this) && MainWorld.points > 0) {
                if (MainWorld.points - cost > 0) {
                    level++;
                    Bobius.heavyDamage += 2;
                    MainWorld.points -= cost;
                    cost += 15;
                }
            }
        }
        if (getWorld() instanceof MainWorld) {
            cooldownAnimation();
        }
    }
    
    /**
     * Animation for the cooldown in the MainWorld
     */
    private void cooldownAnimation() {
        if (Bobius.heavyAttackTimer < 3 || heavyAttackImg != 0) {
            if (Bobius.counter % 26 == 0)
                heavyAttackImg++;
            if (heavyAttackImg >= heavyAttackA.length) {
                heavyAttackImg = 0;
            }
            GreenfootImage newImage = new GreenfootImage(heavyAttackA[heavyAttackImg]);
            newImage.scale(newImage.getWidth() / 2, newImage.getHeight() / 2);
            setImage(newImage);
        }
    }
    
    /**
     * Changes the size of the image depending on what world it is in
     */
    @Override
    protected void addedToWorld(World world) {
        if (world instanceof MainWorld)
            getImage().scale(getImage().getWidth() / 2, getImage().getHeight() / 2);
    }
}
