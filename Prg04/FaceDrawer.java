package Lab09;
import Graphics230.*;
import java.awt.*;
import java.applet.Applet;
import javax.swing.*;
/**Author L. Szuecs Modified by Mike Norton 9/17/03, 9/19/03, 9/22/03.
 * <br> <ul>
 * <li> Functional Purpose: To draw a circle with size and location detrmined by
 * the user. With colors. 9/22/03; And eyes.
 * <li> Educational Purpose: To draw circles in applets. To use an init function
 * and to use the color functions. 9/22/03; And to call functions from another
 * user created package.
 * <li> Modifications as of 9/19/03: Creation of init(), creation of
 * fillCircle(), and g.setColor()s.
 * </ul>*/
public class FaceDrawer extends Applet
{
  private int x = 0;
  private int y = 0;
  private int r = 0;
  /**9/22/03; init() stores the user's input so the applet doesn't reprompt
   * every time it is redrawn.
   */
  public void init(){
    this.setBackground(Color.green);
    x = Integer.parseInt(JOptionPane.showInputDialog("Please enter X coordinant of center"));
    y = Integer.parseInt(JOptionPane.showInputDialog("Please enter Y coordinant of center"));
    r = Integer.parseInt(JOptionPane.showInputDialog("Please enter the radius"));
  }
  /**9/22/03; paint sets the color of the circles the user wants drawn, then
   * calls the needed functions to draw a face.
   */
  public void paint(Graphics g)
  {
    g.setColor(Color.cyan);
    CircleUtilities.fillCircle(g, x,y,r);
    g.setColor(Color.pink);
    CircleUtilities.drawCircle(g, x,y,r);
    g.setColor(Color.BLACK);
    CircleUtilities.fillCircle(g,x - (int)(.4 * r),y - (int)(.4 * r),(int)(.1 * r));
    g.setColor(Color.BLACK);
    CircleUtilities.fillCircle(g,x + (int)(.4 * r),y - (int)(.4 * r),(int)(.1 * r));
  }
}

