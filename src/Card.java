import java.util.Stack;

public class Card {
	
	private Keepers keeper;
	private Goals goal;
	private Rules rule;
	
	private Stack<Keepers> keeperStack;
	private Stack<Goals> goalStack;
	private Stack<Rules> ruleStack;
	
	public Card()
	{
		/*
		 * Do this in a loop after getting vars from the json
		this.keepers = new Keepers(...);
		this.goals = new Goals(...);
		this.rules = new Rules(...);
		*/
	}

	public Stack<Keepers> getKeeperStack() {
		return keeperStack;
	}

	public Stack<Goals> getGoalStack() {
		return goalStack;
	}

	public Stack<Rules> getRuleStack() {
		return ruleStack;
	}
}
