/**
 * Mike Norton; 09/12/2003. Modified by Mike Norton 09/21/03.
 * <br>
 * Functional purpose:
 * <>
 * Educational purpose:
 * <ul>
 * <li>
  * <li>
  * </ul>
 * <br><br>
  */
class Student
{
   private String name;
   private int score ;
   private static String nameChamp;
   private static int scoreChamp =0;
   private static String nameAntiChamp;
   private static int scoreAntiChamp = 10000;

   public Student(String iname, int iscore) {
     name = iname;
     score = iscore;

     }

   /** If this student's score is better than the champion's score,
   * then:
   * <ul>
   * <li>
   * This student's name will be assigned to the champion's name
   * <li>
   * This student's score will be assigned to the champion's score
   * </ul> */
	public void updateChamp() {
      if(score > scoreChamp){
      nameChamp = name;
      scoreChamp = score;
      }
    }
   /** If this student's score is lower than the anti-champion's score,
   * then:
   * <ul>
   * <li>
   * This student's name will be assigned to the anti-champion's name
   * <li>
   * This student's score will be assigned to the anti-champion's score
   * </ul> */
	public void updateAntiChamp() {
      if(score < scoreAntiChamp){
      nameAntiChamp = name;
      scoreAntiChamp = score;
      }
    }

    
    /**A method to pass the champ's name back to main.*/
    public static String getChampName(){
      return nameChamp;
    }
    /**A method to pass the value of the champ's score back to main.*/
    public static int getChampScore(){
      return scoreChamp;
    }
    /**A method to pass the anti-champ's name back to main.*/
    public static String getAntiChampName(){
    	return nameAntiChamp;
    }
    /**A method to pass the value of the anti-champ's score back to main.*/
    public static int getAntiChampScore(){
    	return scoreAntiChamp;
    }

}



