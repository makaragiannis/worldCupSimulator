package worldCupSimulator;

public class WorldCupSimulator {

	public static void main(String[] args) {
		
		// TODO -> add txt file with teams, groups etc as Constructor parameter //
		Tournament worldCup2026 = new Tournament();
		
		worldCup2026.addTeams();
		worldCup2026.printTeams();

		worldCup2026.addteamsInGroups();
		worldCup2026.printGroups();
		
		worldCup2026.simulateGroups();
		
		worldCup2026.formKnockouts();
		
		worldCup2026.simulateKnockouts();
	}
	
	
	


}
