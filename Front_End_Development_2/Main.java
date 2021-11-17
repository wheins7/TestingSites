// --== CS400 File Header Information ==--
// Name: Max Murray and Will Heins
// Email: msmurray4@wisc.edu, wheins@wisc.edu
// Team: DG
// Role: Front End Developers
// TA: Yelun
// Lecturer: Max: Gary Dahl, Will: Florian
// Notes to Grader: Will and I worked together to create the userInterface and we worked along side the Data Wrangler(Griffin) to develop the read method.
// later test engineers Arjay and Keith found some ways to improve the user interface methods and added those.

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
	}

	public static void userInterface() {
		Scanner scan = new Scanner(System.in);
		String s;

		final String intro = "Thanks for choosing group DG's covid testing center selector.\n";
		final String menu = "***************************************** \n"
				+ "* List of commands:                     * \n" + "* landmarks - displays a list of all    * \n"
				+ "*   the possible campus landmarks       * \n" + "* find - find your closest testing      * \n"
				+ "*   center                              * \n" + "* testingcenters - list all the testing * \n"
				+ "*   centers in the database             * \n" + "* menu - to display the commands        * \n" 
        + "* quit - to quit the app                * \n"
				+ "***************************************** \n";
		final String thanks = "Thanks for using our app. Have a nice day!";

		System.out.println(intro);
		System.out.println(menu);

		s = scan.next();
		while (s.toLowerCase().compareTo("quit") != 0) {
			switch (s.toLowerCase()) {
			case "quit":
				break;
          
      case "menu":
        System.out.println(menu);
        break;
          
			case "landmarks":
				System.out.println(displayLandmarks());
				break;

			case "testingcenters":
				System.out.println(displayCenters());
				break;

			case "find":
				System.out.println("Please enter your nearest landmark.");
				String b = scan.next();
				System.out.println(findCenter(b));
				break;
			}
			s = scan.next();

		}
		System.out.println(thanks);
		scan.close();

	}

	public static LinkedList<Location> tests = new LinkedList<>();
	static Hashtable<String, Location> allLocations = new Hashtable<String, Location>();
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
					}
					Location location = new Location(tempArr[1], isTestingFacility);
					allLocations.put(tempArr[1], location);
					graph.insertVertex(location);
          if (isTestingFacility) {
						tests.add(location);
					}
				} else {
					Location location1 = allLocations.get(tempArr[1]);
					Location location2 = allLocations.get(tempArr[2]);
					int weight = Integer.parseInt(tempArr[3]);
					try {
						graph.insertEdge(location1, location2, weight);
					} catch (NullPointerException e) {
						continue;
					}
				}
			}
			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static String displayLandmarks() {
		String s = "";
		Enumeration<String> enumeration = allLocations.keys();
		while (enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();
			if (!allLocations.get(key).isTestingFacility()) {
				s = s + key + "\n";
			}

		}
		return s;
	}

	public static String findCenter(String b) {

		Location source = null;
		if (!allLocations.containsKey(b) || !graph.containsVertex(allLocations.get(b))) {
			return "Not a known landmark.";
		} else {
			source = allLocations.get(b);
		}
		String closest = null;
		int cost = 100;
		for (Location center : tests) {
			try {
				int c = graph.getPathCost(source, center);
				if (c < cost) {
					cost = c;
					closest = center.getName();
				}
			} catch (NoSuchElementException e) {
				continue;
			}
		}
		if (closest == null || cost == 100) {
			return "No Paths";
		}
		return "Your nearest testing center is: " + closest + "\nThis center is " + ((double) cost / 10) + " miles away";

	}

	public static String displayCenters() {
		String s = "";
		Enumeration<String> enumeration = allLocations.keys();
		while (enumeration.hasMoreElements()) {

			String key = enumeration.nextElement();
			if (allLocations.get(key).isTestingFacility())
				s = s + key + "\n";
		}
		return s;
	}

}
