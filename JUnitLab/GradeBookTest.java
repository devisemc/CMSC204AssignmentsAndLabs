import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTest {
	GradeBook gb1;
	GradeBook gb2;
	@BeforeEach
	void setUp() throws Exception {
		gb1 = new GradeBook(5);
		gb1.addScore(5);
		gb1.addScore(10);
		gb2 = new GradeBook(5);
		gb2.addScore(2);
		gb2.addScore(1);
		gb2.addScore(-5);
	}

	@AfterEach
	void tearDown() throws Exception {
		gb1 = null;
		gb2 = null;
	}

	@Test
	void testAddScore() {
		assertEquals(gb1.toString(), "5.0 10.0 ");
		assertEquals(gb1.getScoreSize(), 2);
		assertEquals(gb2.toString(), "2.0 1.0 -5.0 ");
		assertEquals(gb2.getScoreSize(), 3);
	}

	@Test
	void testGetScoreSize() {
		assertEquals(gb1.getScoreSize(), 2);
		assertEquals(gb2.getScoreSize(), 3);
	}

	@Test
	void testToString() {
		assertEquals(gb1.toString(), "5.0 10.0 ");
		assertEquals(gb2.toString(), "2.0 1.0 -5.0 ");
	}

	@Test
	void testSum() {
		assertEquals(gb1.sum(), 15);
		assertEquals(gb2.sum(), -2);
	}

	@Test
	void testMinimum() {
		assertEquals(gb1.minimum(), 5);
		assertEquals(gb2.minimum(), -5);
	}

	@Test
	void testFinalScore() {
		assertEquals(gb1.finalScore(), 10);
		assertEquals(gb2.finalScore(), 3);
	}

}
