import greenfoot.*;

/**
 * The PostCycleMessage shows up after each cycle of 3 waves has been complete
 * @author Team Empirium 
 * @version (09/12/2020)
 */
public class PostCycleMessage extends World {
    // frames increases every time the act() method has been called
    int frames = 0;
    // seconds counts the amount of seconds that have passed since the world was first instantiated
    int seconds = 0;
    /**
     * Constructor for PostCycleMessage
     */
    public PostCycleMessage() {    
        super(1280, 720, 1); 
    }
    
    /**
     * act method is constantly being called
     */
    public void act() {
        showText("The king was quite UNSATISFIED with your performance in the ring.", 650, 350);
        showText("He demands an encore with some even finer fighters, to push you to your limits!", 650, 370);
        frames++;
        waveTime();
        if (seconds >= 10) {
            MainWorld.waveNum = 1;
            MainWorld.totalWaves++;
            Greenfoot.setWorld(new MainWorld());
        }
    }
    
    /**
     * identical to the waveTime method in MainWorld, counts seconds using framerate 
     */
    public void waveTime() {
        if (frames % 60 == 0) {
            seconds++;
            frames = 0;
        }
    }
}
