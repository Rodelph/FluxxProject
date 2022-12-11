import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

@SuppressWarnings("unused")
public class Game {
	
	enum GameState 
	{
		DrawPhase,
		ChoseCardPhase,
		PlayPhase,
		EndTurn
	}

	private GameState game;
	
	private boolean gameRunning;
	
	private int numberOfPlayers, drawLimit,
				playLimit      , keeperLimit,
				handLimit;
	
	private ArrayList<Player> players;
	private Stack<Card>       pile;
	private Deck              deck;
	private Goals             goalToAchieve;
	
	public Game(int _userInput) throws FileNotFoundException
	{	
		this.deck            = new Deck();
		this.players         = new ArrayList<Player>();
		this.pile 			 = new Stack<Card>();
		this.numberOfPlayers = _userInput;	
		
		for(int i = 0 ; i < this.numberOfPlayers ; i++)
			this.players.add(new Player("Player_" + (i + 1)));
		
		this.drawLimit   = 1;
		this.playLimit   = 1;
		this.keeperLimit = 100;
		this.handLimit   = 100;
		this.gameRunning = true;
	}
	
	// Draws a card from the deck stack
	public Card drawCard() { return this.deck.getDeckStack().pop(); }
	
	// Players throws a card and adds it to the pile
	public void throwCard(Card _card) { this.pile.add(_card); }
	
	// Places goal and rule cards
	// We keep the goal card object as a variable
	// We check what kind of rule type is it and change the rules accordingly
	public void placeCard(Card _card)
	{
		if(_card.getClass() == Goals.class)
		{
			this.goalToAchieve = (Goals)_card;
		}
		else if(_card.getClass() == Rules.class)
		{
			Rules rule = (Rules)_card;

			var ruleType = Card.RuleType.valueOf(rule.getRuleType());
			
			switch(ruleType)
			{
				case drawLimitType:
					this.drawLimit = rule.getDrawLimit();
					break;
				
				case keeperLimitType:
					this.keeperLimit = rule.getKeeperLimit();
					break;
				
				case handLimitType:
					this.handLimit = rule.getHandLimit();
					break;
				
				case playLimitType:
					this.playLimit = rule.getPlayLimit();
					break;
			}
		}
	}
	
	// Game loop
	public void run(int _userInput)
	{
		this.players.get(0).getCardsInHand().add(drawCard());
		this.players.get(0).showHand();
//		throwCard(this.players.get(0).throwCard(0));
//		this.players.get(0).showHand();
		placeCard(this.players.get(0).throwCard(0));
		System.out.printf("The keeper limit is %d", this.keeperLimit);
	}

	// Game State
	public boolean isGameRunning() { return this.gameRunning; }
}