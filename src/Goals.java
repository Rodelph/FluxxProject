import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Goals extends Card 
{
	public static final int totalGoals = 20;
	
	private String goalName, winCondition1, winCondition2;
	private JsonReader jsonReader;
	private JsonObject jsonObj, jsonObjParser;
	
	public Goals(int i) throws FileNotFoundException
	{
		this.jsonReader = Json.createReader(new FileInputStream("./src/GoalData.json"));
		
		this.jsonObj = this.jsonReader.readObject();
		this.jsonReader.close();
		
		this.jsonObjParser = this.jsonObj.getJsonObject("goal" + i);
		this.goalName      = this.jsonObjParser.getString("goalName");
		this.winCondition1 = this.jsonObjParser.getString("winCondition1");
		this.winCondition2 = this.jsonObjParser.getString("winCondition2");
	}

	public String getWinCondition1() { return this.winCondition1; }

	public String getWinCondition2() { return this.winCondition2; }

	public String getName()          { return this.goalName ; }
}