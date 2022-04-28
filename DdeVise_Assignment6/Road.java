/**
 * Author: Donovan deVise
 * Professor Eivazi
 * CMSC-204-36708
 * 4/27/2022
 */
import java.util.Arrays;

/**
 * 
 * Class to represent a road
 *
 */
public class Road implements Comparable<Road>{
	// Instance Vars
	private Town[] endpoints;
	private int weight;
	private String name;
	
	// Parameterized Constructor
	public Road(Town source, Town destination, int degrees, String name)
	{
		endpoints = new Town[2];
		endpoints[0] = source;
		endpoints[1] = destination;
		weight = degrees;
		this.name = name;
	}
	// Parameterized Constructor, default weight = 1
	public Road(Town source, Town destination, String name)
	{
		endpoints = new Town[2];
		endpoints[0] = source;
		endpoints[1] = destination;
		weight = 1;
		this.name = name;
	}
	// Getters and setters
	public Town getSource() {
		return endpoints[0];
	}
	
	public Town getDestination() {
		return endpoints[1];
	}

	public void setSource(Town source) {
		endpoints[0] = source;
	}
	
	public void setDestination(Town destination) {
		endpoints[1] = destination;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int compareTo(Road o)
	{
		return name.compareTo(o.getName());
	}
	
	public boolean contains(Town town)
	{
		if (endpoints[0].equals(town) || endpoints[1].equals(town))
			return true;
		return false;
	}
	
	public boolean equals(Object r)
	{
		Road road = (Road)r;
		//System.out.println(r);
		//return endpoints[0].equals(((Road)r).getSource()) && endpoints[1].equals(((Road)r).getDestination());
		return road.contains(endpoints[0]) && road.contains(endpoints[1]);
	}

	@Override
	public String toString() {
		return "Road [endpoints=" + Arrays.toString(endpoints) + ", weight=" + weight + ", name=" + name + "]";
	}
	
}
