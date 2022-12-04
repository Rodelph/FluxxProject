import java.io.FileNotFoundException;
import java.util.Stack;

public class Deck {
	
	private Keepers keep;
	private Rules rule;
	private Goals goal;
	private Stack<Card> deckStack;
	
	public Deck() throws FileNotFoundException
	{
		this.deckStack = new Stack<Card>();
		createDeck();
	}
	
	public Stack<Card> shuffle(Stack<Card> _cards)
	{
		
		
		return this.deckStack;
	}
	
	public Stack<Card> createDeck() throws FileNotFoundException
	{
		for(int i = 1 ; i <= Keepers.totalKeepers ; i++)
		{
			this.keep = new Keepers(i);
			this.deckStack.push(keep);
		}
		
		for(int i = 1 ; i <= Rules.totalRules ; i++)
		{
			this.rule = new Rules(i);
			this.deckStack.push(rule);
		}
		
		for(int i = 1 ; i <= Goals.totalGoals ; i++)
		{
			this.goal = new Goals(i);
			this.deckStack.push(goal);
		}

		return this.deckStack;
	}
	
	public Stack<Card> getDeckStack() { return this.deckStack; }
}
