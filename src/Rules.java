
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
	
	/**
	 * This function reads from the Rule Json file and creates objects based on its content.
	 * It also generates a rule card while taking in consideration the type of the rule.
	 * Example :: 'If the rule card is a limit hand type, only the handLimit variable will be initialised.'
	 * 
	 * @param i
	 * @throws FileNotFoundException
	 */
	public Rules(int i) throws FileNotFoundException
	{
		this.jsonObjParser  = Utility.jsonFileReader(this.jsonReader, this.jsonObj, Utility.ruleDataJsonFile, "rule", i);
		this.cardTypeParser = this.jsonObjParser.getString("ruleType");
		this._type          = RuleType.valueOf(this.cardTypeParser);
		this.ruleType       = this._type.toString();
		this.ruleName       = jsonObjParser.getString("name");
		
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
	
	/**
	 * @return The drawLimit of the Rule Card
	 */
	public int getDrawLimit()   { return this.drawLimit; }
	
	/**
	 * @return The keeperLimit of the Rule Card
	 */
	public int getKeeperLimit() { return this.keeperLimit; }

	/**
	 * @return The handLimit of the Rule Card
	 */
	public int getHandLimit()   { return this.handLimit; }

	/**
	 * @return The playLimit of the Rule Card
	 */
	public int getPlayLimit()   { return this.playLimit; }
	
	/**
	 * @return The rule type of the Rule as a String
	 */
	public String getRuleType() { return this.ruleType; }
	
	/**
	 * @return The name of the Rule as a String
	 */
	public String getName()     { return this.ruleName; }
}