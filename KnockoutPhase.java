package worldCupSimulator;

public enum KnockoutPhase {
	
	R32("Round of 32", 16),
	R16("Round of 16", 8),
	QF("Quarter-Finals", 4),
	SF("Semi-Finals", 2),
	THIRDPLACE("Third Place", 1),
	FINAL("Final", 1);
	
	private final String name;
	private final int numMatches;
	
	KnockoutPhase(String name, int numMatches) {
		this.name = name;
		this.numMatches = numMatches;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getNumMatches() {
		return numMatches;
	}
}
