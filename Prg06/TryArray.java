import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


/** Copyright (c) 2001 Art Gittleman, modified by Mike Norton; 10/03/03, 10/06/03.
 * <br>
 * <ul><li> Functional Purpose:<ul><li> Searches an array for the first occurrence of a
 *  value input by the user. <li> 10/06/03: Puts ranbom numbers in the array,
 * from a user defined seed.</ul>
 * <li> Educational Purose: <ul><li> To introduce us to arrays. <li> 10/06/03:
 * To use a random number generator. </ul>
 * </ul>
*/


public class TryArray extends Applet implements ActionListener{
	int students = 0;
    int [] score;

    private Label targetLabel = new Label("Search for: ");
    private Label seedLabel = new Label("Seed: ");
    private Label numLabel = new Label("Number of Scores: ");
    private TextField targetText = new TextField(3);
    private TextField seedText = new TextField(5);
    private TextField numText = new TextField(5);

    private TextArea resultTextArea = new TextArea(6,30);
    private TextArea studentsArea = new TextArea(4,30);
    private Panel targetPanel = new Panel();
    private Panel resultPanel = new Panel();
    private Panel studentsPanel = new Panel();

	/**10/10/03: initializes the studentPanel and setupStudentsPanel method in stead 
	 * of the array stuff.
	 */
    public void init()
    {
      setupTargetPanel ();
      setupResultPanel ();
      setupStudentsPanel ();

      this.setLayout(new BorderLayout());
      this.add(targetPanel, BorderLayout.NORTH);
      this.add(resultPanel, BorderLayout.CENTER);
      this.add(studentsPanel, BorderLayout.SOUTH);
      setFont(new Font("Arial",Font.BOLD,12));
      this.setBackground(Color.lightGray);
    }
    
    /**10/11/03: setupTargetPanel adds the text fields and labels to the panel. 
     *Also it sets the focus in the numStudents text field, and disables all the others.
     */
    void setupTargetPanel ()
    {
      
      targetPanel.add(numLabel);
      targetPanel.add(numText);
      targetPanel.add(seedLabel);
      targetPanel.add(seedText);
      targetPanel.add(targetLabel);
      targetPanel.add(targetText);
      targetPanel.setBackground(Color.blue);
      numText.addActionListener(this);
      numText.setBackground(Color.cyan);
      numText.requestFocus();
      seedText.addActionListener(this);
      seedText.setBackground(Color.cyan);
      seedText.setEnabled(false);
      targetText.addActionListener(this);
      targetText.setBackground(Color.cyan);
      targetText.setEnabled(false);
    }

    /**10/10/03: changed background colors to orange and pink.
     */
     
    void setupResultPanel ()
    {
      resultTextArea.setText("Target not found yet");
      resultPanel.add(resultTextArea);
      resultPanel.setBackground(Color.orange);
      resultTextArea.setBackground(Color.pink);
    }

    /**setupStudentsPanel does the following:
     * <ul> <li> Implements the method that will set up the array panel.
     * <li> Gives the array panel grid layout with 1 row and 7 columns.
     * <li> Adds the k-th display field to the array panel.
     * <li> Sets the text of the text field.
     * </ul>
     * <br> 10/06/03: setupArrayPanel no longer:
     * <ul><li> Sets up the array with values from the 'scores' array.
     * <li> Formats the text in the array.
     * </ul>
     * <br> 10/10/03: Now adds studentsArea instead of all the array stuff.
     */
    public void setupStudentsPanel()
    {
      studentsPanel.add(studentsArea);
      studentsPanel.setBackground(Color.red);
      studentsArea.setBackground(Color.orange);
    }
    
    /**10/03/06: actionPerformed does the following:
     * <ul><li> Determines where data were entered.
     * <li> If if the data were from the seed text field, actionPerformed
     * converts the string in the seed field into long, and passes it to the
     * random number generator which generates 7 numbers, that are placed into
     * the text field array.
     * <li> If the data were from the target text feild, actionPreformed
     * searches the array for that number.  If it is found, its location is
     * printed to the screen.</ul>
     * <br> 10/11/03: actionPerformed now does the following:
     * <ul><li> Sets the size of the scores array to the number of students entered.
     * <li> Enables the seedText text field, disables the numText field, and
     * puts the focus in the seedText field.
     * <li> Prints a message with the number of students in the resultTextArea area.
     * <li> Takes the seed from the seedText and passes it to the random number 
     * generater, which creates random scores.
     * <li> The scores are then printed, next to thier index number, in the 
     * studentsArea area.
     * <li> SeedText field is then disabled, targetText is enabled, and the focus 
     * is moved to targetText.
     * <li> The valus in the targetText field is compaired against the 'score'
     * array.  All the locations that have that number are listed in the resultArea area.
     * <li> If 'scores' doesn't contain the value in targetText, an error message 
     * is displayed.
     * <li> The seedText field is then turned back on.
     * </ul>
     */
    public void actionPerformed(ActionEvent e) {
      int testValue = 0;
      long seed = 0;
      boolean found = false;
      
      if(e.getSource() == numText)
      { 
      	students = Integer.parseInt(numText.getText());
      	score = new int[students];
      	seedText.setEnabled(true);
      	numText.setEnabled(false);
        seedText.requestFocus();
      	resultTextArea.setText("Memory allocated for " + students + " students." + '\n' + "Please enter a seed.");
      }
      
      if(e.getSource() == seedText)
      {
        seed = Long.parseLong(seedText.getText());
        Random random = new Random(seed);
        for(int c= 0; c < students; c++)
        {
          score[c] = random.nextInt(101);
          studentsArea.append(c + "\t\t" + score[c] + '\n');
        }
        targetText.setEnabled(true);
        targetText.requestFocus();
        seedText.setEnabled(false);
      }
       
      if(e.getSource() == targetText)
      {        
        testValue = Integer.parseInt(targetText.getText());
        resultTextArea.setText("Results for " + testValue + ':' + '\n');
        for (int c = 0; c < score.length; c++)
        {
          if(testValue == score[c]){
            resultTextArea.append("Found score " + testValue + " at index " + c + '.' + '\n');
            found = true;
          }
        }
        if(found == false)
          resultTextArea.append("No students scored " + testValue + '.');
        seedText.setEnabled(true);
      }
    }
}