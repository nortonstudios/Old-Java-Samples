package Lab22;
import java.awt.*;
import Graphics230.*;

/**
 * This class contains the data and polymorphic methods needed to
 * draw a smiling or frowning face, depending on the user's choice.
 */
abstract class Face
{  /**
   * x-coordinate of the center of the face
   */
  int x;

  /**
   * x-coordinate of the center of the face
   */
  int y;

  /**
  * radius of the face
  */
  int r;

  /**
   * Constructor to initialize the center coordinates
   * and radius of the face.
   */
  Face (int xx, int yy, int rr)
  {
    x = xx;
    y = yy;
    r= rr;
  }

  /**
   * This is the polymorphic draw method. In this class
   * (the base class) this method will have an empty body.
   * It is actually implemented in the child classes.
   */
  void polyDraw(Graphics g){}

  /**
   * This method contains the code that used to be the code of
   * the paint method in the Applet class of the previous labs.
   * <ul><li>9/22/03; paint sets the color of the circles the user wants drawn, then
   * calls the needed functions to draw a face.  Paints 3 overlaping secters,
   * which createa mouth and nose.  Calls fillPieSlice for all of its
   * pie drawing needs.</ul>
   */

  void drawDetails(Graphics g, int mode)
  {
    g.setColor(Color.green);
    CircleUtilities.fillCircle(g, x,y,r);

    g.setColor(Color.blue);
    CircleUtilities.drawCircle(g, x,y,r);

    g.setColor(Color.black);
    CircleUtilities.fillCircle(g,x - (int)(.4 * r),y - (int)(.4 * r),(int)(.1 * r));

    g.setColor(Color.yellow);
    CircleUtilities.fillCircle(g,x + (int)(.4 * r),y - (int)(.4 * r),(int)(.1 * r));

    g.setColor(Color.blue);
    CircleUtilities.fillPieSlice(g,x - (int)(.6 * r),x + (int)(.6 * r),y + (int)(.45 * r),130,mode);

    g.setColor(Color.green);
    CircleUtilities.fillPieSlice(g,x - (int)(.6 * r),x + (int)(.6 * r),y + (int)(.45 * r),100,mode);

    g.setColor(Color.pink);
    CircleUtilities.fillPieSlice(g,x - (int)(.15 * r),x + (int)(.15 * r),y,45,-1);
  }
}  // end class Face

 /**
  * This class implements the smiling child class of the
  * Face class.
  */
class Smile extends Face
{   /**
    * Write a constructor here.
    * This constructor will take three arguments (x, y, r)
    * and pass them on to the base class constructor
    */
   public Smile(int sx, int sy, int sr){
     super(sx, sy, sr);
   }

  /**
   * This method simply calls the drawDetails method of the base class
   * with appropriate arguments.
   */
   void polyDraw(Graphics g){
    drawDetails(g, -1);
  }
}

/**
  * This class implements the frowning child class of the
  * Face class.
  */
class Frown extends Face
{  /**
   * Write a constructor here.
   * This constructor will take three arguments (x, y, r)
   * and pass them on to the base class constructor
   */
  public Frown(int sx, int sy, int sr){
    super(sx, sy, sr);
  }

  /**
   * This method simply calls the drawDetails method of the base class
   * with appropriate arguments.
   */
  void polyDraw(Graphics g){
    drawDetails(g, 1);
  }


}

