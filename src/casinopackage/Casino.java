package casinopackage;

import javax.swing.*;

public class Casino {
	
	private String myName;
	private static String[] buttonNames= {"Meyer's CrapsGame","Christina's ??",
										"Jarrett's ??", "Kalieb's ??",
										 " Maddie's ??", "Justin's ??",
										"Bridget's ??","Amar's ??","Chasen's ??", "Bea's ??",
										"Matt's ??","       " ,
										"Patrick's ??","Parker's ??","Charlie's ??", "Marshall's ??",
										"Derek's ??","Truman's ??", "Fidel's ??","Chase's ??","Paule's ??",
										"Shelly's ??","Michael's ??"
										};
	
	public Casino( String s ) {
		myName = s;			
	}
	
	public String getName() {
		return myName;
	}
	
	public String[] getGames() {
		
		return buttonNames;
	}
}
