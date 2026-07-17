package worldCupSimulator;

public enum Continent {
	
	EUROPE("Europe"),
	ASIA("Asia"),
	NORTHAMERICA("North America"),
	SOUTHAMERICA("South America"),
	AFRICA("Africa"),
	OCEANIA("Oceania");
	
	private final String name;
	
	Continent(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
