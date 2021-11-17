import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
	  read("Data3.csv");
	  userInterface();
   // displayLandmarks();
   // displayCenters();
  }

  private static void userInterface() {
    Scanner scan = new Scanner(System.in);
    String s;

    final String intro = "Thanks for choosing group DG's covid testing center selector.\n";
    final String menu = "***************************************** \n" +
                        "* List of commands:                     * \n" +
                        "* landmarks - displays a list of all    * \n" +
                        "*   the possible campus landmarks       * \n" +
                        "* find - find your closest testing      * \n" +
                        "*   center                              * \n" +
                        "* testingcenters - list all the testing * \n" +
                        "*   centers in the database             * \n" +
	    		"* menu - show this list of commands     * \n" +
                        "* quit - to quit the app                * \n" +
                        "***************************************** \n";
    final String thanks = "Thanks for using our app. Have a nice day!";
    
    
    System.out.println(intro);
    System.out.println(menu);

    
    s = scan.next();
    while(s.toLowerCase().compareTo("quit") != 0) {
      switch(s.toLowerCase()) {
        case "quit" :
          return;
		      
        case "menu":
          System.out.println(menu);
          break;
		      
        case "landmarks":
          displayLandmarks();
          break;
          
        case "testingcenters":
          displayCenters();
          break;
          
        case "find":
          findCenter(scan);
          break;
      }
      s = scan.next();
      
    }
    System.out.println(thanks);
    scan.close();
    
  }
  public static LinkedList<Location> tests = new LinkedList<>();
  static Hashtable<String,Location> allLocations = new Hashtable<String,Location>();
  static CS400Graph<Location> graph = new CS400Graph<>();

  public static void read(String csvFile) {
	    try {
	      File file = new File(csvFile);
	      FileReader fr = new FileReader(file);
	      BufferedReader br = new BufferedReader(fr);
	      String line = "";
	      String[] tempArr;
	      while ((line = br.readLine()) != null) {
	        tempArr = line.split(",");
	        if (tempArr[0].equalsIgnoreCase("location")) {
	        	boolean isTestingFacility = false;
	        	if (tempArr[2].equalsIgnoreCase("y")) {
	        		isTestingFacility = true;
	        		//tests.add(new Location(tempArr[1],isTestingFacility));
	        	}
	        	Location location = new Location(tempArr[1],isTestingFacility);
	        	allLocations.put(tempArr[1], location);
	        	graph.insertVertex(location);
	        	//tests.add(location);
	        	if( isTestingFacility) {
	        		tests.add(location);
	        	}
	        	
	        } else {
	       // if(tempArr[0].equalsIgnoreCase("edge")){
	        	//System.out.println("edge");
	        	
	        	Location location1 = allLocations.get(tempArr[1]);
	        	Location location2 = allLocations.get(tempArr[2]);
	        	int weight = Integer.parseInt(tempArr[3]);
	        	try {
	        	graph.insertEdge(location1, location2, weight);
	        	//System.out.println("Successfully added edge");
	        	} catch (NullPointerException e) {
	        		//System.out.println("Could not add edge.");
	        		continue;
	        	}
	        }
	        }
	      br.close();
	    } catch (IOException ioe) {
	      ioe.printStackTrace();
	    }
	    //System.out.println(graph.getEdgeCount());
	   // System.out.println(graph.getVertexCount());

	   // return covidLocations;
  }

  private static void displayLandmarks() {
    // TODO Auto-generated method stub
	 Enumeration<String> enumeration = allLocations.keys();
	 while(enumeration.hasMoreElements()) {
         String key = enumeration.nextElement();
         if(!allLocations.get(key).isTestingFacility())
         System.out.println(key);
     }
  }

  private static void findCenter(Scanner scan) {
    // TODO Auto-generated method stub
	  

	  System.out.println("Please enter your nearest landmark.");
	  String b = scan.next();
	  Location source = null;
	  if(! allLocations.containsKey(b) || !graph.containsVertex(allLocations.get(b))) {
		  System.out.println("Not a known landmark.");
		  return;
	  } else {
		  source = allLocations.get(b);
		  //System.out.println(source.isTestingFacility());
	  }
	 // List<String> shortestPath;
	  String closest = null;
	  int cost = 100;
	  //LinkedList<String> tests = new LinkedList<>();
	  for(Location center: tests) {
		  //System.out.println(graph.dijkstrasShortestPath(source, center).distance);
		  try {
			  //System.out.println(center.getName());
			  int c = graph.getPathCost(source, center);
			  if(c < cost) {
				  cost = c;
				  closest = center.getName();
			  }
		  } catch (NoSuchElementException e) {
			  continue;
		  }
	  }
	  if(closest == null || cost == 100) {
		  System.out.println("No Paths");
		  return;
	  }
	  System.out.println("Your nearest testing center is: " + closest);
	  System.out.println("This center is " + ((double)cost / 10) + " miles away");
	  
  }

  private static void displayCenters() {
    // TODO Auto-generated method stub
	  Enumeration<String> enumeration = allLocations.keys();
		 while(enumeration.hasMoreElements()) {
			 
	         String key = enumeration.nextElement();
	         if(allLocations.get(key).isTestingFacility())
	         System.out.println(key);
	     }
  }
  
}
