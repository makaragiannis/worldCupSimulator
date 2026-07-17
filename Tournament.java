package worldCupSimulator;

public class Tournament {
	
	int teamsNum = 48;
	int groupsNum = 12;
	
	Team teams[];
	Group groups[];
	
	Utility utility;
	
	TournamentPrinter tournamentPrinting;
	
	KnockoutStage knockoutStage;
	
	public Tournament() {
		
		teams = new Team[teamsNum];
		groups = new Group[groupsNum];
		
		utility = new Utility();
		tournamentPrinting = new TournamentPrinter();
		
		knockoutStage = new KnockoutStage(groups, groupsNum, tournamentPrinting, utility);
	}
	
	// add all teams in the teams array //
	public void addTeams() {
		
		// --- GROUP A ---
		teams[0] = new Team("Mexico", Continent.NORTHAMERICA);
		teams[1] = new Team("South Africa", Continent.AFRICA);
		teams[2] = new Team("South Korea", Continent.ASIA);
		teams[3] = new Team("Czechia", Continent.EUROPE);

		// --- GROUP B ---
		teams[4] = new Team("Canada", Continent.NORTHAMERICA);
		teams[5] = new Team("Bosnia and Herzegovina", Continent.EUROPE);
		teams[6] = new Team("Qatar", Continent.ASIA);
		teams[7] = new Team("Switzerland", Continent.EUROPE);

		// --- GROUP C ---
		teams[8] = new Team("Brazil", Continent.SOUTHAMERICA);
		teams[9] = new Team("Morocco", Continent.AFRICA);
		teams[10] = new Team("Haiti", Continent.NORTHAMERICA);
		teams[11] = new Team("Scotland", Continent.EUROPE);

		// --- GROUP D ---
		teams[12] = new Team("United States", Continent.NORTHAMERICA);
		teams[13] = new Team("Paraguay", Continent.SOUTHAMERICA);
		teams[14] = new Team("Australia", Continent.ASIA);
		teams[15] = new Team("Turkiye", Continent.EUROPE);

		// --- GROUP E ---
		teams[16] = new Team("Germany", Continent.EUROPE);
		teams[17] = new Team("Curacao", Continent.NORTHAMERICA);
		teams[18] = new Team("Ivory Coast", Continent.AFRICA);
		teams[19] = new Team("Ecuador", Continent.SOUTHAMERICA);

		// --- GROUP F ---
		teams[20] = new Team("Netherlands", Continent.EUROPE);
		teams[21] = new Team("Japan", Continent.ASIA);
		teams[22] = new Team("Sweden", Continent.EUROPE);
		teams[23] = new Team("Tunisia", Continent.AFRICA);

		// --- GROUP G ---
		teams[24] = new Team("Belgium", Continent.EUROPE);
		teams[25] = new Team("Egypt", Continent.AFRICA);
		teams[26] = new Team("Iran", Continent.ASIA);
		teams[27] = new Team("New Zealand", Continent.OCEANIA);

		// --- GROUP H ---
		teams[28] = new Team("Spain", Continent.EUROPE);
		teams[29] = new Team("Cape Verde", Continent.AFRICA);
		teams[30] = new Team("Saudi Arabia", Continent.ASIA);
		teams[31] = new Team("Uruguay", Continent.SOUTHAMERICA);

		// --- GROUP I ---
		teams[32] = new Team("France", Continent.EUROPE);
		teams[33] = new Team("Senegal", Continent.AFRICA);
		teams[34] = new Team("Iraq", Continent.ASIA);
		teams[35] = new Team("Norway", Continent.EUROPE);

		// --- GROUP J ---
		teams[36] = new Team("Argentina", Continent.SOUTHAMERICA);
		teams[37] = new Team("Algeria", Continent.AFRICA);
		teams[38] = new Team("Austria", Continent.EUROPE);
		teams[39] = new Team("Jordan", Continent.ASIA);

		// --- GROUP K ---
		teams[40] = new Team("Portugal", Continent.EUROPE);
		teams[41] = new Team("DR Congo", Continent.AFRICA);
		teams[42] = new Team("Uzbekistan", Continent.ASIA);
		teams[43] = new Team("Colombia", Continent.SOUTHAMERICA);

		// --- GROUP L ---
		teams[44] = new Team("England", Continent.EUROPE);
		teams[45] = new Team("Croatia", Continent.EUROPE);
		teams[46] = new Team("Ghana", Continent.AFRICA);
		teams[47] = new Team("Panama", Continent.NORTHAMERICA);
	}
	
	
	// create groups by adding four teams in each group (teams are already in order)
	public void addteamsInGroups() {
		
		char startingGroupIndex = 'A';
		
		for (int i = 0; i < groupsNum; i++) {
			
			groups[i] = new Group(teams[i*4], teams[i*4 + 1], teams[i*4 + 2], teams[i*4 + 3], (char) (startingGroupIndex + i));
		}
	}
	

	
	// simulate groups //
	public void simulateGroups() {
		
		for (int i = 0; i < groupsNum; i++) {
			simulateGroup(groups[i]);
		}
	}
	
	// simulate a single group //
	void simulateGroup(Group group) {
		
		group.playGroupMatches();
		group.printGroupMatches();
		
		group.sortTeams();
		
		group.printTable();
	}	
	
	
	
	public void printTeams() {
		tournamentPrinting.printTeams(teams, teamsNum);
	}
	
	public void printGroups() {
		tournamentPrinting.printGroups(groups, groupsNum);
	}
	
	public void simulateKnockouts() {
		
	}
	
	public void formKnockouts() {
		knockoutStage.formKnockouts();
	}
	

	
	
}
