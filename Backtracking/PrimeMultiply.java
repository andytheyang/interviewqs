/*
Glassdoor: https://www.glassdoor.ie/Interview/Facebook-Software-Engineer-New-Grad-Interview-Questions-EI_IE40772.0,8_KO9,35.htm#InterviewReview_8424236
For Facebook Software Engineer New Grad interview questions

Given a list of distinct prime numbers, return all products of 1, 2, 3 prime numbers.

This approach uses backtracking as it is a cumulative problem that asks for
a list of cumulative solutions. It is similar to generating all possible subsets
with the following modifications:
1. Instead of storing a set of current numbers in consideration, just store
the current product and length
2. Only add to output when number of numbers multiplied is within the valid
range
3. End recursion early when depth reaches > 3, to save time.

This approach is O(n!)
*/

import java.util.List;
import java.util.LinkedList;

public class PrimeMultiply {
	public static final int PRODUCT_MAX_LENGTH = 3;

	public static List<Integer> get123Multiples(int[] primes) {
		List<Integer> result = new LinkedList<>();
		backtrack(result, 1, 0, primes, 0);

		return result;
	}

	private static void backtrack(List<Integer> results, int current, int curLength, int[] primes, int start) {
		if (curLength >= 1 && curLength <= PRODUCT_MAX_LENGTH)
			results.add(current);
		else if (curLength > PRODUCT_MAX_LENGTH)		// cancel recursive tree if we are past the max length
			return;
 
		for (int i = start; i < primes.length; i++) {	// start at start to avoid dupes. iterate through remaining and attempt to multiply
			backtrack(results, current * primes[i], curLength + 1, primes, i + 1);	// add a number to the current "stack"
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[args.length];

		for (int i = 0; i < args.length; i++) {
			nums[i] = Integer.parseInt(args[i]);
		}

		System.out.println(get123Multiples(nums));
	}
}