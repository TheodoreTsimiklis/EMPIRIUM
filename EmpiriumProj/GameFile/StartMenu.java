import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The StartMenu World/Class is the main menu for the game, that allows you to view the credits, instructions, and to start the game
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class StartMenu extends World {
    // music is the music that plays when the world is instatiated - Composed by Anthony Nadeau
    public static GreenfootSound music = new GreenfootSound("StartMenu.wav");
    
    /**
     * Constructor for StartMenu that adds all the objects
     */
    public StartMenu() {    
        super(1280, 720, 1); 
        addObject(new Start(), 640, 240);
        addObject(new Instructions(), 640, 380);
        addObject(new Credits(), 640, 520);
        addObject(new BobDance(), 1110, 379);
        addObject(new BobDance(), 170, 379);
        reset();
        music.setVolume(15);
        music.playLoop();
    }
    
    /**
     * resets every static variable in the game to it's inital value
     */
    public void reset() {
        MainWorld.points = 0;
        MainWorld.waveNum = 1;
        MainWorld.totalWaves = 1;
        Bobius.counter = 0;
        Bobius.lightDamage = Greenfoot.getRandomNumber(3) + 1;
        Bobius.heavyDamage = Greenfoot.getRandomNumber(3) + 2;
        Bobius.kickDamage = Greenfoot.getRandomNumber(6) + 5;
        Bobius.health = 600;
        Bobius.kickTimer = 0;
        Bobius.lightAttackTimer = 0;
        Bobius.heavyAttackTimer = 0;
        Bobius.rollTimer = 0;
        Bobius.invincibilityFrame = 0;
        Enemy.prisonerNum = 0;
        Champion.speed = 0;
        Champion.health = 0;
        Champion.damage = 0;
        Prisoner.damage = 1;
        HeavyAttack.level = 1;
        LightAttack.level = 1;
        SpartanKick.level = 1;
    }
}
