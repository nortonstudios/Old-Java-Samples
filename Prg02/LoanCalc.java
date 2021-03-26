import javax.swing.*;
import java.text.*;

/** Author Mike Norton;  09/08/2003 Modified by Mike Norton; 09/17/2003.
 * <ul>
 * <li>   Educational purpose: To create a class that will contain a loop, a
 * constructor, a "cruncher" method, and a  main method.  To pass a boolean 
 * value with the "cruncher" method, and to work with boolean values.
 * <li>   Functional purpose: To take a principal loan, an interest rate, and a
 * monthly payment from a user, and determine the number of monthly payments
 * it will take to fully pay off the loan.  The program will also display an 
 * error message if the first month's payment will not cover intrest. 
 * </ul>
 */
public class LoanCalc
{
   private float principal;
   private float payAmount;
   private float interest;
   private int payments;

/** Constructor Method
    * <ul>
    * <li> The constructor method stores the user entered data into the data's
    * respective variables.
    * </ul> */
   public LoanCalc(float prStart, float paStart, float iStart){
     principal = prStart;
     payAmount = paStart;
     interest = iStart;
    }
/** Cruncher Method
     * <ul>
     * <li> The cruncher method uses the user defined variables.  First, the
     * payment counter, payments, is set to zero.  Then a while loop is
     * established. Each cycle through the loop calculates the monthly interest
     * made by the account, and increments the "payments" counter.  The cruncher
     * calculates by dividing the yearly interest, interest, by 12 to convert it
     * into monthly interest, then divides the interest again by 100 to convert
     * the percent into decimal form.  Cruncher then multiplies the interest by
     * the variably "principal" which is the initial principal on the loop's
     * first pass, or is the last month's principal on all subsequent passes.
     * Cruncher then subtracts the interest generated in that month from the
     * monthly payment "payAmount."  In that way, the interest is accounted for.
     * Cruncher then subtracts the monthly payment, minus interest, from the
     * previous principal, and stores the value as the new principal. Payments()
     * then determines whether the initial payment will cover the intrest on the
     * first month, if so, payments() returns true, if not, payments() returns
     * false. 
     * </ul>*/
   public boolean payments()
   {
     payments = 0;
     if(principal * (interest / 1200) < payAmount){
     	while(principal + (principal * (interest / 1200)) > payAmount)
     	{
       		principal = principal - (payAmount - (principal * (interest / 1200)));
       		payments++;
    	}}
    if(principal < payAmount)
    	return true;
    else
    	return false;
     }
   
/** Main Method does the following:
    * <ul>
    * <li> Prompts the user for the initial principal, monthly payment, and
    * annual percentage rate.
    * <li> Converts the string data received from the user into float data, and
    * passes it to the LoanCalc object "loan."
    * <li> Formats the value in loan.principal to two decimal places, and prints
    * it, along with the value in loan.interest.
    * <li> Calls the cruncher method.
    * <li> If the monthly payment is enought to cover the first month's intrest,
    * it prints both the number of payments necessary to pay of the loan, and
    * the amount of each payment, formatted to two decimal places.
    * <li> If the monthly payment will not cover the first month's intrest, main 
    * prints an error message.
    * </ul>
    * */
   public static void main (String[] args)
   {
      System.out.println("Output by Mike Norton");
      NumberFormat cf = NumberFormat.getCurrencyInstance();

      String p = JOptionPane.showInputDialog("Initial Principal?");
      String a = JOptionPane.showInputDialog("Monthly Payment?");
      String i = JOptionPane.showInputDialog("Annual Percentage Rate?");
      boolean check = false;

      LoanCalc loan = new LoanCalc(Float.parseFloat(p), Float.parseFloat(a), Float.parseFloat(i));

      System.out.println("With an Initial Principal of " + cf.format(loan.principal));
      System.out.println("And at a fixed APR of " + loan.interest + '%');

      check = loan.payments();	  
	  
	  if(check){
	  	System.out.println("The loan will be paid off after " + loan.payments + " monthly payments");
    	System.out.print("Of " + cf.format(loan.payAmount) + ", plus a final payment of ");
		System.out.println(cf.format(loan.principal));
   		}else{
   			System.out.print("A payment of " + cf.format(loan.payAmount));
   			System.out.println(" will not cover first month's interest.");
   		}
   		System.out.println("End of Output");
    	System.exit(0);
}}



