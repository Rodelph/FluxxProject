import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Rules extends Card
{	
	public static final int totalRules = 16;
	
	private String ruleName, cardTypeParser, ruleType;
	private RuleType _type;
	private JsonReader jsonReader;
	private JsonObject jsonObj, jsonObjParser;
	
	private int drawLimit, keeperLimit,
				handLimit, playLimit;
	
	//https://www.baeldung.com/java-string-to-enum
	
	public Rules(int i) throws FileNotFoundException
	{
		this.jsonReader = Json.createReader(new FileInputStream("./src/RulesData.json"));
		
		this.jsonObj = this.jsonReader.readObject();
		this.jsonReader.close();
		
		this.jsonObjParser = this.jsonObj.getJsonObject("rule" + i);
		this.cardTypeParser = this.jsonObjParser.getString("ruleType");
		
		this._type = Card.RuleType.valueOf(this.cardTypeParser);
		
		switch(this._type)
		{
			case drawLimitType:
				this.ruleType = this._type.toString();
				this.ruleName = jsonObjParser.getString("name");
				this.drawLimit = jsonObjParser.getInt("limitValue");
				break;
			
			case keeperLimitType:
				this.ruleType = this._type.toString();
				this.ruleName = jsonObjParser.getString("name");
				this.keeperLimit = jsonObjParser.getInt("limitValue");
				break;
				
			case handLimitType:
				this.ruleType = this._type.toString();
				this.ruleName = jsonObjParser.getString("name");
				this.handLimit = jsonObjParser.getInt("limitValue");
				break;
				
			case playLimitType:
				this.ruleType = this._type.toString();
				this.ruleName = jsonObjParser.getString("name");
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
	
	public String getRuleName() { return this.ruleName; }
}