import java.util.*;
public class UnsortedLinkedDictionary<K, V> {
    private Node first; 
    private Node last; 
    private int count;

    public static void main(String[] args) {
        System.out.print("I've recently given my child a phone that I need to add some important phone numbers in: ");
        String emergency = "police";
        String uncle = "uncle";
        String aunt = "aunt";
        String godfather = "godfather";
        String godmother = "godmother";
        String dad = "father";
        String mom = "mother";
        String brother = "brother";
        String sister = "sister";
        String grandfather = "grandfather";
        String grandmother = "grandmother";

        System.out.println(emergency + ", " + uncle + ", " + aunt + ", " + godfather + ", " + godmother + ", " + dad + ", " + mom + ", " + brother + ", " + sister + ", " + grandfather + ", " + grandmother);

        System.out.println("\nIn order to add these names to my phone's software, let us use a dictionary");
        System.out.println("Creating dictionary... ");
        UnsortedLinkedDictionary <String, String> phoneNumbers = new UnsortedLinkedDictionary<String, String>();

        System.out.println("Is this list empty? " + (String.valueOf(phoneNumbers.isEmpty())).toUpperCase());

        System.out.println("Let's now add all of these numbers");
        phoneNumbers.add(emergency, "911");
        phoneNumbers.add(uncle, "1");
        phoneNumbers.add(aunt, "2");
        phoneNumbers.add(godfather, "3");
        phoneNumbers.add(godmother, "4");
        phoneNumbers.add(dad, "5");
        phoneNumbers.add(mom, "6");
        phoneNumbers.add(brother, "7");
        phoneNumbers.add(sister, "8");
        phoneNumbers.add(grandfather, "9");
        phoneNumbers.add(grandmother, "10");

        System.out.println("\nIs this list empty now? " + (String.valueOf(phoneNumbers.isEmpty())).toUpperCase());
        System.out.println("How many elements are in it, then? " + phoneNumbers.getSize());
        System.out.println("\nDoes the list contain " + brother + "? " + (String.valueOf(phoneNumbers.contains(brother))).toUpperCase());
        System.out.println("How about the child's teacher? " + (String.valueOf(phoneNumbers.contains("teacher"))).toUpperCase());
        System.out.println("\nWe live in a pretty safe place, so I don't think the " + emergency + "number is needed. let us remove it.");
        phoneNumbers.remove(emergency);
        System.out.println("How many elements are in it now? " + phoneNumbers.getSize());
        System.out.println("\nI actually don't really like this list. Let's start over. ");
        phoneNumbers.clear();
        System.out.println("How many elements are in it now? " + phoneNumbers.getSize());
        System.out.println("Is this list empty now? " + (String.valueOf(phoneNumbers.isEmpty())).toUpperCase());
    }

    public UnsortedLinkedDictionary() { //constructor
        first = null; 
        last = null; 
        count = 0;
    }  //end UnsortedLinkedDictionary

    public V add(K key, V value) {
        V result = null;
        if (contains(key)) { //if there exists a key, replace the key's old value with the new value
            Node current = first;
            while (current != null) {
                if (key.equals(current.getKey())) { //iterating until we get key
                    result = current.getValue(); 
                    current.setValue(value); 
                    break;
                }
                else {
                    current = current.getNextNode();
                } 
            } 
        }
        else { //if key does not exist, create a new key and update node indexes
            Node updated = new Node(key, value); 
            if (isEmpty()) { //if this node is the first node, make this node the first node
                first = updated;
            }
            else { //if not the first, modify ONLY the last node
                last.setNextNode(updated);
            }
            last = updated;
            count++; //increase count of nodes
        }
        return result;
    } //end add

