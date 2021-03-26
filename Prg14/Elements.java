
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class contains the data and polymorphic methods needed to
 * create and draw the elements of the game.
 */
abstract class Elements
{
	/**the x coordinant of the object*/			
        int x;
	/**the y coordinant of the object*/			
        int y;
    /**the direction of the povement of the object, on the x axis*/    
        int d = 1;
    /**whether the alien is active or not*/
        boolean caught = false;

        /**Constructs and initializes the top left corner of the object.*/
        Elements(int xx, int yy){
                x = xx;
                y = yy;
        }

        /**
        * This is the polymorphic draw method. In this class
        * (the base class) this method will have an empty body.
        * It is actually implemented in the child classes.
        */
        void polyDraw(Graphics g){}

        /**
         * This is the universal method to move the aliens down the screen.
         */
        public void step(int dx, int dy){
                x = x + (d * dx);
                y = y + dy;
        }

        /**This method moves the player's ship when the player enters a move comand*/
        public void move(int dx){
          x = x + dx;
        }
        
        /**This method bounces aliens off of the side of the screen*/
        public void bounce(){
        	d = -d;
        }
        
		/**this method checks to see if the alien has touched the ship. if it has
		 *cought becose true.  either way, cought is returned.*/
        public boolean testBounds(int x1, int y1){
          int ax = x + 30;
          int ay = y + 30;
          int x2 = x1 + 30;
          int y2 = y1 + 30;

          if(x1 >= x && x1 <= ax && y1 >= y && y1 <= ay ||
             x1 >= x && x1 <= ax && y2 >= y && y2 <= ay ||
             x2 >= x && x2 <= ax && y1 >= y && y1 <= ay ||
             x2 >= x && x2 <= ax && y2 >= y && y2 <= ay){
            caught = true;
            return(caught);
          }else
          	return(caught);
        }
        
        /**this method restores all the defaults for members of the Elements class*/
        public void reset(int nx){
        	x = nx;
        	y = 0;
        	caught = false;
        }
        
        /**this method calculates a miss*/
        public boolean miss(){
        	if(y>440){
        		return(true);
        	}else{
        		return(false);
        	}	
        }	 


        /**This method just draws both aliens and the player's ship.*/
        public void drawDetails(Graphics g, Image token){
                g.setColor(Color.magenta);
                g.drawImage(token, x, y, token.getWidth(null), token.getHeight(null), null);
        }


}

/**This class implements the Alien child class of the Elements class*/
class Alien extends Elements{
    Image token = new ImageIcon("vadbot1.gif").getImage();
    public Alien(int ax, int ay){
        super(ax, ay);
	//System.out.println(token.getWidth(null));
    }

    void polyDraw(Graphics g){
        drawDetails(g, token);
    }

}

/**This class implements the Ship child class of the Elements class*/
class Ship extends Elements{
	Image token = new ImageIcon("ship.gif").getImage();
	public Ship(int sx, int sy){
    	super(sx, sy);
  	}

  	void polyDraw(Graphics g){
    	drawDetails(g, token);
  	}
}

