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