    public V remove(K key) {
        V result = null;
        if (contains(key)) {
            Node current = first;
            while (current != null) {
                //case 1: target very first index
                if (current.equals(first) && key.equals(current.getKey())) {
                    //splicing off the very first node and making the node.next() the first count
                    result = current.getValue();
                    first = first.getNextNode();
                    //if we are removing the one and only node
                    if (count == 1) {
                        last = null; 
                    }
                    //since we've removed the first node
                    count--;
                    break;
                }
                //case 2: target is at everything after the first index
                else if (key.equals(current.getNextNode().getKey())) {
                    //"capturing" the node before and after the target, splicing off the target, reconnecting the before and after
                    Node previous = current;
                    Node target = previous.getNextNode();
                    Node after = target.getNextNode();
                    result = target.getValue();
                    previous.setNextNode(after); 
                    if (last.equals(target)) { //no new connection to be made, snip off the end and close the line.
                        last = previous; 
                    }
                    count--; //-1 node, so -1 count
                    break;
                } 
                 //iterating through until we find a match
                else {
                    current = current.getNextNode();
                }
            }
        }
        return result;
    } //end remove

    public V getValue(K key) {
        V result = null;
        if (contains(key)) { //if key exists, get its value. if not, return nothing/null
            Node current = first; //iterating through each node until it is found
            while (current != null) {
                if (key.equals(current.getKey())) {
                    result = current.getValue(); //capturing the value of the key
                    break;
                }
                else {
                    current = current.getNextNode();
                }
            }
        }
        return result;
    } //end getValue

    public boolean contains(K key) {
        boolean found = false;
        Node current = first;
        while (!found && (current != null)) { //going through every node and automatically breaking when found
            if (key.equals(current.getKey())) { //comparing if current has found target. otherwise, keep going
                found = true;
            }
            else {
                current = current.getNextNode();
            }
        } 
        return found;
    } //end contains

    public boolean isEmpty() {
        boolean result;
        if (count == 0)  { //empty!
            assert first == null; //checking if there is no first node
            result = true;
        }
        else { //not empty!
            assert first != null; //checking if first node exists
            result = false;
        } 
        return result;
    } //end isEmpty

    public int getSize() {
        return count;
    } //end getSize

    public final void clear() {
        //instead of deleting everything, just make all the previous elements inaccessible
        first = null;
        last = null;
        count = 0;
    } //end clear

    //accessor method for key iterator to properly iterate through a linkedlist
    public Iterator<K> getKeyIterator() {
        return new KeyIterator();
    } 

    //accessor method for value iterator to properly iterate through a linkedlist
    public Iterator<V> getValueIterator() {
        return new ValueIterator();
    } 

    // class to iterate through keys (K)
    private class KeyIterator implements Iterator<K> { 
        private Node current;

        private KeyIterator() {
            current = first;
        } 

        public boolean hasNext() {
            return current.getNextNode() != null;
        } 

        public K next() {
            K result = null;
            if (hasNext()) {
                current = current.getNextNode();
                result = current.getKey();
            }
            else {
                throw new NoSuchElementException();
            }
            return result;
        } 

        //iterators cannot remove
        public void remove(){
            throw new UnsupportedOperationException();
        }
    } 

    // class to iterate through values (V)
    private class ValueIterator implements Iterator<V> {
        private Node current;
        
        private ValueIterator() {
            current = first;
        } 

        public boolean hasNext() {
            return current.getNextNode() != null;
        } 

        public V next() {
            V result = null;
            if (hasNext()) {
                current = current.getNextNode();
                result = current.getValue();
            }
            else {
                throw new NoSuchElementException();
            }
            return result;
        } 

        //iterators cannot remove
        public void remove() {
            throw new UnsupportedOperationException();
        } 
    } 

    //provided class
    private class Node {
        private K key;
        private V value;
        private Node next;

        private Node(K searchKey, V dataValue) {
            key = searchKey;
            value = dataValue;
            next = null;
        } 

        private Node(K searchKey, V dataValue, Node nextNode) {
            key = searchKey;
            value = dataValue;
            next = nextNode;
        } 

        private K getKey() {
            return key;
        } 

        private V getValue() {
            return value;
        } 

        private void setValue(V newValue) {
            value = newValue;
        } 

        private Node getNextNode() {
            return next;
        } 

        private void setNextNode(Node nextNode) {
            next = nextNode;
        } 
    } 
} 