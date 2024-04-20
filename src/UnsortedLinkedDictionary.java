import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnsortedLinkedDictionary <K, V>  {
	private Node first;
	private Node last;
	private int count;
	
	public static void main(String args[]) {
		// Add you tests here

	}
	public UnsortedLinkedDictionary() {
		this.first = null;
		this.last = null;
		count = 0;
	} // end default constructor
	
   	public V add(K key, V value) {
		V result = null;
		if (contains(key)) {
			Node currentNode = first; //iterating through nodes
			while (currentNode != null) {
				if (key.equals(currentNode.getKey())) { 
					result = currentNode.getValue();
					currentNode.setValue(value);
					break;
				}
				else {
					currentNode = currentNode.getNextNode();
				}
			}
		}
		else { //creating a new object if no key is present
			Node newNode = new Node (key, value);
			if (isEmpty()) {
				first = newNode;
			}
			else {
				last.setNextNode(newNode);
				last = newNode;
				count++;
			}
		}
		return result;
  	 } // end add

   	public V remove(K key) {
		V result = null;
		if(contains(key)){
			Node currentNode = first;
			while (currentNode != null) {
				//case 1: removing first and only entry
				if (currentNode.equals(first) && key.equals(currentNode.getKey())) {
					result = currentNode.getValue();
					first = first.getNextNode();
					if (count == 1) {
						last = null;
						count--;
						break;
					}
				}
				//case 2: removing !(case 1)
				else if (key.equals(currentNode.getNextNode().getKey())) {
					Node nodeBefore = currentNode;
					Node nodeToRemove = nodeBefore.getNextNode();
					Node nodeAfter = nodeToRemove.getNextNode();
					result = nodeToRemove.getValue();
					nodeBefore.setNextNode(nodeAfter); // disconnect the node to be removed
					// save entry to be removed
					if (last.equals(nodeToRemove))
					last = nodeBefore; // last node was removed
					count--;
					break;
				}
				//case 3 !(case 1 && case 2): incrementing
				else {
					currentNode = currentNode.getNextNode();
				}
			}
		}
		return result;
   	} // end remove

   	public V getValue(K key) {
		V result = null;
		if(contains(key)){
			Node currentNode = first;
			while (currentNode != null) {
				//case 1: removing first and only entry
				if (key.equals(currentNode.getKey())) {
					result = currentNode.getValue();
					break;
				}
				else {
					currentNode = currentNode.getNextNode();
				}
			}
		}
		return result;
   	} // end getValue

	public boolean contains(K key) {
		boolean found = false;
		Node currentNode = first;

		while (!found && (currentNode != null)) {
			if (key.equals(currentNode.getKey())) {
				found = true;
			}
			else { //iterate through the nodes
				currentNode = currentNode.getNextNode();
			}
		}
		return found;
   	} // end contains

   	public boolean isEmpty() {
		boolean result;
		if (count == 0) {
			assert first == null;
			result = true;
		}
		else {
			assert first != null;
			result = false;
		}
		return result;
   	} // end isEmpty
	
   	public int getSize() {
		return count;
   	} // end getSize

	public final void clear() { 
		first = null;
		last = null;
		count = 0;
	} // end clear

	private class Node {
		private K key;
		private V value;
		private Node next;

		private Node(K searchKey, V dataValue) {
			key = searchKey;
			value = dataValue;
			next = null;	
		} // end constructor
		
		private Node(K searchKey, V dataValue, Node nextNode) {
			key = searchKey;
			value = dataValue;
			next = nextNode;	
		} // end constructor
		
		private K getKey() {
			return key;
		} // end getKey
		
		private V getValue() {
			return value;
		} // end getValue

		private void setValue(V newValue) {
			value = newValue;
		} // end setValue

		private Node getNextNode()
		{
			return next;
		} // end getNextNode
		
		private void setNextNode(Node nextNode) {
			next = nextNode;
		} // end setNextNode
	} // end Node
} // end UnsortedLinkedDictionary
		
