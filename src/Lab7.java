import java.util.* ;

public class Lab7 {
	public static void main(String[] args)  {
		int [] A1 = {3, 6, 5, 1, 4};
		int [] A2 = {4, 7, 5, 2, 6, 1};

		int [] A3 = {9, 2, 4, 8, 9, 4, 3, 2, 8, 1, 2, 7, 2, 5};
		int A3temp = 9;

		System.out.print("Suppose an array A1 with values: ");
		print(A1);
		System.out.println("\nThere happens to be a missing value of: " + findMissing(A1));
	
		System.out.print("\nSuppose another array A1 with values: ");
		print(A2);
		System.out.println("\nThere also happens to be a missing value of: " + findMissing(A2));

		System.out.print("\nSuppose an array A3 with values: ");
		print(A3);
		System.out.println("\nSorting the array... ");
		countingSort(A3, A3temp);
	}
	/*Output:
	 * Suppose an array A1 with values: 3 6 5 1 4
		There happens to be a missing value of: 2

		Suppose another array A1 with values: 4 7 5 2 6 1
		There also happens to be a missing value of: 3

		Suppose an array A3 with values: 9 2 4 8 9 4 3 2 8 1 2 7 2 5
		Sorting the array...
		Sorted array: 1 2 2 2 2 3 4 4 5 7 8 8 9 9
		Sorted frequency of array: 1 4 1 2 1 0 1 2 2
	 */

	// Problem 1
	public static int findMissing(int [] a) {
		int lastindex = a.length + 1; 					//finding last index of array
		int sum = 0;									//initializing int
		int target = (lastindex * (lastindex + 1))/2; 	//using hint to find the ideal sum of a[]
		for (int element: a) {							//adding all ints within a[], should be less than target since it's missing a number
			sum += element;
		}
		return target - sum;							//returning the difference, or the desired number
	}
	//RUNNING TIME: O(n) since we are iterating through the array once only using a for loop
	//MMEMORY USAGE: O(1) since we only have four variables (lastindex, sum, target, element) for any case scenario. O(4) => O(1) in big-O notation

	public static void print(int [] a) { //O(n) time complexity since we are iterating through an array once using a for loop :)
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
	
	// Problem 2
	public static void countingSort(int[] a, int n) {
		int[] count = new int[n];
	
		for (int i = 0; i < a.length; i++) {
			count[a[i] - 1]++;
		}
	
		int index = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < count[i]; j++) {
				a[index++] = i + 1;
			}
		}
		System.out.print("Sorted array: ");
		print(a);
		System.out.print("\nSorted frequency of array: ");
		print(count);
	}
	/*
	 * Worst Case Running Time:
	 * Every element in a[] is unique, therefore giving count to be [1,1,1,1,...]
	 * O(M) for count initialization with a.length
	 * O(N) for a nested for loop 
	 * Therefore, O(M x N) = O(M^2) where M > N
	 */
} 
