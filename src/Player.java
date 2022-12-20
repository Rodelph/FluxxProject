import java.util.ArrayList;

public class Player {
	
	private String playerName;
	private ArrayList<Keepers> keepersOnSideTable;
	private ArrayList<Card> cardsInHand;
	
	/**
	 * Constuctor that will take a string as the player name, and initializes the ArrayList for
	 * keepers on his side, and his hand.
	 * 
	 * @param _playerName
	 */
	public Player(String _playerName)
	{
		this.playerName = _playerName;
		this.keepersOnSideTable = new ArrayList<Keepers>();
		this.cardsInHand = new ArrayList<Card>();
	}
	
	/**
	 * Prints the cards in the hand of the player.
	 */
	public void showHand()
	{
		System.out.println(Utility.ANSI_RED + "\nYour hand contains the follwing card(s)" + Utility.ANSI_RESET);
		
		for(int i = 0 ; i < this.cardsInHand.size() ; i++)
		{
			if(this.cardsInHand.get(i).getClass() == Rules.class)
			{
				Rules rule = (Rules) this.cardsInHand.get(i);
				System.out.printf(Utility.ANSI_YELLOW + "%d - %s\n" + Utility.ANSI_RESET,i, rule.getName());
			}
			else if(this.cardsInHand.get(i).getClass() == Keepers.class)
			{
				Keepers keeper = (Keepers) this.cardsInHand.get(i);
				System.out.printf(Utility.ANSI_GREEN + "%d - %s\n" + Utility.ANSI_RESET,i, keeper.getName());
			}
			else if(this.cardsInHand.get(i).getClass() == Goals.class)
			{
				Goals goal = (Goals) this.cardsInHand.get(i);
				System.out.printf(Utility.ANSI_PURPLE + "%d - %s\n" + Utility.ANSI_RESET,i, goal.getName());
			}
		}
	}
	/**
	 * Prints the keepers on the side of the player
	 */
	public void showKeepers()
	{
		System.out.println(Utility.ANSI_RED + "\nYour posses the following Keeper(s)" + Utility.ANSI_RESET);
		
		if(this.keepersOnSideTable.isEmpty())
		{
			System.out.println(Utility.ANSI_GREEN + "You have no keepers on your side at the moment !" + Utility.ANSI_RESET);
		}
		else
		{
			for(int i = 0 ; i < this.keepersOnSideTable.size() ; i++)
			{
				Keepers keep = this.keepersOnSideTable.get(i);
				System.out.printf(Utility.ANSI_GREEN + "%d - %s\n" + Utility.ANSI_RESET,i, keep.getName());
			}
		}
	}
	
	/**
	 * 
	 * @param index :: Input from the player (Scanner) that choses the index of the cards.
	 * 
	 * @return The card wanted to be played.
	 */
	public Card playCard(int index)
	{
		Card cacheCard = this.cardsInHand.get(index);
		this.cardsInHand.remove(index);
		
		return cacheCard;
	}
	
	/**
	 * @return How many cards in the player's hand.
	 */
	public int handSize() { return this.cardsInHand.size(); }

	/**
	 * @return The name of the player.
	 */
	public String getPlayerName() { return playerName; }

	/**
	 * @return Keepers as ArrayList on the side of the player.
	 */
	public ArrayList<Keepers> getKeepersOnTable() { return keepersOnSideTable; }

	/**
	 * @return Cards in had as ArrayList possessed by the player.
	 */
	public ArrayList<Card> getCardsInHand() { return cardsInHand; }	
}