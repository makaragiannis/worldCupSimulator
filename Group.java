package worldCupSimulator;

import java.util.Arrays;
import java.util.Comparator;

import worldCupSimulator.match.GroupMatch;

public class Group {
	
	Team[] teams;
	char groupIndex;
	GroupMatch[] matches;
	GroupStanding[] groupStandings;
	
	
	Group(Team team1, Team team2, Team team3, Team team4, char groupIndex) {
		
		teams = new Team[4];
		teams[0] = team1;
		teams[1] = team2;
		teams[2] = team3;
		teams[3] = team4;
		
		groupStandings = new GroupStanding[4];
		
		groupStandings[0] = new GroupStanding(team1);
		groupStandings[1] = new GroupStanding(team2);
		groupStandings[2] = new GroupStanding(team3);
		groupStandings[3] = new GroupStanding(team4);
		
		// 6 matches in each group //
		matches = new GroupMatch[6];
		
		// initialize all six matches //
		matches[0] = new GroupMatch(groupStandings[0], groupStandings[1]);
		matches[1] = new GroupMatch(groupStandings[2], groupStandings[3]);
		matches[2] = new GroupMatch(groupStandings[0], groupStandings[2]);
		matches[3] = new GroupMatch(groupStandings[1], groupStandings[3]);
		matches[4] = new GroupMatch(groupStandings[0], groupStandings[3]);
		matches[5] = new GroupMatch(groupStandings[1], groupStandings[2]);
		
		this.groupIndex = groupIndex;
		
		team1.setGroup(groupIndex);
		team2.setGroup(groupIndex);
		team3.setGroup(groupIndex);
		team4.setGroup(groupIndex);
	
	}
	
	public void printGroupTeams() {
		System.out.printf("%s\n%s\n%s\n%s\n\n", teams[0].getName(), teams[1].getName(), teams[2].getName(), teams[3].getName());
	}
		
	public void sortTeams() {
		
		// first criterion is Points //
		// second criterion is Head to Head //
		// third criterion is Goal Difference //
		// fourth criterion is Best Offence //
		// after that, YCs and RCs, but not implemented here //
		
		// actually for now, neither Head to head is implemented //
		
		// note for Comparator: it seems like reversed reverses the whole comparator //
		// and not just the part after the dot //
		// so we apply it to the end, because all comparators must be reversed //
		Arrays.sort(groupStandings, Comparator
				.comparingInt(GroupStanding::getPoints)
				.thenComparingInt(GroupStanding::getGoalDifference)
				.thenComparingInt(GroupStanding::getGoalsScored).reversed());
	}
	
	public void playGroupMatches() {
		
		for (int i = 0; i < 6; i++) {
			matches[i].playMatchRandom();
		}
	}
	
	public void printGroupMatches() {
		
		System.out.printf("Group %c matches:\n", groupIndex);
		System.out.println("-----------------");
		for (int i = 0; i < 6; i++) {
			matches[i].printMatchFT();
		}
		System.out.println();
	}
	
	public void printTable() {
		
		System.out.printf("Group %c Table:\n", groupIndex);
		System.out.println("-----------------");
		for (int i = 0; i < 4; i++) {
			groupStandings[i].printTeamStanding();
		}
		System.out.println();
	}
	
	

}
