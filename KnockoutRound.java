package worldCupSimulator;

import worldCupSimulator.match.KnockoutMatch;

public class KnockoutRound {
	
	KnockoutMatch[] knockoutRoundMatches;
	KnockoutPhase knockoutPhase;
	
	int numMatches;
	
	
	public KnockoutRound(KnockoutPhase knockoutPhase, KnockoutMatch[] knockoutMatches) {
			
		this.knockoutPhase = knockoutPhase;
		this.knockoutRoundMatches = knockoutMatches;
		
		numMatches = knockoutPhase.getNumMatches();

	}
	
	void playKnockoutRoundMatches() {
		for (int i = 0; i < numMatches; i++) {
			
			knockoutRoundMatches[i].playMatchRandom();
		}
	}
	
	void printKnockoutRoundMatches() {
		
		// print Match or Matches depending on whether the round has one match or more //
		if (knockoutPhase.getNumMatches() == 1) {
			System.out.printf("\n %s Match:\n", knockoutPhase.getName());
		}
		else {
			System.out.printf("\n %s Matches:\n", knockoutPhase.getName());
		}
		
		for (int i = 0; i < numMatches; i++) {
			
			knockoutRoundMatches[i].printKnockoutMatch();
			
		}
	}
	
	KnockoutMatch[] getNextRoundMatches() {
		
		int nextRoundNumMatches = numMatches/2;
		
		KnockoutMatch[] nextRoundMatches = new KnockoutMatch[nextRoundNumMatches];
		
		Team team1;
		Team team2;
		
		// iterate every two matches
		for (int i = 0; i < nextRoundNumMatches; i++) {
			
			team1 = knockoutRoundMatches[2*i].getWinner();
			team2 = knockoutRoundMatches[2*i + 1].getWinner();
			
			nextRoundMatches[i] = new KnockoutMatch(team1, team2);
		}
		
		return nextRoundMatches;
	}
	
	// matches against the Losing teams (example: third place final)
	KnockoutMatch[] getLosersMatches() {
		
		int losersNumMatches = numMatches/2;
		
		KnockoutMatch[] losersMatches = new KnockoutMatch[losersNumMatches];
		
		Team team1;
		Team team2;
		
		// iterate every two matches
		for (int i = 0; i < losersNumMatches; i++) {
			
			team1 = knockoutRoundMatches[2*i].getLoser();
			team2 = knockoutRoundMatches[2*i + 1].getLoser();
			
			losersMatches[i] = new KnockoutMatch(team1, team2);
		}
		
		return losersMatches;
	}
	
}
