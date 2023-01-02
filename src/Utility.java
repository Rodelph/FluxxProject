import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * 
 * This class has everything needed for colouring the terminal, as well as a function to load the JSON files.
 *
 */
public class Utility 
{
	
	//https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	
	/**
	 * Resets to white
	 */
	public static final String ANSI_RESET 				= "\u001B[0m" ;
	
	/**
	 * Red font
	 */
	public static final String ANSI_RED 				= "\u001B[31m";
	
	/**
	 * Green font
	 */
	public static final String ANSI_GREEN 				= "\u001B[32m";
	
	/**
	 * Yellow font
	 */
	public static final String ANSI_YELLOW 				= "\u001B[33m";
	
	/**
	 * Purple font
	 */
	public static final String ANSI_PURPLE              = "\u001B[35m";
	
	/**
	 * Cyan font
	 */
	public static final String ANSI_CYAN 				= "\u001B[36m";
	
	/**
	 * Cyan Background
	 */
	public static final String ANSI_CYAN_BACKGROUND 	= "\u001B[46m";
	
	/**
	 * Red Background
	 */
	public static final String ANSI_RED_BACKGROUND 		= "\u001B[41m";

	/**
	 * Green Background
	 */
	public static final String ANSI_GREEN_BACKGROUND 	= "\u001B[42m";
	
	/**
	 * Yellow Background
	 */
	public static final String ANSI_YELLOW_BACKGROUND 	= "\u001B[43m";
	
	/**
	 * Path to Keeper JSON file
	 */
	public static final String keeperDataJsonFile   	= "./src/Json/KeeperData.json";
	
	/**
	 * Path to Goal JSON file
	 */
	public static final String goalDataJsonFile   		= "./src/Json/GoalData.json"  ;
	
	/**
	 * Path to Rules JSON file
	 */
	public static final String ruleDataJsonFile			= "./src/Json/RulesData.json" ;
	
	/**
	 * https://docs.oracle.com/javaee/7/api/javax/json/package-summary.html
	 * 
	 * The JsonParser is what we will use to search using the :
	 * 	- getString('Name of the variable in the JSON file') to store a String
	 *  - getInt('Name of the variable in the JSON file') to store an Int
	 *  - ...
	 *  
	 * The search is done in the Unordered Map which is the jsObject that has all name/value pairs.
	 * 
	 * @param jsReader :: Reads a JSON object or an array structure from the given file.
	 * @param jsObject :: Store a non modifiable unordered collection of name/value pairs (Map)
	 * @param path	   :: Path of the JSON file in the project
	 * @param cardType :: Given card type (Goal, Rule, Keeper) upon instantiation 
	 * @param index    :: Index of the card (Max is total type of card)
	 * 
	 * @return A JSON object parser that will give us the ability to store the data in the JSON 
	 *         files in our private variables.
	 *
	 * @throws FileNotFoundException In case the files were not found the game will exit.
	 */
	public static JsonObject jsonFileReader(JsonReader jsReader, JsonObject jsObject, String path, String cardType, int index) throws FileNotFoundException
	{
		jsReader = Json.createReader(new FileInputStream(path));
		jsObject = jsReader.readObject();
		jsReader.close();
		JsonObject jsObjectParser = jsObject.getJsonObject(cardType + index);
		
		return jsObjectParser;
	}
	
	/**
	 * Prints the introduction of the game.
	 */
	public static void printIntro()
	{
		System.out.println(Utility.ANSI_RED + 
				           " --------------------- Welcome to Fluxx Game --------------------- " );
		System.out.println("|                                                                 |");
		System.out.println("|         Made By :: Naqi Amine && Kooli Mohamed Firas            |");
		System.out.println("|                                                                 |");
		System.out.println(" ----------------------------------------------------------------- " 
		                   + Utility.ANSI_RESET);
	}
	
	/**
	 * Cool animation before the start of the game.
	 * @throws InterruptedException Thread Interruption in case of exiting during the animation
	 */
	public static void loadingAnimation() throws  InterruptedException
	{
		System.out.print(Utility.ANSI_RED + "\n@ The game is going to start in " + Utility.ANSI_RESET + Utility.ANSI_RED_BACKGROUND + "3" + Utility.ANSI_RESET);
		Thread.sleep(1000);
		System.out.print(" " + Utility.ANSI_YELLOW_BACKGROUND + "2" + Utility.ANSI_RESET + " ");
		Thread.sleep(1000);
		System.out.print(Utility.ANSI_CYAN_BACKGROUND  + "1"  + Utility.ANSI_RESET);
		Thread.sleep(1000);
		System.out.print(" .");
		Thread.sleep(1000);
		System.out.print(".");
		Thread.sleep(1000);
		System.out.print(".");
		Thread.sleep(1000);
		System.out.print(" " + Utility.ANSI_GREEN_BACKGROUND + "GO FLUX !!" + Utility.ANSI_RESET + Utility.ANSI_RED + " @\n" + Utility.ANSI_RESET);
		Thread.sleep(1000);
	}
}