import greenfoot.*;

/**
 * The DeathScreen World/Class is the screen that the player sees when they lose all their hp/lose the game
 * Also includes special thanks to people outside the team who helped in it's development
 * @author Team Empirium  
 * @version (09/12/2020)
 */
public class DeathScreen extends World {  
    public static GreenfootSound deathSound = new GreenfootSound("Ded.wav");
    /**
     * Constructor that adds the restart button, plays the music, and displays the special thanks 
     */
    public DeathScreen() {    
        super(1280, 720, 1);
        addObject(new QRImage(), 220, 600);
        deathSound.setVolume(75);
        deathSound.play();
        showText("Special Thanks to Tassia for all the help you've given us this semester", 650, 525);
        showText("Also special thanks to Yi for helping us out when we were stuck", 620, 555);
    }
}
