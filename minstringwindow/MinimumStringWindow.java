/*
 * Minimum string window
 *
 * USAGE:
 * java MinimumStringWindow [string to find substring in] [alphabet]
 *
 * EXPLANATION:
 * I was asked this question on a Facebook phone screen, it is leetcode hard:
 * https://leetcode.com/problems/minimum-window-substring/description/
 * 
 * It is an object oriented approach that I made readable adapted from this:
 * https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems
 *
 * This approach is O(n) time and O(number of characters in alphabet) space.
 * 
 * We keep track of the number of characters in alphabet visited / validate string 
 * using a counter variable, which is only modified up and down when characters
 * that explicitly contribute to the fulfillment of the condition. This way, we
 * can validate substrings in O(1) time.
 *
 * In order to keep track of the alphabet, we use a HashMap which keeps track of
 * ONLY characters in the alphabet, and how many times they have occurred. We
 * must use this hashmap in order to account for more occurrences of
 * "necessary characters" (in alphabet) within a substring than that of that
 * character in the alphabet. When a count of occurrences in a HashMap goes
 * negative, it means the above case has happened, in which case counter will
 * not be modified. Using a HashMap allows us to track and update these
 * occurrences in O(1) time and minimal space.
 *
 * A possibly faster solution would to implement an array as a hash storage
 * method. However, as an elegant object-oriented solution this would be use
 * a lot more memory to account for all the unicode characters in Java
 * Characters (albeit not that much).
 */

import java.util.HashMap;

public class MinimumStringWindow {
	public static final int NOT_FOUND = Integer.MAX_VALUE;

	private static HashMap<Character,Integer> hash = new HashMap<>();
	private static int counter = 0;		// keeps track of number of characters remaining to be visited before valid string

	private static boolean see(char c) {	// returns true if string becomes valid after this see()
		Character ch = new Character(c);

		if (hash.containsKey(ch)) {			// means c appears in alphabet
			int newValue = hash.get(ch) - 1;
			hash.put(ch, newValue);

			if (newValue >= 0)
				counter--;		// only reduce counter if ch was required to fulfill alphabet
		}		// if it doesn't, we don't want to track any changes

		return counter == 0;
	}

	private static boolean unsee(char c) {	// returns true if string stays valid after unsee()
		Character ch = new Character(c);

		if (hash.containsKey(ch)) {			// only need to update hash and counter if we unsee a char counting toward alphabet
			int newValue = hash.get(ch) + 1;
			hash.put(ch, newValue);

			if (newValue > 0)
				counter++;		// only increase counter if ch was a character that counted toward the alphabet in the window
		}

		return counter == 0;
	}

	private static void initialize(String alphabet) {
		counter = 0;
		hash = new HashMap<>();
		Character ch;

		for (int i = 0; i < alphabet.length(); i++) {
			ch = new Character(alphabet.charAt(i));

			if (hash.containsKey(ch))			// if contains already
				hash.put(ch, hash.get(ch) + 1);	// just increase number
			else
				hash.put(ch, 1);	// otherwise, make new and set occurences to 1

			counter++;
		}
	}

	public static String minString(String s, String alphabet) {
		initialize(alphabet);		// initialize our trackers

		int start = 0;
		int end = 0;
		int minPos = 0;				// will only be used when updated
		int minLength = NOT_FOUND;

		while (end < s.length()) {
			if (see(s.charAt(end++)))	{	// see the character at end
				do {
					if (end - start < minLength) {	// found more optimal solution
						minPos = start;
						minLength = end - start;
					}
				} while (unsee(s.charAt(start++)));
			}
		}

		if (minLength == NOT_FOUND)
			return null;
		else
			return s.substring(minPos, minPos + minLength); // TODO: substring here
	}

	public static void main(String[] args) {
		String s = args[0];
		String alphabet = args[1];

		System.out.println(minString(s, alphabet));
	}
}