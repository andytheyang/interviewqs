/*
Leetcode MEDIUM: https://leetcode.com/problems/decode-ways/description/
Uses subproblem dp solution in order to determine number of decode ways.
If the previous digit could be encoded one way, then we just use the previous decoding
If the previous two digits can be encoded in two ways, then we add to the decodings that we did two digits ago
10 can only be encoded one way
*/
public class DecodeWays {
	public static int getWays(String input) {
		if (input == null || input.length() == 0)		// i don't see why the second condition couldn't be encoded, but leetcode accepts this
			return 0;

		int[] dp = new int[input.length() + 1];	// dp[n] = # of decode ways up to index n - 1
		dp[0] = 1;		// there is 1 way to decode a String of 0 length

		if (input.charAt(0) == '0')
			return 0;		// can't encode just a 0 on the first character
		else
			dp[1] = 1;		// there is 1 way to decode 1 valid character

		for (int i = 2; i < dp.length; i++) {
			int block2 = Integer.parseInt(input.substring(i - 2, i));	// consider 2 digit block
			int block1 = Integer.parseInt(input.substring(i - 1, i));	// consider 1 digit block

			if (block1 != 0)		// if 0, then we have to encode using the previous two digits (block2) no matter what
				dp[i] += dp[i - 1];

			if (block2 >= 10 && block2 <= 26)	// if in two digit range, we can add two digit char mappings to previous decodings
				dp[i] += dp[i - 2];
		}

		return dp[dp.length - 1];
	}

	public static void main(String[] args) {
		System.out.println(getWays(args[0]));
	}
}