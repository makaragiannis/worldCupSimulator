package worldCupSimulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import worldCupSimulator.match.KnockoutMatch;

public class KnockoutStage {
	
	GroupStanding[] bestThirdPlaceStandings;
	TournamentPrinter tournamentPrinting;
	Group[] groups;
	Utility utility;
	
	KnockoutMatch[] roundOf32Matches, roundOf16Matches, quarterFinalMatches, semiFinalMatches, thirdPlaceMatch, firstPlaceMatch;
	
	KnockoutRound roundOf32, roundOf16, quarterFinals, semiFinals, thirdPlace, firstPlace;
	
	int groupsNum;
	
	public KnockoutStage(Group[] groups, int groupsNum, TournamentPrinter tournamentPrinting, Utility utility) {
		
		this.groupsNum = groupsNum;
		this.groups = groups;
		
		this.tournamentPrinting = tournamentPrinting;
		this.utility = utility;
		
		tournamentPrinting = new TournamentPrinter();
	}

	
	public void formKnockouts() {
		GroupStanding[] bestThirdPlaceStandings = getBestThirdPlaceTeams();
		
		// the actual algorithm is way too complicated and involves over 400 possibilities //
		// instead we will place the 1st and 2nd teams normally //
		// and generate random scenarios for the 3rd place teams //
		// we keep the scenario in which teams from the same group do not meet in R32 or R16 //
		
//		Match[] roundOf32Matches = generateRoundOf32Matces(bestThirdPlaceStandings);
		
		generateRoundOf32Matches(bestThirdPlaceStandings);
	}
	
	public void simulateKnockouts() {
		
		roundOf32 = new KnockoutRound(KnockoutPhase.R32, roundOf32Matches);
		roundOf32.playKnockoutRoundMatches();
		roundOf32.printKnockoutRoundMatches();
		
		roundOf16Matches = roundOf32.getNextRoundMatches();
		roundOf16 = new KnockoutRound(KnockoutPhase.R16, roundOf16Matches);
		roundOf16.playKnockoutRoundMatches();
		roundOf16.printKnockoutRoundMatches();
		
		quarterFinalMatches = roundOf16.getNextRoundMatches();
		quarterFinals = new KnockoutRound(KnockoutPhase.QF, quarterFinalMatches);
		quarterFinals.playKnockoutRoundMatches();
		quarterFinals.printKnockoutRoundMatches();
		
		semiFinalMatches = quarterFinals.getNextRoundMatches();
		semiFinals = new KnockoutRound(KnockoutPhase.SF, semiFinalMatches);
		semiFinals.playKnockoutRoundMatches();
		semiFinals.printKnockoutRoundMatches();
		
		thirdPlaceMatch = semiFinals.getLosersMatches();
		thirdPlace = new KnockoutRound(KnockoutPhase.THIRDPLACE, thirdPlaceMatch);
		thirdPlace.playKnockoutRoundMatches();
		thirdPlace.printKnockoutRoundMatches();
	
		firstPlaceMatch = semiFinals.getNextRoundMatches();
		firstPlace = new KnockoutRound(KnockoutPhase.FINAL , firstPlaceMatch);
		firstPlace.playKnockoutRoundMatches();
		firstPlace.printKnockoutRoundMatches();

	}
	
	
	GroupStanding[] getBestThirdPlaceTeams() {
		
		// add them in an array of Teams //
		GroupStanding[] thirdPlaceStandings = new GroupStanding[12];
		GroupStanding[] bestThirdPlaceStandings = new GroupStanding[8];
		
		for (int i = 0; i < groupsNum; i++) {
			thirdPlaceStandings[i] = groups[i].groupStandings[2];
		}
		
//		printThirdPlaceTeams(thirdPlaceStandings);
		
		sortThirdPlaceTeams(thirdPlaceStandings);
		
		// transfer the 8th best teams to the other array
		for (int i = 0; i < 8; i++) {
			bestThirdPlaceStandings[i] = thirdPlaceStandings[i];
		}

		tournamentPrinting.printBestThirdPlaceTeams(bestThirdPlaceStandings);
		
		return thirdPlaceStandings;
	}
	
	void sortThirdPlaceTeams(GroupStanding[] thirdPlaceStandings) {
		Arrays.sort(thirdPlaceStandings, Comparator
				.comparingInt(GroupStanding::getPoints)
				.thenComparingInt(GroupStanding::getGoalDifference)
				.thenComparingInt(GroupStanding::getGoalsScored).reversed());
	}
	


