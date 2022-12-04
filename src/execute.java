import java.io.FileNotFoundException;
import java.util.Stack;

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
 *  @New Rules (Yellow cards)@
 *  - To play a new rule, place it face up near the basic rules. If it contradicts
 *    a new rule already in play; discard the old rule. New rules take effect
 *    immediately, so all players must follow the new Rule as required. 
 *  - This will often cause the player whose turn it is to draw or play additionnal 
 *    card right away, or it may cause other players to immediately discard 
 *    some of their cards.
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
 *  @Action (Blue Cards)@
 *  - Actions are used once and discarded. Just do whatever the card says, then
 *    place it on the discard pile. Actions can sometimes cause major chaos, and
 *    yet at other times have no effect at all.
 *  - Note that while some Actions may cause additionnal cards to be played
 *	  everything that happens as a result of an action card is considered part of
 *	  one play.
 *
 *  @HOW TO WIN@----------------------------------------------------*
 *                                                                  |
 *  The game continues until someone meets the conditions of the    |  
 *	current Goal. That player wins instantly, no matter whose turn  |
 *	it is! (If a tie, the game continues until one winner emerges.) |
 *																	|
 *  ----------------------------------------------------------------*     
 *    
 */


public class execute {

	public static void main(String[] args) throws FileNotFoundException
	{	
		Deck d = new Deck();
		d.createDeck();
		
	
		
		Keepers keep =  (Keepers) d.getDeckStack().firstElement();
		System.out.println(keep.getKeeperName());
		
		//getClass to get the class of the card 
		

	}
}