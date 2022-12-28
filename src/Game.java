import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Game{	
	
	private GameState game;
	
	private boolean gameIsRunning    = true, 
			        winCon1Satisfied = false, 
			        winCon2Satisfied = false;
	
	private int numberOfPlayers, 
				drawLimit,
				playLimit, 
				keeperLimit,
				handLimit;
	
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
						 + " Press a key number to continue !" + Utility.ANSI_RESET);
	}
	
	/**
	 * Prints the possible commands on the terminal upon a certain 'Key' click 
	 */
	public void printCommands()
	{
		System.out.println(Utility.ANSI_RED + "\nCommands :\n" + Utility.ANSI_RESET
						 + "1- To show Keepers on your side press 'K'\n"
						 + "2- To show your Hand press 'P' \n"
						 + "3- To show the goal of the game press 'G'\n"
						 + "4- To show the current Rules press 'R'\n"
						 + "5- For help and game logic press 'H'\n"
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
	
	// Places goal and rule cards
	// We keep the goal card object as a variable
	// We check what kind of rule type is it and change the rules accordingly
	
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
	 * 	https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html
	 *	https://stackoverflow.com/questions/18590901/check-and-extract-a-number-from-a-string-in-java
	 * 
	 * @param scan :: Scanner that takes the player input
	 * @throws InterruptedException
	 */
	public void run(Scanner scan) throws InterruptedException 
	{	
		String _userInput = scan.next();
		
		if(_userInput.isEmpty() || _userInput == " " || _userInput == "\n")
			System.out.println("Please insert an input that is not empty !");
		else if(!_userInput.matches(".*\\d.*"))
		{
			if((_userInput.toLowerCase()).equals("p"))
			{
				this.currentPlayer.showHand();
				System.out.println(Utility.ANSI_RED + "Press a key number to continue !" + Utility.ANSI_RESET);
			}
			if((_userInput.toLowerCase()).equals("k"))
			{
				this.currentPlayer.showKeepers();
				System.out.println(Utility.ANSI_RED + "Press a key number to continue !" + Utility.ANSI_RESET);
			}
			if((_userInput.toLowerCase()).equals("r"))
			{
				this.showRules();
				System.out.println(Utility.ANSI_RED + "Press a key number to continue !" + Utility.ANSI_RESET);
			}
			if((_userInput.toLowerCase()).equals("h"))
			{
				this.showHelp();
				System.out.println(Utility.ANSI_RED + "Press a key number to continue !" + Utility.ANSI_RESET);
			}
			if((_userInput.toLowerCase()).equals("g"))
			{
				this.showGoal();
				System.out.println(Utility.ANSI_RED + "Press a key number to continue !" + Utility.ANSI_RESET);
			}
			if((_userInput.toLowerCase()).equals("c"))
			{
				this.printCommands();
				System.out.println(Utility.ANSI_RED + "Press a key number to continue !" + Utility.ANSI_RESET);
			}
		}
		else if(_userInput.matches(".*\\d.*"))
		{
			int cardIndex = Integer.parseInt(_userInput);
			
			switch(this.game)
			{
				case DrawPhase:
					
					this.currentPlayer = this.players.get(this.indexPlayer);
					System.out.printf(Utility.ANSI_CYAN + "\nThe player %s will now draw %d card(s).\n" ////////
					                + Utility.ANSI_RESET, this.currentPlayer.getPlayerName(), this.drawLimit);
					
					for(int i = 1; i <= this.drawLimit ; i++)
						this.currentPlayer.getCardsInHand().add(drawCard());
					
					this.currentPlayer.showHand();

					System.out.println("\nYou will now switch to the Next Phase " + Utility.ANSI_RED 
							         + "press a number key to continue !" + Utility.ANSI_RESET);
					
					this.game = GameState.ChoseCardPhase;
					break;
					
				case ChoseCardPhase:
					
					boolean phaseMessageIsNeeded = false; ///// for the msg thingie to appeard when needed/////////
					
					if((this.currentPlayer.handSize() > this.handLimit) == true)
					{
						phaseMessageIsNeeded = true; //////////////
						System.out.println(Utility.ANSI_RED + "You have more cards in your hand than the hand limit rule ! Reduce your hand !" + Utility.ANSI_RESET);
						System.out.printf(Utility.ANSI_YELLOW +"The hand limit is set to %d so you need to throw %d !\n" + Utility.ANSI_RESET, this.handLimit, (this.currentPlayer.handSize() - this.handLimit));
						
						while(this.currentPlayer.handSize() > this.handLimit)
						{
							System.out.printf("You need to throw %d more time(s)\n", (this.currentPlayer.handSize() - this.handLimit));
							int handIndex = scan.nextInt();
							
							while(handIndex > this.currentPlayer.handSize() - 1)
							{
								System.out.println(Utility.ANSI_RED + "The card that you selected does not exist !" + Utility.ANSI_RESET);
								handIndex = scan.nextInt();
							}
							this.throwCard(this.currentPlayer.playCard(handIndex));
							this.currentPlayer.showHand();
						}
						System.out.println(Utility.ANSI_CYAN + "Finished removing extra cards in hand !" + Utility.ANSI_RESET);
					}
			
					if((this.currentPlayer.getKeepersOnTable().size() > this.keeperLimit) == true)
					{
						phaseMessageIsNeeded = true;  /////////////////////
						System.out.println("You have more keepers on the table than the keeper limit rule ! Time to reduct it !");
						System.out.printf("The keeper limit is set to %d so you need to throw %d !\n", this.keeperLimit, (this.currentPlayer.getKeepersOnTable().size() - this.keeperLimit));
						
						while(this.currentPlayer.getKeepersOnTable().size() > this.keeperLimit)
						{
							System.out.printf("You need to throw %d more time(s)\n", (this.currentPlayer.getKeepersOnTable().size() - this.keeperLimit));
							int keeperIndex = scan.nextInt();
							while(keeperIndex > this.currentPlayer.getKeepersOnTable().size() - 1)
							{
								System.out.println(Utility.ANSI_RED + "The card that you selected does not exist !" + Utility.ANSI_RESET);
								keeperIndex = scan.nextInt();
							}
							this.throwCard(this.currentPlayer.getKeepersOnTable().get(keeperIndex));
							this.currentPlayer.showHand();
							this.currentPlayer.getKeepersOnTable().remove(keeperIndex);
						}
						System.out.println(Utility.ANSI_CYAN + "Finished removing extra keepers !" + Utility.ANSI_RESET);
					}
					
					if (phaseMessageIsNeeded) //////////////
					{
						System.out.println("\nYou will now switch to the Next Phase " + Utility.ANSI_RED 
						         + "press a number key to continue !" + Utility.ANSI_RESET);
					}
					this.game = GameState.PlayPhase;
					break;
					
				case PlayPhase:
					
					System.out.printf(Utility.ANSI_CYAN + "You are now in play phase. Please select the card to be played !\n" + Utility.ANSI_RESET);
					
					while(cardIndex >= this.currentPlayer.getCardsInHand().size())
					{
						System.out.printf(Utility.ANSI_RED + "The index of the chosen card is non-existant ! "
										 + "Please chose another number between [0 and %d]\n" + Utility.ANSI_RESET, (this.currentPlayer.getCardsInHand().size() - 1));	
						cardIndex = scan.nextInt();
					}
					
					for(int i = 1 ; i <= this.playLimit; i++)
					{
						if(this.currentPlayer.getCardsInHand().isEmpty())
						{
							System.out.println("Your hand is empty so you can't throw anymore !");
							break;
						}
						
						this.cardCache = this.currentPlayer.playCard(cardIndex); 
						if(this.cardCache.getClass() == Rules.class)
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
							
							if(ruleType == RuleType.handLimitType)
							{
								this.handLimit = ruleCache.getHandLimit();
								this.currentPlayer.showHand();
								if((this.currentPlayer.handSize() > this.handLimit) == true)
								{
									while(this.currentPlayer.handSize() > this.handLimit)
									{
										System.out.printf("You need to throw %d more time(s)\n", (this.currentPlayer.handSize() - this.handLimit));
										int cacheRuleIndex = scan.nextInt();
										while(cacheRuleIndex > this.currentPlayer.handSize() - 1)
										{
											System.out.printf(Utility.ANSI_RED + "You can not select the card with the number %d since you do not have it !\nPlease insert a new number from 0 to %d!" + Utility.ANSI_RESET, cacheRuleIndex, (this.currentPlayer.handSize()- 1));
											cacheRuleIndex = scan.nextInt();
										}
											
										this.throwCard(this.currentPlayer.playCard(cacheRuleIndex));
									}
									System.out.println(Utility.ANSI_CYAN + "Finished removing extra cards in hand. Choose the corresponding number of the card to play !" + Utility.ANSI_RESET);
								}
							}
							
							if(ruleType == RuleType.keeperLimitType)
							{
								this.keeperLimit = ruleCache.getKeeperLimit();
								if((this.currentPlayer.getKeepersOnTable().size() > this.keeperLimit) == true)
								{
									System.out.println("You have more keepers on the table than the keeper limit rule ! Time to reduce it !");
									System.out.printf("The keeper limit is set to %d so you need to throw %d !\n", this.keeperLimit, (this.currentPlayer.getKeepersOnTable().size() - this.keeperLimit));

									while(this.currentPlayer.getKeepersOnTable().size() > this.keeperLimit)
									{
										int cacheKeeperIndex = scan.nextInt();
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
							
							if(ruleType == RuleType.playLimitType)
							{
								if ( (ruleCache.getPlayLimit() < this.playLimit) && ( i > ruleCache.getPlayLimit()) )
								{
									System.out.println("You've played more times than the new Play Limit, so your turn ends.");
									this.playLimit = ruleCache.getPlayLimit();
									break;
								}
								else 
								{
									this.playLimit = ruleCache.getPlayLimit();
									this.currentPlayer.showHand();
									{
										for(int j = 1 ; j < this.playLimit ; j++) //
										{
											System.out.printf("You need to play %d more time(s)\n", (this.playLimit - j - 1));
											if(this.currentPlayer.handSize() == 0)
											{
												System.out.println("You don't have enough cards to play anymore !");
												break;
											}
											System.out.println("Choosing card phase for play limit");
											cardIndex = scan.nextInt();
											while(cardIndex > this.currentPlayer.handSize() - 1)
											{
												System.out.printf(Utility.ANSI_RED + "You can not select the card with the number %d since you do not have it !\nPlease insert a new number from 0 to %d!" + Utility.ANSI_RESET, cardIndex, (this.currentPlayer.handSize()- 1));
												cardIndex = scan.nextInt();
											}
											this.game = GameState.ChoseCardPhase;
										}
									}
								}

							}
						}
						
						if(cardCache.getClass() == Goals.class)
						{
							Goals goalCache = (Goals) this.cardCache;
							
							System.out.printf(Utility.ANSI_CYAN + "You will now play the card that you selected :: %s\n" + Utility.ANSI_RESET, goalCache.getName());
							
							if(this.goalToAchieve == null)
								this.goalToAchieve = (Goals) this.cardCache;
							else
							{
								this.throwCard(this.goalToAchieve);
								this.goalToAchieve = (Goals) this.cardCache;
							}
						}
						
						if(cardCache.getClass() == Keepers.class)
						{
							Keepers keeperCache = (Keepers) this.cardCache;
							
							if ( this.keeperLimit < this.currentPlayer.getKeepersOnTable().size() + 1 )
							{
								System.out.printf(Utility.ANSI_CYAN + "You already have the maximum number of Keepers, please discard an old one\n" + Utility.ANSI_RESET);
								this.currentPlayer.showHand();
								int keeperIndex = scan.nextInt();
								while(keeperIndex > this.currentPlayer.getKeepersOnTable().size() - 1)
								{
									System.out.println(Utility.ANSI_RED + "The card that you selected does not exist !" + Utility.ANSI_RESET);
									keeperIndex = scan.nextInt();
								}
								this.throwCard(this.currentPlayer.getKeepersOnTable().get(keeperIndex));
								this.currentPlayer.showHand();
								this.currentPlayer.getKeepersOnTable().remove(keeperIndex);
							}
							else 
							{
								System.out.printf(Utility.ANSI_CYAN + "You will now play the card that you selected :: %s\n" + Utility.ANSI_RESET, keeperCache.getName());
							}
							
							this.currentPlayer.getKeepersOnTable().add((Keepers) this.cardCache);
							if(this.goalToAchieve != null)
							{
								{
									System.out.printf(Utility.ANSI_GREEN + "\n%s has won the game !\n" + Utility.ANSI_RESET, this.currentPlayer.getPlayerName());
									Thread.sleep(3000);
									this.gameIsRunning = false;
								}
							}
						}
						
						this.currentPlayer.showHand();
						System.out.println(Utility.ANSI_RED + "Press a key number to continue !" + Utility.ANSI_RESET);
					}								
					this.game = GameState.EndTurnPhase;
					break;
					
				case EndTurnPhase:
					
					System.out.println(Utility.ANSI_RED + "You will now end your turn, please press a key number to end your turn !\n" 
					                 + Utility.ANSI_RESET); 
					
					this.indexPlayer++;
					if(this.indexPlayer == this.numberOfPlayers)
						this.indexPlayer = 0;
					this.game = GameState.DrawPhase;
					break;
			}
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