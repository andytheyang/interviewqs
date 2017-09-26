/*
Leetcode MEDIUM: https://leetcode.com/problems/next-permutation/description/
Get the next permutation (modified to use chars)

O(n) time, O(1) space

This program gets the next lexicographical ordering of all the elements in vals.
It does this by the following steps:
1. Starting from the right, find the first value that is out of place (descending)
2. Starting from that location, find the smallest value larger than that out
of place value
3. Swap the two values
4. Reverse the remaining elements to the right of descending location
*/

import java.util.Arrays;

public class NextPermutation {

	public static void nextPermutation(char[] vals) {
		if (vals.length <= 1)			// base case, definitely no next permutation available
			return;

		int dec = vals.length - 2;

		while (dec >= 0 && vals[dec] > vals[dec + 1])	// search for next decreasing number (pivot)
			dec--;

		if (dec == -1) {	// if this ever happens, that means we have the highest lexicographical order
			reverse(vals, 0, vals.length - 1);	// sort in ascending order
			return;
		}

		char curMin = ' ';		// dummy values that will always be overwritten
		int curMinLoc = 0;

		for (int i = dec + 1; i < vals.length; i++) {
			// the first value saved will always be greater than vals[dec] because of the above while loop
			// i == dec + 1 is the "first value" condition
			if (i == dec + 1 || (vals[i] < curMin && vals[i] > vals[dec])) {		// find value just above our pivot
				curMin = vals[i];
				curMinLoc = i;
			}
		}

		swap(vals, dec, curMinLoc);
		reverse(vals, dec + 1, vals.length - 1);
	}

	// reverses all chars in range [start, end]
	private static void reverse(char[] vals, int start, int end) {
		while (start < end) {
			swap(vals, start, end);
			start++;
			end--;
		}
	}

	private static void swap(char[] vals, int a, int b) {
		char temp = vals[a];
		vals[a] = vals[b];
		vals[b] = temp;
	}


	public static void main(String[] args) {
		char[] vals = new char[args.length];

		for (int i = 0; i < args.length; i++) {
			vals[i] = args[i].charAt(0);
		}

		nextPermutation(vals);

		System.out.println(Arrays.toString(vals));
	}
}