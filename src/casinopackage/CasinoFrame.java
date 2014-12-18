package casinopackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

////////////////  GUI ELEMENTS  //////////////////////
public class CasinoFrame extends JFrame {
   private JButton[]   gameButtons;
   private JButton	 newPlayerButton, quitButton;
   private JScrollPane registerPane;
   private JTextArea   playersDialog;
   private Casino	  thisCasino;
   private String[]	buttonNames;
   private Player	  thePlayer;

   public CasinoFrame(Casino c) {
	  super("Welcome to  " + c.getName());
	  thisCasino = c;
	  JOptionPane.showMessageDialog(null, "Welcome to " + thisCasino.getName()
			+ "!", "Gambler's Interface", JOptionPane.INFORMATION_MESSAGE);
	  thePlayer = new Player();

	  ButtonHandler bHandler = new ButtonHandler();
	  buttonNames = thisCasino.getGames();

	  JPanel topPanel = new JPanel();
	  newPlayerButton = new JButton("	"); // "New Player"
	  newPlayerButton.addActionListener(bHandler);
	  topPanel.add(newPlayerButton);

	  JPanel middlePanel = new JPanel();
	  middlePanel.setLayout(new GridLayout(12, 2));

	  gameButtons = new JButton[buttonNames.length];
	  for (int i = 0; i < buttonNames.length; i++) {
		 gameButtons[i] = new JButton(buttonNames[i]);
		 gameButtons[i].addActionListener(bHandler);
		 middlePanel.add(gameButtons[i]);
	  }

	  JPanel bottomPanel = new JPanel();
	  quitButton = new JButton("QUIT");
	  quitButton.addActionListener(bHandler);
	  bottomPanel.add(quitButton);

	  playersDialog = new JTextArea("\t***    " + thePlayer.getName()
			+ "'s  Account    ***\n");
	  playersDialog
			.append("Bank balance: " + thePlayer.getBankBalance() + "\n");

	  registerPane = new JScrollPane(playersDialog);

	  JPanel buttonPanels = new JPanel();
	  buttonPanels.add(topPanel);
	  buttonPanels.add(middlePanel);
	  buttonPanels.add(bottomPanel);

	  getContentPane().setLayout(new GridLayout(1, 2));
	  getContentPane().add(buttonPanels);
	  getContentPane().add(registerPane);

	  setSize(new Dimension(800, 500));
	  setLocation(600, 150);
	  setVisible(true);

   }

   public class ButtonHandler implements ActionListener {
	  int choice = -1;

	  public void actionPerformed(ActionEvent event) {

		 if (event.getSource() == newPlayerButton) {

			thePlayer = new Player();
			playersDialog.append("\t***    " + thePlayer.getName()
				  + "'s  Account    ***\n");
			playersDialog.append("Bank balance: " + thePlayer.getBankBalance()
				  + "\n");
			JOptionPane.showMessageDialog(null,
				  "Welcome to " + thisCasino.getName() + "!",
				  "Gambler's Interface", JOptionPane.INFORMATION_MESSAGE);

		 } else if (event.getSource() == quitButton) {

			playersDialog.append("Final balance: " + thePlayer.getBankBalance()
				  + "\n\n\n");
			playersDialog.append("\t***  " + thePlayer.getName()
				  + "'s  Account    CLOSED ***\n");
			JOptionPane.showMessageDialog(
				  null,
				  "Hope you had a great round.  \nFarewell from "
						+ thisCasino.getName() + ". \n\t Come again soon!",
				  "Customer Interface", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);

		 } else if (event.getSource() == gameButtons[0]) {
			CrapsMeyer game0 = new CrapsMeyer(thePlayer, playersDialog);
			game0.play();
			game0.showStats();
		 }
		 /*
		  * else {
		  * 
		  * for (int i = 0; i < buttonNames.length; i++) {
		  * 
		  * if (event.getSource() == gameButtons[i]) { choice = i; break; } }
		  * 
		  * if (choice >= 0 && choice < buttonNames.length) switch (choice) {
		  * 
		  * case 0: ChuckOLuckHernandez game0 = new ChuckOLuckHernandez(
		  * thePlayer, playersDialog); game0.play(); game0.showStats(); break;
		  * case 1: CrapsBrown game1 = new CrapsBrown(thePlayer, playersDialog);
		  * game1.play(); game1.showStats(); break; case 2: OverUnderHarthorn
		  * game2 = new OverUnderHarthorn(thePlayer, playersDialog);
		  * game2.play(); game2.showStats(); break; case 3: OverUnderJordan
		  * game3 = new OverUnderJordan(thePlayer, playersDialog); game3.play();
		  * game3.showStats(); break; case 4: OverUnderKim game4 = new
		  * OverUnderKim(thePlayer, playersDialog); game4.play();
		  * game4.showStats(); break; case 5: OverUnderMin game5 = new
		  * OverUnderMin(thePlayer, playersDialog); game5.play();
		  * game5.showStats(); break; case 6: OverUnderWoolf game6 = new
		  * OverUnderWoolf(thePlayer, playersDialog); game6.play();
		  * game6.showStats(); break; case 7: OverUnderZhong game7 = new
		  * OverUnderZhong(thePlayer, playersDialog); game7.play();
		  * game7.showStats(); break; case 8: RouletteAmmons game8 = new
		  * RouletteAmmons(thePlayer, playersDialog); game8.play();
		  * game8.showStats(); break; case 9: CrapsKumana game9 = new
		  * CrapsKumana(thePlayer, playersDialog); game9.play();
		  * game9.showStats(); break;
		  * 
		  * }
		  * 
		  * }
		  */
	  }

   }
}
