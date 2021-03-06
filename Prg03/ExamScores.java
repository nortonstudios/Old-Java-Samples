import javax.swing.*;
import java.text.*;
/**
 * Mike Norton, 09/12/2001. Modified by Mike Norton 09/21/03.
 * <br>
 * Functional purpose: To recive a list of names and scores from the user, then
 * diplay the names and scores of the best and worst students.  Also to display
 * the average of the scores entered.
 * <br>
 * Educational purpose:
 * <ul>
 * <li> To use a loop.
 * <li> To use multilpe classes in one program.
 * </ul>*/
public class ExamScores
{
  /**Main does the following:
   * <br><ul>
   * <li>
   */

  public static void main(String[] args){
    System.out.println("Output by Mike Norton");
    String n = JOptionPane.showInputDialog("Please enter a student's name.\n(Press Enter to quit)");
    if(n.length()>0){
      int count=0;
      float av=0;
      boolean c = true;
      while(c==true){
        if(c==true){
          String s = JOptionPane.showInputDialog("Please enter the student's score.");
          int scoreTemp;
	  scoreTemp = Integer.parseInt(s);
    	  Student student1 = new Student(n, scoreTemp);
          student1.updateChamp();
    	  student1.updateAntiChamp();
      	  System.out.print("Name: " + n);
	  System.out.println("  Score: " + scoreTemp);
	  av = av + scoreTemp;
	  count++;
        }
        n = JOptionPane.showInputDialog("Please enter a student's name.\n(Press Enter to quit)");
    	if(n.length()==0){
          c = false;
	  }
        }
      System.out.print("The Champion is: ");
      System.out.print(Student.getChampName());
      System.out.print(", with a high score of: ");
      System.out.println(Student.getChampScore());

      System.out.print("The Anti-Champion is: ");
      System.out.print(Student.getAntiChampName());
      System.out.print(", with a low score of: ");
      System.out.println(Student.getAntiChampScore());

      av = av / count;
      NumberFormat nf = NumberFormat.getNumberInstance();
      nf.setMaximumFractionDigits(2);
      System.out.println("The avarage score is: " + nf.format(av));
      }else{
   	System.out.println("No Data Entered");
   	}

    System.out.println("End of output");
    System.exit(0);
    }
}


