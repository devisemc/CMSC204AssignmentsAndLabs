import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility {
	/**
	 * 
	 * @param password
	 * @param passwordConfirm
	 * @throws UnmatchedException
	 */
	static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException
	{
		if (!password.equals(passwordConfirm))
			throw new UnmatchedException();
	}
	/**
	 * 
	 * @param password
	 * @param passwordConfirm
	 * @return
	 */
	static boolean comparePasswordsWithReturn(String password, String passwordConfirm)
	{
		return password.equals(passwordConfirm);
	}
	/**
	 * 
	 * @param passwords
	 * @return
	 */
	static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords)
	{
		ArrayList<String> invalid = new ArrayList<String>();
		for (int i = 0; i < passwords.size(); i++)
		{
			try
			{
				isValidPassword(passwords.get(i));
			}
			catch (Exception e)
			{
				invalid.add(passwords.get(i) + " -> " + e.getMessage());
			}
		}
		return invalid;
	}
	/**
	 * 
	 * @param password
	 * @return
	 */
	static boolean hasBetweenSixAndNineChars(String password)
	{
		return 6 <= password.length() && password.length() <= 9;
	}
	/**
	 * 
	 * @param password
	 * @return
	 * @throws NoDigitException
	 */
	static boolean hasDigit(String password) throws NoDigitException
	{
		if (!password.matches(".*\\d.*"))
			throw new NoDigitException();
		return true;
	}
	/**
	 * 
	 * @param password
	 * @return
	 * @throws NoLowerAlphaException
	 */
	static boolean hasLowerAlpha(String password) throws NoLowerAlphaException
	{
		if (!password.matches(".*[a-z].*"))
			throw new NoLowerAlphaException();
		return true;
	}
	/**
	 * 
	 * @param password
	 * @return
	 * @throws NoSpecialCharacterException
	 */
	static boolean hasSpecialChar(String password) throws NoSpecialCharacterException
	{
		if (!password.matches(".*[^A-Za-z0-9].*"))
			throw new NoSpecialCharacterException();
		return true;
	}
	/**
	 * 
	 * @param password
	 * @return
	 * @throws NoUpperAlphaException
	 */
	static boolean hasUpperAlpha(String password) throws NoUpperAlphaException
	{
		if (!password.matches(".*[A-Z].*"))
			throw new NoUpperAlphaException();
		return true;
	}
	/**
	 * 
	 * @param password
	 * @return
	 * @throws LengthException
	 */
	static boolean isValidLength(String password) throws LengthException
	{
		if (password.length() < 6)
			throw new LengthException();
		return true;
	}
	/**
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	static boolean isValidPassword(String text) throws Exception
	{
		try
		{
			if (
					isValidLength(text) &&
					hasDigit(text) &&
					hasUpperAlpha(text) &&
					hasLowerAlpha(text) &&
					hasSpecialChar(text) &&
					NoSameCharInSequence(text))
				return true;
		}
		catch (Exception e)
		{
			throw e;
		}
		// Should never reach this point
		return false;
	}
	/**
	 * 
	 * @param text
	 * @return
	 * @throws WeakPasswordException
	 */
	static boolean isWeakPassword(String text) throws WeakPasswordException
	{
		try
		{
			if (!(hasBetweenSixAndNineChars(text)) && isValidPassword(text))
				return false;
		}
		catch (Exception e)
		{
			throw new WeakPasswordException();
		}
		throw new WeakPasswordException();
	}
	/**
	 * 
	 * @param password
	 * @return
	 * @throws InvalidSequenceException
	 */
	static boolean NoSameCharInSequence(String password) throws InvalidSequenceException
	{
		for (int i = 1; i < password.length(); i++)
		{
			if (password.charAt(i) == password.charAt(i-1))
				throw new InvalidSequenceException();
		}
		return true;
	}
}
