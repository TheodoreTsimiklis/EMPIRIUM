import greenfoot.*;  
import java.util.List;
/**
 * Main character/ Player Class
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class Bobius extends Actor {
    // retrieves the image set to Bobius
    private GreenfootImage image = getImage();
    
    // Standing Right Frame
    public GreenfootImage standR = new GreenfootImage("TestSpriteUpscaled.png");
    // Standing Left Frame
    public GreenfootImage standL = new GreenfootImage("TestSpriteUpscaledLeft.png");
    
    // ARRAY STRINGS
    
    private String walk[] = {"WalkingAnimation1.png", "WalkingAnimation2.png", "WalkingAnimation3.png", 
            "WalkingAnimation4.png", "WalkingAnimation5.png", "WalkingAnimation6.png"};
    
    private String roll[] = {"RollingAnimation1.png", "RollingAnimation2.png", "RollingAnimation3.png", "RollingAnimation4.png", 
            "RollingAnimation5.png", "RollingAnimation6.png", "RollingAnimation7.png", "RollingAnimation8.png"};
    
    private String lightAttack[] = {"LightAttack1.png", "LightAttack2.png", "LightAttack3.png", "LightAttack4.png", "LightAttack5.png", "LightAttack6.png"};
    
    private String spartanKick[] = {"SpartanAttack1.png", "SpartanAttack2.png", "SpartanAttack3.png", "SpartanAttack4.png", "SpartanAttack5.png", "SpartanAttack6.png", "SpartanAttack7.png",
            "SpartanAttack8.png", "SpartanAttack9.png", "SpartanAttack10.png", "SpartanAttack11.png", "SpartanAttack12.png", "SpartanAttack13.png", "SpartanAttack14.png"};
            
    private String heavyAttack[] = {"HeavyAttack1.png", "HeavyAttack2.png", "HeavyAttack3.png", "HeavyAttack4.png", "HeavyAttack5.png", "HeavyAttack6.png", "HeavyAttack7.png",
             "HeavyAttack8.png", "HeavyAttack9.png", "HeavyAttack10.png","HeavyAttack11.png", "HeavyAttack12.png"};
             
    
    // Counters
    public static int counter;
    public int walkImg = 0, rollImg = 0, lightAttackImg = 0, heavyAttackImg = 0, kickImg = 0;
    public static int kickTimer = 0;
    public static int lightAttackTimer = 0;
    public static int heavyAttackTimer = 0;
    public static int rollTimer = 0;
    private int frames = 0;
    
    //Damage variables
    private int damage = 0;
    public static int lightDamage = Greenfoot.getRandomNumber(3) + 1;
    public static int heavyDamage = Greenfoot.getRandomNumber(3) + 2;
    public static int kickDamage = Greenfoot.getRandomNumber(6) + 5;
    
    // Memory Variables
    private boolean isStanding;
    
    // MoveSpeed and Knockback
    private int movingDistance = 15;
    int knockback;
    int knockbackDelta = 15;
    
    // Gravity Variables
    private static final int GRAVITY = 4;
    private int vSpeed;
    
    // Jump
    private int jumpSpeed = 0; // Vertical Jump Speed 
    private int jumpStrength = -40;
    private int jumpDelay = 0;
    
    //Hitbox & Healthbar
    public static int health = 600;
    Hitbox hitbox;
    public static int invincibilityFrame = 0;
    BobHealth bobhealth = new BobHealth(this);
    
    /**
    * Bobius Constructor sets his starting image, and instantiates his hitbox
    */
    public Bobius(){
        setImage(standR);
        isStanding = true;
        counter = 0;
        this.hitbox = new Hitbox(health, health);
    }
    
   
    
    /**
    * Act method runs constantly
    */
    public void act(){

        // Cooldowns
        frames++;
        kickTimer = cooldownTimer(kickTimer);
        lightAttackTimer = cooldownTimer(lightAttackTimer);
        heavyAttackTimer = cooldownTimer(heavyAttackTimer);
        rollTimer = cooldownTimer(rollTimer);
        
        //Animations
        walk();
        roll();
        attackCollision();
        
        // Animation Counter
        counter();
        
        // Gravity
        checkFall();
        isJumping();
        checkGround();
        gravity();
        
        // Health Status
        bobhealth.act();
        if (this.hitbox.healthValue <= 0) {
            getWorld().removeObject(this);
        }
    }
    
    // ANIMATION METHODS
    
    /**
     * Bobius' Walk animation
     */
    private boolean walk() {
        if (Greenfoot.isKeyDown("d")) {
            movingDistance = 15;
            GreenfootImage newImage = new GreenfootImage(walk[walkImg]);  
            if (counter % 4 == 0) {
                walkImg++;
                setLocation(getX() + movingDistance, getY());
            }
            if (walkImg >= walk.length) {
                walkImg = 0;
            }
            setImage(newImage);
            return true;
        }
        else if (Greenfoot.isKeyDown("a")) {
            movingDistance = -15;
            GreenfootImage newImage = new GreenfootImage(walk[walkImg]);
            if (counter % 4 == 0) {
                walkImg++;
                setLocation(getX() + movingDistance, getY());
            }
            if (walkImg >= walk.length) {
                walkImg = 0;
            }
            newImage.mirrorHorizontally();
            setImage(newImage);
            return true;
        }
        else {
            if (movingDistance > 0)
                setImage(standR);
            else
                setImage(standL);
        }
        return false;
    }
    
    /**
     * Bobius' roll animation
     */
    private boolean roll() {
        if ((Greenfoot.isKeyDown("shift") && movingDistance > 0 && rollTimer >= 4) || rollImg != 0 && movingDistance > 0) {
            movingDistance = 50;
            invincibilityFrame = 1;
            GreenfootImage newImage = new GreenfootImage(roll[rollImg]);
            if (counter % 4 == 0) {
                rollImg++;
                setLocation(getX() + movingDistance, getY());
                rollTimer = 0;
            }
            if (rollImg >= roll.length) {
                rollImg = 0;
            }     
            setImage(newImage);
            return true;
        }
        else if ((Greenfoot.isKeyDown("shift") && movingDistance < 0 && rollTimer >= 4) || rollImg != 0 && movingDistance < 0) {
            movingDistance = -50;
            invincibilityFrame = 1;
            GreenfootImage newImage = new GreenfootImage(roll[rollImg]);
            if (counter % 4 == 0) {
                rollImg++;
                setLocation(getX() + movingDistance, getY());
                rollTimer = 0;
            }
            if (rollImg >= roll.length) {
                rollImg = 0;
            }
            newImage.mirrorHorizontally();
            setImage(newImage);
            return true;
        }
        invincibilityFrame = 0;
        return false;
    }
    
    /**
     * Bobius' Heavy Attack animation
     */
    private boolean heavyAttack() {
        if ((Greenfoot.isKeyDown("o") && heavyAttackTimer >= 3 || heavyAttackImg != 0) && !walk() && !isJumping() && !roll()) {   
            GreenfootImage newImage = new GreenfootImage(heavyAttack[heavyAttackImg]);
            if (counter % 7 == 0 && heavyAttackImg < 4) { 
                heavyAttackImg++;
                heavyAttackTimer = 0;
            }
            else if (counter % 3 == 0 && heavyAttackImg >= 4)
                heavyAttackImg++;
            if (heavyAttackImg >= heavyAttack.length) {
                heavyAttackImg = 0;
            }
            if (getImage() == standL) 
                newImage.mirrorHorizontally(); 
            setImage(newImage);
            return true;
        }
        return false;
    }
    
    /**
     * Bobius' Light Attack animation
     */
    private boolean lightAttack() {
        if ((Greenfoot.isKeyDown("i") && lightAttackTimer >= 1 || lightAttackImg != 0) && !walk() && !isJumping() && !roll()) {   
            GreenfootImage newImage = new GreenfootImage(lightAttack[lightAttackImg]);
            if (counter % 2  == 0) {
                lightAttackImg++;
                lightAttackTimer = 0;
            }
            if (lightAttackImg >= lightAttack.length) {
                lightAttackImg = 0;
            }
            if (getImage() == standL) 
                newImage.mirrorHorizontally(); 
            setImage(newImage);
            return true;
        }
        return false;
    }
   
    /**
     * Bobius' Kick animation
     */
    private boolean kickAnimation() {
        if ((Greenfoot.isKeyDown("p") && kickTimer >= 15 || kickImg != 0) && !walk() && !isJumping() && !roll())  {            
            GreenfootImage newImage = new GreenfootImage(spartanKick[kickImg]);
            if (counter % 8 == 0 && kickImg < 7) { 
                kickImg++;
                kickTimer = 0;
            }
            else if (counter % 3 == 0 && kickImg >= 7)
                kickImg++;
            if (kickImg >= spartanKick.length) {
                kickImg = 0;
            }
            if (getImage() == standL) 
                newImage.mirrorHorizontally(); 
            setImage(newImage);
            return true;
        }
        return false;
    }
  
    /**
    * To make a player jump using isKeyDown("w")
    */
    private boolean isJumping() {
        jumpDelay++;
        if(Greenfoot.isKeyDown("w") && onGround() && jumpDelay == 2 && !roll()) {
           jumpSpeed = jumpStrength;
           //Greenfoot.playSound("jump.wav");
           fall();
           return true;
        }
        if (jumpDelay == 3) {
           jumpDelay = 0;
        }
        return false;
    }
    
    // ATTACK COLLISIONS AND EFFECTS
    
    /**
     * Assigns the damage based on the attack used, sets the knockback value
     * returns a boolean -> true or false
     */
    public boolean isAttacking() {
        if (lightAttack() && lightAttackImg == lightAttack.length - 1) {
            damage = lightDamage;
            knockback = knockbackDelta;
            return true;
        }
        if (heavyAttack() && heavyAttackImg == heavyAttack.length - 1) {
            damage = heavyDamage; 
            knockback = knockbackDelta * 4;
            return true;
        }
        if (kickAnimation() && kickImg == spartanKick.length - 1) {
            damage = kickDamage; 
            knockback = knockbackDelta * 10;
            return true;
        }
        return false;
    }
    
    /**
     * inflicts the damage on the enemy, does the knockback 
     */
    public boolean attackCollision() {
        List hitboxes = getIntersectingObjects(Hitbox.class);
        //System.out.println(hitboxes.size());
        for (int i = 0; i < hitboxes.size(); i++) {
           Hitbox hitbox = (Hitbox) hitboxes.get(i);
           if (hitbox == this.hitbox) continue;
           if (isAttacking() && hitbox != null) {
               ((MainWorld)getWorld()).points++;
               hitbox.healthValue -= damage;
               hitbox.lowerHealth();
               knockback(hitbox);
           }
        }
        if (hitboxes.size() == 1) {       
            lightAttack();
            heavyAttack();
            kickAnimation();
        }
        return false;
    }
    
    /**
     * Figures out which hitbox and enemy to knock back
     */
   public boolean knockback(Hitbox hitbox) { 
       int intendedKnockback = knockback;
       if (movingDistance < 0) 
            intendedKnockback *= -1;
       if (counter % 1 == 0) {
            if (hitbox.prisoner != null) {
                hitbox.prisoner.setLocation(hitbox.prisoner.getX() + intendedKnockback, hitbox.prisoner.getY());
            }
            else if (hitbox.champion != null) {
                hitbox.champion.setLocation(hitbox.champion.getX() + intendedKnockback, hitbox.champion.getY());
            }
       }
       return true;
   }
   
   /**
    * counts to 25, and then resets the counter to 0
    */
   private void counter(){
       if (counter < 25){
           counter++;
       }
       else {
           counter = 0;
       }
   }

   // GRAVITY METHODS

   /**
   * to set up gravity so the player falls on the ground and stays there
   */
   private void gravity() {
       setLocation(getX(), getY() + vSpeed);
   }
   
   /**
    * Checks to see if Bobius is touching the ground
    */
   private void checkGround() {
      if (!isTouching(Ground.class)) {
          vSpeed += GRAVITY;
      }
      else {
          vSpeed = 0;
      }
   }
    
   /** 
    * to call when the player jumps and starts falling
    */
   private void fall() {
      setLocation(getX(), getY() + jumpSpeed);
   }
    
   /**
    * To see if is onGround when you fall
    */
   private boolean onGround() {
       Actor under = getOneObjectAtOffset(0, getImage().getHeight() / 2, Ground.class);
       return under != null;
   }
    
   /**
    * To check if the actor is falling
    */
   private void checkFall() {
       if (onGround()) {
           jumpSpeed = 0;
       }
       else {
           fall();
       }
   }
   
   /**
    * Uses seconds to make countable cooldowns
    */
   public int cooldownTimer(int timer) {
        if (frames % 60 == 0) {
            timer++;
            frames = 0;    
        }
        return timer;
   }
    
  /**
   * Inner class that displays Bobius' health over his health bar
   */
  public class BobHealth extends Actor {
        Bobius bobius;
        
        /**
         * Constructor that contains a bobius object
         */
        public BobHealth(Bobius bobius) {
            this.bobius = bobius;
        }
        
        /**
         * act method runs constantly
         */
        public void act() {
            if (bobius == null)
                getWorld().removeObject(this);
            else
                setLocation(bobius.getX() - 5, bobius.getY() - 195);
            adjustHealth();
        }
        
        /**
         * Updates the number represented above the health bar
         */
        public void adjustHealth() {
            GreenfootImage img = new GreenfootImage("" + bobius.hitbox.healthValue + " / " + bobius.health, 24, Color.BLACK, null);
            setImage(img);
        }
 }}