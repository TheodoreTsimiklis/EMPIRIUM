import greenfoot.*;  

/**
 * NextWave applys the wave multiplier,and sets the world back to the mainWorld
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class NextWave extends Actor
{
    
    /**
     * Act method runs constantly
     */
    public void act() 
    {
        nextWaveButton();
    }
    
    /**
     * Applies the wave multipliers
     */
    public void nextWaveButton() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new MainWorld());
            MainWorld.waveNum++;
            Prisoner.damage += 1;
            Bobius.health += 25;
            if (MainWorld.waveNum > 3) {
                Greenfoot.setWorld(new PostCycleMessage());
            }
        }
    }
}
