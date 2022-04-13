/**
 * Author: Donovan deVise
 * Professor: Prof. Eivazi
 * Class: CMSC-204-36708
 * Due Date: 4/12/2022
 */
import java.util.ArrayList;

/**
 * Class to represent morse code alphabet as a binary tree
 * @author Donovan deVise
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
	// Instance Vars
	private TreeNode<String> root;
	
	/**
	 * Default Constructor
	 */
	public MorseCodeTree()
	{
		root = new TreeNode<String>("");
		buildTree();
	}
	/**
	 * @return root of tree
	 */
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}
	/**
	 * @param newNode - new root of tree
	 */
	@Override
	public void setRoot(TreeNode<String> newNode) {
		root = newNode;
	}
	/**
	 * Inserts letter into correct position in tree
	 * @param code
	 * @param letter
	 */
	@Override
	public void insert(String code, String letter) {
		addNode(root, code, letter);
	}
	/**
	 * Recursive method to add node into correct position in tree
	 */
	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		// Base Case
		if (code.length() == 1)
		{
			if (code.equals("."))
				root.setLeft(new TreeNode<String>(letter));
			else if (code.equals("-"))
				root.setRight(new TreeNode<String>(letter));
			else System.out.println("ERROR");
			return;
		}
		// Recursive Algorithm
		if (code.charAt(0) == '.')
			addNode(root.getLeft(), code.substring(1), letter);
		else if (code.charAt(0) == '-')
			addNode(root.getRight(), code.substring(1), letter);
		else System.out.println("ERROR");
	}
	/**
	 * Gets letter from code
	 */
	@Override
	public String fetch(String code) {
		return fetchNode(root, code);
	}
	/**
	 * Recursive method to fetch node using code, . = left - = right
	 */
	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		if (code.length() < 1)
			return "";
		// Base Case
		if (code.length() == 1)
		{
			if (code.equals("."))
				return root.getLeft().getData();
			else if (code.equals("-"))
				return root.getRight().getData();
			else System.out.println("error");
		}
		// Recursive Algorithm
		if (code.charAt(0) == '.')
			return fetchNode(root.getLeft(), code.substring(1));
		else 
			return fetchNode(root.getRight(), code.substring(1));
	}
	/**
	 * Unsupported method
	 */
	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	/**
	 * Unsupported method
	 */
	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	/**
	 * Inserts each alphabet letter into tree with code
	 */
	@Override
	public void buildTree() {
		insert(".", "e");
		insert("-", "t");
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		insert("....", "h"); 
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
	}
	/**
	 * Converts tree to arraylist wtih LNR traversal
	 */
	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> treeArr = new ArrayList<String>();
		LNRoutputTraversal(root, treeArr);
		return treeArr;
	}
	/**
	 * Adds nodes of tree into list using LNR traversal
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		// Base Case, if no children
		if (root.getLeft() == null)
		{
			list.add(root.getData());
			return;
		}
		// Recursive Algorithm
		LNRoutputTraversal(root.getLeft(), list);
		list.add(root.getData());
		if (root.getRight() != null)
			LNRoutputTraversal(root.getRight(), list);
	}
}
