/**
 * Author: Donovan deVise
 * Professor Eivazi
 * CMSC-204-36708
 * 4/27/2022
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Class to represent a graph of towns and their connections
 */
public class Graph implements GraphInterface<Town, Road>{
	// Instance Vars
	private Set<Town> towns;
	private Set<Road> roads;
	private Map<Town, Road> shortestMap;
	
	/**
	 * Default Constructor
	 */
	public Graph()
	{
		towns = new HashSet<Town>();
		roads = new HashSet<Road>();
	}
	/**
	 * Returns an edge connecting two given towns
	 */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		
		for (Road road : roads)
		{
			if (road.contains(sourceVertex) && road.contains(destinationVertex))
				return new Road(sourceVertex, destinationVertex, road.getWeight(), road.getName());
		}
		return null;
	}
	/**
	 * Adds an edge with given values
	 */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (sourceVertex == null || destinationVertex == null)
			throw new NullPointerException();
		if (!towns.contains(sourceVertex) || !towns.contains(destinationVertex))
			throw new IllegalArgumentException();
		Road newEdge = new Road(sourceVertex, destinationVertex, weight, description);
		if (containsEdge(sourceVertex, destinationVertex))
			return null;
		roads.add(newEdge);
		return newEdge;
	}
	/**
	 * Adds a vertex with given town
	 */
	@Override
	public boolean addVertex(Town v) {
		int size = towns.size();
		towns.add(v);
		return size != towns.size();
	}
	/**
	 * Adds an edge with given towns
	 */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		for (Road road : roads)
		{
			if (road.equals(new Road(sourceVertex, destinationVertex, ""))) return true;
		}
		return false;
	}
	/**
	 * Checks if given town is a vertex in graph
	 */
	@Override
	public boolean containsVertex(Town v) {
		for (Town town : towns)
		{
			if (town.equals(v)) return true;
		}
		return false;
	}
	/**
	 * Return edges as set
	 */
	@Override
	public Set<Road> edgeSet() {
		return roads;
	}
	/**
	 * Return all edges containing given vertex
	 */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		if (vertex == null)
			throw new NullPointerException();
		if (!towns.contains(vertex))
			throw new IllegalArgumentException();
		Set<Road> adjacent = new HashSet<Road>();
		for (Road road : roads)
		{
			if (road.contains(vertex))
			{
				adjacent.add(road);
			}
		}
		return adjacent;
	}
	/**
	 * Removes edge from graph
	 */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		boolean valid;
		Road template = new Road(sourceVertex, destinationVertex, weight, description);
		for (Road road : roads)
		{
			if (road.equals(template))
			{
				valid = true;
				if (weight > -1 && road.getWeight() != weight)
					valid = false;
				if (description != null && !road.getName().equals(description))
					valid = false;
				if (valid)
				{
					roads.remove(road);
					return road;
				}
			}
		}
		return null;
	}
	/**
	 * Removes vertex from graph, and all edges connected to it
	 */
	@Override
	public boolean removeVertex(Town v) {
		int size = towns.size();
		towns.remove(v);
		if (size != towns.size())
		{
			Set<Road> roadsCopy = new HashSet<Road>();
			roadsCopy.addAll(roads);
			for (Road road : roadsCopy)
			{
				if (road.contains(v))
					removeEdge(road.getSource(), road.getDestination(), road.getWeight(), road.getName());
			}
			return true;
		}
		return false;
	}
	/**
	 * Returns towns as set
	 */
	@Override
	public Set<Town> vertexSet() {
		return towns;
	}
	/**
	 * Uses dijkstraShortestPath(), formats and returns the results
	 */
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		ArrayList<String> shortestArr = new ArrayList<String>();
		Town current = destinationVertex;
		Road data;
		while (!current.equals(sourceVertex))
		{
			data = shortestMap.get(current);
			shortestArr.add(0, data.getSource() + " via " + data.getName() + " to " + current + " " + data.getWeight() + " mi");
			current = data.getSource();
		}
		return shortestArr;
	}
	/**
	 * Uses Dijkstra's shortest path algorithm to calculate all shortest paths from a town
	 */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		// Create Map<Town, road with shortest path
		shortestMap = new HashMap<Town, Road>();
		// Populate Map
		for (Town t : towns)
		{
			shortestMap.put(t, new Road(sourceVertex, t, Integer.MAX_VALUE, "placeholder"));
		}
		// Create town queue
		Queue<Town> queue = new LinkedList<Town>();
		
		// Create prev queued list
		Set<Town> searched = new HashSet<Town>();
		
		// Add first town to map
		shortestMap.put(sourceVertex, new Road(sourceVertex, sourceVertex, 0, "reflexive"));
		// Add first town to queue
		queue.add(sourceVertex);
		searched.add(sourceVertex);
		
		// Algorithm
		while (queue.size() > 0)
		{
			Town current = queue.remove();
			
			for (Road r : roads)
			{
				if (r.contains(current))
				{
					Town source = current;
					Town destination;
					// Clarify destination
					if (r.getSource().equals(current))
					{
						destination = r.getDestination();
					}
					else 
					{
						destination = r.getSource();
					}
					// Queue town if not already searched
					if (!searched.contains(destination))
					{
						queue.add(destination);
						searched.add(destination);
					}
					// Add town data to map
					int upto = r.getWeight();
						// Calculate upto
					Town temp = source;
					while (!temp.equals(sourceVertex))
					{
						upto += shortestMap.get(temp).getWeight();
						temp = shortestMap.get(temp).getSource();
					}
					int prev = 0;
						// Calculate prev
					temp = destination;
					while (!temp.equals(sourceVertex))
					{
						prev += shortestMap.get(temp).getWeight();
						temp = shortestMap.get(temp).getSource();
					}
					// Check if shorter
					if (upto < prev)
					{
						shortestMap.put(destination, new Road(source, destination, r.getWeight(), r.getName() ));
					}
				}
			}
		}
	}
}