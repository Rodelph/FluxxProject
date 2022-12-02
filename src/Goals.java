
public class Goals 
{
	
	private String goalName, typeGoal;
	
	public Goals(String _goalName, String _typeGoal)
	{
		this.goalName  = _goalName;
		this.typeGoal  = _typeGoal;
	}

	public String getGoalName() { return this.goalName ; }

	public String getTypeGoal() { return this.typeGoal; }
}
