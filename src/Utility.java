import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Utility {
	
	//https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	public static final String ANSI_RESET 				= "\u001B[0m" ;
	public static final String ANSI_RED 				= "\u001B[31m";
	public static final String ANSI_GREEN 				= "\u001B[32m";
	public static final String ANSI_YELLOW 				= "\u001B[33m";
	public static final String ANSI_PURPLE              = "\u001B[35m";
	public static final String ANSI_CYAN 				= "\u001B[36m";
	public static final String ANSI_CYAN_BACKGROUND 	= "\u001B[46m";
	public static final String ANSI_RED_BACKGROUND 		= "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND 	= "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND 	= "\u001B[43m";
	
	public static final String keeperDataJsonFile   	= "./src/KeeperData.json";
	public static final String goalDataJsonFile   		= "./src/GoalData.json"  ;
	public static final String ruleDataJsonFile			= "./src/RulesData.json" ;
	
	public static JsonObject jsonFileReader(JsonReader jsReader, JsonObject jsObject, String path, String cardType, int index) throws FileNotFoundException
	{
		jsReader = Json.createReader(new FileInputStream(path));
		jsObject = jsReader.readObject();
		jsReader.close();
		JsonObject jsObjectParser = jsObject.getJsonObject(cardType + index);
		
		return jsObjectParser;
	}
}