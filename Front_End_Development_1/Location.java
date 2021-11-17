// --== CS400 File Header Information ==--
// Name: Griffin McLeod
// Email: gmcleod@wisc.edu
// Team: DG
// TA: Bao
// Lecturer: Florian
// Notes to Grader: this is the class that is used as the data in the vertices for the graph
public class Location {
	private String name;
	private boolean isTestingFacility;
	public Location(String location, boolean isTestingFacility) {
		this.name = location;
		this.isTestingFacility = isTestingFacility;
	}
	public boolean isTestingFacility() {
		return isTestingFacility;
	}
	public String getName() {
		return name;
	}
}
