import java.util.ArrayList;

public class Player {
	
	private String playerName;
	// We don't need numberofcards in hand we can just get the size of the cardsinhand
	private int numberOfKeepersInHand;
	
	private  ArrayList<Keepers> keepersOnTable;
	private ArrayList<Card> cardsInHand;
	
	public Player(String _playerName)
	{
		this.playerName = _playerName;
		this.numberOfKeepersInHand = 0;
		this.keepersOnTable = new ArrayList<Keepers>();
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
		return keepersOnTable;
	}

	public ArrayList<Card> getCardsInHand() {
		return cardsInHand;
	}
}
