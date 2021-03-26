package Lab22;
import Graphics230.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import javax.swing.*;
/**Author L. Szuecs Modified by Mike Norton 9/17/03, 9/19/03, 9/22/03, 10/20/03.
 * <br> <ul>
 * <li> Functional Purpose: <ul> <li> To draw a circle with size and location detrmined by
 * the user. With colors. <li> 9/22/03; And eyes. <li> 10/01/03; And a mouth and nose.
 * <li> 10/20/03; the user can now choose whether the face smiles or not. <li> 10/27/03;
 * stand-alone application with multiple panels.</ul>
 * <li> Educational Purpose: <ul> <li> To draw circles in applets. To use an init function
 * and to use the color functions. <li> 9/22/03; And to call functions from another
 * user created package. <li>10/20/03; to call fillPieSlice. <li>10/20/03; to use
 * polymprphism and abstract classes. <li> 10/27/03; to use frames.</ul>
 * <li> Modifications as of 9/19/03: Creation of init(), creation of
 * fillCircle(), and g.setColor()s.
 * </ul>*/
public class FaceDrawer extends Frame
{
  private int x, y, r;
  DrawPanel facePanel = new DrawPanel();
  Panel controlPanel = new Panel();
  Label control = new Label("Checkbox group will come here");

  /**9/22/03; init() stores the user's input so the applet doesn't reprompt
   * every time it is redrawn.
   * 10/22/03; creates a Frown object if the user wants a fround and a Smile if
   * the user wants a smile.
   * 10/27/03; there are no more user entered parameters.  init just calls
   * setupControlPanel and sets the panel layouts.
   * 10/27/03; init becomes FaceDrawer.
   */
  public FaceDrawer(String title){
    super(title);
    setupControlPanel();
    this.setLayout(new BorderLayout());
    this.add(facePanel, BorderLayout.CENTER);
    this.add(controlPanel, BorderLayout.SOUTH);
    this.setBackground(Color.magenta);
    addWindowListener(new CloseWindow());
  }

  /**main sets the title and size of the application, and calls it.*/
  public static void main(String [] args){
    FaceDrawer f = new FaceDrawer("Face Drawer by Mike Norton");
    f.setSize(300,320);
    f.show();
  }

  /**CloseWindow lets you close the window*/
  class CloseWindow extends WindowAdapter {
    public void windowClosing(WindowEvent event){
      System.exit(0);
    }
  }

  /**Sets up the control panel*/
  public void setupControlPanel(){
    controlPanel.add(control);
    controlPanel.setBackground(Color.gray);
  }

  /**10/27/03; creates a Panel that draws itself.  Values that were taken from
   * the user are now hard coded in the constructor.
   * */
  class DrawPanel extends Panel{
    Face face;

    /**<ul><li>9/22/03; paint sets the color of the circles the user wants drawn, then
     * calls the needed functions to draw a face.
     * <li>10/01/03; now paints 3 overlaping secters, which createa mouth and nose.
     * <li>10/20/03; changed a bunch of the colors.  now calls fillPieSlice for all of its
     * pie drawing needs.  Also prompts for and uses the smile/froun controler.
     * <li>10/22/03; now only calls the polydraw function.
     * </ul>
     */
    public void paint(Graphics g)
     {
       x = this.getWidth();
       y= this.getHeight();
       if(x>y)
         r = y;
       if(y>x)
         r = x;
       face = new Smile(x/2,y/2,r/3);
       face.polyDraw(g);
    }

  }
}
