package casinopackage;

import javax.swing.*;

public class Craps {

	// fields or instance variables declared here
	private Die theDie;
	private JTextArea dialog;
	private Player player;
	private int initialBalance;
	private int playAgain;

	// CONSTRUCTOR method
	public Craps(Player aPlayer, JTextArea d) {

		player = aPlayer;
		theDie = new Die();
		initialBalance = player.getBankBalance();
		dialog = d;
		dialog.append("Welcome to the Craps Game, " + player.getName() + "." + "\n");
		if (player.getBankBalance() > 0) {
			playAgain = 0;
			dialog.append("  Your initial balance is $" + player.getBankBalance() + "\n\n");
		} else {
			playAgain = 1;
			dialog.append("  Your initial balance is $" + player.getBankBalance() + "\n  Please try again when you have a positive balance.\n\n");
		}
	}

	// MUTATOR method
	public void play() {

		// Start the gaming session
		while (playAgain == 0) {

			// Initialize game variables
			int rollNum = 0;
			int rollResult = 0;
			int shootersPt = 0;
			boolean gameWon = false;
			boolean gameOver = false;

			// Start an individual game
			dialog.append("  Craps Game Number: " + (player.getNumberOfWins() + player.getNumberOfLosses() + 1) + ".\n");
			try {
				player.placeBet();
			} catch (RuntimeException exc) {
				dialog.append("  GAME CANCELLED\n");
				return;
			}
			dialog.append("    You bet: $" + player.getBet() + ".\n");

			// Run the game until it ends
			while (!gameOver) {
				rollResult = theDie.rollIt() + theDie.rollIt();
				// Rules for the first roll are different than subsequent rolls
				if (rollNum == 0) {
					if (rollResult == 2 || rollResult == 3 || rollResult == 12) {
						gameOver = true;
					} else if (rollResult == 7 || rollResult == 11) {
						gameWon = true;
						gameOver = true;
					} else {
						shootersPt = rollResult;
					}
				// Subsequent rolls
				} else {
					if (rollResult == 7) {
						gameOver = true;
					} else if (rollResult == shootersPt) {
						gameWon = true;
						gameOver = true;
					}
				}
				// Report the result of each roll
				dialog.append("    You rolled: " + rollResult + ".\n");
				rollNum++;
			}

			// Report the result of the game
			if (gameWon) {
				player.logAWin(player.getBet());
				dialog.append("    Yeah Baby! You won $" + player.getBet()
						+ ".\n");
				dialog.append("    Your new balance is $"
						+ player.getBankBalance() + "\n\n");
			} else {
				player.logALoss(player.getBet());
				dialog.append("    Nice going, loser! You just blew $"
						+ player.getBet() + ".\n");
				dialog.append("    Your new balance is $"
						+ player.getBankBalance() + "\n\n");
			}
			dialog.setCaretPosition(dialog.getDocument().getLength()); //Scroll textArea to bottom
			
			// Play again dialog
			if (player.getBankBalance() > 0) {
				playAgain = JOptionPane.showConfirmDialog(null,
						"Would you like to play again?", "More Cowbell?",
						JOptionPane.YES_NO_OPTION);
			// Stop play when bank balance is zero
			} else {
				playAgain = 1;
			}
		}
	}

	// ACCESSOR method
	public void showStats() {

		// If user had an initial balance and games were played, compute and display stats
		int totalPlays = player.getNumberOfWins() + player.getNumberOfLosses();
		if(initialBalance > 0 && totalPlays > 0) {
			String outcome = (player.getBankBalance() > initialBalance) ? "Won" : "Lost";
			dialog.append("  GAME OVER in " + totalPlays + " plays.\n");
			dialog.append("  Games Won: " + player.getNumberOfWins() + "\n");
			dialog.append("  Games Lost: " + player.getNumberOfLosses() + "\n");
			dialog.append("  You " + outcome + ": $" + Math.abs(player.getBankBalance() - initialBalance) + "\n");
			dialog.append(String.format("  Average Amount " + outcome + " Per Game: $%5.2f", (double) Math.abs(player.getBankBalance() - initialBalance)/totalPlays) + "\n\n");
			dialog.setCaretPosition(dialog.getDocument().getLength()); //Scroll textArea to bottom
		}
	}

}
