/**
 * @author Donovan deVise
 */
public class CourseDBElement implements Comparable<CourseDBElement> {
	// Instance Vars
	private String id;
	private int crn, credits;
	private String roomNum, instructorName;
	
	/**
	 * Parameterized constructor
	 * @param id
	 * @param crn
	 * @param credits
	 * @param roomNum
	 * @param instructorName
	 */
	public CourseDBElement(String id, int crn, int credits, String roomNum, String instructorName) {
		this.id = id;
		this.crn = crn;
		this.credits = credits;
		this.roomNum = roomNum;
		this.instructorName = instructorName;
	}
	/**
	 * No-Arg Constructor, initializes String vars to null and int vars to -1
	 */
	public CourseDBElement() {
		this.id = this.roomNum = this.instructorName = "null";
		this.crn = this.credits = -1;
	}
	/**
	 * Custom hashCode function to base hash code on CRN
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + crn;
		return result;
	}
	// Getters and Setters
	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public int getCRN() {
		return crn;
	}

	public void setCRN(int crn) {
		this.crn = crn;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	/**
	 * Overridden compareTo() function
	 */
	@Override
	public int compareTo(CourseDBElement o) {
		if (this.crn < o.getCRN())
			return -1;
		else if (this.crn > o.getCRN())
			return 1;
		else return 0;
	}
}
