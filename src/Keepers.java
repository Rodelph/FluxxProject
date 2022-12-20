import java.io.FileNotFoundException;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Keepers extends Card{

	public static final int totalKeepers = 18;
	
	private String     keeperName;
	private JsonReader jsonReader;
	private JsonObject jsonObj, jsonObjParser;
	
	/**
	 * This function reads from the Keepers Json file and creates objects based on its content.
	 * 
	 * @param i :: Index of the cards from 0 to 'totalKeepers' when creating a deck (Look Deck.createDeck() method)
	 * @throws FileNotFoundException
	 */
	public Keepers(int i) throws FileNotFoundException
	{	
		this.jsonObjParser = Utility.jsonFileReader(this.jsonReader, this.jsonObj, Utility.keeperDataJsonFile, "keeper", i);
		this.keeperName = this.jsonObjParser.getString("keeperName");
	}
	/**
	 * @return String :: The name of the name of the current Keeper
	 */
	public String getName() { return this.keeperName; }
}