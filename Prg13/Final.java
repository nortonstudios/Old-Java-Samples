
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**Author Michael Norton 12/10/03.<br>
 *Functional Purpose:<ul>
 *<li>To create a game that reasonably emulates Space Invaders, including; aliens,
 *the player's spaceship, and bullets.<ul><br>
 *Educational Purpose:<ul>
 *<li>To create a program that uses features from the entire year.<ul>
 */
public class Final extends Frame{
  DrawPanel spacePanel = new DrawPanel();

  /**Adds the spacePanel to the frame and allowed the usre to close the window.*/
  public Final(String title){
    super(title);
    this.add(spacePanel);
    addWindowListener(new CloseWindow());
  }

  /**Creates the frame.*/
  public static void main(String [] args){
    Final game = new Final("Simple Geometric Shape Invaders");
    game.setSize(600,600);
    game.show();
  }

  /**Makes a panel that draws itself. */
  class DrawPanel extends JPanel implements Runnable{

    int num = 10;
    Elements[] enemy =  new Elements[num];

    /**sets the background color and starts the moving aliens thread*/
    public DrawPanel() {
      super();
      this.setBackground(Color.black);
      for(int c = 0; c < num; c++){
        enemy[c] = new Alien((c * 40) + 10 , 100);
      }
      Thread t = new Thread(this);
      t.start();
      }

    /**Draws the panel*/
    public void paint(Graphics g){
      super.paint(g);
      for(int c = 0; c < num; c++){
        enemy[c].polyDraw(g);
      }
    }

    /**Moves the alien*/
    public void run(){
      while(true){
        for(int c = 0; c < num; c++){
          enemy[c].step(1, 0);
        }
        repaint();
        try{
          Thread.sleep(10);
        }catch(InterruptedException e){
          e.printStackTrace();
        }
      }
    }
  }
}

  /**CloseWindow lets you close the window*/
  class CloseWindow extends WindowAdapter {
    public void windowClosing(WindowEvent event){
      System.exit(0);
    }
  }


