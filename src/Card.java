import java.util.Stack;

public class Card {
	
	/**
	 * Total number of cards in all of the stacks
	 */
	public static final int totalCards = Keepers.totalKeepers + Goals.totalGoals + Rules.totalRules;
	
	/**
	 * A stack of Card Type holding only it's type
	 */
	private Stack<Keepers> keeperStack;
	private Stack<Goals>   goalStack;
	private Stack<Rules>   ruleStack;
	
	/**
	 * Initialises the stacks
	 */
	public Card()
	{
		this.keeperStack = new Stack<Keepers>();
		this.goalStack   = new Stack<Goals>();
		this.ruleStack   = new Stack<Rules>();
	}

	/**
	 * @return :: A stack of the Keepers card type
	 */
	public Stack<Keepers> getKeeperStack() { return this.keeperStack; }

	/**
	 * @return :: A stack of the Goals card type
	 */
	public Stack<Goals> getGoalStack() { return this.goalStack; }

	/**
	 * @return :: A stack of the Rules card type
	 */
	public Stack<Rules> getRuleStack() { return this.ruleStack; }
}