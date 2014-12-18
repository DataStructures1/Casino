package casinopackage;

/*************************
 * 
 *An interface is a template.  The template lists all methods that should be coded
 *   for any class that implements the interface.
 * 
 *In order to make sure that all games supply the same functionality
 *   we are creating this "interface" and requiring that every student's
 *   game class implement this interface.
 *
 *This is one of the ways a software designer guarantees that components coded
 *   by multiple people will fit together properly when assembled in to one solution.
 *
 **************************/

public interface Game {
   
   public void play( Player p );

}
