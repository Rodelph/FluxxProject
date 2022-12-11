import java.util.Stack;

public class Card {
	
	public static final int totalCards = 54;
	
	private Stack<Keepers> keeperStack;
	private Stack<Goals> goalStack;
	private Stack<Rules> ruleStack;
	
	enum RuleType
	{
		drawLimitType,
		keeperLimitType,
		playLimitType,
		handLimitType
	}
	
	public Card()
	{
		this.keeperStack = new Stack<Keepers>();
		this.goalStack = new Stack<Goals>();
		this.ruleStack = new Stack<Rules>();
	}

	public Stack<Keepers> getKeeperStack() { return this.keeperStack; }

	public Stack<Goals> getGoalStack() { return this.goalStack; }

	public Stack<Rules> getRuleStack() { return this.ruleStack; }
}
