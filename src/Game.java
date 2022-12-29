import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Game
{	
	private GameState game;
	
	private boolean gameIsRunning    = true, 
			        winCon1Satisfied = false, 
			        winCon2Satisfied = false;
	
	private int numberOfPlayers, 
				drawLimit,
				playLimit, 
				keeperLimit,
				handLimit,
				cardIndex;
	
	private ArrayList<Player> players;
	private Stack<Card>       pile;
	private Deck              deck;
	private Goals             goalToAchieve;
	private Player 			  currentPlayer;
	private Card 			  cardCache;
	private int 			  indexPlayer;
	
	/**
	 * @param _userInput :: Takes User Input as an integer and sets the total number
	 * 						of players. Then we use the total number of players
	 * 						to create players.
	 * 
	 * @throws FileNotFoundException :: For the JSON Files when instantiating the Deck object.
	 */
	public Game(int _userInput) throws FileNotFoundException
	{	
		this.deck            = new Deck();
		this.players         = new ArrayList<Player>();
		this.pile 			 = new Stack<Card>();
		this.numberOfPlayers = _userInput;	
		
		for(int i = 0 ; i < this.numberOfPlayers ; i++)
			this.players.add(new Player("Player_" + (i + 1)));
		
		this.cardIndex 	   = 0;
		this.indexPlayer   = 0;
		this.drawLimit     = 1;
		this.playLimit     = 1;
		this.keeperLimit   = 100;
		this.handLimit     = 5;
		this.gameIsRunning = true;
		this.game 		   = GameState.DrawPhase;
		
		printCommands();		
		showHelp();
		distributeThreeCards();
	}
	
	/**
	 * A function that tracks if the game is still running or not.
	 * 
	 * @return if the game is running or not
	 */
	public boolean isGameRunning() { return this.gameIsRunning; }
	
	/**
	 * Distributes three cards at the beginning of the game.
	 */
	public void distributeThreeCards()
	{
		for(int i = 0; i < 3 ; i++)
		{
			for(int j = 0 ; j < this.players.size(); j++)
				this.players.get(j).getCardsInHand().add(drawCard());
		}
		
		System.out.println(Utility.ANSI_CYAN + "Three cards have been distributed for each player!\n" + Utility.ANSI_RESET
						 + "The game will start with Player 1 drawing a card." + Utility.ANSI_RED 
						 + " Press a key to continue !" + Utility.ANSI_RESET);
	}
	
	/**
	 * Prints the possible commands on the terminal upon a certain 'Key' click 
	 */
	public void printCommands()
	{
		System.out.println(Utility.ANSI_RED + "\nCommands :\n" + Utility.ANSI_RESET
						 + "1- To show Keepers on your side press 'K'\n"
						 + "2- To show your Hand press 'H' \n"
						 + "3- To show the goal of the game press 'G'\n"
						 + "4- To show the current Rules press 'R'\n"
						 + "5- For extra information press 'E'\n"
						 + "6- To play a card enter the number association to the card\n"
						 + "7- To commands press 'C'\n");
	}
	
	/**
	 * Draws a card from the deck stack.
	 * If the deck is empty we first add all the cards in the pile, then we shuffles them, and finally we add them to the deck stack.
	 * 
	 * @return card :: A popped card from the top of the stack, and added to the hand of the player
	 */
	public Card drawCard() 
	{
		if(this.deck.getDeckStack().isEmpty())
			this.deck.getDeckStack().addAll(this.deck.shuffle(this.pile));
		
		return this.deck.getDeckStack().pop(); 
	}
	
	/**
	 * @param _card :: Throws a card to the pile (Adds it to the stack of piles)
	 */
	public void throwCard(Card _card) { this.pile.add(_card); }
	
	/**
	 * A logic that handles placing Rule cards and Goal cards.
	 * 
	 * If the card is a Goal card, we keep it as an instance in order to get the Wincondition1 (String) and Wincondition2 (String)
	 * to win the game, and to print them in the terminal upon Key press.
	 * 
	 * @param _card :: What the player throws
	 */
	public void placeCard(Card _card)
	{
		if(_card.getClass() == Goals.class)
		{
			this.goalToAchieve = (Goals)_card;
		}
		else if(_card.getClass() == Rules.class)
		{
			Rules rule = (Rules)_card;

			RuleType ruleType = RuleType.valueOf(rule.getRuleType());
			
			switch(ruleType)
			{
				case drawLimitType:
					this.drawLimit   = rule.getDrawLimit();
					break;
				
				case keeperLimitType:
					this.keeperLimit = rule.getKeeperLimit();
					break;
				
				case handLimitType:
					this.handLimit   = rule.getHandLimit();
					break;
				
				case playLimitType:
					this.playLimit   = rule.getPlayLimit();
					break;
			}
		}
	}
	
	/**
	 * Game loop of the game where everything is handled.
	 * We used reggex to extract a number from a string since we are taking Letters (for commands), and (Numbers) to select cards.
	 *
	 *	https://stackoverflow.com/questions/18590901/check-and-extract-a-number-from-a-string-in-java
	 * 
	 * @param scan :: Scanner that takes the player input
	 * @throws InterruptedException
	 */
	public void run(Scanner scan) throws InterruptedException 
	{	
		String _userInput = scan.next();
		
		/**
		 * This part checks if the input is a letter or a digit. If it's a number we play the card otherwise it's a command input
		 * We check if it has a digit or not with regular expression matching.
		 */
		
		if(_userInput.isEmpty() || _userInput == " " || _userInput == "\n")
			System.out.println("Please insert an input that is not empty !");
		else if(!_userInput.matches(".*\\d.*"))
		{
			if((_userInput.toLowerCase()).equals("h"))
			{
				this.currentPlayer.showHand();
				System.out.println(Utility.ANSI_RED + "Press a key number to continue !" + Utility.ANSI_RESET);
			}
			else if((_userInput.toLowerCase()).equals("k"))
			{
				this.currentPlayer.showKeepers();
				System.out.println(Utility.ANSI_RED + "Press a key number to continue !" + Utility.ANSI_RESET);
			}
			else if((_userInput.toLowerCase()).equals("r"))
			{
				this.showRules();
				System.out.println(Utility.ANSI_RED + "Press a key number to continue !" + Utility.ANSI_RESET);
			}
			else if((_userInput.toLowerCase()).equals("e"))
			{
				this.showHelp();
				System.out.println(Utility.ANSI_RED + "Press a key number to continue !" + Utility.ANSI_RESET);
			}
			else if((_userInput.toLowerCase()).equals("g"))
			{
				this.showGoal();
				System.out.println(Utility.ANSI_RED + "Press a key number to continue !" + Utility.ANSI_RESET);
			}
			else if((_userInput.toLowerCase()).equals("c"))
			{
				this.printCommands();
				System.out.println(Utility.ANSI_RED + "Press a key number to continue !" + Utility.ANSI_RESET);
			}
			else if((_userInput.toLowerCase()).equals("ak"))
			{
				for(int i = 0; i < this.players.size(); i++)
				{
					System.out.printf(Utility.ANSI_CYAN + "\n%s has the following keepers :\n" + Utility.ANSI_RESET, this.players.get(i).getPlayerName());
					for(int j = 0; j < this.players.get(i).getKeepersOnTable().size(); j++)
						System.out.println(Utility.ANSI_GREEN + "\t- " + this.players.get(i).getKeepersOnTable().get(j).getName() + Utility.ANSI_RESET);
				}
				System.out.println(Utility.ANSI_RED + "Press a key number to continue !" + Utility.ANSI_RESET);
			}
			else
				System.out.println(Utility.ANSI_RED + "You have pressed a non valid command !" + Utility.ANSI_RESET);
		}
		else if(_userInput.matches(".*\\d.*"))
		{	
			switch(this.game)
			{
				case DrawPhase:
					draw();
					System.out.println("\nYou will now switch to Playing Card Phase " + Utility.ANSI_RED + "press a key to continue !" + Utility.ANSI_RESET);
					this.game = GameState.PlayPhase;
					break;
					
				case PlayPhase:
					System.out.println(Utility.ANSI_CYAN + "You are now in Playing Phase. Please select the card to be played !" + Utility.ANSI_RESET);
					play(scan);
					System.out.println(Utility.ANSI_CYAN + "Press a key to continue" + Utility.ANSI_RESET);
					this.game = GameState.EndTurnPhase;
					break;
					
				case EndTurnPhase:
					System.out.println(Utility.ANSI_RED + "You will now end your turn, please press a key to end your turn !" + Utility.ANSI_RESET);
					this.indexPlayer++;
					if(this.indexPlayer == this.numberOfPlayers)
						this.indexPlayer = 0;
					this.game = GameState.DrawPhase;
					break;
			}
		}
	}
	
	/**
	 *  The current player draws as many cards as the draw limit
	 */
	public void draw()
	{
		this.currentPlayer = this.players.get(this.indexPlayer);
		System.out.printf(Utility.ANSI_CYAN + "\nThe player %s will now draw %d card.\n" 
		                + Utility.ANSI_RESET, this.currentPlayer.getPlayerName(), this.drawLimit);
		
		for(int i = 1; i <= this.drawLimit ; i++)
			this.currentPlayer.getCardsInHand().add(drawCard());
		
		this.currentPlayer.showHand();
	}
	
	/** 
	 * Takes the input of the index of the card during the play limit loop and depending on the card follows the game rules till
	 * the two keepers are held by someone.
	 * 
	 * @param scan
	 * @throws InterruptedException
	 */
	public void play(Scanner scan) throws InterruptedException
	{	
		for(int i = 1 ; i <= this.playLimit; i++)
		{
			String inputCache = scan.next();
			// Checks if the input is a string when it should be an int (Error Handling)
			while(!inputCache.matches(".*\\d.*"))
			{
				System.out.println(Utility.ANSI_RED + "Please insert a number relative to the card index to be played !" + Utility.ANSI_RESET);
				inputCache = scan.next();
			}
			this.cardIndex = Integer.parseInt(inputCache);
			
			if(this.currentPlayer.getCardsInHand().isEmpty())
			{
				System.out.println("Your hand is empty so you can't throw anymore !");
				break;
			}
			
			while(this.cardIndex >= this.currentPlayer.getCardsInHand().size())
			{
				System.out.printf(Utility.ANSI_RED + "The index of the chosen card is non-existant ! "
								 + "Please chose another number between [0 and %d]\n" + Utility.ANSI_RESET, (this.currentPlayer.getCardsInHand().size() - 1));	
				cardIndex = scan.nextInt();
			}
			
			this.cardCache = this.currentPlayer.getCardsInHand().get(cardIndex);
			this.currentPlayer.playCard(cardIndex);
			
			if(cardCache.getClass() == Goals.class)
			{
				Goals goalCache = (Goals) this.cardCache;
				
				System.out.printf(Utility.ANSI_CYAN + "You will now play the card that you selected :: %s\n"+ Utility.ANSI_RESET, goalCache.getName());
				
				if(this.goalToAchieve == null)
					this.goalToAchieve = (Goals) this.cardCache;
				else
				{
					this.throwCard(this.goalToAchieve);
					this.goalToAchieve = (Goals) this.cardCache;
				}
			}
			else if(cardCache.getClass() == Keepers.class)
			{
				Keepers keeperCache = (Keepers) this.cardCache;
				System.out.printf(Utility.ANSI_CYAN + "You will now play the card that you selected :: %s\n" + Utility.ANSI_RESET, keeperCache.getName());
				this.currentPlayer.getKeepersOnTable().add((Keepers) this.cardCache);
				if(this.goalToAchieve != null)
				{
					if(gameWinningCondition())
					{
						System.out.printf(Utility.ANSI_GREEN + "\n%s has won the game !\n" + Utility.ANSI_RESET, this.currentPlayer.getPlayerName());
						Thread.sleep(3000);
						this.gameIsRunning = false;
					}
				}
			}
			else if(this.cardCache.getClass() == Rules.class)
			{
				Rules ruleCache = (Rules)this.cardCache;
				
				System.out.printf(Utility.ANSI_CYAN + "You will now play the card that you selected :: %s\n" + Utility.ANSI_RESET, ruleCache.getName());
				
				RuleType ruleType = RuleType.valueOf(ruleCache.getRuleType());
				
				if(ruleType == RuleType.drawLimitType)
				{
					this.drawLimit = ruleCache.getDrawLimit();
					for(int j = 1 ; j < this.drawLimit; j++)
						this.currentPlayer.getCardsInHand().add(drawCard());
				}
				else if(ruleType == RuleType.handLimitType)
				{
					this.handLimit = ruleCache.getHandLimit();
					this.currentPlayer.showHand();
					if((this.currentPlayer.handSize() > this.handLimit) == true)
					{
						while(this.currentPlayer.handSize() > this.handLimit)
						{
							System.out.printf("You need to throw %d more time(s)\n", (this.currentPlayer.handSize() - this.handLimit));
							inputCache = scan.next();
							while(!inputCache.matches(".*\\d.*"))
							{
								System.out.println(Utility.ANSI_RED + "Please insert a number relative to the card index to be played !" + Utility.ANSI_RESET);
								inputCache = scan.next();
							}
							
							int cacheRuleIndex = Integer.parseInt(inputCache);
							
							while(cacheRuleIndex > this.currentPlayer.handSize() - 1)
							{
								System.out.printf(Utility.ANSI_RED + "You can not select the card with the number %d since you do not have it !\nPlease insert a new number from 0 to %d!" + Utility.ANSI_RESET, cacheRuleIndex, (this.currentPlayer.handSize()- 1));
								cacheRuleIndex = scan.nextInt();
							}
							this.throwCard(this.currentPlayer.playCard(cacheRuleIndex));
							this.currentPlayer.showHand();
						}
						System.out.println(Utility.ANSI_CYAN + "Finished removing extra cards in hand. Choose the corresponding number of the card to play !" + Utility.ANSI_RESET);
					}
				}
				else if(ruleType == RuleType.keeperLimitType)
				{
					this.keeperLimit = ruleCache.getKeeperLimit();
					if((this.currentPlayer.getKeepersOnTable().size() > this.keeperLimit) == true)
					{
						System.out.println("You have more keepers on the table than the keeper limit rule ! Time to reduce it !");
						System.out.printf("The keeper limit is set to %d so you need to throw %d !\n", this.keeperLimit, (this.currentPlayer.getKeepersOnTable().size() - this.keeperLimit));

						while(this.currentPlayer.getKeepersOnTable().size() > this.keeperLimit)
						{
							inputCache = scan.next();
							while(!inputCache.matches(".*\\d.*"))
							{
								System.out.println(Utility.ANSI_RED + "Please insert a number relative to the card index to be played !" + Utility.ANSI_RESET);
								inputCache = scan.next();
							}
							int cacheKeeperIndex = Integer.parseInt(inputCache);
							while(cacheKeeperIndex > this.currentPlayer.getKeepersOnTable().size() - 1)
							{
								System.out.printf(Utility.ANSI_RED + "You can not select the card with the number %d since you do not have it !\nPlease insert a new number from 0 to %d!" + Utility.ANSI_RESET, cacheKeeperIndex, (this.currentPlayer.getKeepersOnTable().size()- 1));
								cacheKeeperIndex = scan.nextInt();
							}
							this.throwCard(this.currentPlayer.getKeepersOnTable().get(cacheKeeperIndex));
							this.currentPlayer.getKeepersOnTable().remove(cacheKeeperIndex);
							this.currentPlayer.showKeepers();
						}	
						System.out.println(Utility.ANSI_CYAN + "Finished removing extra keepers. Choose the corresponding number of the card to play !" + Utility.ANSI_RESET);
					}
				}
				else if(ruleType == RuleType.playLimitType)
				{
					if ((ruleCache.getPlayLimit() < this.playLimit) && ( i > ruleCache.getPlayLimit()))
					{
						System.out.println("You've played more times than the new Play Limit, so your turn ends.");
						this.playLimit = ruleCache.getPlayLimit();
						break;
					}
					else 
					{
						this.playLimit = ruleCache.getPlayLimit();
						
						System.out.printf("You need to play %d more time(s)\n", (this.playLimit - i));
						if(this.currentPlayer.handSize() == 0)
						{
							System.out.println("You don't have enough cards to play anymore !");
							break;
						}
					}
				}
			}
			this.currentPlayer.showHand();
		}
		
		if((this.currentPlayer.handSize() > this.handLimit) == true)
		{
			System.out.println(Utility.ANSI_RED + "You have more cards in your hand than the hand limit rule ! Reduce your hand !" + Utility.ANSI_RESET);
			System.out.printf(Utility.ANSI_YELLOW +"The hand limit is set to %d so you need to throw %d !\n" + Utility.ANSI_RESET, this.handLimit, (this.currentPlayer.handSize() - this.handLimit));
			
			while(this.currentPlayer.handSize() > this.handLimit)
			{
				System.out.printf("You need to throw %d more time(s)\n", (this.currentPlayer.handSize() - this.handLimit));
				String inputCache = scan.next();
				while(!inputCache.matches(".*\\d.*"))
				{
					System.out.println(Utility.ANSI_RED + "Please insert a number relative to the card index to be played !" + Utility.ANSI_RESET);
					inputCache = scan.next();
				}
				this.cardIndex = Integer.parseInt(inputCache);
				while(this.cardIndex > this.currentPlayer.handSize() - 1)
				{
					System.out.println(Utility.ANSI_RED + "The card that you selected does not exist !" + Utility.ANSI_RESET);
					this.cardIndex = scan.nextInt();
				}
				this.throwCard(this.currentPlayer.playCard(this.cardIndex));
				this.currentPlayer.showHand();
			}
			System.out.println(Utility.ANSI_CYAN + "Finished removing extra cards in hand !" + Utility.ANSI_RESET);
		}
		
		if((this.currentPlayer.getKeepersOnTable().size() > this.keeperLimit) == true)
		{
			System.out.println("You have more keepers on the table than the keeper limit rule ! Time to reduct it !");
			System.out.printf("The keeper limit is set to %d so you need to throw %d !\n", this.keeperLimit, (this.currentPlayer.getKeepersOnTable().size() - this.keeperLimit));
			
			while(this.currentPlayer.getKeepersOnTable().size() > this.keeperLimit)
			{
				System.out.printf("You need to throw %d more time(s)\n", (this.currentPlayer.getKeepersOnTable().size() - this.keeperLimit));
				String inputCache = scan.next();
				while(!inputCache.matches(".*\\d.*"))
				{
					System.out.println(Utility.ANSI_RED + "Please insert a number relative to the card index to be played !" + Utility.ANSI_RESET);
					inputCache = scan.next();
				}
				this.cardIndex = Integer.parseInt(inputCache);
				
				while(this.cardIndex > this.currentPlayer.getKeepersOnTable().size() - 1)
				{
					System.out.println(Utility.ANSI_RED + "The card that you selected does not exist !" + Utility.ANSI_RESET);
					this.cardIndex = scan.nextInt();
				}
				this.throwCard(this.currentPlayer.getKeepersOnTable().get(this.cardIndex));
				this.currentPlayer.showKeepers();
				this.currentPlayer.getKeepersOnTable().remove(this.cardIndex);
			}
			System.out.println(Utility.ANSI_CYAN + "Finished removing extra keepers !" + Utility.ANSI_RESET);
		}
	}
	
	/**
	 * Game winning method. It loops through the player's Keepers, and checks if condition 1 and condition 2 are met.
	 * 
	 * @return boolean (if the player has both keepers or no)
	 */
	public boolean gameWinningCondition()
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
	
	/**
	 * A function the prints the rules of the game. 
	 * 
	 * - Play Limit
	 * - Hand Limit
	 * - Keeper Limit
	 * - Draw Limit
	 */
	public void showRules()
	{
		System.out.printf(Utility.ANSI_CYAN_BACKGROUND + "The current rules are :\n" + Utility.ANSI_RESET + Utility.ANSI_YELLOW +"Hand Limit :: %d\n"
				        + "Play Limit :: %d\nKeeper Limit :: %d\nDraw Limit :: %d\n" + Utility.ANSI_RESET, this.handLimit, this.playLimit, this.keeperLimit, this.drawLimit);
	}
	
	/**
	 * A function that prints the current goal in play, and what are the two conditions to be met (Keepers needed for winning).
	 */
	public void showGoal()
	{
		if(this.goalToAchieve == null)
			System.out.println("No goal has been placed yet !");
		else
			System.out.printf(Utility.ANSI_CYAN_BACKGROUND + "The goal to achieve is %s and you need the following keepers to win :\n" + Utility.ANSI_RESET
				        + Utility.ANSI_RED_BACKGROUND +"Keeper Number 1 :: %s\nKeeper Number 2 :: %s\n" + Utility.ANSI_RESET, this.goalToAchieve.getName(), this.goalToAchieve.getWinCondition1(), this.goalToAchieve.getWinCondition2());
	}
	
	/**
	 * A function that prints the rules of the game and how the game is setup (Took this from the manual).
	 */
	public void showHelp()
	{
		System.out.println(Utility.ANSI_CYAN + "\n@Setup\n" + Utility.ANSI_RESET
				         + "- Place the basic rules card in the center\n"
				 		 + "- Shuffle the deck and deal three cards to each player\n"
				         + "- Place the remainder of the deck face down next to the basic rules next to the basic rules to form a draw pile\n"
				 		 + "- At the start of the game there will be no goal or new rule cards in play but they will be added as the game progresses\n"
				         + Utility.ANSI_CYAN +"\n@Card Types\n" + Utility.ANSI_RESET
				 		 + Utility.ANSI_YELLOW + "@Basic Rules (Card with orange stripes)@\n" + Utility.ANSI_RESET
				         + "- This is the starting point, the foundation on which the rest of the game is built.\n"
				         + "- These initial rules will be superseded by \"new rules\" during the course of play, but this card should remain on the table at all times.\n"
				         + "  The basic rules are :\n"
				         + "	- Draw 1 card per turn\n"
				         + "	- Play 1 card per turn (with no other restrictions such as a Hand or Keeper Limits).\n"
				         + Utility.ANSI_PURPLE + "\n@Goal (Pink cards)@\n" + Utility.ANSI_RESET
				         + "- These cards show the specific Keepers you must have on the table in front of you in order to win.\n"
				         + "- To play a goal, place it face  up in the center of the table, discarding the previous Goal (if any).\n"
				         + "  The game begins with no goal in play, so no one can win until one is played.\n"
				         + "  The Goal applies to everyone, as soon as someone meets these conditions,\n"
				         + "  they win ! (Even if it's someone else's turn).\n"
				         + Utility.ANSI_GREEN + "\n@Keeper (Green Cards)@\n" + Utility.ANSI_RESET
				         + "- To play a Keeper, take it out of your hand and place it on the table in front of you,\n"
				         + "  face up. Most Goals require you to have a particular pair of\n"
				         + "  Keepers so playing a Keeper is always a good thing.\n");
		
		System.out.println("*----" + Utility.ANSI_RED_BACKGROUND + "@HOW TO WIN@" + Utility.ANSI_RESET +"-------------------------------------------------------*");
		System.out.println("|	                                                                |");
		System.out.println("|	The game continues until someone meets the conditions of the    |");
		System.out.println("|	current Goal. That player wins instantly, no matter whose turn  |");
		System.out.println("|	it is !                                                         |");
		System.out.println("|	                                                                |");
		System.out.println("*-----------------------------------------------------------------------*");  
	}
}