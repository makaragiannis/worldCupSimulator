package worldCupSimulator;

public class GroupStanding {
	
	Team team;
	int points;
	int goalsScored;
	int goalsConceded;
	
	public GroupStanding(Team team) {
		
		this.team = team;
		points = 0;
		goalsScored = 0;
		goalsConceded = 0;
	}
	
	public void addMatchResult(int goalsScored, int goalsConceded) {
		
		this.goalsScored += goalsScored;
		this.goalsConceded += goalsConceded;
		
		if (goalsScored > goalsConceded ) {
			points += 3;
		}
		else if (goalsScored == goalsConceded) {
			points += 1;
		}
	}
	
	public int getGoalDifference() {
		return goalsScored - goalsConceded;
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getGoalsScored() {
		return goalsScored;
	}
	
	public int getGoalsConceded() {
		return goalsConceded;
	}
	
	public void printTeamStanding() {
		System.out.printf("%25s - %2d points - %2d goals scored - %2d goals conceded\n", team.getName(), points, goalsScored, goalsConceded);
	}
	
	public char getGroupIndex() {
		return team.getGroupIndex();
	}
	
	public String getTeamName() {
		return team.getName();
	}
	
	public Team getTeam() {
		return team;
	}

}
