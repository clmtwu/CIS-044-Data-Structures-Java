import java.util.Objects;

/**
   A class that implements the ADT list by using a chain of nodes.
   The chain has both a head reference and a tail reference.
   @author Frank M. Carrano
   @version 3.
*/
public class LList2Lab<T> implements ListInterface1<T>
{
	public static void main(String[] args)  {
		LList2Lab <Integer> AFewNumbers = new LList2Lab <Integer>(); //reusing constructors from Lab 3
		LList2Lab <String> AString = new LList2Lab <String>();
		LList2Lab <String> AnotherDifferentString = new LList2Lab <String>();
		LList2Lab <String> TheSameStringAsTheLineAbove = new LList2Lab <String>();
		LList2Lab <Double> NotAString = new LList2Lab <Double>();
		LList2Lab <Double> UninitializedDouble = new LList2Lab <Double>();

		int duplicatenumber = 5; //arbitrary value that is duplicate, assigned to an int for debugging
		int impossiblenumber = 44; //arbitrary value that is not in the list, assigned to an int for debugging

		for (int i = 1; i <= 10; i++) {
			AFewNumbers.add(i);
		}
		AFewNumbers.add(duplicatenumber); //adding another arbitrary value to make it have duplicates (5 is between 0 and 9)

		AString.add("String");

		AnotherDifferentString.add("Different string!");
		AnotherDifferentString.add("With a different length!");

		TheSameStringAsTheLineAbove.add("Different string!");
		TheSameStringAsTheLineAbove.add("With a different length!");

		for (double i = 1; i <= 10; i++) {
			NotAString.add(i);
		}

		System.out.print("The List \"AFewNumbers\" with the values: ");
		for (int i = 1; i <= 11; i++) {
			System.out.print(AFewNumbers.getEntry(i) + " ");
		}
		System.out.println("\n\nLet us search for the last index of 2");
        System.out.println("Last index of 2: " + AFewNumbers.getLastIndex(2));
		System.out.println("\nLet us search for the last index of " + impossiblenumber + ", which is not in the list");
		System.out.println("Last index of " + impossiblenumber + ": " + AFewNumbers.getLastIndex(impossiblenumber));
		System.out.println("Note: Since " + impossiblenumber + " does not exist, its value returns -1 as intended.");
		System.out.println("\nLet us search for the last index of the duplicate value of " + duplicatenumber);
        System.out.println("Last index of " + duplicatenumber + ": " + AFewNumbers.getLastIndex(duplicatenumber));

        System.out.println("\n\nGiven 3 String lists, 1 Double list, and 1 UninitializedDouble Double list: ");
		System.out.print("List \"AString\" with strings: ");
		for (int i = 1; i < (AString.numberOfEntries + 1); i++) {
			System.out.print(AString.getEntry(i) + " ");
		}

		System.out.print("\nList \"AnotherDifferentString\" with strings: ");
		for (int i = 1; i < (AnotherDifferentString.numberOfEntries + 1); i++) {
			System.out.print(AnotherDifferentString.getEntry(i) + " ");
		}

		System.out.print("\nList \"TheSameStringAsTheLineAbove\" with strings: ");
		for (int i = 1; i < (TheSameStringAsTheLineAbove.numberOfEntries + 1); i++) {
			System.out.print(TheSameStringAsTheLineAbove.getEntry(i) + " ");
		}

		System.out.print("\nList \"NotString\" with doubles: ");
		for (int i = 1; i < (NotAString.numberOfEntries + 1); i++) {
			System.out.print(NotAString.getEntry(i) + " ");
		}
		
		System.out.print("\nList \"UninitializedDouble\" with double type that is not unitialized");

		System.out.println("\n\nLet us compare \"AString\" with \"AnotherDifferentString\":");
		System.out.println("Are the two strings equal? " + (String.valueOf(AString.equals(AnotherDifferentString))).toUpperCase());

		System.out.println("\nLet us compare \"AnotherDifferentString\" with \"TheSameStringAsTheLineAbove\":");
		System.out.println("Are the two strings equal? " + (String.valueOf(AnotherDifferentString.equals(TheSameStringAsTheLineAbove))).toUpperCase());

		System.out.println("\nLet us compare \"TheSameStringAsTheLineAbove\" with \"AString\":");
		System.out.println("Are the two strings equal? " + (String.valueOf(TheSameStringAsTheLineAbove.equals(AString))).toUpperCase());

		System.out.println("\nLet us compare \"Astring\" with \"NotString\":");
		System.out.println("Are the two strings equal? " + (String.valueOf(AString.equals(NotAString))).toUpperCase());

		System.out.println("\nLet us compare \"Astring\" with \"UninitializedDouble\":");
		System.out.println("Are the two strings equal? " + (String.valueOf(AString.equals(UninitializedDouble))).toUpperCase());

		System.out.println("\nSince UnitializedDouble has no values, let's see if it can find duplicates!");
		System.out.println("Is such a feat possible with pi? " + UninitializedDouble.getLastIndex(3.14));
	}

