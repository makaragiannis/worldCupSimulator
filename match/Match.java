package worldCupSimulator.match;

import java.util.Random;

import worldCupSimulator.GameResult;
import worldCupSimulator.Team;

public abstract class Match {
	
	int awayTeamFTScore;
	int homeTeamFTScore;
	
	Team homeTeam;
	Team awayTeam;
	
	GameResult FTResult;
	
	protected Match(Team homeTeam, Team awayTeam) {
		this.homeTeamFTScore = 0;
		this.awayTeamFTScore = 0;
		
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}
	
	protected void scoreGoalHomeTeam() {
		homeTeamFTScore++;
	}
	
	protected void scoreGoalAwayTeam() {
		awayTeamFTScore++;
	}
	
	protected void endMatchFT() {
		if (homeTeamFTScore > awayTeamFTScore) {
			FTResult = GameResult.HOMEWIN;
		}
		else if (homeTeamFTScore < awayTeamFTScore) {
			FTResult = GameResult.AWAYWIN;
		}
		else {
			FTResult = GameResult.DRAW;
		}

	}
	
	protected GameResult getFTResult() {
		
		return FTResult;
	}
	
	protected void setMatchScore(int homeGoals, int awayGoals) {
		homeTeamFTScore = homeGoals;
		awayTeamFTScore = awayGoals;
	}
	
	public void playMatchRandom() {
		
		Random random = new Random();
		
		// below code is commented out because it does not do what one would expect //
		// however it favours 1-2 goals per team, which may be realistic //
		
//		// home team goals //
//		for (int i = 0; i < random.nextInt(0, 5); i++) {
//			scoreGoalHomeTeam();
//		}
//		
//		// away team goals //
//		for (int i = 0; i < random.nextInt(0, 5); i++) {
//			scoreGoalAwayTeam();
//		}
		
//		endMatch();
		
		// instead the below approach with a flat 20% chance for each number of goals 0-4 is used //
		int homeGoals = random.nextInt(0, 5);
		int awayGoals = random.nextInt(0, 5);
		setMatchScore(homeGoals, awayGoals);
		
		endMatchFT();
		
	}
	
	public void printMatch() {
		System.out.printf("%25s - %2s\n", homeTeam.getName(), awayTeam.getName());
	}
	
	public void printMatchFT() {
		System.out.printf("%25s %d - %d %s\n", homeTeam.getName(), homeTeamFTScore, awayTeamFTScore, awayTeam.getName());
	}



}
