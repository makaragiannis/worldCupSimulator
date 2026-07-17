package worldCupSimulator.match;

import worldCupSimulator.GroupStanding;

public class GroupMatch extends Match{
	
	GroupStanding homeTeamStanding;
	GroupStanding awayTeamStanding;
		
	public GroupMatch(GroupStanding homeTeamStanding, GroupStanding awayTeamStanding) {
		
		super(homeTeamStanding.getTeam(), awayTeamStanding.getTeam());
		this.homeTeamStanding = homeTeamStanding;
		this.awayTeamStanding = awayTeamStanding;
		
	}
	
	public void endMatchFT() {
		
		super.endMatchFT();
		
		homeTeamStanding.addMatchResult(homeTeamFTScore, awayTeamFTScore);
		awayTeamStanding.addMatchResult(awayTeamFTScore, homeTeamFTScore);
	}
	
	
}
