/**
 * 
 * @author Donovan deVise
 *
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {
	PasswordCheckerUtility pcu;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		// Try valid password
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("faN92gbf942!@"));
		}
		catch (Exception e)
		{
			// Shouldn't reach here
			fail("Exception thrown. True was expected.");
		}
		// Try password that is too short
		assertThrows(LengthException.class, () -> PasswordCheckerUtility.isValidPassword("f!@"));

	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		// Try valid password
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("ieagvfASF4398!@"));
		}
		catch (Exception e)
		{
			// Shouldn't reach here
			fail("Exception thrown. True was expected.");
		}
		// Try password with no Uppercase Alpha characters
		assertThrows(NoUpperAlphaException.class, () -> PasswordCheckerUtility.isValidPassword("ieagvfrnb4398!"));
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		// Try valid password
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("DFASFsd4398!@"));
		}
		catch (Exception e)
		{
			// Shouldn't reach here
			fail("Exception thrown. True was expected.");
		}
		// Try password with no Lowercase Alpha characters
		assertThrows(NoLowerAlphaException.class, () -> PasswordCheckerUtility.isValidPassword("DFASFSD4398!@"));
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		// Try strong valid password
		try {
			assertFalse(PasswordCheckerUtility.isWeakPassword("!2Dfghjasdfsd"));
		}
		catch (Exception e)
		{
			// Shouldn't reach here
			fail("Exception thrown. True was expected.");
		}
		// Try weak password
		assertThrows(WeakPasswordException.class, () -> PasswordCheckerUtility.isWeakPassword("!2Dfghj"));
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		// Try valid password
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("fG1@sdgf"));
		}
		catch (Exception e)
		{
			// Shouldn't reach here
			fail("Exception thrown. True was expected.");
		}
		// Try password with 2 of the same character in a row
		assertThrows(InvalidSequenceException.class, () -> PasswordCheckerUtility.isValidPassword("ffG1@sdgf"));
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		// Try valid password
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("fG1@sdgf"));
		}
		catch (Exception e)
		{
			// Shouldn't reach here
			fail("Exception thrown. True was expected.");
		}
		// Try password with no digits
		assertThrows(NoDigitException.class, () -> PasswordCheckerUtility.isValidPassword("fGk@sdgf"));
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		// Try valid passwords
		try
		{
			assertTrue(PasswordCheckerUtility.isValidPassword("fG1!ohjb"));
			assertTrue(PasswordCheckerUtility.isValidPassword("lK9#gjsg"));
			assertTrue(PasswordCheckerUtility.isValidPassword("SGHDBgjh149!"));
			assertTrue(PasswordCheckerUtility.isValidPassword("wiIFEGB328%"));
		}
		catch (Exception e)
		{
			// Shouldn't reach here
			fail("Exception thrown. True was expected.");
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> words = new ArrayList<String>();
		words.add("fG1!ohjb");
		words.add("lK9#gjsg");
		words.add("SGHDBgjh149!");
		words.add("wiIFEGB328%");
		words.add("f");
		words.add("fk@djnfsakFDKJ");
		words.add("fasdFDS241");
		words.add("ffG1@sdgf");
		words.add("DFASFSD4398!@");
		assertEquals(PasswordCheckerUtility.getInvalidPasswords(words).size(), 5);
	}
	
}
