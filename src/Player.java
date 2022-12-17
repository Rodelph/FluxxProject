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
		System.out.println(Game.ANSI_RED + "\nYour hand contains the follwing card(s)" + Game.ANSI_RESET);
		
		for(int i = 0 ; i < this.cardsInHand.size() ; i++)
		{
			if(this.cardsInHand.get(i).getClass() == Rules.class)
			{
				Rules rule = (Rules) this.cardsInHand.get(i);
				System.out.printf(Game.ANSI_YELLOW + "%d - %s\n" + Game.ANSI_RESET,i, rule.getName());
			}
			else if(this.cardsInHand.get(i).getClass() == Keepers.class)
			{
				Keepers keeper = (Keepers) this.cardsInHand.get(i);
				System.out.printf(Game.ANSI_GREEN + "%d - %s\n" + Game.ANSI_RESET,i, keeper.getName());
			}
			else if(this.cardsInHand.get(i).getClass() == Goals.class)
			{
				Goals goal = (Goals) this.cardsInHand.get(i);
				System.out.printf(Game.ANSI_RED + "%d - %s\n" + Game.ANSI_RESET,i, goal.getName());
			}
		}
		
		
	}
	
	public void showKeepers()
	{
		System.out.println(Game.ANSI_RED + "\nYour posses the following Keeper(s)" + Game.ANSI_RESET);
		
		if(this.keepersOnSideTable.isEmpty())
		{
			System.out.println(Game.ANSI_GREEN + "You have no keepers on your side at the moment !" + Game.ANSI_RESET);
		}
		else
		{
			for(int i = 0 ; i < this.keepersOnSideTable.size() ; i++)
			{
				Keepers keep = this.keepersOnSideTable.get(i);
				System.out.printf(Game.ANSI_GREEN + "%d - %s\n" + Game.ANSI_RESET,i, keep.getName());
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