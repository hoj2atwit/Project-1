
import java.util.Arrays;


public class LinkedBag <T> implements BagInterface <T> {
private Node head;
	
	private int numberOfEntries;
	
	public LinkedBag () {
		head = null;
		numberOfEntries = 0;
		
	}
	
	
	
	public int getCurrentSize() {
		/* int count = 0;
		for (Node currNode = head; currNode != null; currNode = currNode.getNext()) 
			count ++;
		
		return count;
		*/
		return numberOfEntries;
	}
	
	
	public boolean isEmpty() {
		
		if (head == null) {
			assert (numberOfEntries == 0);
			return true;
		}
		return false;
	}
	
	public boolean add(T newEntry) {
		
		Node newNode = new Node (newEntry, head);
		head = newNode;
		numberOfEntries ++;
		return true;
	}
	
	
	public boolean remove(T anEntry) {
		
		for (Node currNode = head; currNode != null; currNode = currNode.getNext() ) {
			if (anEntry.equals (currNode.getData())) {
				currNode.setData(head.getData());
				head = head.getNext();
				numberOfEntries --;
				return true;
			}
		}
		return false;
	}
	
	
	public T remove() {
		if (isEmpty())
			return null;
		T outData = head.getData();
		head = head.getNext();
		numberOfEntries --;
		return outData;
	}
	
	
	public void clear() {
		while (!isEmpty())
			remove();
	}
	

	/**
	  Tests whether a certain item is in the bag
	  @param an object of type T
	  @return true if the item is in the bag; false otherwise
	*/
	public boolean contains(T anEntry) {
		
		for (Node currNode = head; currNode != null; currNode = currNode.getNext() ) {
			if (anEntry.equals (currNode.getData())) 
				return true;
		}
		return false;
			
	}


	
	/**
	  Counts the number of times a given item occurs in the bag
	  @param an object of type T
	  @return the number of times the specified item occurs in the bag
	*/
	public int getFrequencyOf (T anEntry) {
		int count = 0;
		for (Node currNode = head; currNode != null; currNode = currNode.getNext() ) {
			if (anEntry.equals (currNode.getData())) 
				count ++;
		}
		return count;
	}
	
	/**
	  Retrieves all entries that are in the bag
	  @return a newly allocated array of all the entries in the bag.
	  Note: if the bag is empty, the array is also empty.
               Although the method is declared as array of type T, the runtime type is Object
	*/
	
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T []) new Object [numberOfEntries];
		int idx = 0;
		for (Node currNode = head; currNode != null; currNode = currNode.getNext() ) {
			result[idx++] = currNode.getData();
		}
		return result;
	}

             /**
	  Retrieves all entries that are in the bag
	  @return a newly allocated array of all the entries in the bag.
               The runtime type of the new array is that of the array a
	  Note: if the bag is empty, the array is also empty.
           */
	public T[] toArray(T[] a) {
		T[] result = Arrays.copyOf (a, numberOfEntries);
		int idx = 0;
		for (Node currNode = head; currNode != null; currNode = currNode.getNext() ) {
			result[idx++] = currNode.getData();
		}
		return result;		
	}
	
	private Node getHead() {
		return head;
	}
	public LinkedBag <T>  copy () {
		  T[] arr = toArray();
		  LinkedBag <T> result = new LinkedBag <T> ();
		  for (int idx = 0; idx < numberOfEntries; idx ++)
			  result.add (arr[idx]);
		  return result;
	  }
	  
	  public LinkedBag <T> union (LinkedBag <T> other) {
		  LinkedBag <T> unionBag = new LinkedBag();
		  Node currNode;
		  for (currNode = head; currNode != null; currNode = currNode.getNext()) {
			  unionBag.add (currNode.getData());
		  }	  
		  for (currNode = other.getHead(); currNode != null; currNode = currNode.getNext()) {
			  unionBag.add (currNode.getData());
		  }
		  return unionBag;
	  }
	  
	  public LinkedBag <T> intersection (LinkedBag <T> other) {
		  T item;
		  T[] arr = toArray();
		  LinkedBag <T> result = copy();
		  LinkedBag <T> otherCopy = other.copy();
		 for (int idx = 0; idx < arr.length; idx ++) {
			item = arr[idx];
			if (otherCopy.contains (item))
				otherCopy.remove (item);
			else
				result.remove (item);
		 }
		 
		 return result;
	  }
	
	private class Node {
		private T data;
		private Node next;
		
		public Node (T data, Node nextNode) {
			this.data = data;
			next = nextNode;
		}
		
		public Node (T data) {
			this(data, null);
		}
		
		public T getData() {
			return data;
		}
		
		public Node getNext () {
			return next;
		}
		
		public void setData (T newData) {
			data = newData;
		}
		
		public void setNext (Node nextNode) {
			next = nextNode;
		}
		
		public Node copyData() {
			return new Node (data);
		}
	}

	@Override
	public boolean removeAll(T anEntry) {
		int counter = 0;
		while(remove(anEntry)) {
			counter++;
		}
		return (counter > 0);
	}
}
