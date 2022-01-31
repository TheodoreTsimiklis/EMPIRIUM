import greenfoot.*;  
import java.util.List;

/**
 * Instructions button sets the world to the InstructionWorld
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class Instructions extends Actor {
    // Images for the animation
    private GreenfootImage animation = new GreenfootImage("InstructionsFP.png");
    private GreenfootImage instructions = new GreenfootImage("InstructionsF.png");
    
    /**
     * Act method runs constantly
     */
    public void act() 
    {
        instructions();
        animation();
    }
    
    /**
     * Sets the world to the instructionWorld
     */
    private void instructions() {
        if  (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new InstructionWorld());
        }
    }

    /**
     * Changes the image when the mouse hovers over the button
     */
    public void animation(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            setImage(instructions);
            List objects = getWorld().getObjectsAt(mouse.getX(), mouse.getY(), Instructions.class);
            for(Object object : objects) {
                if (object == this) {
                    setImage(animation);
                }
            }
        }

    }
}