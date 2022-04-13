/**
 * Author: Donovan deVise
 * Professor: Prof. Eivazi
 * Class: CMSC-204-36708
 * Due Date: 4/12/2022
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Utility class that uses MorseCodeTree to convert from Morse code to English
 * 
 * @author Donovan deVise
 */
public class MorseCodeConverter {
	// Instance Vars
	private static MorseCodeTree tree;
	
	/**
	 * Default Constructor
	 */
	public MorseCodeConverter()
	{
		tree = new MorseCodeTree();
	}
	/**
	 * Converts from Morse code to English
	 * @param text
	 * @return
	 */
	public static String convertToEnglish(String text)
	{
		tree = new MorseCodeTree();
		String[] words = text.split(" / ");
		String[] letters;
		String word = "";
		for (int i = 0; i < words.length; i++)
		{
			letters = words[i].split(" ");
			for (int j = 0; j < letters.length; j++)
			{
				word += tree.fetch(letters[j]);
			}
			word += ' ';
		}
		return word.substring(0, word.length()-1);
	}
	/**
	 * Converts file of Morse code to English
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String convertToEnglish(File file) throws FileNotFoundException
	{
		Scanner fileIn = new Scanner(file);
		String text = fileIn.nextLine();
		fileIn.close();
		return convertToEnglish(text);
	}
	/**
	 * @return String representation of Morse code tree
	 */
	public static String printTree() {
		tree = new MorseCodeTree();
		String treeStr = "";
		for (String letter : tree.toArrayList())
		{
			treeStr += letter + " ";
		}
		return treeStr.substring(0, treeStr.length()-1);
	}
}