	/* Terminal Output:
	 * The List "AFewNumbers" with the values: 1 2 3 4 5 6 7 8 9 10 5 

		Let us search for the last index of 2
		Last index of 2: 1

		Let us search for the last index of 44, which is not in the list
		Last index of 44: -1
		Note: Since 44 does not exist, its value returns -1 as intended.

		Let us search for the last index of the duplicate value of 5
		Last index of 5: 10


		Given 3 String lists, 1 Double list, and 1 UninitializedDouble Double list:
		List "AString" with strings: String
		List "AnotherDifferentString" with strings: Different string! With a different length!
		List "TheSameStringAsTheLineAbove" with strings: Different string! With a different length!
		List "NotString" with doubles: 1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 10.0
		List "UninitializedDouble" with double type that is not unitialized

		Let us compare "AString" with "AnotherDifferentString":
		Are the two strings equal? FALSE

		Let us compare "AnotherDifferentString" with "TheSameStringAsTheLineAbove":
		Are the two strings equal? TRUE

		Let us compare "TheSameStringAsTheLineAbove" with "AString":
		Are the two strings equal? FALSE

		Let us compare "Astring" with "NotString":
		Are the two strings equal? FALSE

		Let us compare "Astring" with "UninitializedDouble":
		Are the two strings equal? FALSE

		Since UnitializedDouble has no values, let's see if it can find duplicates!
		Is such a feat possible with pi? -1
	 */

	// Lab 3
	// Problem 2.1
	public int getLastIndex(T item) { //method is identical to LList2Lab but modified for nodes, please see the code on that file for the comments
		Node nodeindex = firstNode;
		int result = -1;
        int intindex = 1;

        while (nodeindex != null) {
            if (Objects.equals(item, nodeindex.getData())) {
                result = intindex;
            }
            nodeindex = nodeindex.getNextNode();
            intindex++;
        }
        return result;
	}
	
	// Problem 2.2
	public boolean equals(Object other) { //method is identical to LList2Lab but modified for nodes, please see the code on that file for the comments
		LList2Lab <?> temporary = (LList2Lab <?>) other;
		Node increment = (LList2Lab<T>.Node) temporary.firstNode; //will have a typecast warning but in the parameters of this program is fine
		Node now = firstNode;

		if (other == null) {
			return false;
		}
	
		if (other == this) {
            return true;
        }

        if (!(other instanceof LList2Lab)) {
            return false;
        }

        if (this.getLength() != temporary.getLength()) {
            return false;
        }

        while (now != null) {
            if (!Objects.equals(now.getData(), increment.getData())) {
                return false;
            }
            increment = increment.getNextNode();
			now = now.getNextNode();
        }
        return true;
	}
	
	private Node firstNode; // head reference to first node
	private Node lastNode;  // tail reference to last node
	private int  numberOfEntries;

	public LList2Lab()
	{
		clear();
	} // end default constructor

	public final void clear() // NOTICE clear is not final in interface and that is OK
	{
		firstNode = null;
  		lastNode = null;
		numberOfEntries = 0;
	} // end clear
  
	public void add(T newEntry) 	  // OutOfMemoryError possible
	{
		Node newNode = new Node(newEntry); // create new node

		if (isEmpty())
			firstNode = newNode;
		else
			lastNode.setNextNode(newNode);
		
  		lastNode = newNode;
  		numberOfEntries++;
	}  // end add

