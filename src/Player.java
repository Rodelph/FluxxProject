import java.util.ArrayList;

public class Player {
	
	private String playerName;
	private int numberOfKeepersInHand;
	
	private  ArrayList<Keepers> keepersOnSideTable;
	private ArrayList<Card> cardsInHand;
	
	public Player(String _playerName)
	{
		this.playerName = _playerName;
		this.numberOfKeepersInHand = 0;
		this.keepersOnSideTable = new ArrayList<Keepers>();
		this.cardsInHand = new ArrayList<Card>();
	}

	public int getNumberOfKeepersInHand() {
		return numberOfKeepersInHand;
	}

	public void setNumberOfKeepersInHand(int numberOfKeepersInHand) {
		this.numberOfKeepersInHand = numberOfKeepersInHand;
	}

	public String getPlayerName() {
		return playerName;
	}

	public ArrayList<Keepers> getKeepersOnTable() {
		return keepersOnSideTable;
	}

	public ArrayList<Card> getCardsInHand() {
		return cardsInHand;
	}
	
	public void showHand()
	{
		for(int i = 0 ; i < this.cardsInHand.size() ; i++)
		{
			if(this.cardsInHand.get(i).getClass() == Rules.class)
			{
				Rules rule = (Rules) cardsInHand.get(i);
				System.out.println(rule.getRuleName());
			}
			else if(this.cardsInHand.get(i).getClass() == Keepers.class)
			{
				Keepers rule = (Keepers) cardsInHand.get(i);
				System.out.println(rule.getKeeperName());
			}
			else if(this.cardsInHand.get(i).getClass() == Goals.class)
			{
				Goals rule = (Goals) cardsInHand.get(i);
				System.out.println(rule.getGoalName());
			}
		}
		
		System.out.println(this.cardsInHand.size());
	}
	
	
}
