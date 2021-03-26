
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class contains the data and polymorphic methods needed to
 * create and draw the elements of the game.
 */
abstract class Elements
{
        int x;
        int y;
        int w = 20;
        int h = 40;

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
                x = x + dx;
                y = y + dy;
        }

        /**This method just draws a pink rectangle*/
        public void drawDetails(Graphics g, Image token){
                g.setColor(Color.magenta);
                    g.drawImage(token, x, y, token.getWidth(null), token.getHeight(null), null);
        }

        /**This method moves the block of enemies down and switches thier direction of movement*/
        public void bounce(){

        }
}

/**This class implements the Alien child class of the Elements class*/
class Alien extends Elements{
  Image token = new ImageIcon("vadbot1.gif").getImage();
        public Alien(int ax, int ay){
                super(ax, ay);

        }

        void polyDraw(Graphics g){
                drawDetails(g, token);
        }
}

