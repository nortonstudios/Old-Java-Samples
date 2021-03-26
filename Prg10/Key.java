import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/**  Modified by Mike Norton, 11/03/03, 11/05/03.<br>
 *  Functional Purpose: Displays a key pressed by the user, 11/05/03; and deal
 *  with new lines.<br>
 *  Educational Purpose: to work with the key handlers.
 */


public class Key extends Applet implements KeyListener
{

  static final int x=10;
  private int y;
  private int p = x;
  private Font f = new Font("Serif",Font.BOLD,36);
  private String s = "";
  private String cursor = "_";
  private FontMetrics m;

  public void init()
  {
    setFont(f);
    addKeyListener(this);
    requestFocus();
    m = getFontMetrics(f);
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
    y = m.getHeight();
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
    System.out.println("key pressed");

  }
  public void keyReleased(KeyEvent event)
  {
    System.out.println("key released");

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
      System.out.println("key typed: " + (int) theKey);
      repaint();
    }
  }
}