import java.io.FileNotFoundException;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Goals extends Card 
{
	public static final int totalGoals = 20;
	
	private String     goalName, winCondition1, winCondition2;
	private JsonReader jsonReader;
	private JsonObject jsonObj, jsonObjParser;
	
	/**
	 * This function reads from the Goal Json file and creates objects based on its content.
	 * 
	 * @param i :: Index of the cards from 0 to 'totalGoals' when creating a deck (Look Deck.createDeck() method)
	 * @throws FileNotFoundException
	 */
	public Goals(int i) throws FileNotFoundException
	{	
		this.jsonObjParser = Utility.jsonFileReader(this.jsonReader, this.jsonObj, Utility.goalDataJsonFile, "goal", i);
		
		this.goalName      = this.jsonObjParser.getString("goalName");
		this.winCondition1 = this.jsonObjParser.getString("winCondition1");
		this.winCondition2 = this.jsonObjParser.getString("winCondition2");
	}

	/**
	 * @return String :: The name of the keeper number 1 for the current played goal
	 */
	public String getWinCondition1() { return this.winCondition1; }

	/**
	 * @return String :: The name of the keeper number 2 for the current played goal
	 */
	public String getWinCondition2() { return this.winCondition2; }

	/**
	 * @return String :: The name of the name of the current played goal
	 */
	public String getName()          { return this.goalName ; }
}