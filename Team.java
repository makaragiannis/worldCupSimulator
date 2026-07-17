package worldCupSimulator;

public class Team {
	
	
	String name;
	Continent continent;
	char groupIndex;

	Team(String name, Continent continent) {
		this.name = name;
		this.continent = continent;
	}
	
	public String getName() {
		return name;
	}

	public Continent getContinent() {
		return this.continent;
	}
	
	public void setGroup(char groupIndex) {
		this.groupIndex = groupIndex;
	}
	
	public char getGroupIndex() {
		return groupIndex;
	}

}


