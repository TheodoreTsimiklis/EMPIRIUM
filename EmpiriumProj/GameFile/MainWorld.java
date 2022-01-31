import greenfoot.*;

/**
 * The MainWorld Class is the world in which the player fights against the waves of enemies, it is where all the action in the game takes place.
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class MainWorld extends World {
    // points variable collects points whenever bobius hit an enemy, and is used as currency in the upgrade screen
    public static int points = 0;
    
    // seconds variable uses the waveTime method to count the amount of seconds in the round, assuming that greenfoot is running at 60 FPS
    public int seconds = 0;
    // counter increases every time the act() method is called
    private int counter = 0;
    // prisonerSpawned is used to determine what side of the world the last prisoner spawned on
    private boolean prisonerSpawned;
    // wave is used to determine when the champion of the wave can be spawned
    private boolean wave = false;
    
    // waveNum indicates the number of the wave (1, 2, or 3) and is used to spawn the different champions, as well as restarting after wave 3
    public static int waveNum = 1;
    // totalWaves calculates the amount of times the player has completed the cycle of 3 waves. 
    public static int totalWaves = 1;
    
    // championDelta is the multiplier added to the champions after each wave cycle to make them more difficult to fight
    public int championDelta = totalWaves - 1;
    
    // music is the fight music that plays while the world has been instantiated - Composed by Anthony Nadeau
    public static GreenfootSound music = new GreenfootSound("Fight.wav");
    
    
    // DIFFERENTIATING CHAMPION ARRAYS
    
    private String c2Stand = "C1 Stand.png";
    private String c2Walk[] = {"C1WalkingAnimation1.png", "C1WalkingAnimation2.png", "C1WalkingAnimation3.png", "C1WalkingAnimation4.png", "C1WalkingAnimation5.png", "C1WalkingAnimation6.png"};
    private String c2Attack[] = {"C1LightAttack1.png", "C1LightAttack2.png", "C1LightAttack3.png", "C1LightAttack4.png", "C1LightAttack5.png", "C1LightAttack6.png"};
    
    private String c1Stand = "C2 Stand.png";
    private String c1Walk[] = {"C2WalkingAnimation1.png", "C2WalkingAnimation2.png", "C2WalkingAnimation3.png", "C2WalkingAnimation4.png", "C2WalkingAnimation5.png", "C2WalkingAnimation6.png"};
    private String c1Attack[] = {"C2LightAttack1.png", "C2LightAttack2.png", "C2LightAttack3.png", "C2LightAttack4.png", "C2LightAttack5.png", "C2LightAttack6.png"};
    
    private String c3Stand = "C3 Stand.png";
    private String c3Walk[] = {"C3WalkingAnimation1.png", "C3WalkingAnimation2.png", "C3WalkingAnimation3.png", "C3WalkingAnimation4.png", "C3WalkingAnimation5.png", "C3WalkingAnimation6.png"};
    private String c3Attack[] = {"C3LightAttack1.png", "C3LightAttack2.png", "C3LightAttack3.png", "C3LightAttack4.png", "C3LightAttack5.png", "C3LightAttack6.png"};
    
    /**
     * Constructor that adds all the objects to MainWorld.
     */
    public MainWorld()
    {    
        super(1280, 720, 1); 
        
        // All the Bobius/Player objects
        Bobius bobius = new Bobius();
        addObject(bobius, 640, 410);
        addObject(bobius.hitbox, 640, 410);
        addObject(bobius.hitbox.healthbar, 800, 235);
        addObject(bobius.bobhealth, 635, 215);
        
        // All the "world" objects that affect the environment
        addObject(new Ground(), 640, 650);
        addObject(new RestartButton(), 1200, 65);
        addObject(new SecondBackground(), 640, 360);
        
        // Cooldown timer objects
        addObject(new Dodge(), 528, 650);  
        addObject(new LightAttack(), 598, 650);
        addObject(new HeavyAttack(), 668, 650);    
        addObject(new SpartanKick(), 738, 650);
        
        // Music commands to play the music when the world has been instantiated
        StartMenu.music.stop();
        BuyScreen.music.stop();
        music.setVolume(15);
        music.playLoop();
        
        // Order of which objects show up in front of others
        setPaintOrder(RestartButton.class, LightAttack.class, HeavyAttack.class, Dodge.class, SpartanKick.class, Bobius.class, Bobius.BobHealth.class, HealthBar.class, Bird.class, 
                Prisoner.class, Champion.class, SecondBackground.class, Hitbox.class, Ground.class, World.class);
    }
    
    /**
     * Act method is called constantly -> Runs at 60 "acts" per second when at optimal performance
     */
    public void act() {
        counter++;
        waveTime();
        showRoundNum();
        Enemy.preventOverlap(this);
        spawnRandBird();
        spawnRandPris();
        spawnChamp();
        GameOver();
        if (wave == true && getObjects(Champion.class).isEmpty()) {
            Greenfoot.setWorld(new BuyScreen());
        }
    }
    
    /**
     * Instatiates the DeathScreen when bobius is no longer in the world
     */
    public void GameOver() {
        if (getObjects(Bobius.class).isEmpty()) {
            music.stop();
            Greenfoot.setWorld(new DeathScreen());
        }
    }
    
    // SPAWNING METHODS
    
    /**
     * Spawns a champion when the round exceeds 60 seconds, and there are no prisoners left in the world
     * There are 3 champions -> A different one spawns depending on the waveNum
     */
    public void spawnChamp() {
        if (seconds >= 60 && getObjects(Prisoner.class).isEmpty() && wave != true) {  
            if (waveNum == 1) {
                Champion champion = new Champion(25, 30 + championDelta, 10 + championDelta, c1Stand, c1Walk, c1Attack);
                addObject(champion, 1255, 410);
                addObject(champion.hitbox, 1255, 410);
                addObject(champion.hitbox.healthbar, 1250, 235);
                wave = true;
            }
            if (waveNum == 2) {
                Champion champion = new Champion(5, 60 + championDelta, 3 + championDelta, c2Stand, c2Walk, c2Attack);
                addObject(champion, 1255, 410);
                addObject(champion.hitbox, 1255, 410);
                addObject(champion.hitbox.healthbar, 1250, 235);
                wave = true;
            }
            if (waveNum == 3) {
                Champion champion = new Champion(10, 50 + championDelta, 7 + championDelta, c3Stand, c3Walk, c3Attack);
                addObject(champion, 1255, 410);
                addObject(champion.hitbox, 1255, 410);
                addObject(champion.hitbox.healthbar, 1250, 235);
                wave = true;
            }
        }
    }
    
    /**
     * Spawns prisoners into the world on alternating sides (after the first 3 seconds)
     */
    public void spawnRandPris() {
        if (seconds <= 60 && seconds > 3) {
            if (getObjects(Prisoner.class).size() < 2 ) {
                if (prisonerSpawned) {
                    Prisoner prisoner = new Prisoner();
                    addObject(prisoner, 1255 , 410);
                    addObject(prisoner.hitbox, 1255, 410);
                    addObject(prisoner.hitbox.healthbar, 1250, 235);
                }
                else {
                    Prisoner prisoner = new Prisoner();
                    addObject(prisoner, 25 , 410);
                    addObject(prisoner.hitbox, 25, 410);
                    addObject(prisoner.hitbox.healthbar, 20, 235);
                }
                prisonerSpawned = !prisonerSpawned;
            }
        }
    }
    
    /**
     * Spawns the bird that flys over the world, using a random number to dictate when it spawns, and another 
     * random number to decide which side of the world it spawns on
     */
    public void spawnRandBird() {
        int randNum = Greenfoot.getRandomNumber(350); 
        int spawnNum = 10;
        Bird bird = new Bird();
        if (randNum == spawnNum && getObjects(Bird.class).isEmpty()) {
            if (Greenfoot.getRandomNumber(2) == 1)
                addObject(new Bird(), 1255, 115);
            else {
                addObject(new Bird(), 25, 115);
                bird.move(3);
            }
        }
    }
    
    // DIRECTLY AFFECTS THE WORLD
    
    /**
     * Shows the waveNum and the total amount of cycles of 3 waves completed at the top of the world
     */
    public void showRoundNum() {   
        showText("Wave: " + waveNum + " - " + totalWaves, 640, 30 );
        showText("Points: " + points, 600, 60);
        showText("Time: " + seconds, 700, 60);
    }
    
    /**
     * Uses the act method to count seconds
     */
    public void waveTime() {
        if (counter % 60 == 0) {
            seconds++;
            counter = 0;
        }
    }
}
