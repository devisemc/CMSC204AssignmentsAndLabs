/**
 * Author: Donovan deVise
 * Professor: Prof. Eivazi
 * Class: CMSC-204-36708
 * Due Date: 4/12/2022
 */
/**
 * Class to represent a node in a tree
 * 
 * @author Donovan deVise
 *
 * @param <T>
 */
public class TreeNode<T> {
	// Instance Vars
	private T data;
	private TreeNode<T> left, right;
	
	// Getters and Setters
	public TreeNode<T> getLeft() {
		return left;
	}
	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}
	public TreeNode<T> getRight() {
		return right;
	}
	public void setRight(TreeNode<T> right) {
		this.right = right;
	}
	public void setData(T data) {
		this.data = data;
	}
	/**
	 * Creates a new TreeNode with data set to dataNode and left and right set to null
	 * @param dataNode
	 */
	public TreeNode(T dataNode)
	{
		data = dataNode;
		left = right = null;
	}
	/**
	 * Copy constructor used for deep copies
	 * @param node
	 */
	public TreeNode(TreeNode<T> node)
	{
		data = node.data;
		left = node.left;
		right = node.right;
	}
	/**
	 * 
	 * @return tree node data
	 */
	public T getData()
	{
		return data;
	}
}
