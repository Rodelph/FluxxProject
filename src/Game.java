import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

public class Game {
	
	enum GameState 
	{
		gettingInfo,
		runGame
	}
	
	private GameState game;
	
	private boolean gameRunning;
	
	private int numberOfPlayers, drawLimit,
				playLimit      , keeperLimit,
				handLimit;
	
	private ArrayList<Player> players;
	private Stack<Card>       pile;
	private Deck              deck;
	private ArrayList<Card>   cardsOnTable;
	
	public Game(int _userInput) throws FileNotFoundException
	{	
		this.deck = new Deck();
		this.players = new ArrayList<Player>(); 
		this.numberOfPlayers = _userInput;	
		
		for(int i = 0 ; i < this.numberOfPlayers ; i++)
		{
			this.players.add(new Player("Player_" + (i + 1)));
		}
		this.drawLimit   = 1;
		this.playLimit   = 1;
		this.keeperLimit = 100;
		this.handLimit   = 100;
		this.gameRunning = true;
	}
	
	public Card drawCard()
	{
		return this.deck.getDeckStack().pop();
	}
	
	public void throwCard(Card _card)
	{
		this.pile.add(_card);
	}
	
	public Card placeCard(Card _card)
	{
		
		return null;
	}
	
	public void run(int _userInput)
	{
		this.players.get(0).getCardsInHand().add(drawCard());
		this.players.get(0).showHand();
		//drawCard();
	}

	public int getNumberOfPlayers() {
		return this.numberOfPlayers;
	}

	public boolean isGameRunning() {
		return this.gameRunning;
	}
}