/**
 * Author: Donovan deVise
 * Professor: Prof. Eivazi
 * Class: CMSC-204-36708
 * Due Date: 4/12/2022
 */
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * STUDENT JUnit class
 * 
 * @author Donovan deVise
 */
class MorseCodeTreeTestSTUDENT {
	MorseCodeTree tree;
	ArrayList<String> testArr;
	@BeforeEach
	void setUp() throws Exception {
		tree = new MorseCodeTree();
	}

	@AfterEach
	void tearDown() throws Exception {
		tree = null;
		testArr = null;
	}

	@Test
	void test() {
		testArr = tree.toArrayList();
		assertEquals(testArr.get(0), "h");
		assertEquals(testArr.get(1), "s");
		assertEquals(testArr.get(testArr.size()-1), "o");
		assertEquals(tree.fetch("."), "e");
		
		tree.setRoot(new TreeNode<String>(""));
		assertEquals(tree.toArrayList().size(), 1);
		
	}
}
