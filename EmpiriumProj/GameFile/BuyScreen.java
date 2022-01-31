import greenfoot.*;

/**
 * The BuyScreen World/Class is the upgrade screen that the player gets after every wave to upgrade abilities using points accumulated during the round.
 * @author Team Empirium 
 * @version (09/12/2020)
 */
public class BuyScreen extends World {
    public static GreenfootSound music = new GreenfootSound("Menu.wav");
    
    /**
     * Constructor for objects of class BuyScreen.
     */
    public BuyScreen() {    
        super(1280, 720, 1);
        prepare();
    }
    
    /**
     * adds all the objects to the world and starts the menu music
     */
    private void prepare() {
        addObject(new Dodge(), 325, 450);  
        addObject(new LightAttack(), 525, 450);
        addObject(new HeavyAttack(), 725, 450);    
        addObject(new SpartanKick(), 925, 450);
        addObject(new NextWave(), 1050, 100);
        
        music.setVolume(15);
        MainWorld.music.stop();
        music.playLoop();
    }
    
    /**
     * Displays the amount of points the player has available to spend on upgrades
     */
    public void act() {
        showText("Points Remaining: " + MainWorld.points, 640, 200);
    }
}
