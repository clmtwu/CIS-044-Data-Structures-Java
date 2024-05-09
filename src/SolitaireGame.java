import java.util.Iterator;
import java.util.Random;
/**
   A solitaire matching game in which you have a list of random
   integer values between 10 and 99. You remove from the list any
   pair of consecutive integers whose first or second digits match.
   If all values are removed, you win.

 */
public class SolitaireGame {
	//values used to initialize random, no magic numbers!
	static final int start = 1;
	static final int end = 40;

	/** Initializes the list with 40 random 2 digit numbers. */
	public static void initializeList(ArrayListWithIterator<Integer> theList) {
		Random random = new Random();
		for (int i = start; i <= end; i++) {
			theList.add(random.nextInt(90) + 10);
		}
	} // end initializeList

	/** Sees whether two numbers are removable.
		@param x  The first 2 digit integer value.
		@param y  The second 2 digit integer value.
 		@return  True if x and y match in the first or second digit. */
	public static boolean removable(Integer x, Integer y) {
		Integer a = x % 10; //x's last digit
		Integer b = x / 10; //x's first digit
		Integer c = y % 10; //y's last digit
		Integer d = y / 10; //y's first digit
		return (a.equals(c) || b.equals(d));
		//a little trick i learned in ap cs to mod 10 to get the last digit and divide by 10 to get the first digit
		//memory should be O(1) and should be better than creating an array and comparing
		//works for all edge cases since this method works for all two digit int numbers
		//would be faster to do something like return the actual statement, but for recordkeeping i thought this was reasonable
	} // end removable

	/** Display the contents of theList using an Iterator
	 * 
	 */
	public static void displayList(ArrayListWithIterator<Integer> theList) {
		//creating an iterator object to transverse the list (temper.hasNext()) and print it
		//stops when reached end of list
		Iterator <Integer> temper = theList.iterator();
		while (temper.hasNext()) {
			System.out.print(Integer.toString(temper.next()) + " ");
		}		
	}

	/** Scans over the list and removes any pairs of values that are removable.
		@param theList  The list of 2 digit integers to scan over.
		@return  True if any pair of integers was removed. */
	public static boolean scanAndRemovePairs(ArrayListWithIterator<Integer> theList) {
		boolean flag = false;
		Iterator<Integer> current = theList.getIterator();
		while (current.hasNext()) {
			Integer x = current.next(); //adding value of .next() to x
			if (!current.hasNext()) {
				break; // Reached the end of the list, no more pairs to check
			}
			Integer y = current.next(); //adding the next value of .next() to y

			if (removable(x, y)) { //giving output when removed
				System.out.println("Removed: " + x + " " + y);
				current.remove();
				flag = true; //stop scenario
			}
		}

		if (theList.getLength() == 0) { //if list is 0, don't compare anymore
			return !flag; //stop scenario
		}
		return flag;
	} // end scanAndRemovePairs

	/*
	 * Grade: 
	 * -10 scanAndRemove removes only one elment when there is a match and not both elements: 
	 * if (removable(x, y)) { //giving output when removed 
	 * System.out.println("Removed: " + x + " " + y); 
	 * current.remove(); 
	 * flag = true; //stop scenario 
	 * }
	 */

	public static void main(String args[]) {
		ArrayListWithIterator<Integer> RandomList = new ArrayListWithIterator<Integer>();
		initializeList(RandomList);
		System.out.print("Let us assume a random list with " + RandomList.getLength() + " characters: ");
		displayList(RandomList);
		System.out.println("\nLet us now filter this list:");

		Iterator<Integer> listIterator = RandomList.getIterator();
		boolean pairsRemoved = true;

		while (listIterator.hasNext() && pairsRemoved) {
			pairsRemoved = scanAndRemovePairs(RandomList);

			if (pairsRemoved) {
				System.out.print("The list is now: [");
				displayList(RandomList);
				System.out.print("] \n");
			}
			else {
				System.out.print("No more pairs to remove.");
			}
		}
	} // end main
} // end SolitaireGame

/* README: Output:
 * 	Let us assume a random list with 40 characters: 46 56 85 39 51 18 96 71 39 96 59 99 83 53 80 68 17 34 68 80 60 62 33 57 56 50 25 25 49 88 39 11 91 37 68 51 53 92 20 14 
	Let us now filter this list:
	Removed: 46 56
	Removed: 59 99
	Removed: 83 53
	Removed: 60 62
	Removed: 56 50
	Removed: 25 25
	The list is now: [46 85 39 51 18 96 71 39 96 59 83 80 68 17 34 68 80 60 33 57 56 25 49 88 39 11 91 37 68 51 53 92 20 14 ]
	Removed: 83 80
	Removed: 80 60
	The list is now: [46 85 39 51 18 96 71 39 96 59 83 68 17 34 68 80 33 57 56 25 49 88 39 11 91 37 68 51 53 92 20 14 ]
	No more pairs to remove.
 */

/*
Example outupt
The list is originally: [81, 50, 11, 61, 42, 74, 16, 65, 49, 49, 11, 19, 67, 79, 33, 95, 85, 52, 59, 67, 46, 81, 62, 30, 60, 66, 80, 96, 30, 81, 37, 30, 34, 30, 15, 80, 11, 61, 55, 46]
   Removed: 11  61
   Removed: 49  49
   Removed: 11  19
   Removed: 95  85
   Removed: 52  59
   Removed: 30  60
   Removed: 37  30
   Removed: 34  30
   Removed: 11  61
The list is now: [81, 50, 42, 74, 16, 65, 67, 79, 33, 67, 46, 81, 62, 66, 80, 96, 30, 81, 15, 80, 55, 46]
   Removed: 65  67
   Removed: 62  66
The list is now: [81, 50, 42, 74, 16, 79, 33, 67, 46, 81, 80, 96, 30, 81, 15, 80, 55, 46]
   Removed: 81  80
The list is now: [81, 50, 42, 74, 16, 79, 33, 67, 46, 96, 30, 81, 15, 80, 55, 46]
   Removed: 46  96
The list is now: [81, 50, 42, 74, 16, 79, 33, 67, 30, 81, 15, 80, 55, 46]
No more pairs to remove.

 */
