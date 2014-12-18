package casinopackage;

import java.util.*;
import javax.swing.*;

public class Player {

   private String  name;
   private int	 bankBalance;
   private int	 numberOfWins, numberOfLosses;
   private int	 betAmount;
   private boolean randomBettor;
   private Random  rGen;

   // constructor methods
   // 'true' sets "randomBettor" to true
   public Player(boolean bettorType) {

	  name = JOptionPane.showInputDialog("What is your name?");
	  boolean inputValid = false;

	  while (!inputValid) {

		 String bankString = JOptionPane.showInputDialog(name
			   + ", please enter your bank balance.");

		 try {

			bankBalance = Integer.parseInt(bankString);
			inputValid = true;

		 } catch (NumberFormatException exc) {

			JOptionPane.showMessageDialog(null, "invalid entry: " + bankString,
				  "Input Eror", JOptionPane.ERROR_MESSAGE);

			inputValid = false;
		 }
	  }

	  numberOfWins = 0;
	  betAmount = 0;
	  rGen = new Random();
	  randomBettor = bettorType;
   }

   /*
    * Constructor method -- no arg
    * queries the user for a name and a bankBalance
    */
   public Player() {
	  name = JOptionPane.showInputDialog("What is your name?");
	  boolean inputValid = false;

	  while (!inputValid) {

		 String bankString = JOptionPane.showInputDialog(name
			   + ", please enter your bank balance.");

		 try {
			bankBalance = Integer.parseInt(bankString);
			inputValid = true;

		 } catch (NumberFormatException exc) {
			JOptionPane.showMessageDialog(null, "invalid entry: " + bankString,
				  "Bank balance input error", JOptionPane.ERROR_MESSAGE);
			exc.printStackTrace();
			inputValid = false;
		 }
	  }
	  numberOfWins = 0;
	  betAmount = 0;
	  rGen = new Random();
	  /*
	   * String answer = JOptionPane.showInputDialog(null,
	   * "Do you want to place your own bets?"); if (answer.equals("yes"))
	   * randomBettor = false; else randomBettor = true;
	   */
   }

   /*
    * queries the user for a betAmount
    */
   public void placeBet() {
	  if (randomBettor)
		 betAmount = rGen.nextInt(bankBalance) + 1;
	  else {
		 String aBet = JOptionPane.showInputDialog(null, "Place your bet.");
		 betAmount = Integer.parseInt(aBet);
		 while (betAmount > bankBalance  || betAmount <=0 ) {
			aBet = JOptionPane.showInputDialog(null,
				  "Sorry.  That's too much. Place a legal bet.");
			betAmount = Integer.parseInt(aBet);
		 }
	  }
   }

   /*
    * increments the numberOfWins
    */
   private void incrementWins() {
	  numberOfWins++;
   }

   /*
    * increments the numberOfLosses
    */
   private void incrementLosses() {
	  numberOfLosses++;
   }

   /*
    * winAmount the amount won on this play of the game 
    * increments the numberOfWins
    * updates bankBalance by the value of winAmount
    */
   private void incrementWins(int winAmount) {
	  numberOfWins++;
	  bankBalance += winAmount;
   }

   /*
    * lossAmount the amount lost on this play of the game 
    * increments the numberOfLosses
    * decreases bankBalance by the value of lossAmount
    */
   private void incrementLosses(int lossAmount) {
	  numberOfLosses++;
	  bankBalance -= lossAmount;
   }

   /*
    * winAmount the amount won on this play of the game
    * updates bankBalance and the numberOfWins
    */
   public void logAWin(int winAmount) {

	  bankBalance += winAmount;
	  incrementWins();
   }

   /*
    * lossAmount the amount lost on this play of the game
    * updates bankBalance and the numberOfLosses
    */
   public void logALoss(int lossAmount) {

	  bankBalance -= lossAmount;
	  incrementLosses();
   }

   //************************************* accessor methods
   
   /*
    * @return this player's numberOfWins
    */
   public int getNumberOfWins() {
	  return numberOfWins;
   }

   /*
    * @return this player's numberOfLosses
    */
   public int getNumberOfLosses() {
	  return numberOfLosses;
   }

   /*
    * @return this player's name
    */
   public String getName() {
	  return name;
   }

   /*
    * @return this player's bankBalance
    */
   public int getBankBalance() {
	  return bankBalance;
   }

   /*
    * @return this player's current betAMount
    */
   public int getBet() {
	  return betAmount;
   }

}
