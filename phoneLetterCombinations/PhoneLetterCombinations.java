/*
Leetcode MEDIUM: https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
Letter combinations of a phone number

This runs in O(n^4) time. the 4 is determined by the maximum number of mappings
for one digit to characters (for example, 7 to "pqrs"). It operates with minimal
space by reusing a Queue.

It runs like this:
1. create a Queue to hold the current combinations
2. loop from the rightmost character
3. If we are at the first loop (rightmost character), add it's mappings to the
queue;
4. if not, prepend all its mappings to all the current values in the queue, and
add those to queue
5. repeat steps 3 to 5 until the length of results from the queue increase
in length (indicates next round to be run)
6. move to one character to the left

*/

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Queue;

public class PhoneLetterCombinations {
	public static final HashMap<Character, String> letters;
	public static final String INVALID_NUMBER = "invalid";

	static {
		letters = new HashMap<>();
		letters.put('1', INVALID_NUMBER);
		letters.put('2', "abc");
		letters.put('3', "def");
		letters.put('4', "ghi");
		letters.put('5', "jkl");
		letters.put('6', "mno");
		letters.put('7', "pqrs");
		letters.put('8', "tuv");
		letters.put('9', "wxyz");
		letters.put('0', " ");
		letters.put('*', "+");
		letters.put('#', INVALID_NUMBER);
	}

	// "408"
	public static List<String> letterCombos(String digits) {
		LinkedList<String> result = new LinkedList<>();		// need to serve both queue and list

		if (digits.length() == 0)
			return result;

		for (int i = digits.length() - 1; i >= 0; i--) {
			String set = letters.get(digits.charAt(i));

			if (set.equals(INVALID_NUMBER))				// can't be encoded
				return new LinkedList<>();

			if (result.isEmpty()) {
				for (char c : set.toCharArray())
					result.add(String.valueOf(c));
			} else {
				String s;

				while (result.peek().length() == digits.length() - i - 1)	{// while the current string is from the previous run
					s = result.poll();

					for (char c : set.toCharArray())
						result.add(String.valueOf(c) + s);
				}
			}
		}

		return result;

	}

	public static void main(String[] args) {
		System.out.println(letterCombos(args[0]));
	}
}