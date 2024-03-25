public class Lab5
{
	public static void main(String[] args)  {
		
		Integer [] unsorted = {50, 10, 30, 80, 70, 20, 60, 40};
		int length = unsorted.length;

		Integer [] sorted = {1, 2, 3, 4, 5};
		Integer listed = 4;
		Integer notlisted = 10;
		
		System.out.print("An unsorted list to consider for problem 1: ");
		for (int i = 0; i < length; i++) {
			System.out.print(unsorted[i] + " ");
		}

		System.out.println("\nAttempting sort... ");
		modifiedSelectionSort(unsorted, length);
		
		System.out.println("A newly sorted list: ");

		for (int i = 0; i < length; i++) {
			System.out.print(unsorted[i] + " ");
		}

		System.out.print("\n\nA sorted list to consider for problem 2: ");
		for (int l = 0; l < sorted.length; l++) {
			System.out.print(sorted[l] + " ");
		}

		System.out.println("\nIs " + listed + " in the list? " + (String.valueOf(inArrayIterativeSorted(sorted, listed))).toUpperCase());
		System.out.println("Is " + notlisted + " in the list? " + (String.valueOf(inArrayIterativeSorted(sorted, notlisted))).toUpperCase());

		System.out.println("\nWhat about our previous problem 1 list?");

		System.out.println("Is " + listed + " in the list? " + (String.valueOf(inArrayIterativeSorted(unsorted, listed))).toUpperCase());
		System.out.println("Is " + notlisted + " in the list? " + (String.valueOf(inArrayIterativeSorted(unsorted, notlisted))).toUpperCase());

	}
	/*	An unsorted list to consider for problem 1: 50 10 30 80 70 20 60 40 
		Attempting sort...
		A newly sorted list:
		10 20 30 40 50 60 70 80

		A sorted list to consider for problem 2: 1 2 3 4 5
		Is 4 in the list? TRUE
		Is 10 in the list? FALSE

		What about our previous problem 1 list?
		Is 4 in the list? FALSE
		Is 10 in the list? TRUE 
	*/

	// Problem 1
	public static <T extends Comparable<? super T>> boolean inArrayIterativeSorted(T[] anArray, T anEntry) {
		int first = 0;
        int last = anArray.length - 1;
        while (first <= last) {
            int mid = (first + last) / 2;
            int comparison = anEntry.compareTo(anArray[mid]);
            if (comparison == 0) {
                return true;
            } 
			else if (comparison < 0) {
                last = mid - 1;
            } 
			else {
                first = mid + 1;
            }
        }
        return false;
		
	}
	
	// Problem 2
    public static <T extends Comparable<? super T>> void modifiedSelectionSort(T[] a, int n) {
		for (int i = 0, j = n - 1; i < j; i++, j--)  { 
			//System.out.print("\nRun " + i + ": ");
			int min_index = i;
			int max_index = i;
			T min = a[i];
			T max = a[i];

			for (int k = i; k <= j; k++) { 
				if (a[k].compareTo(max) > 0) { 
					max = a[k]; 
					max_index = k; 
				}  
				
				else if (a[k].compareTo(min) < 0) { 
					min = a[k]; 
					min_index = k; 
				} 
			} 
	
			swap(a, i, min_index); 
			if (a[min_index].compareTo(max) == 0) {
				swap(a, j, min_index); 
			} 
			else {
				swap(a, j, max_index); 
			}
		} 
	}
	/* For problem 2, how many comparisons are necessary to sort n values?  Write your answer below
	 * By tracing the problem above, we can see that each pass is determinant on the one before it:
	 * Simplyfing would equate to N passes turn 1, N-1 passes turn 2, N-2 passes turn 3, and so on until it reaches N passes
	 * Therefore, it is apt to use algebriac analysis and logic to see that this is N + 1 passes in total, since it is also comparing the list once again but not making any swaps
	 * 
	 * We can divide the actual sequence of the sort by half because each comparison takes two items at a time, effectively cutting it in half.
	 * If N represents the number of passes, it is safe to say that:
	 * 
	 * Comparison # = Pass # x Swap # 
	 * = (N + 1) x 1/2(N)
	 * = (N ^ 2 + N)/2
	 * 
	 * Simplifying the result in terms of Big O notation would yield us O(n^2) comparisons with O(n) swaps, which is my answer.
	 * Therefore, if we were to have a relatively large number (say 10,000 integers) we would be getting well over 5 million comparisons; which is not ideal to put it lightly.
	 */

	public static <T> void swap (T[] arr, int i, int j) { //assistance method to swap since i was struggling to debug for hours, less clutter
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}	
} 