   public boolean add(int newPosition, T newEntry)  // OutOfMemoryError possible	                                                 
	{
      boolean isSuccessful = true;

      if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) 
      {
         Node newNode = new Node(newEntry);

         if (isEmpty())
         {
            firstNode = newNode;
            lastNode = newNode;
         }
         else if (newPosition == 1)
         {
            newNode.setNextNode(firstNode);
            firstNode = newNode;
         }
         else if (newPosition == numberOfEntries + 1)
         {
            lastNode.setNextNode(newNode);
            lastNode = newNode;
         } 
         else
         {
            Node nodeBefore = getNodeAt(newPosition - 1);
            Node nodeAfter = nodeBefore.getNextNode();
            newNode.setNextNode(nodeAfter);
            nodeBefore.setNextNode(newNode);
         } // end if	    
         numberOfEntries++;
      }
      else
         isSuccessful = false;
       
	   return isSuccessful;
	} // end add

	public T remove(int givenPosition)
	{
      T result = null;                 // return value

      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
         assert !isEmpty();
         if (givenPosition == 1)        // case 1: remove first entry
         {
            result = firstNode.getData();     // save entry to be removed 
            firstNode = firstNode.getNextNode();
            if (numberOfEntries == 1)
               lastNode = null; // solitary entry was removed
            }
            else                           // case 2: givenPosition > 1
            {
               Node nodeBefore = getNodeAt(givenPosition - 1);
               Node nodeToRemove = nodeBefore.getNextNode();
               Node nodeAfter = nodeToRemove.getNextNode();
               nodeBefore.setNextNode(nodeAfter);  // disconnect the node to be removed
               result = nodeToRemove.getData();  // save entry to be removed

               if (givenPosition == numberOfEntries)
                  lastNode = nodeBefore; // last node was removed
         } // end if

         numberOfEntries--;
      } // end if

      return result;                   // return removed entry, or 
                                      // null if operation fails
	} // end remove

	public boolean replace(int givenPosition, T newEntry)
	{
		boolean isSuccessful = true;

      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {   
      	assert !isEmpty();

			Node desiredNode = getNodeAt(givenPosition);
			desiredNode.setData(newEntry);
      }    
		else
			isSuccessful = false;
			
		return isSuccessful;
   } // end replace

   public T getEntry(int givenPosition)
   {
      T result = null;  // result to return
      
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			assert !isEmpty();
         result = getNodeAt(givenPosition).getData();
     	} // end if
      
      return result;
   } // end getEntry

	public boolean contains(T anEntry)
	{
		boolean found = false;
		Node now = firstNode;
		
		while (!found && (now != null))
		{
			if (anEntry.equals(now.getData()))
				found = true;
			else
				now = now.getNextNode();
		} // end while
		
		return found;
	} // end contains

   public int getLength()
   {
      return numberOfEntries;
   } // end getLength

   public boolean isEmpty()
   {
   		boolean result;
   		
      	if (numberOfEntries == 0) // or getLength() == 0
      	{
      		assert firstNode == null;
      		result = true;
      	}
      	else
      	{
      		assert firstNode != null;
      		result = false;
      	} // end if
      	
      	return result;
   } // end isEmpty
	
   public T[] toArray()
   {
      // the cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries]; // warning: [unchecked] unchecked cast

	  int index = 0;
     Node now = firstNode;
	  while ((index < numberOfEntries) && (now != null))
	  { 
	    result[index] = now.getData();
	    now = now.getNextNode();
       index++; 
	  } // end while
     
     return result;
   } // end toArray

	// Returns a reference to the node at a given position.
	// Precondition:  List is not empty; 1 <= givenPosition <= numberOfEntries.	
	private Node getNodeAt(int givenPosition)
	{
		assert (firstNode != null) && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node now = firstNode;
		
      if (givenPosition == numberOfEntries)
         now = lastNode;
      else if (givenPosition > 1)      // traverse the chain to locate the desired node
		{
         for (int counter = 1; counter < givenPosition; counter++)
            now = now.getNextNode();
		} // end if
      
		assert now != null;
		return now;
	} // end getNodeAt

	private class Node 
	{
		private T data;  // data portion
		private Node next;  // next to next node

		private Node(T dataPortion)//  PRIVATE or PUBLIC is OK
		{
			data = dataPortion;
			next = null;	
		} // end constructor

		private Node(T dataPortion, Node nextNode)//  PRIVATE or PUBLIC is OK
		{
			data = dataPortion;
			next = nextNode;	
		} // end constructor

		private T getData()
		{
			return data;
		} // end getData
		
		private void setData(T newData)
		{
			data = newData;
		} // end setData
		
		private Node getNextNode()
		{
			return next;
		} // end getNextNode
		
		private void setNextNode(Node nextNode)
		{
			next = nextNode;
		} // end setNextNode
	} // end Node
} // end LList2Lab

