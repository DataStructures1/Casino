package casinopackage;

import javax.swing.*;

//Mr. Meyer
//Craps Game
//October 30, 2011

public class CrapsMeyer {

   // fields or instance variables
   private int	   currRoll;
   private int	   pointRoll;
   private int	   totalPlays;
   private Die	   theDie;
   private JTextArea dialog;
   private Player	player;

   // constructor
   public CrapsMeyer(Player aPlayer, JTextArea d) {

	  player = aPlayer;
	  dialog = d;
	  dialog.append("Welcome to the Craps Table, " + player.getName() + "."
			+ "\n");
	  dialog.append("  Your initial balance is $" + player.getBankBalance()
			+ "\n");
	  theDie = new Die();
   }

   // mutator
   public void play() {

	  int playNum = 1;
	  while (player.getBankBalance() > 0) {
		 
		 dialog.append("\t\tPlay number " + playNum + "\n" );
		 player.placeBet();
		 dialog.append("You bet $" + player.getBet() + "\n" );
		 currRoll = theDie.rollIt() + theDie.rollIt();
		 dialog.append("Initial roll is a " + currRoll);

		 // losing roll
		 if (currRoll == 2 || currRoll == 3 || currRoll == 12) {
			dialog.append(" which is an automatic LOSS.\n");
			player.logALoss(player.getBet());
		 } else {

			// winning roll
			if (currRoll == 7 || currRoll == 11) {
			   dialog.append(" which is an automatic WIN.\n");
			   player.logAWin(player.getBet());
			} else {

			   // storing roll to point and rolling again
			   pointRoll = currRoll;
			   dialog.append("  so this is your point.\n");

			   dialog.append(" Subsequent rolls: ");

			   // loop to keep rolling until point or 7 is scored
			   do {
				  currRoll = theDie.rollIt() + theDie.rollIt();
				  dialog.append(currRoll + " ");
			   } while (currRoll != pointRoll && currRoll != 7);

			   if (currRoll == pointRoll) {
				  dialog.append("\nYou rolled your point.  That's a WIN.\n");
				  player.logAWin(player.getBet());

			   } else {
				  dialog.append("\nToo bad.  You rolled a 7 before you rolled a " + pointRoll + ".  That's a LOSS.\n");
				  player.logALoss(player.getBet());
			   }
			}
		 }
		 playNum++;
		 dialog.append("  New Balance: $" + player.getBankBalance() + "\n");
	  }
	  
   }

   // acccessor
   public void showStats() {

	  totalPlays = player.getNumberOfWins() + player.getNumberOfLosses();
	  dialog.append("GAME OVER in " + totalPlays + " plays.\n");
   }

}
