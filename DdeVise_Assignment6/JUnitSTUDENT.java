import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class JUnitSTUDENT {
	private TownGraphManagerInterface gmi;
	private Graph graph;
	private String[] towns;
	  
	@Before
	public void setUp() throws Exception {
		  graph = new Graph();
		  gmi = new TownGraphManager();
		  towns = new String[5];
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
		gmi = null;
		towns = null;
	}

	@Test
	public void student() {
		for (int i = 0; i < 5; i++)
		  {
			  towns[i] = "Town_" + (i+1);
			  graph.addVertex(new Town(towns[i]));
			  gmi.addTown(towns[i]);
		  }
		graph.addEdge(new Town(towns[0]), new Town(towns[1]), 5, "Road_1");
		graph.addEdge(new Town(towns[0]), new Town(towns[2]), 1, "Road_2");
		graph.addEdge(new Town(towns[2]), new Town(towns[3]), 1, "Road_3");
		graph.addEdge(new Town(towns[3]), new Town(towns[4]), 1, "Road_4");
		graph.addEdge(new Town(towns[4]), new Town(towns[1]), 1, "Road_5");
		
		ArrayList<String> path = graph.shortestPath(new Town(towns[0]), new Town(towns[1]));
		assertEquals(path.get(0), "Town_1 via Road_2 to Town_3 1 mi");
		assertEquals(path.size(), 4);
		
		gmi.addRoad(towns[0], towns[1], 5, "Road_1");
		gmi.addRoad(towns[0], towns[2], 1, "Road_2");
		gmi.addRoad(towns[2], towns[3], 1, "Road_3");
		gmi.addRoad(towns[3], towns[4], 1, "Road_4");
		gmi.addRoad(towns[4], towns[1], 1, "Road_5");
		
		path = gmi.getPath(towns[0], towns[1]);
		assertEquals(path.get(0), "Town_1 via Road_2 to Town_3 1 mi");
		assertEquals(path.size(), 4);
	}
}
