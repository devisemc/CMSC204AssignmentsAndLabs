/**
 * Author: Donovan deVise
 * Professor Eivazi
 * CMSC-204-36708
 * 4/27/2022
 */
import java.util.ArrayList;

/**
 * Class to represent a town
 */
public class Town implements Comparable<Town>{
	// Instance Vars
	private String name;
	private ArrayList<Town> adjTowns;
	
	/**
	 * Parameterized Constructor
	 * 
	 * @param name
	 */
	public Town(String name)
	{
		this.name = name;
	}
	/**
	 * Copy Constructor
	 * 
	 * @param templateTown
	 */
	public Town(Town templateTown)
	{
		name = templateTown.getName();
		adjTowns = templateTown.getAdjTowns();
	}
	// Getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Town> getAdjTowns()
	{
		return adjTowns;
	}
	public void setAdjTowns(ArrayList<Town> adjTowns) {
		this.adjTowns = adjTowns;
	}
	public int compareTo(Town o)
	{
		return name.compareTo(o.getName());
	}
	
	@Override
	public String toString() {
		return name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Town other = (Town) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
