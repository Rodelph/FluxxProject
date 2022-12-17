
import java.io.FileNotFoundException;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Rules extends Card
{	
	public static final int totalRules = 16;
	
	private RuleType _type;
	private JsonReader jsonReader;
	private JsonObject jsonObj, jsonObjParser;
	
	private String ruleName, cardTypeParser, ruleType;
	private int drawLimit, keeperLimit,
				handLimit, playLimit;
	
	//https://docs.oracle.com/javaee/7/api/javax/json/package-summary.html
	//https://www.baeldung.com/java-string-to-enum
	
	/**
	 * 
	 * @param i
	 * @throws FileNotFoundException
	 * 
	 */
	public Rules(int i) throws FileNotFoundException
	{
		this.jsonObjParser = Utility.jsonFileReader(this.jsonReader, this.jsonObj, Utility.ruleDataJsonFile, "rule", i);
		this.cardTypeParser = this.jsonObjParser.getString("ruleType");
		
		this._type = RuleType.valueOf(this.cardTypeParser);
		
		this.ruleType  = this._type.toString();
		this.ruleName  = jsonObjParser.getString("name");
		
		switch(this._type)
		{
			case drawLimitType:
				this.drawLimit = jsonObjParser.getInt("limitValue");
				break;
			
			case keeperLimitType:
				this.keeperLimit = jsonObjParser.getInt("limitValue");
				break;
				
			case handLimitType:
				this.handLimit = jsonObjParser.getInt("limitValue");
				break;
				
			case playLimitType:
				this.playLimit = jsonObjParser.getInt("limitValue");
				break;
		}
		
		this.jsonObj = null;
		this.jsonObjParser = null;
	}
	
	public int getDrawLimit()   { return this.drawLimit; }

	public int getKeeperLimit() { return this.keeperLimit; }

	public int getHandLimit()   { return this.handLimit; }

	public int getPlayLimit()   { return this.playLimit; }
	
	public String getRuleType() { return this.ruleType; }
	
	public String getName()     { return this.ruleName; }
}