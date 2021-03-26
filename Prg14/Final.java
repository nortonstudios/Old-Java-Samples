
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;


/**Author Michael Norton 12/17/03.<br>
 *Functional Purpose:<ul>
 *<li>To create a game where the player catches falling aliens in a resque ship.<ul><br>
 *Educational Purpose:</ul>
 *<ul><li>To create a program that uses features from the entire semister.</ul>
 */
public class Final extends JFrame implements KeyListener, ActionListener{
  static int WIDTH = 400;
  JMenuBar bar = new JMenuBar();
  int num = 10;
  int runs = 0;
  int zz = 40;
  int score = 0;
  boolean dif = false;
  boolean newg = false;
  boolean gstop = false;

  JMenuItem newgame = new JMenuItem("New Game");
  JMenuItem exit = new JMenuItem("Exit");
  JMenuItem easy = new JMenuItem("Easy");
  JMenuItem hard = new JMenuItem("Hard");

  DrawPanel spacePanel = new DrawPanel();
  Elements player = new Ship(180, 370);


  /**Adds the spacePanel and the menu stuff to the frame.  Also adds the action 
   *listeners and the closewindow listener to the frame.  Also sets colors for 
   *menu bar and its components.*/
  public Final(String title){
    super(title);
    this.getContentPane().add(spacePanel);
    setJMenuBar(bar);
    bar.setBackground(Color.magenta);

    JMenu file = new JMenu("File");
    JMenu diff = new JMenu("Difficulty");
    bar.add(file);
    bar.add(diff);

    file.setBackground(Color.magenta);
    diff.setBackground(Color.magenta);
    diff.add(easy);
    diff.add(hard);
    file.add(newgame);
    file.add(exit);
    easy.addActionListener(this);
    hard.addActionListener(this);
    exit.addActionListener(this);
    newgame.addActionListener(this);

    addKeyListener(this);
    addWindowListener(new CloseWindow());

  }

  /**Creates the frame, in this case, not resizable.*/
  public static void main(String [] args){
    Final game = new Final("Alien Catcher by Michael Norton");
    game.setSize(WIDTH,450);
    game.setResizable(false);
    game.show();
  }
  
  /**reads actions from the user.  Mainly if the user chooses a menu item.<ul>
   *<li>If the user selects a new game, everything is re-initialized.
   *<li>If the user selects easy, the difficulty boolean is set to false, designating easy.
   *<li>If the user selects hard, the difficulty boolean is set to true, designating hard.
   *<li>If the user selects exit, the window closes.
   * </ul>*/ 
  public void actionPerformed(ActionEvent event){
  	Object source = event.getSource();
  	if(source == exit){
  		System.exit(0);
  	}
  	if(source == newgame){
  		runs = 0;
  		zz = 40;
  		score = 0;
  		newg = true;
  		gstop = false;

  	}
  	if(source == easy){
  		dif = false;
  	}
  	if(source == hard){
  		dif = true;
  	}
  }

  /**this reads the user's keystrokes.  If the user presses an arrow key, the corresponding 
   *movement is made.*/
  public void keyPressed(KeyEvent event)
  {
    //System.out.println("key pressed" + event.getKeyCode());
	System.out.println(event.getKeyCode());
    if(event.getKeyCode() == 37)
    {
      player.move(-10);
      //System.out.println("37");
    }
    if(event.getKeyCode() == 39){
      player.move(10);
      //System.out.println("39");
    }


  }
  public void keyReleased(KeyEvent event)
  {
    //System.out.println("key released");
  }

  public void keyTyped(KeyEvent event)
  {
    //System.out.print(event.getKeyChar());

  }
  

  /**Makes a panel that draws itself. */
  class DrawPanel extends JPanel implements Runnable{
    Elements tester = new Alien(200, 0);
    //Elements[] enemy = new Elements[num];

    Random random = new Random(35);
    int left = random.nextInt(10);
        
        
    /**sets the background color and starts the moving aliens thread.  Also displays 
     *a message window that has the object and controls in it.*/
    public DrawPanel() {
      super();
      this.setBackground(Color.black);
      //for (int c = 0; c < num; c++) {
      //  enemy[c] = new Alien( (c * 40) + 10, 100);
      //}
      Thread t = new Thread(this);
      JOptionPane.showMessageDialog(this,"Catch as many falling aliens" + '\n' + "as possable!" + '\n' + "Use the left and right arrow" + '\n' + "keys to control the resque ship!","The Point",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Ship.gif"));
      t.start();
    }

    /**Draws the panel and its elements.  Does not draw the alien if it has been resqued.*/
    public void paint(Graphics g) {
      super.paint(g);
      if(!tester.testBounds(player.x,player.y))
	      tester.polyDraw(g);
      player.polyDraw(g);
      //for (int c = 0; c < num; c++) {
      //	if(!enemy[c].caught)
      //  	enemy[c].polyDraw(g);
      //}
    }

    /**Moves the alien.<ul>
     *<li>If the alien hits a wall, it bounces.
     *<li>If the player misses an alien, the game is over and an appropiate diolog window 
     *is spawned, it shows the score too.
     *<li>If the user wanted a hard game, the alien move in two directions. If the 
     *wanted an easy game, it moves in one.  In both cases its speed increases as
     *the game progresses.
     *<li>When the alien is caught, everything reinitializes, and scores and controls
     *are incremented.
     *</ul>*/
    public void run(){
      while(true){
      	if(newg){
      		tester.reset(random.nextInt(369));
      		newg = false;
      	}
      	if(tester.caught){
      		left = random.nextInt(10);
      		tester.reset(random.nextInt(369));
      		runs++;
      		score++;
      		//System.out.println(score);
      		if((int)(40 - (3 * runs)) > 5){
      			zz = (int)(40 - (3 * runs));
      		}
      		//System.out.println(zz);
      	}else{
      		if(!dif){
      			tester.step(0,3);
      		}
      		if(dif){
      			tester.step(left, 4);
      		}
      	}
      	if(tester.x >= 400 - 40 || tester.x <= 0){
            tester.bounce();
        }
        if(tester.miss() && !gstop)
        {
        	JOptionPane.showMessageDialog(this,"PLAY AGAIN SOON" + '\n' + "YOUR SCORE IS  " + score * 1000, "YOU LOSE!!", JOptionPane.PLAIN_MESSAGE, new ImageIcon("VADBOT1.GIF"));
        	//System.out.println("lose");
        	gstop = true;
        }
     		
      	
      	
        //for(int c = 0; c < num; c++){
        //  enemy[c].step(0, 1);
        //}


        repaint();
        
        try{
          Thread.sleep(zz);
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


