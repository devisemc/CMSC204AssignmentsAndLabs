/**
 * Author: Donovan deVise
 * Subject: CMSC-204-36708
 * Professor: Prof Eivazi
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T> {
	// Attrs
	protected Node head, tail;
	protected int size;
	
	/**
	 * Default Constructor
	 */
	public BasicDoubleLinkedList()
	{
		head = tail = null;
		size = 0;
	}
	/**
	 * Adds to end of list
	 * @param data
	 */
	public void addToEnd(T data)
	{
		Node newNode = new Node(data);
		if (size == 0)
		{
			head = tail = newNode;
		}
		else
		{
			// Connect with previous node
			tail.next = newNode;
			newNode.prev = tail;
			// Set as new tail
			tail = newNode;
		}
		size++;
	}
	/**
	 * Adds to front of list
	 * @param data
	 */
	public void addToFront(T data)
	{
		Node newNode = new Node(data);
		if (size == 0)
		{
			head = tail = newNode;
		}
		else
		{
			// Connect with previous node
			head.prev = newNode;
			newNode.next = head;
			// Set as new head
			head = newNode;
		}
		size++;
	}
	/**
	 * returns head of list
	 * @return
	 */
	public T getFirst()
	{
		return head.data;
	}
	/**
	 * returns tail of list
	 * @return
	 */
	public T getLast()
	{
		return tail.data;
	}
	/**
	 * returns size var
	 * @return
	 */
	public int getSize()
	{
		return size;
	}
	/**
	 * returns custom iterator object
	 */
	public ListIterator<T> iterator()
	{
		return new DoubleLinkedListIterator();
	}
	/**
	 * finds node with matching data and removes it from list
	 * 
	 * @param targetData
	 * @param comparator
	 * @return removed node
	 */
	public Node remove(T targetData, Comparator<T> comparator)
	{
		Node current = head;
		Node temp;
		while (current != null) {
			if (comparator.compare(targetData, current.data) == 0)
			{
				size--;
				// Save data
				temp = current;
				// Removal
				if (current.next == null) // If replacing tail
				{
					tail = current.prev;
					tail.next = null;
				}
				else if (current.prev == null) // If replacing head
				{
					head = current.next;
					head.prev = null;
				}
				else
				{
					current.next.prev = current.prev;
					current.prev.next = current.next;
				}
				// Output removed node
				return temp;
			}
			current = current.next;
		}
		return null;
	}
	/**
	 * removes first element and returns it
	 * @return
	 */
	public T retrieveFirstElement()
	{
		if (size == 0)
			return null;
		// Save info
		T temp = head.data;
		if (size == 1)// If only one node is present
		{
			head = tail = null;
			return temp;
		}
		else if (size == 2)// If only two nodes are present
		{
			head = tail;
			return temp;
		}
		//Set new head
		head = head.next;
		head.prev = null;
		// Return removed info
		size--;
		return temp;
	}
	/**
	 * removes last element and returns it
	 * @return
	 */
	public T retrieveLastElement()
	{
		if (size == 0)
			return null;
		// Save info
		T temp = tail.data;
		if (size == 1)// If only one node is present
		{
			head = tail = null;
			return temp;
		}
		else if (size == 2)// If only two nodes are present
		{
			tail = head;
			return temp;
		}
		// Set new tail
		tail = tail.prev;
		tail.next = null;
		// Return removed info
		size--;
		return temp;
	}
	/**
	 * Represents list as arraylist
	 * @return
	 */
	public ArrayList<T> toArrayList()
	{
		ArrayList<T> items = new ArrayList<T>();
		ListIterator<T> it = iterator();
		while (it.hasNext()) {
			items.add(it.next());
		}
		return items;
	}
	/**
	 * Nested node class
	 * @author Donovan deVise
	 *
	 */
	class Node
	{
		protected T data;
		protected Node prev, next;
		
		public Node(T dataNode)
		{
			data = dataNode;
		}
	}
	/**
	 * Nested iterator class
	 * @author Donovan deVise
	 *
	 */
	class DoubleLinkedListIterator implements ListIterator<T>
	{
		private Node currentNodeNext, currentNodePrev;
		
		public DoubleLinkedListIterator()
		{
			currentNodeNext = currentNodePrev = new Node(null);
			
			currentNodeNext.next = head;
		}
		@Override
		public boolean hasNext() {
			return currentNodeNext.next != null;
		}

		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException("Illegal call to next(); no element ahead");
			currentNodeNext = currentNodeNext.next;
			currentNodePrev.prev = currentNodeNext;
			return currentNodeNext.data;
		}

		@Override
		public boolean hasPrevious() {
			return currentNodePrev.prev != null;
		}

		@Override
		public T previous() {
			if (!hasPrevious())
				throw new NoSuchElementException("Illegal call to previous(); no element ahead");
			currentNodePrev = currentNodePrev.prev;
			currentNodeNext.next = currentNodePrev;
			return currentNodePrev.data;
		}

		@Override
		public int nextIndex() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T e) throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(T e) throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}
		
	}
}
