import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

public class Game {
	
	enum GameState 
	{
		DrawPhase,
		ChoseCardPhase,
		PlayPhase,
		EndTurnPhase
	}

	private GameState game;
	
	private boolean gameIsRunning    = true, 
			        winCon1Satisfied = false, 
			        winCon2Satisfied = false;
	
	private int numberOfPlayers, drawLimit,
				playLimit      , keeperLimit,
				handLimit;
	
	private ArrayList<Player> players;
	private Stack<Card>       pile;
	private Deck              deck;
	private Goals             goalToAchieve;
	private Player 			  currentPlayer;
	private int 			  indexPlayer;
	
	public Game(int _userInput) throws FileNotFoundException
	{	
		this.deck            = new Deck();
		this.players         = new ArrayList<Player>();
		this.pile 			 = new Stack<Card>();
		this.numberOfPlayers = _userInput;	
		
		for(int i = 0 ; i < this.numberOfPlayers ; i++)
			this.players.add(new Player("Player_" + (i + 1)));
		
		this.indexPlayer   = 0;
		this.drawLimit     = 1;
		this.playLimit     = 1;
		this.keeperLimit   = 100;
		this.handLimit     = 100;
		this.gameIsRunning = true;
		this.game 		   = GameState.DrawPhase;
		
		printScreen();
	}
	
	public void printScreen()
	{
		System.out.println("\nCommands :\n"
						 + "1- To show Keepers on your side press K\n"
						 + "2- To show your Hand press H\n"
						 + "3- To show the current Rules press R\n"
						 + "4- For help and game logic press P\n"
						 + "5- To draw a card press D\n"
						 + "6- To play a card enter the index of the card\n");
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
		// Give each player 3 cards
		// Keybindings letters for menu and numbers for cards
		switch(this.game)
		{
			case DrawPhase:
				this.currentPlayer = this.players.get(this.indexPlayer);
				System.out.printf("\nThe player %s will now draw a card.\n", this.currentPlayer.getPlayerName());
				this.currentPlayer.getCardsInHand().add(drawCard());
				System.out.println("Your hand contains the following cards :\n");
				this.currentPlayer.showHand();
				this.indexPlayer++;
				if(this.indexPlayer == this.numberOfPlayers)
					this.indexPlayer = 0;
				this.game = GameState.ChoseCardPhase;
				//System.out.println(this.indexPlayer);
				break;
				
			case ChoseCardPhase:
				System.out.println("Please chose which card to play !");
				Card cardCache = currentPlayer.getCardsInHand().get(_userInput);
				
				if(cardCache.getClass() == Rules.class)
					System.out.println("Rules");
				else if(cardCache.getClass() == Keepers.class)
					System.out.println(2);
				else if(cardCache.getClass() == Goals.class)
					System.out.println(3);
				
				this.currentPlayer.throwCard(_userInput);
				System.out.println("chose phase");
				this.game = GameState.PlayPhase;
				break;
				
			case PlayPhase:

				System.out.println("play phase");
				this.game = GameState.EndTurnPhase;
				break;
				
			case EndTurnPhase:

				System.out.println("end phase");
				this.game = GameState.DrawPhase;
				break;
			
		}
		
		//this.players.get(0).getCardsInHand().add(drawCard());
		//this.players.get(0).showHand();
//		throwCard(this.players.get(0).throwCard(0));
//		this.players.get(0).showHand();
		//placeCard(this.players.get(0).throwCard(0));
		//showRules();
	}
	
	public boolean gameWinningCondition(String keeperName1, String keeperName2)
	{
		for(int i = 0 ; i < this.currentPlayer.getKeepersOnTable().size(); i++)
		{
			if(this.currentPlayer.getKeepersOnTable().get(i).getName() == this.goalToAchieve.getWinCondition1())
				this.winCon1Satisfied = true;
			
			if(this.currentPlayer.getKeepersOnTable().get(i).getName() == this.goalToAchieve.getWinCondition2())
				this.winCon2Satisfied = true;
		}
		
		return (this.winCon1Satisfied && this.winCon2Satisfied);
	}
	
	public void showRules()
	{
		System.out.printf("The current rules are :\nHand Limit :: %d\nPlay Limit :: %d\n"
				        + "Keeper Limit :: %d\nDraw Limit :: %d", this.handLimit, this.playLimit, this.keeperLimit, this.drawLimit);
	}
	
	public void showGoal()
	{
		System.out.printf("The goal to achieve is %s and you need the following keepers to win :\n"
				        + "Keeper N°1 :: %s\nKeeper N°2 :: %s", this.goalToAchieve.getName(), this.goalToAchieve.getWinCondition1(), this.goalToAchieve.getWinCondition2());
	}
	
	// Display Input
	public void help()
	{
		System.out.println("\n@Setup\n"
				         + "- Place the basic rules card in the center\n"
				 		 + "- Shuffle the deck and deal three cards to each player\n"
				         + "- Place the remainder of the deck face down next to the basic rules next to the basic rules to form a draw pile\n"
				 		 + "- At the start of the game there will be no goal or new rule cards in play but they will be added as the game progresses\n"
				         + "\n@Card Types\n"
				 		 + "@Basic Rules (Card with orange stripes)@\n"
				         + "- This is the starting point, the foundation on which the rest of the game is built.\n"
				         + "- These initial rules will be superseded by \"new rules\" during the course of play, but this card should remain on the table at all times.\n"
				         + "  The basic rules are :\n"
				         + "	- Draw 1 card per turn\n"
				         + "	- Play 1 card per turn (with no other restrictions such as a Hand or Keeper Limits).\n"
				         + "\n@Goal (Pink cards)@\n"
				         + "- These cards show the specific Keepers you must have on the table in front of you in order to win.\n"
				         + "- To play a goal, place it face  up in the center of the table, discarding the previous Goal (if any).\n"
				         + "  The game begins with no goal in play, so no one can win until one is played.\n"
				         + "  The Goal applies to everyone, as soon as someone meets these conditions,\n"
				         + "  they win ! (Even if it's someone else's turn).\n"
				         + "\n@Keeper (Green Cards)@\n"
				         + "- To play a Keeper, take it out of your hand and place it on the table in front of you,\n"
				         + "  face up. Most Goals require you to have a particular pair of\n"
				         + "  Keepers so playing a Keeper is always a good thing.\n");
		
		System.out.println("@HOW TO WIN@---------------------------------------------------*");
		System.out.println("                                                               |");
		System.out.println("The game continues until someone meets the conditions of the   |");
		System.out.println("current Goal. That player wins instantly, no matter whose turn |");
		System.out.println("it is !                                                        |");
		System.out.println("                                                               |");
		System.out.println("---------------------------------------------------------------*");    
	}

	// Game State
	public boolean isGameRunning() { return this.gameIsRunning; }
}