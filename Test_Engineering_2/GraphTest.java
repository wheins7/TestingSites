// --== CS400 File Header Information ==--
// Name: Arjay McCandless
// Email: mccandless2@wisc.edu
// Team: DG
// TA: Yelun Bao
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;
import java.util.Scanner;

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
        // insert verticies 0-9
        for(int i=0;i<10;i++)
            graph.insertVertex(i);
        // insert edges from Week 08. Dijkstra's Activity
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,7,2);
        graph.insertEdge(1,8,4);
        graph.insertEdge(2,4,4);
        graph.insertEdge(2,6,3);
        graph.insertEdge(3,1,6);
        graph.insertEdge(3,7,2);
        graph.insertEdge(4,5,4);
        graph.insertEdge(5,0,2);
        graph.insertEdge(5,1,4);
        graph.insertEdge(5,9,1);
        graph.insertEdge(6,3,1);
        graph.insertEdge(7,0,3);
        graph.insertEdge(7,6,1);
        graph.insertEdge(8,9,3);
        graph.insertEdge(9,4,5);
    }

    /**
     * Checks the distance/total weight cost from the vertex labelled 0 to 8
     * (should be 15), and from the vertex labelled 9 to 8 (should be 17).
     */
    @Test
    public void providedTestToCheckPathCosts() {
        assertTrue(graph.getPathCost(0,8) == 15);    
        assertTrue(graph.getPathCost(9,8) == 17);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * labelled 0 to 8, and from the vertex labelled 9 to 8.
     */
    @Test
    public void providedTestToCheckPathContents() {
        assertTrue(graph.shortestPath(0, 8).toString().equals(
            "[0, 2, 6, 3, 1, 8]"
        ));
        assertTrue(graph.shortestPath(9, 8).toString().equals(
            "[9, 4, 5, 1, 8]"
        ));
    }
    /**
     * Makes sure last weeks length was correct
     */
    @Test
    public void testLastWeeksActivity() {
    	assertTrue(graph.getPathCost(6,5) == 15);   
    }
    /**
     * Makes sure last weeks path was correct
     */
    @Test
    public void testLastWeeksActivityPath() {
    	assertTrue(graph.shortestPath(6, 5).toString().equals(
                "[6, 3, 7, 0, 2, 4, 5]"
            ));
    }
    /**
     * Tests a path of length 0
     */
    @Test
    public void testPathOfLength0() {
    	assertTrue(graph.getPathCost(0, 0)==0);
    }
    /**
     * Tests a path which does not exist
     */
    @Test
    public void testNoPath() {
    	CS400Graph<Integer> graph2 = new CS400Graph<>();
    	for(int i=0;i<10;i++)
            graph2.insertVertex(i);
    	try {
    	graph2.getPathCost(6, 7);
    	}
    	catch(NoSuchElementException e) {
    		assertTrue(1==1);
    	}
    }
    
    @Test
    public void testDisplayLandmarks() {
    	Main.read("Data3.csv");
    	assertEquals(Main.displayLandmarks(), "Dejope\n"
    			+ "MemorialUnion\n"
    			+ "Gordons\n"
    			+ "Sellery\n"
    			+ "Lakeshore\n"
    			+ "Witte\n"
    			+ "Hub\n"
    			+ "Walgreens\n"
    			+ "BascomHall\n"
    			+ "Ogg\n"
    			+ "LibraryMall\n");
    }
    
    @Test
    public void testDisplayCenters() {
    	Main.read("Data3.csv");
    	assertEquals(Main.displayCenters(), "OggTest\n"
    			+ "FrankHoltCenter\n"
    			+ "ParkSt\n"
    			+ "KohlCenter\n"
    			+ "AlliantEnergyCenter\n"
    			+ "PorterBoathouse\n");
    }
    
    @Test
    public void testFindCenter() {
    	Main.read("Data3.csv");
    	System.out.println(Main.findCenter("Ogg"));
    	assertEquals(Main.findCenter("Ogg"),"Your nearest testing center is: OggTest\n"
    			+ "This center is 0.0 miles away");
    }
    
    
    
    

}