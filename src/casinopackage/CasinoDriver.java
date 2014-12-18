package casinopackage;

import javax.swing.*;

public class CasinoDriver {

   /**
    * This program generates a user interface for playing several
    * student-generated casino games.
    */
   public static void main(String[] args) {
	  
	  Casino paradice = new Casino("Paradice Casino");
	  CasinoFrame userInterface = new CasinoFrame( paradice );
	  
   }

}
