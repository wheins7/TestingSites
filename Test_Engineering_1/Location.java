// --== CS400 File Header Information ==--
// Name: Keith Lienert
// Email: klienert@wisc.edu 
// Team: DG
// Role: Test Engineer
// TA: Yelun
// Lecturer: Dahl
// Notes to Grader: <optional extra notes>

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
