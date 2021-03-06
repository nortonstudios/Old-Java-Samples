import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import javax.swing.*;

/**  Modified by Mike Norton, 11/03/03, 11/05/03, 11/10/03.<br>
 *  Functional Purpose: <ul><li> Displays a key pressed by the user, <li> 11/05/03; and deal
 *  with new lines. <li> 11/10/03; the font and its size will be passed from the
 *  command line. <li> 11/12/03; if invalit data are passed, the program will recognize
 *  them as invalid and use defaults.</ul><br>
 *  Educational Purpose:<ul><li> to work with the key handlers. <li> 11/10/03;
 *  to work with passing arguments from the command line. <li> 11/12/03; to work
 *  exception handling.</ul>
 */


public class Key extends Frame implements KeyListener
{

  static final int x=10;
  private int y;
  private int p = x;
  private String s = "";
  private String cursor = "_";
  int size = 12;
  private FontMetrics m;

  /**Key constructs the Key object by passing the title to the frame, setting the
   * passed font, and setting up the window in general. 11/12/03; If the data, for
   * font size, are too small or invalid some how, they will be replaced with defaults.
   */

  public Key(String title, String[] args){
    super(title);
    try{
      if(Integer.parseInt(args[1]) >=9){
        size = Integer.parseInt(args[1]);
      }else{
        JOptionPane.showMessageDialog(null,"Using default 12","Font Size Too Small or Negative - '" + args[1] + "'!!!",JOptionPane.INFORMATION_MESSAGE);
      }
    }
    catch(NumberFormatException e){
      JOptionPane.showMessageDialog(null,"Using default size 12","Invalid Font Size - '" + args[1] + "'!!!",JOptionPane.INFORMATION_MESSAGE);
    }
    Font f = new Font(args[0], Font.BOLD, size);
    setFont(f);
    addKeyListener(this);
    addWindowListener(new CloseWindow());
    requestFocus();
    m = getFontMetrics(f);
  }

  /**main declares a new Key object, sets the window size, and displays the Key
   * object.
   */

  public static void main(String [] args){
    Key k = new Key("NoteBad by Mike Norton (Title in progress)", args);
    k.setSize(800,400);
    k.show();
  }

  /**<ul><li> paint writes the characters in the "s" string to the screen, one at a time
   * so they are editable later.
   * <li> 11/05/03; if paint receives a new line character, it drops down the
   * height of the font. Also, an underscore just sits at the end of the printed
   * string, for effect.
   */

  public void paint(Graphics g)
  {
    String sTemp = "";
    y = m.getHeight() + this.getInsets().top;
    p = x;
    for(int c = 0; c < s.length(); c++)
    {
      if((int)s.charAt(c) >= 32 && (int)s.charAt(c) <= 126){
        p = p + m.stringWidth(sTemp);
        sTemp = String.valueOf(s.charAt(c));
        g.drawString(sTemp, p, y);
      }
      if((int)s.charAt(c) == 10){
        p = x;
        y += m.getHeight();
        sTemp = "";
      }
    }
    p = p + m.stringWidth(sTemp);
    g.drawString(cursor, p, y);
  }
  public void keyPressed(KeyEvent event)
  {
    //System.out.println("key pressed");

  }
  public void keyReleased(KeyEvent event)
  {
    //System.out.println("key released");

  }

  /**keyTyped adds the new key to the string of keys to be displayed, 11/05/03 if
   * it is a printable character*/
  public void keyTyped(KeyEvent event)
  {
    char theKey = 'A';
    theKey = event.getKeyChar();
    if ( (int) theKey >= 32 && (int) theKey <= 126 || (int) theKey == 10) {
      String s1 = String.valueOf(theKey);
      s = s + s1;
      //System.out.println("key typed: " + (int) theKey);
      repaint();
    }
  }
}

/**CloseWindow lets you close the window*/
class CloseWindow extends WindowAdapter {
  public void windowClosing(WindowEvent event){
    System.exit(0);
  }
}
