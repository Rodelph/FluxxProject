import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Stack;

public class Deck {
	
	private Keepers     keep;
	private Rules       rule;
	private Goals       goal;
	private Stack<Card> deckStack;
	
	/**
	 * Generate a deck of card upon instantiation
	 * @throws FileNotFoundException
	 */
	public Deck() throws FileNotFoundException
	{
		this.deckStack = new Stack<Card>();
		createDeck();
	}

/**
 * This function's objective is to simply swap 2 elements in a given array 
 * made of Card elements.
 * 
 * @param _array2Swap
 * @param _positionOfElement1
 * @param _positionOfElement2
 * @return
 */
	public Card[] arrayElementsSwapper(Card[] _array2Swap, int _positionOfElement1, int _positionOfElement2)
	{	
		Card temp;
		
		temp                             = _array2Swap[_positionOfElement1];
		_array2Swap[_positionOfElement1] = _array2Swap[_positionOfElement2];
		_array2Swap[_positionOfElement2] = temp;
		
		return _array2Swap;
	}
	
	/**
	 * The shuffle function randomises the order of the given stack's elements
		by copying the contents of our stack into an array then randomising its elements
		by using the arrayElementsSwapper function combined with Random indexes 
		generated by the Random class instance 'rand'.
	 * 
	 * @param _cards       :: Takes a stack of cards to be shuffled 
	 * @return resultStack :: A shuffled stack
	 */
	public Stack<Card> shuffle(Stack<Card> _cards)
	{
		Card[] temporaryArray = new Card[_cards.size()];
		Stack<Card> resultStack = new Stack<Card>(); 

		 _cards.copyInto(temporaryArray);
		
		for (int i=0; i < _cards.size(); i++)
		{              
			Random rand = new Random(); 
			int firstRandomIndex =  rand.nextInt(_cards.size());
			int secondRandomIndex = rand.nextInt(_cards.size());
			temporaryArray= arrayElementsSwapper(temporaryArray, firstRandomIndex, secondRandomIndex);
		}
		
		for (int i=0; i < _cards.size(); i++)
			resultStack.push(temporaryArray[i]); 
		
		return resultStack;
	}
	
	/**
	 * Generates the total amount of cards (Keepers, Goals, Rules) and shuffles them.
	 * @return :: A new generated shuffled deck
	 * @throws FileNotFoundException
	 */
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
		this.deckStack= shuffle(this.deckStack);
		
		return this.deckStack;
	}
	
	/**
	 * To be able to manipulate the cards
	 * @return The Deck stack
	 */
	public Stack<Card> getDeckStack() { return this.deckStack; }
}
