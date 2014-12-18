package casinopackage;

public class Die {

   private int numSides;
   private int lowNum;
   private int highNum;

   public Die() {
	  numSides = 6;
	  lowNum = 1;
	  highNum = 6;
   }

   public Die(int s) {
	  numSides = s;
	  lowNum = 1;
	  highNum = s;
   }

   public Die(int a, int b) {
	  numSides = Math.max(a, b) - Math.min(a, b) + 1;
	  lowNum = Math.min(a, b);
	  highNum = Math.max(a, b);
   }

   public int rollIt() {
	  return lowNum + (int) (numSides * Math.random());
   }

}
