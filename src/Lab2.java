import java.text.NumberFormat;
import java.util.Locale;

public class Lab2 {
	public static void main(String[] arg) {
		NumberFormat Format = NumberFormat.getInstance(Locale.US); //used for commas in display 
		int [] a = {2, 3, 5, 7, 11, 13, 17, 19, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 23, 29, 31, 37, 41, 43}; //hardcoded test values
		System.out.print("Array Contents: "); //printing out array 
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.print("\nTotal elements: " + a.length); //printing out length, used mostly for debugging
		System.out.println("\nMinimum value of array: " + min(a, 0, a.length - 1)); //Answer for Question 3
		System.out.println("Computing pay in cents at Day 39: " + Format.format(computePay(39, 1)) + " cents."); //Formatted Answer for Question 4
		System.out.println("Computing savings in cents at Day 39: " + Format.format(computeSavings(39, 1)) + " cents."); //Formatted Answer for Question 5
	}

	/* Output: 
	 * Array Contents: 2 3 5 7 11 13 17 19 47 53 59 61 67 71 73 79 83 89 97 23 29 31 37 41 43 
	 * Total elements: 25
	 * Minimum value of array: 2
	 * Computing pay in cents at Day 39: 274,877,906,944 cents.
	 * Computing savings in cents at Day 39: 549,755,813,887 cents.
	 */

	// Problem 4
	public static int min(int [] a, int begin, int end) {
		if (begin >= end) { //easiest way to check for ends without messing with indexes for both halves
            return a[begin];
        } 
		else {
			int half = (begin + end) / 2; //setting midpoint for array
			int firsthalf = min(a, begin, half); //resursively calculating the minimum integer of the first half by the return statement on line 36
			int secondhalf = min(a, half + 1, end); //resursively calculating the minimum integer of the second half by the return statement on line 36
			return Math.min(firsthalf, secondhalf); //returning smaller value between firsthalf and secondhalf
        }
	}
	
	// Problem 5
	public static long computePay(int day, long money) {
		if (day == 1) { //recursion exit case
			return money;
		}
		return computePay(day - 1, money * 2); //doubling money in the parameter itself to save computation, decrementing day

	}
	
	public static long computeSavings(int day, long money) {
		if (day == 1) { //recursion exit case
			return 1;
		}
		money = money * 2; //doubling money to be used in line 54 as well, so cannot multiple in parameter section as above
		return money + computeSavings(day - 1, money); //adding the money along with its recursive function
	}
}