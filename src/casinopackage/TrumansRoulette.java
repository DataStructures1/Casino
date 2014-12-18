package casinopackage;
import javax.swing.*;
	/*
	 * This is roulette!
	 * My rules are you can bet on a specific number, and get payed back 35 to 1.
	 * There is a 0, but there is no 00
	 * 0 does not count as an even, nor a multiple of any number
	 * You can bet on evens or odds and get payed 2 to 1
	 * You can bet on a multiple of any number, and get payed back accordingly
	 * (multiples of 8 gets 8 to 1, multis of 5 gets 5 to 1, etc etc.)
	 * You can bet on 1-18 or 19-36 and get payed 2 to 1
	 */
public class TrumansRoulette {
   // fields or instance variables declared here
   private Die rouletteWheel;
   private JTextArea dialog;
   private Player	player;
   private int betAmount;
   private int wagerNumber;
   private int result;
   
   public TrumansRoulette(Player aPlayer, JTextArea d) {
	  player = aPlayer;
	  dialog = d;
	  rouletteWheel= new Die(0, 36);
	  dialog.append("Welcome to Truman's Single-Zero Roulette, " + player.getName() + "!"
			+ "\n");
	  dialog.append("\tYour initial balance is $" + player.getBankBalance()
			+ "\n");  
   }
   
   // MUTATOR method
   public void play() {
	  int betChoice;
	  boolean hasWin=false;
	   do{
		   String betChoiceString= JOptionPane.showInputDialog(null, "Which bet would you like to make?" +
			   		"\nOdds=1" +
			   		"\nEvens=2" +
			   		"\n1-18=3" +
			   		"\n19-36=4" +
			   		"\nSpecific number=5" +
			   		"\nSpecific multiple=6");
		   try{
			   betChoice=Integer.parseInt(betChoiceString);
		   }catch(Exception e){
			   betChoice=0;
			   JOptionPane.showMessageDialog(null, "You did not enter a valid integer, please try again.");
		   }
	   }while(!(betChoice>=1&&betChoice<=6));
	   player.placeBet();
	   betAmount=player.getBet();
	   result=rouletteWheel.rollIt();
	   if(betChoice==1){    								//bet on odds
		   if(result%2==1){
			   player.logAWin(betAmount);
		   		dialog.append(result+" was rolled, which is odd so you won!\n");
		   		hasWin=true;
		   }else{
			   player.logALoss(betAmount);
			   dialog.append(result+" was rolled, which is not odd so you lost.\n");
			   hasWin=false;
		   }
	   }
	   else if(betChoice==2){								//bet on evens
		   if(result%2==0&&result!=0){
			   player.logAWin(betAmount);
			   dialog.append(result+" was rolled, which is even so you won!\n");
			   hasWin=true;
		   }else{
			   player.logALoss(betAmount);
			   dialog.append(result+" was rolled, which is not even so you lost\n");
			   hasWin=false;
		   }
	   }
	   else if(betChoice==3){								//bet on 1-18
		   if(result<=18&&result!=0){
			   player.logAWin(betAmount);
			   dialog.append(result+" was rolled, which is between 1 and 18 so you won!\n");
			   hasWin=true;
		   }else{
			   player.logALoss(betAmount);
			   dialog.append(result+" was rolled, which is not between 1 and 18 so you lost.\n");
			   hasWin=false;
		   }
	   }
	   else if(betChoice==4){								//bet on 19-36
		   if(result>=19){
			   player.logAWin(betAmount);
			   dialog.append(result+" was rolled, which is between 19 and 36 so you won!\n");
			   hasWin=true;
		   }else{
			   player.logALoss(betAmount);
			   dialog.append(result+" was rolled, which is not between 19 and 36 so you lost.\n");
			   hasWin=false;
		   }
	   }
	   else if(betChoice==5){								//bet on specific number
		   for(boolean b=false; !b;){
			   	String input=JOptionPane.showInputDialog(null, "What number do you want to bet on?");
			  	try{
				  	b=true;
				  	wagerNumber=Integer.parseInt(input);
			  	}catch(NumberFormatException nfe){
				  	JOptionPane.showMessageDialog(null, "You did not enter a valid integer, please try again.");
				  	b=false;
				  	wagerNumber=-1;
			  	}
			  	b =!(wagerNumber<0||wagerNumber>36);
		   	}
		   if (result==wagerNumber){
			   player.logAWin(betAmount*35);
			   dialog.append(result+" was rolled, which is indeed "+wagerNumber+" so you won!\n");
			   hasWin=true;
		   }else{
			   player.logALoss(betAmount);
			   dialog.append(result+" was rolled, which is not "+wagerNumber+" so you lost.\n");
			   hasWin=false;
		   }
	   }
	   else if(betChoice==6){							//bet on multiple
		   for(boolean b=false; !b;){
			   	String input=JOptionPane.showInputDialog(null, "What multiple do you want to bet on?");
			  	try{
				  	b=true;
				  	wagerNumber=Integer.parseInt(input);
			  	}catch(NumberFormatException nfe){
				  	JOptionPane.showMessageDialog(null, "You did not enter a valid integer, please try again.");
				  	b=false;
				  	wagerNumber=-1;
			  	}
			  	b =!(wagerNumber<1||wagerNumber>36);
		   	}
		   if(result%wagerNumber==0&&result!=0){
			   player.logAWin(betAmount*wagerNumber);
			   dialog.append(result+" was rolled, which is indeed a multiple of "+wagerNumber+" so you won!\n");
			   hasWin=true;
		   }
		   else{
			   player.logALoss(betAmount);
			   dialog.append(result+" was rolled, which is not a multiple of "+wagerNumber+" so you lost.\n");
			   hasWin=false;
		   }
	   }
	   if(hasWin)
		   if(betChoice<=4)
			   dialog.append("You won $"+betAmount+"!\n");
		   else if(betChoice==5)
			   dialog.append("You won $"+betAmount*35+"!\n");
		   else
			   dialog.append("You won $"+betAmount*wagerNumber+"!\n");
	   else
		   dialog.append("You lost $"+betAmount+".\n");
   }
   
   //ACCESSOR method
   public void showStats() {
	  int totalPlays = player.getNumberOfWins() + player.getNumberOfLosses();
	  //many more stats need to be computed and reported  here.
	  dialog.append("GAME OVER in " + totalPlays + " plays.\n");
   }
}