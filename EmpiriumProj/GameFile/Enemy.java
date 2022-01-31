import greenfoot.*; 
import java.util.List;

/**
 * Enemy parent class holds all universal methods between Prisoners and Champions
 * @author Team Empirium 
 * @version (09/12/2020)
 */
public class Enemy extends Actor
{
    // Timers for the attacks - used in animation
    public int attackTimer = 0;
    private int attackImg = 0;
    
    // Variables used for walking and changing sides
    private int movingDistance;
    private int walkImg = 0;
    public int enemyRadiusModifier = 0;
    public static int prisonerNum = 0;
    
    // State variables - change without player input
    public int counter;    
    public int frames;    
    private boolean isStanding;
    
    // Gravity
    private static final int GRAVITY = 4;
    private int vSpeed;
    
    // Used to keep the enemies separated
    private final static int DISTANCE = 200;
    private final static int DISTANCE_ADDITIONAL = 60;
    private final static int CHANGE_SIDE = 100;
    
    /**
    * To check if there is collision with the ground (this is the collision factor with the ground)
    */
    public void checkGround() {
        if (!isTouching(Ground.class)) {
               vSpeed += GRAVITY;
           }
           else {
               vSpeed = 0;
        }
    }
    
    /**
    * to set  up gravity so the player falls on the ground and stays there
    */
    public void gravity() {
        setLocation(getX(), getY() + vSpeed); 
    }
    
    /**
     * Checks to see if the enemy should be facing right
     */
    public boolean isFacingRight() {
        int enemyX = getX();
        Actor bobius = (Actor)getWorld().getObjects(Bobius.class).get(0);
        int bobiusX = bobius.getX();
        if (enemyRadiusModifier % 2 == 0)
            return enemyX - bobiusX - CHANGE_SIDE < 0;
        else
            return enemyX - bobiusX + CHANGE_SIDE < 0;
    }
    
    /**
     * Checks to see if enemy should be facing left
     */
    public boolean isFacingLeft() {
        int enemyX = getX();
        Actor bobius = (Actor)getWorld().getObjects(Bobius.class).get(0);
        int bobiusX = bobius.getX();
        if (enemyRadiusModifier % 2 == 0)
            return enemyX - bobiusX - CHANGE_SIDE > 0;
        else
            return enemyX - bobiusX + CHANGE_SIDE > 0;
    }
  
    /**
     * Counts until 25 and then resets to 0
     */
    public void counter(){
        if (counter < 25){
            counter++;
        }
        else {
            counter = 0;
        }
    }
    
    /**
     * Walking Animation
     * Takes a variable for speed, the animation, and the default standing position
     */
    public boolean walk(int walkSpeed, String walkArray[], String stand) {
        if (isFacingRight() && !isClose(this)) {
            if (counter % 4 == 0) {
                walkImg++;
                setLocation(getX() + walkSpeed, getY());
            }
            if (walkImg >= walkArray.length) {
                walkImg = 0;
            }
            GreenfootImage newImage = new GreenfootImage(walkArray[walkImg]);   
            setImage(newImage);
            return true;
        }
        else if (isFacingLeft() && !isClose(this)) {
            if (counter % 4 == 0) {
                walkImg++;
                setLocation(getX() - walkSpeed, getY());
            }
            if (walkImg >= walkArray.length) {
                walkImg = 0;
            }
            GreenfootImage newImage = new GreenfootImage(walkArray[walkImg]);
            newImage.mirrorHorizontally();
            setImage(newImage);
            return true;
        }
        else {
            GreenfootImage standing = new GreenfootImage(stand);
            if (isFacingRight())
                setImage(standing);
            else {
                standing.mirrorHorizontally();
                setImage(standing);
            }
        }
        return false;
    }
    
    /**
     * Checks to see if the enemy is within Bobius' range
     */
    public static boolean isClose(Enemy enemy) {
        int enemyX = enemy.getX();
        if (enemy.getWorld().getObjects(Bobius.class).size() == 0) 
            return false;
        int bobiusX = enemy.getWorld().getObjects(Bobius.class).get(0).getX();
        int left;
        int right;
        int modifier = (DISTANCE + DISTANCE_ADDITIONAL * (enemy.enemyRadiusModifier / 2));
        if (enemy.enemyRadiusModifier % 2 == 0) {
            left = bobiusX + CHANGE_SIDE;
            right = bobiusX + modifier;
        } else {
            left = bobiusX - modifier;
            right = bobiusX - CHANGE_SIDE;
        }
        return enemyX > left && enemyX < right;
    }
    
    /**
     * Prevents the enemies from overlapping with eachother, makes them move away from eachother
     */
    public static void preventOverlap(World MyWorld) {
        List prisoners = MyWorld.getObjects(Prisoner.class);
        int closeEnemies = 0;
        for (int i = 0; i < prisoners.size(); i++) {
            Prisoner prisoner = (Prisoner) prisoners.get(i);
            if (prisoner.enemyRadiusModifier == 0 || prisonerNum != prisoners.size()) {
                prisoner.enemyRadiusModifier = closeEnemies;
            }
            if (isClose(prisoner))
                closeEnemies++;
        }
        prisonerNum = prisoners.size();
    }
    
    /**
     * Enemy attack animation
     */
    public boolean enemyAttack(int walkSpeed, int attackSpeed, String walkArray[], String attackArray[], String stand) {
        if (inAttackRange() && !walk(walkSpeed, walkArray, stand) && attackTimer > attackSpeed || attackImg != 0) {   
            GreenfootImage newImage = new GreenfootImage(attackArray[attackImg]);
            if (counter % 2 == 0) {
                attackImg++;
                attackTimer = 0;
            }
            if (attackImg >= attackArray.length) {
                attackImg = 0;
            }
            if (isFacingLeft()) 
                newImage.mirrorHorizontally(); 
            setImage(newImage);
            return true;
        }
        return false;
    }
    
    /**
     * Checks to see if bobius is within the Enemy's attack range
     */
    public boolean inAttackRange() {
        int enemyX = getX();
        int bobiusX = getWorld().getObjects(Bobius.class).get(0).getX();
        return enemyX > bobiusX - DISTANCE  && enemyX < bobiusX + DISTANCE;
    }
    
    /**
     * Cooldown timer for the attacks
     */
    public int cooldownTimer(int timer) {
        if (frames % 60 == 0) {
            timer++;
            frames = 0;    
        }
        return timer;
    }
}

    



