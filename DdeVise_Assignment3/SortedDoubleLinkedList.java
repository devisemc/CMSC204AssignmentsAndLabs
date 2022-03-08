/**
 * Author: Donovan deVise
 * Subject: CMSC-204-36708
 * Professor: Prof Eivazi
 */
import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> implements Iterable<T> {
	// attrs
	Comparator<T> listComparator;
	/**
	 * Parameterized constructor
	 * @param compareableObject
	 */
	public SortedDoubleLinkedList(Comparator<T> compareableObject)
	{
		super();
		listComparator = compareableObject;
	}
	/**
	 * Adds to list at correct spot, sorted from least to greatest
	 * @param data
	 */
	public void add(T data)
	{
		Node newNode = new Node(data);
		Node current = head;
		boolean added = false;
		T currentData;
		if (size == 0) // If list is empty
		{
			added = true;
			head = tail = newNode;
		}
		else
		{
			while (true)
			{
				currentData = current.data;
				if (listComparator.compare(data, currentData) < 0)
				{
					added = true;
					if (current == head) //if current is head, make newNode the new head
					{
						current.prev = newNode;
						newNode.next = current;
						head = newNode;
					}
					else
					{
						// set new nodes attrs
						newNode.next = current;
						newNode.prev = current.prev;
						
						//insert
						current.prev.next = newNode;
						current.prev = newNode;
					}
					break;
				}
				if (current.next != null)
					current = current.next;
				else break;
			}
		}
		if (!added)
		{
			current.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;
	}
	public void addToEnd(T data) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	public void addToFront(T data)
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	public ListIterator<T> iterator()
	{
		return super.iterator();
	}
	public Node remove(T data, Comparator<T> comparator)
	{
		return super.remove(data, comparator);
	}
}
