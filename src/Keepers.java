import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Keepers extends Card{

	public static final int totalKeepers = 18;
	
	private String keeperName;
	private JsonReader jsonReader;
	private JsonObject jsonObj, jsonObjParser;
	
	public Keepers(int i) throws FileNotFoundException
	{	
		this.jsonReader = Json.createReader(new FileInputStream("./src/KeeperData.json"));
		this.jsonObj = this.jsonReader.readObject();
		this.jsonReader.close();
		
		this.jsonObjParser = this.jsonObj.getJsonObject("keeper"+i);
		this.keeperName = this.jsonObjParser.getString("keeperName");
	}

	public String getKeeperName() { return this.keeperName; }
}