/*
Leetcode Easy: https://leetcode.com/problems/reverse-integer/description/
O(digits) runtime O(digits space)
 */

public class Reverse {

	public static int reverse(int num) {
		boolean negative = (num < 0);

		String numString = Integer.toString(num);
		StringBuilder ret = new StringBuilder();

		if (negative)
			ret.append("-");

		for (int i = numString.length() - 1; i >= 0; i--) {		// iterate in reverse order
			ret.append(numString.charAt(i));
		}

		if (negative)
			ret.deleteCharAt(ret.length() - 1);		// remove negative character

		Long result = Long.parseLong(ret.toString());

		if (result.compareTo(new Long(Integer.MAX_VALUE)) > 0 || result.compareTo(new Long(Integer.MIN_VALUE)) < 0)		// if out of range	
			return 0;

		return Integer.parseInt(ret.toString());
	}

	public static void main(String[] args) {
		String input = args[0];
		System.out.println(reverse(Integer.parseInt(input)));

	}
}