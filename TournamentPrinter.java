package worldCupSimulator;

import worldCupSimulator.match.KnockoutMatch;

public class TournamentPrinter {

	// print teams //
	public void printTeams(Team[] teams, int teamsNum) {
		
		for (int i = 0; i < teamsNum; i++) {
			
			System.out.printf("Team %d: %s from %s\n", i, teams[i].getName(), teams[i].continent.getName());
		}
	}
	
	// print groups //
	public void printGroups(Group[] groups, int groupsNum) {
		
		for (int i = 0; i < groupsNum; i++) {
			
			System.out.println("Group " + (i+1) + ": ");
			groups[i].printGroupTeams();
		}
	}
	
	public void printBestThirdPlaceTeams(GroupStanding[] thirdPlaceStandings) {
		
		GroupStanding tempStanding;
		
		System.out.println("Best Third-Place Teams:");
		
		for (int i = 0; i < 8; i++) {
			tempStanding = thirdPlaceStandings[i];
			System.out.printf("Team %2d (Group %c): %25s - %2dp - %2d-%2d GD\n", i+1, tempStanding.getGroupIndex(), tempStanding.team.getName(), tempStanding.getPoints(), tempStanding.getGoalsScored(), tempStanding.getGoalsConceded());
		}
	}
	
	public void printRoundOf32Matches(KnockoutMatch[] matches) {
		
		for (int i = 0; i < 16; i++) {
			matches[i].printMatch();
		}
	}
}
