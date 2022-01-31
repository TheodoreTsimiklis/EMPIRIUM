import greenfoot.*;  
import java.util.List;

/**
 * Prisoner class is the main enemy's that spawn throughout a round
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class Prisoner extends Enemy {
    // Hitbox, as well as damage and speed variables
    public Hitbox hitbox;
    public static int damage = 1;
    private int walkSpeed = 10;
    private final static int ATTACK_SPEED = 1;
    
    private String stand = "Prisoner StandR.png";
    
    // ARRAYS FOR ANIMATION
    
    private String walk[] = {"PWalkingAnimation1.png", "PWalkingAnimation2.png", "PWalkingAnimation3.png", 
            "PWalkingAnimation4.png", "PWalkingAnimation5.png", "PWalkingAnimation6.png"};
    
    private String attack[] = {"PLightAttack1.png", "PLightAttack2.png", "PLightAttack3.png", "PLightAttack4.png", "PLightAttack5.png", "PLightAttack6.png"};
    
    /**
     * Constructor that assigns a hitbox to the prisoner
     */
    public Prisoner() {
       this.hitbox = new Hitbox(20, 20);
       this.hitbox.prisoner = this;
    }
    
    /**
     * Act method runs constantly
     */
    public void act() {
        walk(10, walk, stand);
        frames++;
        attackTimer = cooldownTimer(attackTimer);
        enemyAttack(walkSpeed, ATTACK_SPEED, walk, attack, stand);
        attackCollision();
        gravity();
        counter();
        checkGround();      
        if (hitbox.healthValue <= 0) {
           getWorld().removeObject(this);
        }
        if (getWorld() != null) {
           hitbox.setLocation(this.getX(), this.getY());
        }           
    }
    
    /**
     * Similar to Bobius' attack collision, does damage to bobius
     */
    public boolean attackCollision() {
        List bobius = getIntersectingObjects(Bobius.class);
        for (int i = 0; i < bobius.size(); i++) {
           Hitbox hitbox = ((Bobius)bobius.get(i)).hitbox;
           if (hitbox == this.hitbox && hitbox != hitbox.prisoner.hitbox) continue;
           if (enemyAttack(walkSpeed, ATTACK_SPEED, walk, attack, stand) && hitbox != null && Bobius.invincibilityFrame != 1) {
               hitbox.healthValue -= damage;
               hitbox.lowerHealth();
           }
        }
        return false;
    }
}

