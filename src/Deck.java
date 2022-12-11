import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Stack;

public class Deck {
	
	private Keepers keep;
	private Rules rule;
	private Goals goal;
	private Card card;
	private Stack<Card> deckStack;
	private Random rand;
	
	public Deck() throws FileNotFoundException
	{
		this.card = new Card();
		this.deckStack = new Stack<Card>();
		createDeck();
	}
	
	private Stack<Card> shuffle(Stack<Keepers> _cardsKeepers, Stack<Rules> _cardsRules, Stack<Goals> _cardsGoals)
	{
		this.rand = new Random();
		// While statement
		switch(rand.nextInt(2))
		{
			case 0:
				//this.deckStack.push(_cardsKeepers.get(i));
				break;
				
		}
		
		return this.deckStack;
	}
	
	public Stack<Card> createDeck() throws FileNotFoundException
	{
		for(int i = 1 ; i <= Keepers.totalKeepers ; i++)
		{
			this.keep = new Keepers(i);
			this.card.getKeeperStack().push(keep);
		}
		
		for(int i = 1 ; i <= Rules.totalRules ; i++)
		{
			this.rule = new Rules(i);
			this.card.getRuleStack().push(rule);
		}
		
		for(int i = 1 ; i <= Goals.totalGoals ; i++)
		{
			this.goal = new Goals(i);
			this.card.getGoalStack().push(goal);
		}
		
		//this.deckStack = shuffle(this.card.getKeeperStack(), this.card.getRuleStack(), this.card.getGoalStack());
		
		this.deckStack.addAll(card.getKeeperStack());
		this.deckStack.addAll(card.getGoalStack());
		this.deckStack.addAll(card.getRuleStack());
		
		return this.deckStack;
	}
	
	public Stack<Card> getDeckStack() { return this.deckStack; }
	
	public Card getCard() { return this.card; }
}
