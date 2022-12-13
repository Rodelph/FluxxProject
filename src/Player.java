import java.util.ArrayList;

public class Player {
	
	private String playerName;
	private ArrayList<Keepers> keepersOnSideTable;
	private ArrayList<Card> cardsInHand;
	
	public Player(String _playerName)
	{
		this.playerName = _playerName;
		this.keepersOnSideTable = new ArrayList<Keepers>();
		this.cardsInHand = new ArrayList<Card>();
	}
	
	public void showHand()
	{
		for(int i = 0 ; i < this.cardsInHand.size() ; i++)
		{
			if(this.cardsInHand.get(i).getClass() == Rules.class)
			{
				Rules rule = (Rules) this.cardsInHand.get(i);
				System.out.printf("%d - %s",i, rule.getName());
			}
			else if(this.cardsInHand.get(i).getClass() == Keepers.class)
			{
				Keepers keeper = (Keepers) this.cardsInHand.get(i);
				System.out.printf("%d - %s",i, keeper.getName());
			}
			else if(this.cardsInHand.get(i).getClass() == Goals.class)
			{
				Goals goal = (Goals) this.cardsInHand.get(i);
				System.out.printf("%d - %s",i, goal.getName());
			}
		}
	}
	
	public void showKeepers()
	{
		for(int i = 0 ; i < this.keepersOnSideTable.size() ; i++)
		{
			Keepers keep = this.keepersOnSideTable.get(i);
			System.out.printf("%d - %s",i, keep.getName());
		}
	}
	
	public Card throwCard(int index)
	{
		Card cacheCard = this.cardsInHand.get(index);
		this.cardsInHand.remove(index);
		
		return cacheCard;
	}
	
	public int handSize() { return this.cardsInHand.size(); }
	

	public String getPlayerName() { return playerName; }

	public ArrayList<Keepers> getKeepersOnTable() { return keepersOnSideTable; }

	public ArrayList<Card> getCardsInHand() { return cardsInHand; }	
}