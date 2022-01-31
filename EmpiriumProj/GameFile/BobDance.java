import greenfoot.*; 

/**
 * Uses the Heavy Attack from the Bobius class to dance on the Start Menu
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class BobDance extends Bobius {
    
    // String Array for Heavy Attack
    private String heavyAttack[] = {"HeavyAttack1.png", "HeavyAttack2.png", "HeavyAttack3.png", "HeavyAttack4.png", "HeavyAttack5.png", "HeavyAttack6.png", "HeavyAttack7.png",
             "HeavyAttack8.png", "HeavyAttack9.png", "HeavyAttack10.png","HeavyAttack11.png", "HeavyAttack12.png"};
    
    // Used to modify the speed of the animation
    private int counter = 0;
    
    /**
     * act method runs constantly
     */
    public void act() {
        counter();
        if (getWorld() instanceof StartMenu || heavyAttackImg != 0) {
            if (counter % 5 == 0) 
                heavyAttackImg++;
            if (heavyAttackImg >= heavyAttack.length) 
                heavyAttackImg = 0;
            GreenfootImage newImage = new GreenfootImage(heavyAttack[heavyAttackImg]);
            if (getX() > 640) 
                newImage.mirrorHorizontally(); 
            setImage(newImage);       
        } 
    }
    
    /**
     * Counts to 25 and then resets
     */
    private void counter(){
       if (counter < 25){
           counter++;
       }
       else {
           counter = 0;
       }
    }
}