	void printThirdPlaceTeams(GroupStanding[] thirdPlaceStandings) {
		
		GroupStanding tempStanding;
		
		System.out.println("Third-Place Teams:");
		
		for (int i = 0; i < groupsNum; i++) {
			tempStanding = thirdPlaceStandings[i];
			System.out.printf("Team %2d: %25s - %2dp - %2d-%2d GD\n", i+1, tempStanding.team.getName(), tempStanding.getPoints(), tempStanding.getGoalsScored(), tempStanding.getGoalsConceded());
		}
	}
	
//	KnockoutMatch[] generateRoundOf32Matches(GroupStanding[] bestThirdPlaceStandings) {
	void generateRoundOf32Matches(GroupStanding[] bestThirdPlaceStandings) {
		
		// get an array of chars which we will randomize multiple times //
		// so that we can get a sequence where teams from the same group //
		// do not meet in R32 and in R16 //
		Team[] bestThirdPlaceTeams = getValidThirdPlaceTeamSequence(bestThirdPlaceStandings);
		
		roundOf32Matches = new KnockoutMatch[16];

		roundOf32Matches[0] = new KnockoutMatch(groups[4].groupStandings[0].getTeam(), bestThirdPlaceTeams[0]); // 1E vs 3i //
		roundOf32Matches[1] = new KnockoutMatch(groups[8].groupStandings[0].getTeam(), bestThirdPlaceTeams[1]); // 1I vs 3ii //
		roundOf32Matches[2] = new KnockoutMatch(groups[0].groupStandings[1].getTeam(), groups[1].groupStandings[1].getTeam()); // 2A vs 2B //
		roundOf32Matches[3] = new KnockoutMatch(groups[5].groupStandings[0].getTeam(), groups[2].groupStandings[1].getTeam()); // 1F vs 2C //
		roundOf32Matches[4] = new KnockoutMatch(groups[10].groupStandings[1].getTeam(), groups[11].groupStandings[1].getTeam()); // 2K vs 2L //
		roundOf32Matches[5] = new KnockoutMatch(groups[7].groupStandings[0].getTeam(), groups[9].groupStandings[1].getTeam()); // 1H vs 2J //
		roundOf32Matches[6] = new KnockoutMatch(groups[3].groupStandings[0].getTeam(), bestThirdPlaceTeams[2]); // 1D vs 3iii //
		roundOf32Matches[7] = new KnockoutMatch(groups[6].groupStandings[0].getTeam(), bestThirdPlaceTeams[3]); // 1G vs 3iv //
		roundOf32Matches[8] = new KnockoutMatch(groups[2].groupStandings[0].getTeam(), groups[5].groupStandings[1].getTeam()); // 1C vs 2F //
		roundOf32Matches[9] = new KnockoutMatch(groups[4].groupStandings[1].getTeam(), groups[8].groupStandings[1].getTeam()); // 2E vs 2I //
		roundOf32Matches[10] = new KnockoutMatch(groups[0].groupStandings[0].getTeam(), bestThirdPlaceTeams[4]); // 1A vs 3v //
		roundOf32Matches[11] = new KnockoutMatch(groups[11].groupStandings[0].getTeam(), bestThirdPlaceTeams[5]); // 1L vs 3vi //
		roundOf32Matches[12] = new KnockoutMatch(groups[9].groupStandings[0].getTeam(), groups[7].groupStandings[1].getTeam()); // 1J vs 2H //
		roundOf32Matches[13] = new KnockoutMatch(groups[3].groupStandings[1].getTeam(), groups[6].groupStandings[1].getTeam()); // 2D vs 2G //
		roundOf32Matches[14] = new KnockoutMatch(groups[1].groupStandings[0].getTeam(), bestThirdPlaceTeams[6]); // 1B vs 3vii //
		roundOf32Matches[15] = new KnockoutMatch(groups[10].groupStandings[0].getTeam(), bestThirdPlaceTeams[7]); // 1K vs 3viii //

	}
	
	Team[] getValidThirdPlaceTeamSequence(GroupStanding[] bestThirdPlaceStandings) {
		
		ArrayList<Character> bestThirdPlaceGroupIndices = new ArrayList<Character>();
		
		for (int i = 0; i < 8; i++) {
			bestThirdPlaceGroupIndices.add(bestThirdPlaceStandings[i].getGroupIndex());
		}
		
		do {
			Collections.shuffle(bestThirdPlaceGroupIndices);
			
			// debug: print failed sequences //
//			
//			System.out.print("\nFailed Sequence: ");
//			
//			for (int i = 0; i < 8; i++) {
//				System.out.printf("%c ", bestThirdPlaceGroupIndices.get(i));
//			}
		}
		while (utility.checkValidThirdPlaceTeamSequence(bestThirdPlaceGroupIndices) == false);
		
//		System.out.print("\nThe valid sequence is: ");
//		
//		for (int i = 0; i < 8; i++) {
//			System.out.printf("%c ", bestThirdPlaceGroupIndices.get(i));
//		}
		
		Team[] bestThirdPlaceTeams = new Team[8];
		int groupIndex;
		
//		System.out.println("Third Place Teams: ");
//		System.out.println();
		
		for (int i = 0; i < 8; i++) {
			groupIndex = utility.getGroupIntegerFromGroupChar(bestThirdPlaceGroupIndices.get(i));
			bestThirdPlaceTeams[i] = groups[groupIndex].groupStandings[2].getTeam();
			
//			System.out.printf("%d: %s\n", (i+1), bestThirdPlaceTeams[i].getName());
		}
		
		return bestThirdPlaceTeams;
			
	}
	
}
