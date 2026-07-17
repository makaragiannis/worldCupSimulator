package worldCupSimulator.match;

import worldCupSimulator.GameResult;
import worldCupSimulator.Team;

public class KnockoutMatch extends Match {

	int homeTeamFTScore;
	int awayTeamFTScore;
	
	GameResult FTResult;
	
	int homeTeamPenaltiesScore;
	int awayTeamPenaltiesScore;
	
	GameResult PenaltiesResult;
	
	Team winningTeam;
	
	public KnockoutMatch(Team homeTeam, Team awayTeam) {
		super(homeTeam, awayTeam);
	}
	
	public void playMatchRandom() {
		
		super.playMatchRandom();
		
		// Because this is a knock-out round, after a FT draw we must play penalties //
	}
	
//	public Team getWinner() {
//		
//	}
	
	
	

}
