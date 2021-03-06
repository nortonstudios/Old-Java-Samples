import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** @author Laszlo Szuecs -- 10/7/03; modified by Mike Norton, 10/08/03, 10/13/03.
 * <br><ul>
 * <li> Functional: This applet will allow the user to enter the number of mines in a minesweeper
 * field, and the seed for a random number generator that will determine the
 * mine locations in the minesweeper field. 10/13/03: When row and column values
 * are entered, it checks for mines at that and sorrounding locations. 10/17/03:
 * make the row and data come from a grid of buttons.
 * <li> Educational: to use 2-d arrays.
 * </ul>
*/


public class MineSweeper extends Applet implements ActionListener
{
    static final int fieldSize = 15;
    boolean[][] mines = new boolean[fieldSize][fieldSize];
    Label[][] showMinesLabel = new Label [fieldSize][fieldSize];
    MSButton[][] mineField = new MSButton[fieldSize][fieldSize];
    int revealValue = 0;
    Label statusLabel = new Label("Ready...Set...");

    private Label mineCountLabel = new Label("Number of mines: ");
    private TextField mineCountText = new TextField(5);

    private Label seedLabel = new Label("Seed: ");
    private TextField seedText = new TextField(5);

    private Panel controlPanel = new Panel();
    private Panel playPanel = new Panel();

    public void init()
    {
      setupControlPanel ();
      setupPlayPanel ();

      this.setLayout(new BorderLayout());
      this.add(controlPanel, BorderLayout.NORTH);
      this.add(playPanel, BorderLayout.CENTER);

      setFont(new Font("Arial",Font.BOLD,12));
      this.setBackground(Color.yellow);
    }

    /**
     * Add two label / text field pairs to the control panel.
     * The text field for the number of mines doesn't have a listener
     * associated with it.
     * If the user fails to enter the number of mines before entering the
     * seed, the program will fail.
     * 10/13/03: Adds the rows and columns stuff to the control panel.
     * 10/15/03: There is no more rows and column stuff.
     */
    void setupControlPanel ()
    {
      controlPanel.add(statusLabel);
      statusLabel.setBackground(Color.yellow);
      controlPanel.add(mineCountLabel);
      mineCountLabel.setBackground(Color.yellow);
      controlPanel.add(mineCountText);

      controlPanel.add(seedLabel);
      seedLabel.setBackground(Color.yellow);
      controlPanel.add(seedText);

      seedText.addActionListener(this);
      mineCountText.setBackground(Color.cyan);
      seedText.setBackground(Color.cyan);
    }

    /**
     * Create a grid layout for the play panel.
     * <br>
     * Construct a label for each slot of the grid layout and
     * add that label to the play panel.
     * 10/15/03: setupPlayPanel now adds the button array to the panel, and attaches
     * the listener to the buttons.  The buttons are disabled.
     */
    void setupPlayPanel ()
      {
         playPanel.setLayout(new GridLayout(fieldSize,fieldSize));
         for (int row = 0; row < fieldSize; row++)
           for (int col = 0; col < fieldSize; col++)
           {  mineField[row][col] = new MSButton(row, col);
              playPanel.add( mineField[row][col] );
              mineField[row][col].setBackground(Color.red);
              mineField[row][col].addActionListener(this);
              mineField[row][col].setEnabled(false);
           }
    }
    /**10/13/03: Reveal checks the values of the array at whatever row and column were passed
     * to it.  If the array is true there (meaning there is a mine there), reveal
     * returns a negative 1.  If the array is false there, reveal sweeps the surrounding
     * 8 squares, tallies the number of trues it finds, and returns that number.
     */
    int reveal(int r, int c){
      int mineCount = 0;
      if(mines[r][c] == true){
        mineCount = -1;
        return(mineCount);
      }else{
        for(int tr = r - 1; tr <= r + 1; tr++){
          for(int tc = c - 1; tc <= c + 1; tc++){
            if(tr >= 0 && tc >= 0 && tr <= fieldSize - 1 && tc <= fieldSize - 1 && mines[tr][tc] == true){
              mineCount++;
      }}}
        return(mineCount);
      }
    }

