package chatbotProject;

import java.util.Scanner;

public class ChatbotMain {
	
	public static Chatbot chatbot = new Chatbot();
	//static is independent of instances
	//able to access the state of the program everywhere in the program through variable

	public static void main(String[] args) {
		chatbot.startTalking();

	}
		//pasted from class notes
	private static Scanner inputSource = new Scanner(System.in);
	
	  /**
	   * This method returns the index of keyword in the searchString (after startPsn)
	   * where keyword is isolated and has noNegations.
	   * It returns -1 if the keyword is not found.
	   */
	  
	  public static int findKeyword(String searchString, String keyword, int startPsn) {
		  searchString = searchString.toLowerCase();
		  keyword = keyword.toLowerCase();
		  //find first position after startPsn
		  int psn = searchString.indexOf(keyword, startPsn);
		  
		  //keep searching until keyword is found (noNegations and isolated)
		  while(psn >= 0) {
			  if(keywordIsIsolated(psn, keyword, searchString) && noNegations(searchString, psn))
				  return psn;
			  else {
				  //look for next occurrence
				  psn = searchString.indexOf(keyword, psn + 1);
			  }
		  }
		  return -1;
	  }
	  
	  public static boolean keywordIsIsolated(int psn, String keyword, String s){
		  String a = "a";
		if((psn + keyword.length() == s.length()) && !(psn==0))
			return (a.compareTo(s.substring(psn - 1, psn)) > 0);
		else if(psn == 0)
			return (a.compareTo(s.substring(psn + keyword.length())) > 0);
			else if((a.compareTo(s.substring(psn - 1, psn)) > 0) && (a.compareTo(s.substring(psn + keyword.length(), psn + keyword.length() + 1))) > 0)
				return true;
			else
				return false;
	  }
	  
	  public static boolean noNegations(String s, int psn){
			if(psn == 0 || psn-4 < 0) {
				return true;
			}
			else{
				if(("not".equals(s.toLowerCase().substring(psn-4,psn-1))) || ("no".equals(s.toLowerCase().substring(psn-3,psn-1))))
					return false;
				else
					return true;
			}
		}
	  
	  
	  public static String getInput(){
	    return inputSource.nextLine();
	  }
	  
	  public static void print(String s){
	    multiLinePrint(s);
	  }
	  
	  public static void multiLinePrint(String s){
	    String printString = "";
	 int cutoff = 55;
	 //this while loop last as long as there are words left in the original String
	 while(s.length() > 0){

	 String currentCut = "";
	 String nextWord = "";

	 //while the current cut is still less than the line length 
	 //AND there are still words left to add
	 while(currentCut.length()+nextWord.length() < cutoff && s.length() > 0){

	 //add the next word
	 currentCut += nextWord;
	 
	 //remove the word that was added from the original String
	 s = s.substring(nextWord.length());
	 
	 //identify the following word, exclude the space
	 int endOfWord = s.indexOf(" ");

	 //if there are no more spaces, this is the last word, so add the whole thing
	 if(endOfWord == -1) {
	 endOfWord = s.length()-1;//subtract 1 because index of last letter is one les than length
	 }

	 //the next word should include the space
	 nextWord = s.substring(0,endOfWord+1);
	 }

	 printString +=currentCut+"\n";

	 }
	 System.out.print(printString);
	  }

	 public static int getIntegerInput() {
	 print("Please enter an integer.");
	 String integerString = getInput();
	 boolean isInteger = false;
	 int value = 0;
	 while(!isInteger){
	 try{
	 value = Integer.parseInt(integerString);
	 //will not continue if an error above is thrown
	 isInteger = true;//exits loop if entry is valid
	 }catch(NumberFormatException e){
	 print("You must enter an integer. You better try again.");
	 integerString = getInput();
	 }
	 }
	 return value;
	 }
}
