import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author Donovan deVise
 */
public class CourseDBManager implements CourseDBManagerInterface{
	// Instance Vars
	CourseDBStructure structure;
	
	/**
	 * No-Arg constructor, initializes a structure with n=20
	 */
	public CourseDBManager()
	{
		structure = new CourseDBStructure(100);
	}
	/**
	 * Adds element to structure
	 */
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		structure.add(new CourseDBElement(id, crn, credits, roomNum, instructor));
	}
	/**
	 * Gets element from structure, prints error if IOException is thrown
	 */
	@Override
	public CourseDBElement get(int crn) {
		try {
			return structure.get(crn);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Fills structure using file data
	 */
	@Override
	public void readFile(File input) throws FileNotFoundException {
		// Instance Vars
		Scanner fileIn = new Scanner(input);
		String line;
		String[] data;
		CourseDBElement e;
		String name;
		
		while (fileIn.hasNextLine()) // Runs for every line in file
		{
			// Reset name var
			name = "";
			// Read in line
			line = fileIn.nextLine();
			// Split line into its pieces, using space key as delimiter
			data = line.split(" ");
			// Create new element using above values, leaving name blank
			e = new CourseDBElement(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3], null);
			// Create name using all data after room number
			for (int i = 4; i < data.length; i++)
			{
				name += data[i] + " ";
			}
			e.setInstructorName(name.trim()); //.trim() to cut trailing whitespace
			// Add Element
			structure.add(e);
		}
	}
	/**
	 * Uses showAll() function from CourseDBStructure
	 */
	@Override
	public ArrayList<String> showAll() {
		return structure.showAll();
	}
}
