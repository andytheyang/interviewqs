/*
Leetcode Medium: https://leetcode.com/problems/longest-palindromic-substring/description/
O(n^2) runtime, I can't think of anything faster
*/
public class LongestPalindrome {
	private static int maxLength = 0;		// length of max palindrome
	private static int start = 0;			// start of max palindrome

	public static String longestPalindrome(String input) {
		if (input.length() < 2)		// base case
			return input;

		for (int i = 0; i < input.length(); i++) {
			searchPalindrome(input, i, i);		// odd-length palindrome case
			searchPalindrome(input, i, i + 1);	// even-length palindrome case
		}

		return input.substring(start, start + maxLength);
	}

	private static void searchPalindrome(String input, int left, int right) {		// given two indices right next to or on top of each other, perform O(n) search for palindrome
		while (left >= 0 && right < input.length() && input.charAt(left) == input.charAt(right)) {
			left--;		// extend search boundary if palindrome condition is met
			right++;
		}

		// right and left now point to locations JUST OUTSIDE of palindrome
		if (right - left - 1 > maxLength) {	
			maxLength = right - left - 1;
			start = left + 1;
		}
	}

	public static void main(String[] args) {
		System.out.println(longestPalindrome(args[0]));
	}
}