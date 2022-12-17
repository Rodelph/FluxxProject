import java.io.FileNotFoundException;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Keepers extends Card{

	public static final int totalKeepers = 18;
	
	private String keeperName;
	private JsonReader jsonReader;
	private JsonObject jsonObj, jsonObjParser;
	
	public Keepers(int i) throws FileNotFoundException
	{	
		this.jsonObjParser = Utility.jsonFileReader(this.jsonReader, this.jsonObj, Utility.keeperDataJsonFile, "keeper", i);
		this.keeperName = this.jsonObjParser.getString("keeperName");
	}

	public String getName() { return this.keeperName; }
}