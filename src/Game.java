import java.util.ArrayList;
import java.util.Stack;

public class Game {
	
	private int numberOfPlayers, drawLimit,
				playLimit      , keeperLimit,
				handLimit;
	
	private Card card; 
	private ArrayList<Player> players;
	private Stack<Card> pile;
	private Deck deck;
	private ArrayList<Card> cardsOnTable;
	
	public Game(int _numOfPlayers)
	{
		this.numberOfPlayers = _numOfPlayers;
		this.drawLimit = 1;
		this.playLimit = 1;
		this.keeperLimit = 100;
		this.handLimit = 100;
	}
	
	public Card drawCard()
	{

		return null;
	}
	
	public Card throwCard()
	{

		return null;
	}
	
	public Card placeCard(Card _card)
	{
		
		return null;
	}

	
}