    /**
     * 10/8/03:<ul>
     * <li> The 1st loop to set every entry of the two-dimensional boolean array to
     * false.
     * <li> Gets the seed from the text field. Uses this seed to construct
     * a Random object.
     * <li> The 2nd loop will execute until the number of mines actually created
     * is equal to the nubmer of mines entered by the user.<ul>
     * <li> Generates a random row number (in the range 0 to fieldSize-1).
     * <li> Generates a random column number (in the range 0 to fieldSize-1).
     * <li> If the boolean entry in this [row][col] location is still false,
     * then: <ul><li> set the boolean entry in this [row][col] location to true
     * (which will designate this location as a mine),
     * and <li> increment the counter for the number of mines.</ul></ul>
     * <li> The 3rd loop will again sweep across the two-dimensional boolean
     *  array. At each [row][col] location:
     * <ul><li>If the boolean entry in this location is true, then:
     * <ul><li> Set the background color of the label corresponding to this
     * [row][col] location to red, and
     * <li> Set the text of the label corresponding to this
     * [row][col] location to "T"</ul>
     * <li> Otherwise:
     * <ul><li> Set the background color of the label corresponding to this
     * [row][col] location to another color, and
     * <li> Set the text of the label corresponding to this
     * [row][col] location to "F"</ul></ul></ul>
     * 10/13/03:<ul>
     * <li> If the action was received from the seedText box, then old notes apply
     * <li> If the action was received from from the rowText box, the focus is moved
     * to the colText box, which is then enabled.
     * <li> If the action comes from the colText box, reveal is called, with the
     * data in the rowText and colText boxes passed to it.  The values that reveal
     * returns is printed to the screen, along with the row and column position.</ul>
     * 10/15/03:<ul><li> actionPerformed does not do any of that colText, rowText, or
     * label array stuff anymore.
     * <li> When the action comes from seedText, actionPerfomed generates the mine
     * positions from the user entered seed.  Then it enables the button array,
     * and changes it's color (to show the user that it is ready to play).
     * <li> When the action comes from a button.<ul>
     * <li> The row and column information is passed from the MSButton to reveal.
     * <li> The results are printed to the screen. </ul></ul>
     */
    public void actionPerformed(ActionEvent event){
      if (event.getSource() == seedText) {
        for (int row = 0; row < fieldSize; row++) {
          for (int col = 0; col < fieldSize; col++) {
            mines[row][col] = false;
        }}
        long seed = Long.parseLong(seedText.getText());
        int mineCount = Integer.parseInt(mineCountText.getText());
        Random random = new Random(seed);
        int rrow = 0;
        int rcol = 0;
        for (int m = 0; m < mineCount; ) {
          rrow = random.nextInt(fieldSize);
          rcol = random.nextInt(fieldSize);
          if (mines[rrow][rcol] == false) {
            mines[rrow][rcol] = true;
            m++;
        }}
        for (int row = 0; row < fieldSize; row++)
          for (int col = 0; col < fieldSize; col++) {
            mineField[row][col].setEnabled(true);
            mineField[row][col].setBackground(Color.blue);
          }
        controlPanel.statusLabel = "Sweep!";
    }else{
        MSButton clicked = (MSButton)event.getSource();
        int cRow = clicked.row;
        int cCol = clicked.col;
        revealValue = reveal(cRow, cCol);
        System.out.println("Row = " + cRow + "\t\t" + "Col = " + cCol + "\t\t" + "Reveal = " + revealValue);
      }
    }
}
/** 10/15/03: MSButton in a button object that knows it's location in an array.*/
class MSButton extends Button
{
    int row;
    int col;
   public MSButton(int r, int c) {
     super();
     row = r;
     col = c;
   }
}

