import greenfoot.*; 

/**
 * The bird travels along the x-axis, going to randomly generated points, and flying out of the world after a certain amount of time
 * @author Team Empirium
 * @version (09/12/2020)
 */
public class Bird extends Actor {
    // Bird animation array
    private String animateBird[] = {"BirdWingUp.png", "BirdWingDown.png"};
    // Variable for bird's next X coordinate
    private static int birdXLocation = -1;
    // Kill variable is what dictates when the bird flies out of the world
    private int kill = 0;
    // Frame variable is used for the animation
    private int frame = 0;
    
   /**
     * act method runs constantly
     */
    public void act() 
    {
       removeAtEdge();
       movement();
       twitchLOL();
       animateBird();
    }
    
   /**
    * Method for the bird animation
    */
    public void animateBird() {
        if (getWorld() != null) {
            frame++;
            if (frame % 9 == 0) {
                GreenfootImage img = new GreenfootImage(animateBird[frame%2]);
                if (birdXLocation > getX()) img.mirrorHorizontally();
                setImage(img);
            }
        }
    }
   
   /**
    * Method for the bird's movement
    */
    public void movement() {
        int moveSteps = 3;
        kill++;
        if (getWorld() == null) return; 
        if (birdXLocation == -1 || (birdXLocation > this.getX() - moveSteps && birdXLocation < this.getX() + moveSteps)) {
            birdXLocation = Greenfoot.getRandomNumber(1250) + 20;
            if (kill >= 2000) {
                birdXLocation = birdXLocation * 0;
            }
        }
        if (birdXLocation > this.getX()) {
            move(moveSteps);
        }
        else {
            move(moveSteps * -1);
        }
    }
    
   /**
    * Method to remove the bird when it reaches the edge of the world
    */ 
    public void removeAtEdge() {
        if (isAtEdge()) {
           getWorld().removeObject(this);
        }
    }
    
   /**
    * Easter Egg method, includes our socials as a neat little feature (SHHHHH)
    */  
    public void twitchLOL() { 
        if  (Greenfoot.mouseClicked(this)) {
            String tonyTwitch = "https://www.twitch.tv/buhbah6";
            String theoTwitch = "https://www.twitch.tv/sslur";
            String georgeYT = "https://www.youtube.com/channel/UCvp8UGGAi_Up0r-4rhZTujg";
            String gret = "Shoutout to Gretron (David)";
            System.out.printf("%s\n%s\n%s\n%s\n", tonyTwitch, theoTwitch, georgeYT, gret);
        }   
    }
}
