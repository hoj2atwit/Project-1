
import java.util.Arrays;

public class LinkedBag<T> implements BagInterface<T> {
	// First node in LinkedBag
	private Node head;
	// Number of entries in LinkedBag
	private int numberOfEntries;
	
	// Constructor
	public LinkedBag() {
		head = null;
		numberOfEntries = 0;
	}

	/**
	 * Returns the number of entries in bag
	 */
	public int getCurrentSize() {
		/*
		 * int count = 0; for (Node currNode = head; currNode != null; currNode =
		 * currNode.getNext()) count ++;
		 * 
		 * return count;
		 */
		return numberOfEntries;
	}

	/**
	 * Returns true if head Node is null; false otherwise
	 */
	public boolean isEmpty() {
		if (head == null) {
			assert (numberOfEntries == 0);
			return true;
		}
		return false;
	}

	/**
	 * Adds a new node to the front of the chain with data anEntry
	 */
	public boolean add(T newEntry) {
		Node newNode = new Node(newEntry, head);
		head = newNode;
		numberOfEntries++;
		return true;
	}

	/**
	 * Sets the data of the node w/ specified data as the first node's data, and removes first node.
	 * Second node in LinkedBag is now the new head
	 */
	public boolean remove(T anEntry) {
		for (Node currNode = head; currNode != null; currNode = currNode.getNext()) {
			if (anEntry.equals(currNode.getData())) {
				currNode.setData(head.getData());
				head = head.getNext();
				numberOfEntries--;
				return true;
			}
		}
		return false;
	}

	/**
	 * Head is removed, second node is set as new head
	 */
	public T remove() {
		if (isEmpty())
			return null;
		T outData = head.getData();
		head = head.getNext();
		numberOfEntries--;
		return outData;
	}

	/**
	 * Removes all the nodes from the first one to the last until LinkedBag is empty
	 */
	public void clear() {
		while (!isEmpty())
			remove();
		/**
		 * Possible alternative:
		 * head = null;
		 * numberOfEntries = 0;
		 */
	}

	/**
	 * Tests whether a certain item is in the bag
	 * 
	 * @param an object of type T
	 * @return true if the item is in the bag; false otherwise
	 */
	public boolean contains(T anEntry) {
		for (Node currNode = head; currNode != null; currNode = currNode.getNext()) {
			if (anEntry.equals(currNode.getData()))
				return true;
		}
		return false;

	}

	/**
	 * Counts the number of times a given item occurs in the bag
	 * 
	 * @param an object of type T
	 * @return the number of times the specified item occurs in the bag
	 */
	public int getFrequencyOf(T anEntry) {
		int count = 0;
		for (Node currNode = head; currNode != null; currNode = currNode.getNext()) {
			if (anEntry.equals(currNode.getData()))
				count++;
		}
		return count;
	}

	/**
	 * Retrieves all entries that are in the bag
	 * 
	 * @return a newly allocated array of all the entries in the bag. Note: if the
	 *         bag is empty, the array is also empty. Although the method is
	 *         declared as array of type T, the runtime type is Object
	 */

	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];
		int idx = 0;
		for (Node currNode = head; currNode != null; currNode = currNode.getNext()) {
			result[idx++] = currNode.getData();
		}
		return result;
	}

	/**
	 * Retrieves all entries that are in the bag
	 * 
	 * @return a newly allocated array of all the entries in the bag. The runtime
	 *         type of the new array is that of the array a Note: if the bag is
	 *         empty, the array is also empty.
	 */
	public T[] toArray(T[] a) {
		T[] result = Arrays.copyOf(a, numberOfEntries);
		int idx = 0;
		for (Node currNode = head; currNode != null; currNode = currNode.getNext()) {
			result[idx++] = currNode.getData();
		}
		return result;
	}

	/**
	 * Gets the head node
	 * @return
	 */
	private Node getHead() {
		return head;
	}

	/**
	 * @return Returns a new LinkedBag<T> that contains all elements of the original LinkedBag<T>
	 */
	public LinkedBag<T> copy() {
		T[] arr = toArray();
		LinkedBag<T> result = new LinkedBag<T>();
		for (int idx = 0; idx < numberOfEntries; idx++)
			result.add(arr[idx]);
		return result;
	}

	/**
	 * Creates a LinkedBag<T> Containing all elements of two LinkedBags
	 * @param other: LinkedBag<T>
	 * @return LinkedBag<T> Containing all elements of this LinkedBag and input LinkedBag
	 */
	public LinkedBag<T> union(LinkedBag<T> other) {
		LinkedBag<T> unionBag = new LinkedBag();
		Node currNode;
		for (currNode = head; currNode != null; currNode = currNode.getNext()) {
			unionBag.add(currNode.getData());
		}
		for (currNode = other.getHead(); currNode != null; currNode = currNode.getNext()) {
			unionBag.add(currNode.getData());
		}
		return unionBag;
	}

	/**
	 * Creates a LinkedBag<T> containing elements in both this LinkedBag and input LinkedBag
	 * @param other: LinkedBag<T>
	 * @return LinkedBag<T> containing elements in both this LinkedBag and input LinkedBag
	 */
	public LinkedBag<T> intersection(LinkedBag<T> other) {
		T item;
		T[] arr = toArray();
		LinkedBag<T> result = copy();
		LinkedBag<T> otherCopy = other.copy();
		for (int idx = 0; idx < arr.length; idx++) {
			item = arr[idx];
			if (otherCopy.contains(item))
				otherCopy.remove(item);
			else
				result.remove(item);
		}

		return result;
	}

	/**
	 * Node class used to contain the data of LinkedBag
	 * @author leey2, Jonny Ho
	 */
	private class Node {
		// Data inside the node; Entry
		private T data;
		// Reference to the next node in chain
		private Node next;
		
		/**
		 * Constructor that specifies data in node to be made and the node to be placed in the following chain
		 * @param data
		 * @param nextNode
		 */
		public Node(T data, Node nextNode) {
			this.data = data;
			next = nextNode;
		}

		/**
		 * Constructor that specifies data in node to be made and sets next node as null
		 * @param data: Entry to be set as data
		 */
		public Node(T data) {
			this(data, null);
		}

		/**
		 * Returns data of current node
		 * @return T
		 */
		public T getData() {
			return data;
		}

		/**
		 * Returns the next node in chain
		 * @return Node
		 */
		public Node getNext() {
			return next;
		}

		/**
		 * Sets the data of the current node to input
		 * @param newData: Element to be set as the data
		 */
		public void setData(T newData) {
			data = newData;
		}

		/**
		 * Changes the next node in chain
		 * @param nextNode: Node to be set as the following node
		 */
		public void setNext(Node nextNode) {
			next = nextNode;
		}

		/**
		 * Makes a copy of the node with the same data
		 * @return Node
		 */
		public Node copyData() {
			return new Node(data);
		}
	}

	/**
	 * RemoveAll method that works just like clear(), implemented as BagInterface has removeAll()
	 */
	@Override
	public boolean removeAll(T anEntry) {
		int counter = 0;
		while (remove(anEntry)) {
			counter++;
		}
		return (counter > 0);
	}
}
