import greenfoot.*; 

/**
 * The CreditWorld displays the credits for the game, including the main team who developed the game and how each person contributed to it's creation
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class CreditWorld extends World {
    /**
     * Constructor that adds all the necessary objects to the world
     */
    public CreditWorld() {    
        super(1280, 720, 1);
        addObject(new BackButton(), 1141, 541);
    }
}
