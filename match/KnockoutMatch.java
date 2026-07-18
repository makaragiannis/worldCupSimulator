package worldCupSimulator.match;

import java.util.Random;

import worldCupSimulator.GameResult;
import worldCupSimulator.Team;

public class KnockoutMatch extends Match {
	
	int homeTeamPenaltiesScore;
	int awayTeamPenaltiesScore;
	
	GameResult penaltiesResult;
	
	Team winningTeam;
	
	public KnockoutMatch(Team homeTeam, Team awayTeam) {
		super(homeTeam, awayTeam);
	}
	
	public void playMatchRandom() {
		
		super.playMatchRandom();
		
		// Because this is a knock-out round, after a FT draw we must play penalties //
		
		FTResult = getFTResult();

		if (FTResult == GameResult.HOMEWIN){
			winningTeam = homeTeam;
		}
		
		else if (FTResult == GameResult.AWAYWIN){
			winningTeam = awayTeam;
		}
		
		else {
			playPenaltiesRandom();
			if (penaltiesResult == GameResult.HOMEWIN) {
				winningTeam = homeTeam;
			}
			else {
				winningTeam = awayTeam;
			}
		}
	}
	
	public Team getWinner() {
		
		return winningTeam;
	}
	
	public Team getLoser() {
		
		if (winningTeam == homeTeam) return awayTeam;
		
		return homeTeam;
	}
	
	
	
	void playPenaltiesRandom() {
		
		Random random = new Random();
		
		int homePenaltiesRemaining = 5;
		int awayPenaltiesRemaining = 5;
		
		// for Simplicity reasons, home team always goes first at pens //
		
		for (int i = 0; i < 5; i++) {
			
			// penalty has approx. 75% of being a goal //
			
			// home team //
			if (isPenaltyScored(random) == true) {
				scorePenaltyHomeTeam();
			}
			
			homePenaltiesRemaining--;
			
			// check for victory //
			if (checkPenaltiesWin(homeTeamPenaltiesScore, awayTeamPenaltiesScore, homePenaltiesRemaining, awayPenaltiesRemaining)) {
				endMatchPenalties();
				break;
			}
			
			
			// away team //
			if (isPenaltyScored(random) == true) {
				scorePenaltyAwayTeam();
			}
			
			awayPenaltiesRemaining--;
			
			// check for victory //
			if (checkPenaltiesWin(homeTeamPenaltiesScore, awayTeamPenaltiesScore, homePenaltiesRemaining, awayPenaltiesRemaining)) {
				endMatchPenalties();
				break;
			}
		}
		
		// if after 5 penalties score is still draw, hit until one team wins //

		while(homeTeamPenaltiesScore == awayTeamPenaltiesScore) {
			
			
			if (isPenaltyScored(random) == true) {
				scorePenaltyHomeTeam();
			}

			if (isPenaltyScored(random) == true) {
				scorePenaltyAwayTeam();
			}
		}
		
		endMatchPenalties();
		
	}
	
	boolean checkPenaltiesWin(int homeTeamPenaltiesScore, int awayTeamPenaltiesScore, int homePenaltiesRemaining, int awayPenaltiesRemaining) {
		
		if (homeTeamPenaltiesScore > awayTeamPenaltiesScore + awayPenaltiesRemaining) return true;
		if (awayTeamPenaltiesScore > homeTeamPenaltiesScore + homePenaltiesRemaining) return true;
				
		return false;
	}
	
	Team pickFirstTeamInPenalties(Random random) {
		
		if (random.nextBoolean() == true) return homeTeam;
		
		return awayTeam;
	}
	
	void scorePenaltyHomeTeam() {
		homeTeamPenaltiesScore++;
	}
	
	void scorePenaltyAwayTeam() {
		awayTeamPenaltiesScore++;
	}
	
	boolean isPenaltyScored(Random random) {
		
		return (random.nextInt(0, 4) != 0); // 75%
	}
	
	void endMatchPenalties() {
		if (homeTeamPenaltiesScore > awayTeamPenaltiesScore) {
			penaltiesResult = GameResult.HOMEWIN;
		}
		else {
			penaltiesResult = GameResult.AWAYWIN;
		}
	}
	
	public void printKnockoutMatch() {
		
		if (FTResult == GameResult.DRAW) {
			printMatchPenalties();
		}
		
		else {
			printMatchFT();
		}
	}

	public void printMatchPenalties() {
		System.out.printf("%25s %d - %d %s (%d - %d at Penalties)\n", homeTeam.getName(), homeTeamFTScore, awayTeamFTScore, awayTeam.getName(), homeTeamPenaltiesScore, awayTeamPenaltiesScore);
	}

	
	

}
