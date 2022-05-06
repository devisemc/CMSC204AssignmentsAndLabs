/**
 * Author: Donovan deVise
 * Professor Eivazi
 * CMSC-204-36708
 * 4/27/2022
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Utility class for Graph
 */
public class TownGraphManager implements TownGraphManagerInterface{
	// Instance Vars
	private Graph graph;
	
	/**
	 * Default Constructor
	 */
	public TownGraphManager()
	{
		graph = new Graph();
	}
	/**
	 * Adds road to graph
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		if (graph.addEdge(new Town(town1), new Town(town2), weight, roadName) == null)
			return false;
		return true;
		
	}
	/**
	 * Gets road from graph
	 */
	@Override
	public String getRoad(String town1, String town2) {
		return graph.getEdge(new Town(town1), new Town(town2)).getName();
	}
	/**
	 * Adds town to graph
	 */
	@Override
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}
	/**
	 * Gets town from graph
	 */
	@Override
	public Town getTown(String name) {
		for (Town town : graph.vertexSet())
		{
			if (town.getName().equals(name))
				return town;
		}
		return null;
	}
	/**
	 * Checks if graph contains town
	 */
	@Override
	public boolean containsTown(String v) {
		for (Town town : graph.vertexSet())
		{
			if (town.getName().equals(v))
				return true;
		}
		return false;
	}
	/**
	 * Checks if graph contains edge
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.containsEdge(new Town(town1), new Town(town2));
	}
	/**
	 * Returns road set
	 */
	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> roads = new ArrayList<String>();
		for (Road road : graph.edgeSet())
		{
			roads.add(road.getName());
		}
		Collections.sort(roads);
		return roads;
	}
	/**
	 * Removes road from graph
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		if (graph.removeEdge(new Town(town1), new Town(town2), -1, road) == null)
			return false;
		return true;
	}
	/**
	 * Removes town from graph
	 */
	@Override
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}
	/**
	 * Return town set
	 */
	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> towns = new ArrayList<String>();
		for (Town town : graph.vertexSet())
		{
			towns.add(town.getName());
		}
		Collections.sort(towns);
		return towns;
	}
	/**
	 * Returns shortest path between two towns
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		ArrayList<String> path = graph.shortestPath(new Town(town1), new Town(town2));
		if (String.join(" ", path).contains("placeholder"))
			return new ArrayList<String>();
		return path;
	}
	/**
	 * Populates graph with towns and edges from file
	 * 
	 * @param selectedFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void populateTownGraph(File selectedFile) throws FileNotFoundException, IOException{
		Scanner fileIn = new Scanner(selectedFile);
		while (fileIn.hasNextLine())
		{
			String[] data = fileIn.nextLine().split(";");
			Town source = new Town(data[1]);
			Town destination = new Town(data[2]);
			graph.addVertex(source);
			graph.addVertex(destination);
			data = data[0].split(",");
			graph.addEdge(source, destination, Integer.parseInt(data[1]), data[0]);
		}
	}

}
