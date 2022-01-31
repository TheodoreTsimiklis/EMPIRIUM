import greenfoot.*;

/**
 * InstructionWorld displays all the keybinds so the player can learn the controls of the game
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class InstructionWorld extends World {
    /**
     * Constructor for the InstructionWorld() adds all the objects into the world
     */
    public InstructionWorld() {    
        super(1280, 720, 1);
        addObject(new BackButton(), 1115, 598);
    }
}
