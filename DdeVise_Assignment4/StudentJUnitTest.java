import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * @author Donovan deVise
 */
class StudentJUnitTest {
	CourseDBManager mngr;
	CourseDBStructure struc;
	
	@BeforeEach
	void setUp() throws Exception {
		mngr = new CourseDBManager();
		struc = new CourseDBStructure(500);
	}

	@AfterEach
	void tearDown() throws Exception {
		mngr = null;
		struc = null;
	}

	@Test
	void test() {
		mngr.add("TestClass", 1234, 4, "HT304", "Dreezy");
		mngr.add("TestClass", 1234, 4, "HT304", "Dreezy");
		assertEquals(mngr.showAll().get(0), "\nCourse:TestClass CRN:1234 Credits:4 Instructor:Dreezy Room:HT304");
		try
		{
			assertEquals(mngr.showAll().get(1), "\nCourse:TestClass CRN:1234 Credits:4 Instructor:Dreezy Room:HT304");
			fail("This should have thrown an exception");
		}
		catch (IndexOutOfBoundsException e)
		{
		}
		struc.add(new CourseDBElement("TestClass2", 4321, 3, "PK106", "ebkdonny"));
		struc.add(new CourseDBElement("TestClass2", 4321, 3, "PK106", "ebkdonny"));
		assertEquals(struc.getTableSize(), 347);
		assertEquals(struc.showAll().size(), 1);
		try {
			assertEquals(struc.get(4321).getID(), "TestClass2");
		} catch (IOException e) {
			fail("This should not have thrown an exception.");
		}
	}

}
