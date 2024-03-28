import java.util.Iterator;
import java.util.Random;
/**
   A solitaire matching game in which you have a list of random
   integer values between 10 and 99. You remove from the list any
   pair of consecutive integers whose first or second digits match.
   If all values are removed, you win.

 */
public class SolitaireGame {
	static final int start = 1;
	static final int end = 40;
	static int index = start;

	/** Initializes the list with 40 random 2 digit numbers. */
	public static void initializeList(ArrayListWithIterator<Integer> theList) {
		Random random = new Random();
		for (int i = start; i <= end; i++) {
			theList.add(random.nextInt(90) + 10);
		}
	} // end initializeList

	//WARNING: list to debug, is not used in submission
	public static void hardcodedList(ArrayListWithIterator<Integer> theList) {
		Integer [] list = {81, 50, 11, 61, 42, 74, 16, 65, 49, 49, 11, 19, 67, 79, 33, 95, 85, 52, 59, 67, 46, 81, 62, 30, 60, 66, 80, 96, 30, 81, 37, 30, 34, 30, 15, 80, 11, 61, 55, 46};
		for (int i = 0; i < list.length; i++) {
			theList.add(list[i]);
		}
	}

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
	} // end removable

	/** Display the contents of theList using an Iterator
	 * 
	 */
	public static void displayList(ArrayListWithIterator<Integer> theList) {
		Iterator <Integer> counter = theList.iterator();
		while (counter.hasNext()) {
			if (!counter.hasNext()) {
				System.out.print(Integer.toString(counter.next()));
			}
			else {
				System.out.print(Integer.toString(counter.next()) + " ");
			}
			
		}		
	}
	/** Scans over the list and removes any pairs of values that are removable.
		@param theList  The list of 2 digit integers to scan over.
		@return  True if any pair of integers was removed. */
	public static boolean scanAndRemovePairs(ArrayListWithIterator<Integer> theList) {
		Iterator <Integer> ahead = theList.iterator();
		Iterator <Integer> current = theList.iterator();

		int now = 1;
		int future = 2;
		boolean flag = false;

		ahead.next();

		while (ahead.hasNext()) {
			Integer x = current.next();
			Integer y = ahead.next();
			if (removable(x, y) && ahead.hasNext()) {
				System.out.print("\tRemoving: ");
				System.out.print(Integer.toString(x) + " ");
				System.out.print(Integer.toString(y) + "\n");
				theList.remove(now);
				theList.remove(future);
				flag = true;
			}
			now++;
			future++;
		}
		
		return flag;

		
		/*
		if (ahead.hasNext()) {
			ahead.next();
		}

		while (ahead.hasNext()) {
			Integer x = current.next();
			Integer y = ahead.next();
			if (removable(x, y)) {
				System.out.print("Removing: ");
				System.out.print(Integer.toString(x) + " ");
				System.out.print(Integer.toString(y) + "\n");
				theList.remove(now);
				theList.remove(future);
				flag = true;
			}
			now++;
			future++;
		}

		index++;
		return flag;
		*/
		
	} // end scanAndRemovePairs

	public static void main(String args[]) {
		ArrayListWithIterator<Integer> RandomList = new ArrayListWithIterator<Integer>();
		Integer Count = 0;
		hardcodedList(RandomList);
		System.out.print("Let us assume a random list with " + RandomList.getLength() + " characters: ");
		displayList(RandomList);
		System.out.println("\nLet us now filter this list:");
		for (Iterator <Integer> counter = RandomList.iterator(); counter.hasNext(); Count = counter.next() ) {
			scanAndRemovePairs(RandomList);
			System.out.print("The list is now: ");
			displayList(RandomList);
			System.out.print("\n");
			if (scanAndRemovePairs(RandomList) == false) {
				System.out.println("No more pairs to remove.");
				break;
			}
		}
	} // end main
} // end SolitaireGame

/* README: Output:
 * 
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
