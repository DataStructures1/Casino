package casinopackage;

import javax.swing.*;

public class CrapsGame implements Game {
   
   private Player thisPlayer;

   public void play( Player p) {
	  thisPlayer = p;
	  JOptionPane.showMessageDialog(null, "Hi " + thisPlayer.getName() + ".  You are playing Craps!");
   }

}
