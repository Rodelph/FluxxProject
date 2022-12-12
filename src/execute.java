import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * @Setup
 * 
 *  - Place the basic rules card in the center
 *  - Shuffle the deck and deal three cards to each player
 *  - Place the remainder of the deck face down next to the basic rules next to
 *    the basic rules to form a draw pile
 *  - At the start of the game there will be no goal or new rule cards in play
 *    but they will be added as the game progresses
 *	-     
 *
 * @Card Types
 * 
 *  @Basic Rules (Card with orange stripes)@
 * 	- This is the starting point, the foundation on
 *    which the rest of the game is built.
 *  - These initial rules will be superseded by "new rules" during the course 
 *    of play, but this card should remain on the table at all times. The basic
 *    rules are :
 *    	- Draw 1 card per turn  
 *      - Play 1 card per turn (with no other restrictions such as a Hand or 
 *        Keeper Limits).
 *    
 *  @Goal (Pink cards)@
 *  - These cards show the specific Keepers you must have on the table in front of u
 *    in order to win.
 *  - To play a goal, place it face  up in the center of the table, discarding the 
 *    previous Goal (if any). The game begins with no goal in play, so no one can win
 *    until one is played. The Goal applies to everyone, as soon as someone meets 
 *    these conditions, they win ! (Even if it's someone else's turn).
 *    
 *  @Keeper (Green Cards)@
 *  - To play a Keeper, take it out of your hand and place it on the table in 
 *    front of you, face up. Most Goals require you to have a particular pair of
 *    Keepers so playing a Keeper is always a good thing.
 *
 *
 *  @HOW TO WIN@----------------------------------------------------*
 *                                                                  |
 *  The game continues until someone meets the conditions of the    |  
 *	current Goal. That player wins instantly, no matter whose turn  |
 *	it is !
 *																	|
 *  ----------------------------------------------------------------*     
 *    
 */


public class execute {

	public static void main(String[] args) throws FileNotFoundException
	{	
//		Deck d = new Deck();
//		d.createDeck();
//		
//	
//		
//		var card = d.getCard().getKeeperStack().firstElement();
//		System.out.println(card.getKeeperName());
		//getClass to get the class of the card 
		
//		Game game;
//		int numberofplayers = 0;
//		try(Scanner scan = new Scanner(System.in)) 
//		{
//			while (numberofplayers < 2 || numberofplayers > 6)
//			{
//				numberofplayers = scan.nextInt(); 
//			}
//			game = new Game(numberofplayers);
//
//			while(game.isGameRunning())
//			{
//				game.run(scan.nextInt());
//			}
//			
//			scan.close();
//		}
		

		Deck d = new Deck();
		
		for ( int i=0; i<109;i++)
		{
			System.out.println("Card number: "+i+" "+d.getDeckStack().get(i));
		}
	}
	
}