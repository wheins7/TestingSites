
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
