import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**  Modified by Mike Norton.<br>
 *  Functional Purpose: <ul><li> Displays a key pressed by the user, <li> 11/05/03; and deal
 *  with new lines. <li> 11/10/03; the font and its size will be passed from the
 *  command line. <li> 11/12/03; if invalit data are passed, the program will recognize
 *  them as invalid and use defaults.<li> 11/19/03; With a menu bar and save and
 *  quit options. <li> 11/21/03; The program now has funcioning save and open
 *  commands.</ul><br>
 *  Educational Purpose:<ul><li> to work with the key handlers. <li> 11/10/03;
 *  to work with passing arguments from the command line. <li> 11/12/03; to work
 *  exception handling.<li> 11/19/03; to work with menus.<li> 11/21/03; To work
 *  with more exeption handling, and more try/catch structures.</ul>
 */


public class Key extends JFrame implements KeyListener, ActionListener
{

  static final int x=10;
  private int y;
  private int p = x;
  private String s = "";
  private String cursor = "_";
  int size = 12;
  private FontMetrics m;
  JMenuItem open = new JMenuItem("Open...");
  JMenuItem save = new JMenuItem("Save As...");
  JMenuItem exit = new JMenuItem("Exit");
  JMenuBar bar = new JMenuBar();
  DrawOn go = new DrawOn();

  /**Key constructs the Key object by passing the title to the frame, setting the
   * passed font, and setting up the window in general. <ul> <li> 11/12/03; If the data, for
   * font size, are too small or invalid some how, they will be replaced with defaults.
   * <li> 11/19/03; a menu bar and menu have been added. the only objects in the menu now are
   * save and exit. <li> 11/21/03; and open.</ul>
   */

  public Key(String title, String[] args){
    super(title);
    
    Container c = getContentPane();
    c.add(go);

    setJMenuBar(bar);
    bar.setBackground(Color.magenta);

    JMenu file = new JMenu("File");
    bar.add(file);

    file.setBackground(Color.magenta);
    file.add(open);
    file.add(save);
    file.add(exit);
    open.setBackground(Color.magenta);
    exit.setBackground(Color.magenta);
    save.setBackground(Color.magenta);
    open.addActionListener(this);
    save.addActionListener(this);
    exit.addActionListener(this);

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
    go.setFont(f);
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
   * <li> 11/18/03; paint now calls 'super.paint' to make up for the lack of
   * automaticly erasing the old screen, and adds the hight of the menubar.</ul>
   */

  
  /**11/12/03when the F12 key is pressed, keyPressed resets the string 's' to blank, thus
   * there will be nothing to display when the string is redrawn, and the screen
   * will be clear.
   */

  public void keyPressed(KeyEvent event)
  {
    if(event.getKeyCode() == KeyEvent.VK_F12)
      {
        s = "";
        repaint();
      }
    //System.out.println("key pressed");

  }
  public void keyReleased(KeyEvent event)
  {
    //System.out.println("key released");

  }

  /**keyTyped adds the new key to the string of keys to be displayed, 11/05/03 if
   * it is a printable character  11/12/03; when the backspace key (ascii code 8)
   * is pressed, the last character in the string is removed*/
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
    if((int) theKey == 8){
      s = s.substring(0, s.length() - 1);
      repaint();
    }

  }
  /**11/19/03; actionperformed determines which menu item was selected, then exicutes that
   * action. 11/21/03; When the user trys to open a file, the program
   * reads the file into a character array, then pases the array to the 's' string.
   * If an IO exception error occurs, the program displays an error message.  When
   * the user saves, the program copies the data in the editor into the user
   * specified file.
   */

  public void actionPerformed(ActionEvent event){
    Object source = event.getSource();
    if(source == exit)
      System.exit(0);
    if(source == open){
      JFileChooser fco = new JFileChooser(".");
      int answer = fco.showOpenDialog(this);
      try{
        File file = fco.getSelectedFile();
        int length = (int)file.length();
        char[] buffer = new char[length];
        FileReader reader = new FileReader(file);
        reader.read(buffer);
        String sFile = "";
        for(int c = 0; c < length; c++){
          sFile = sFile + String.valueOf(buffer[c]);
        }
        reader.close();
        s = sFile;
        repaint();
      }catch(IOException e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Invalid or No Filename Entered", "Failure to Open File", JOptionPane.ERROR_MESSAGE);
      }
    }
    if(source == save){
      JFileChooser fco = new JFileChooser(".");
      int answer = fco.showSaveDialog(this);
      try{
        if (answer == JFileChooser.APPROVE_OPTION) {
          File file = fco.getSelectedFile();
          FileWriter writer = new FileWriter(file);
          writer.write(s);
          writer.close();
        }
      }
      catch(IOException e){
        e.printStackTrace();
      }
    }
  }
  
  class DrawOn extends JPanel
  {
  	public void paintComponent(Graphics g){
  	  super.paintComponent(g); 
  	  
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
  	
  }
}

/**CloseWindow lets you close the window*/
class CloseWindow extends WindowAdapter {
  public void windowClosing(WindowEvent event){
    System.exit(0);
  }
}
