// --== CS400 File Header Information ==--
// Name: Keith Lienert
// Email: klienert@wisc.edu
// Team: DG
// TA: Yelun
// Lecturer: Dahl
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

	private CS400Graph<Integer> graph;

	@BeforeEach
	/**
	 * Instantiate graph from last week's shortest path activity.
	 */
	public void createGraph() {
		graph = new CS400Graph<>();
		// insert vertices 0-9
		for (int i = 0; i < 10; i++)
			graph.insertVertex(i);
		// insert edges from Week 08. Dijkstra's Activity
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 7, 2);
		graph.insertEdge(1, 8, 4);
		graph.insertEdge(2, 4, 4);
		graph.insertEdge(2, 6, 3);
		graph.insertEdge(3, 1, 6);
		graph.insertEdge(3, 7, 2);
		graph.insertEdge(4, 5, 4);
		graph.insertEdge(5, 0, 2);
		graph.insertEdge(5, 1, 4);
		graph.insertEdge(5, 9, 1);
		graph.insertEdge(6, 3, 1);
		graph.insertEdge(7, 0, 3);
		graph.insertEdge(7, 6, 1);
		graph.insertEdge(8, 9, 3);
		graph.insertEdge(9, 4, 5);
	}

	/**
	 * Checks the distance/total weight cost from the vertex labeled 0 to 8 (should
	 * be 15), and from the vertex labeled 9 to 8 (should be 17).
	 */
	@Test
	public void providedTestToCheckPathCosts() {
		assertTrue(graph.getPathCost(0, 8) == 15);
		assertTrue(graph.getPathCost(9, 8) == 17);
	}

	/**
	 * Checks the ordered sequence of data within vertices from the vertex labeled 0
	 * to 8, and from the vertex labeled 9 to 8.
	 */
	@Test
	public void providedTestToCheckPathContents() {
		assertTrue(graph.shortestPath(0, 8).toString().equals("[0, 2, 6, 3, 1, 8]"));
		assertTrue(graph.shortestPath(9, 8).toString().equals("[9, 4, 5, 1, 8]"));
	}

	// ** New Tests

	/**
	 * Week 8 D's activity Starting at Node #2 - checking the path cost to node 8
	 * Should result in the cost of 14
	 */
	@Test
	public void testPathCost() {
		assertTrue(graph.getPathCost(2, 8) == 14);
	}

	/**
	 * Week 8 D's activity starting at node #2 - to node #8 Testing for the shortest
	 * Path Should result in [2, 6, 3, 1, 8]
	 */
	@Test
	public void testPathContents() {
		assertTrue(graph.shortestPath(2, 8).toString().equals("[2, 6, 3, 1, 8]"));
	}

	/**
	 * Testing Path to Self -- should return 0
	 */
	@Test
	public void testPathToSelf() {
		assertTrue(graph.getPathCost(5, 5) == 0);
	}

	/**
	 * Additional tests with separate graph Looking for shortest path with various
	 * weights
	 */
	@Test
	public void newPath() {
		CS400Graph<Integer> newGraph = new CS400Graph<>();
		// insert vertices 0-3
		for (int i = 1; i < 4; i++) {
			newGraph.insertVertex(i);
		}
		newGraph.insertEdge(1, 3, 3); // weight of 3
		newGraph.insertEdge(1, 2, 1); // weight of 1
		newGraph.insertEdge(2, 3, 1); // weight of 1

		assertTrue(newGraph.shortestPath(1, 3).toString().equals("[1, 2, 3]"));

	}

	/**
	 * Test 1 - testDisplayLandmarks()
	 */
	@Test
	public void testDisplayLandmarks() {
		Main.read("Data3.csv");
		assertEquals(Main.displayLandmarks(), "Dejope\n" + "MemorialUnion\n" + "Gordons\n" + "Sellery\n" + "Lakeshore\n"
				+ "Witte\n" + "Hub\n" + "Walgreens\n" + "BascomHall\n" + "Ogg\n" + "LibraryMall\n");

	}

	/**
	 * Test 2 - testDisplayCenters()
	 */
	@Test
	public void testDisplayCenters() {
		Main.read("Data3.csv");
		assertEquals(Main.displayCenters(), "OggTest\n" + "FrankHoltCenter\n" + "ParkSt\n" + "KohlCenter\n"
				+ "AlliantEnergyCenter\n" + "PorterBoathouse\n");
	}

	/**
	 * Test 3 - testFindCenter()
	 */

	public void testFindCenter() {
		Main.read("Data3.csv");
		System.out.println(Main.findCenter("Ogg"));
		assertEquals(Main.findCenter("Ogg"),
				"Your nearest testing center is: OggTest\n" + "This center is 0.0 miles away");
	}
}
