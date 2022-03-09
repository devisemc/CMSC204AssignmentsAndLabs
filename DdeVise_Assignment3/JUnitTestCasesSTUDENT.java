/**
 * Author: Donovan deVise
 * Subject: CMSC-204-36708
 * Professor: Prof Eivazi
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JUnitTestCasesSTUDENT {
	BasicDoubleLinkedList<String> stringList;
	@BeforeEach
	void setUp() throws Exception {
		stringList = new BasicDoubleLinkedList<String>();
		stringList.addToEnd("is");
		stringList.addToEnd("a");
		stringList.addToFront("This");
		stringList.addToEnd("Test");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testArrayList() {
		assertEquals(stringList.toArrayList().get(0), "This");
		assertEquals(stringList.toArrayList().get(1), "is");
		assertEquals(stringList.toArrayList().get(3), "Test");
	}
	
	@Test
	void testRemove() {
		stringList.remove("a", new StringComparator());
		assertEquals(stringList.toArrayList().get(2), "Test");
	}
	
	@Test
	void testRetrieve(){
		assertEquals(stringList.retrieveFirstElement(), "This");
		assertEquals(stringList.retrieveLastElement(), "Test");
		assertEquals(stringList.retrieveLastElement(), "a");
	}
	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
}
