import java.util.Arrays;
/**
   A class that implements the ADT list by using an array.
   The list is never full.
   @author Frank M. Carrano
*/

public class AListLab<T> implements ListInterface<T> 
{
	public static void main(String[] args)  {
		AListLab <Integer> AFewNumbers = new AListLab <Integer>(11); //reusing constructors from Lab 3
		AListLab <String> AString = new AListLab <String>(1);
		AListLab <String> AnotherDifferentString = new AListLab <String>(2);
		AListLab <String> TheSameStringAsTheLineAbove = new AListLab <String>(2);
		AListLab <Double> NotAString = new AListLab <Double>();
		AListLab <Double> UninitializedDouble = new AListLab <Double>();

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

        System.out.println("\n\nGiven 3 String lists, 1 Double list, and 1 Uninitialized Double list: ");
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

	// Problem 1.1
	public int getLastIndex(T item) {
		int result = -1;
        for (int index = 0; index < numberOfEntries; index++) {
            if (item.equals(list[index])) {
                result = index;
            }
        }
        return result;
	}
	
	// Problem 1.2
	public boolean equals(Object other) {
		AListLab <?> temporary = (AListLab <?>) other; 			//creating a new class object with an unknown (?) type and adding input to the list, effectively "casting" it

		if (other == null) {									//making sure the input isn't empty. if it is null, then there is no point comparing and would be false automatically
			return false;
		}

		if (!(getClass() == other.getClass())) {				//if the object is not the same class as the original function, then there is no point comparing either since it will always be false
            return false;
        }
		
		if (this == other) { 									//directly comparing this list to other assuming that there is only one object in other and that this list only has one item
            return true;
        }

		if (!(numberOfEntries == temporary.numberOfEntries)) { 	//checking "if they have the same number of items"
            return false;
        }

        
        for (int i = 0; i < numberOfEntries; i++) { 			//checking if "each item in one object is equal to the item in its corresponding location" using a for loop
            if (!(list[i].equals(temporary.list[i]))) { 
                return false;
            }
        }
        return true;
	}

	private T[] list;   // array of list entries
    private int numberOfEntries;
    private static final int DEFAULT_INITIAL_CAPACITY = 25;  
    
    public AListLab() {
	this(DEFAULT_INITIAL_CAPACITY); // call next constructor
    } // end default constructor
    
    public AListLab(int initialCapacity) {
	numberOfEntries = 0;
	// the cast is safe because the new array contains null entries
	@SuppressWarnings("unchecked")
	    T[] tempList = (T[])new Object[initialCapacity];
	list = tempList; 
    } // end constructor
    
    public void add(T newEntry) {  
	ensureCapacity();
	list[numberOfEntries] = newEntry;
	numberOfEntries++;
    } // end add
    
    public boolean add(int newPosition, T newEntry) {  
	boolean isSuccessful = true;
	if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {	
	    ensureCapacity();
	    makeRoom(newPosition);				
	    list[newPosition - 1] = newEntry;
	    numberOfEntries++;
	}
	else
	    isSuccessful = false;
	return isSuccessful;
	
    } // end add
    
    public T remove(int givenPosition) { 
	
	T result = null; // return value

	if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {	
	    assert !isEmpty();
	    result = list[givenPosition - 1]; // get entry to be removed
	    
	    // move subsequent entries towards entry to be removed, 
	    // unless it is last in list
	    if (givenPosition < numberOfEntries)
		removeGap(givenPosition);
	    
	    numberOfEntries--;
	} // end if
	
	return result; // return reference to removed entry, or
	// null if either list is empty or givenPosition
	// is invalid
	
    } // end remove

    public void clear() { 
	numberOfEntries = 0;
    } // end clear

    public boolean replace(int givenPosition, T newEntry)  { 
	boolean isSuccessful = true;
	
	if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) { // test catches empty list 
	    assert !isEmpty();
	    list[givenPosition - 1] = newEntry;
	}
	else
	    isSuccessful = false;
	
	return isSuccessful;
	
    } // end replace
    
    public T getEntry(int givenPosition)  { 
	T result = null; // result to return
	
	if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
	    assert !isEmpty();
	    result = list[givenPosition - 1];
	} // end if
	
	return result;
	
    } // end getEntry
    
    public boolean contains(T anEntry) { 
	boolean found = false;
	for (int index = 0; !found && (index < numberOfEntries); index++) {
	    if (anEntry.equals(list[index]))
		found = true;
	} // end for
	
	return found;
    } // end contains
    
    public int getLength() {
	return numberOfEntries;
    } // end getLength
    
    public boolean isEmpty() {
	return numberOfEntries == 0; // or getLength() == 0
    } // end isEmpty
    
    public T[] toArray() {
	// the cast is safe because the new array contains null entries
	@SuppressWarnings("unchecked")
	    T[] result = (T[])new Object[numberOfEntries];
	
	for (int index = 0; index < numberOfEntries; index++) {
	    result[index] = list[index];
	} // end for
	
	return result;
    } // end toArray
    
    // Doubles the size of the array list if it is full.
    private void ensureCapacity() {
	if (numberOfEntries == list.length)
	    list = Arrays.copyOf(list, 2 * list.length);
    } // end ensureCapacity
    
    private void makeRoom(int newPosition) {
	assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);
	
	int newIndex = newPosition - 1;
	int lastIndex = numberOfEntries - 1;
	
	// move each entry to next higher index, starting at end of
	// list and continuing until the entry at newIndex is moved
	for (int index = lastIndex; index >= newIndex; index--)
	    list[index + 1] = list[index];
    }  // end makeRoom
    
    private void removeGap(int givenPosition) {
	assert (givenPosition >= 1) && (givenPosition < numberOfEntries);
	
	int removedIndex = givenPosition - 1;
	int lastIndex = numberOfEntries - 1;
	
	for (int index = removedIndex; index < lastIndex; index++)
	    list[index] = list[index + 1];
    } // end removeGap

} // end AListLab