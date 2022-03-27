import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Donovan deVise
 */
public class CourseDBStructure implements CourseDBStructureInterface{
	// Instance Vars
	ArrayList<LinkedList<CourseDBElement>> hashTable;
	
	/**
	 * Constructor, uses an estimated size n with a loading factor of 1.5 and finds next 4k+3 prime to use as size for hash table
	 * @param n
	 */
	public CourseDBStructure(int n)
	{
		// Calculate n/1.5 and add 1 to find NEXT greatest 4k+3
		int starting = (int) (n/1.5) + 1;
		// Create 4k+3 integer
		int currentNum = starting + (3 - (starting % 4));
		
		boolean prime = false;
		while (!prime)
		{
			// Check if prime
			prime = true;
			for (int i = 2; i <= currentNum / 2; i++)
			{
				if (currentNum % i == 0)
				{
					prime = false;
					break;
				}
			}
			if (!prime)
				currentNum += 4;
		}
		// Fill hashTable
		hashTable = new ArrayList<LinkedList<CourseDBElement>>();
		for (int i = 0; i < currentNum; i++)
		{
			hashTable.add(new LinkedList<CourseDBElement>());
		}
	}
	/**
	 * Constructor used solely for testing purposes
	 * 
	 * @param Testing
	 * @param size
	 */
	public CourseDBStructure(String Testing, int size)
	{
		// Fill hashTable
		hashTable = new ArrayList<LinkedList<CourseDBElement>>();
		for (int i = 0; i < size; i++)
		{
			hashTable.add(new LinkedList<CourseDBElement>());
		}
	}
	/**
	 * Adds element to hash table using its hash code to index
	 */
	@Override
	public void add(CourseDBElement element) {
		// Check if existed CRN exists
		LinkedList<CourseDBElement> bucket;
		for (int i = 0; i < hashTable.size(); i++)
		{
			bucket = hashTable.get(i);
			for (int j = 0; j < bucket.size(); j++)
			{
				if (bucket.get(j).getCRN() == element.getCRN())
				{
					bucket.set(j, element);
					return;
				}
			}
		}
		// Place new element into list
		int index = element.hashCode() % hashTable.size();
		hashTable.get(index).add(element);
	}
	/**
	 * Retrieves element from hash table using its CRN
	 * 
	 * @return retrieved element
	 */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		LinkedList<CourseDBElement> bucket;
		for (int i = 0; i < hashTable.size(); i++)
		{
			bucket = hashTable.get(i);
			for (int j = 0; j < bucket.size(); j++)
			{
				if (bucket.get(j).getCRN() == crn)
					return bucket.get(j);
			}
		}
		throw new IOException();
	}
	/**
	 * Collects all elements in hash table and returns as ArrayList
	 * 
	 * @return ArrayList<String> containing all hash table elements
	 */
	@Override
	public ArrayList<String> showAll() {
		// Instance Vars
		ArrayList<String> struc = new ArrayList<String>();
		LinkedList<CourseDBElement> bucket;
		CourseDBElement e;
		
		// Iterate through table
		for (int i = 0; i < hashTable.size(); i++)
		{
			bucket = hashTable.get(i);
			for (int j = 0; j < bucket.size(); j++)
			{
				e = bucket.get(j);
				if (e != null) // Won't print empty element
				{
					struc.add("\nCourse:" + e.getID() 
					+ " CRN:" + e.getCRN() 
					+ " Credits:" + e.getCredits() 
					+ " Instructor:" + e.getInstructorName() 
					+ " Room:" + e.getRoomNum());
				}
			}
		}
		return struc;
	}
	/**
	 * @return size of hash table
	 */
	@Override
	public int getTableSize() {
		return hashTable.size();
	}
}
