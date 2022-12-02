
public class Rules 
{
	
	private String ruleName;
	
	private int drawLimit, keeperLimit,
				handLimit, playLimit;
	
	public Rules(String _ruleName, int _drawLimit, int _keeperLimit, int _handLimit, int _playLimit)
	{
		this.ruleName    = _ruleName;
		this.drawLimit   = _drawLimit;
		this.keeperLimit = _keeperLimit;
		this.handLimit   = _handLimit;
		this.playLimit   = _playLimit;
	}

	public int getDrawLimit() { return this.drawLimit; }

	public String getRuleName() { return this.ruleName; }

	public int getKeeperLimit() { return this.keeperLimit; }

	public int getHandLimit() { return this.handLimit; }

	public int getPlayLimit() { return this.playLimit; }
}