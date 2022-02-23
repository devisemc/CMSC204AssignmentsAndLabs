import java.util.ArrayList;

public class MyStack<T> implements StackInterface {
	private ArrayList<T> stack;
	private int capacity = 10;
	/**
	 * Constructor with parameter
	 * 
	 * @param size
	 */
	public MyStack(int size) {
		stack = new ArrayList<T>(size);
		capacity = size;
	}
	/**
	 * Default Constructor
	 */
	public MyStack() {
		stack = new ArrayList<T>();
	}
	/**
	 * Checks if stack is empty
	 */
	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	/**
	 * Checks if stack is full
	 */
	@Override
	public boolean isFull() {
		return stack.size() >= capacity;
	}
	
	@Override
	public Object pop() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException();
		Object popped = stack.get(stack.size()-1);
		stack.remove(stack.size()-1);
		return popped;
	}

	@Override
	public Object top() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException();
		return stack.get(stack.size()-1);
	}

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public boolean push(Object e) throws StackOverflowException {
		if (isFull())
		{
			throw new StackOverflowException();
		}
		stack.add((T) e);
		return true;
	}

	@Override
	public String toString(String delimiter) {
		if (stack.size() == 0)
			return "";
		
		String s = "" + stack.get(0);
		for (int i = 1; i < stack.size(); i++)
			s += delimiter + stack.get(i);
		return s;
	}
	
	@Override
	public String toString() {
		return toString("");
	}

	@Override
	public void fill(ArrayList list) {
		for (int i = 0; i < list.size(); i++)
		{
			try {
				push((T) list.get(i));
			}catch(Exception e) {}
		}
	}

}
