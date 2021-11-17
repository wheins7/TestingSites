// --== CS400 File Header Information ==--
// Name: Griffin Mcleod
// Email: gmcleod@wisc.edu
// Team: DG
// Role: Data Wragnler
// TA: Yelun
// Lecturer: Florian
// Notes to Grader: Griffin created this class for when uploading his data to have the option to show 
//          this loaction was a testing center or not

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
