/*
Longest Increasing Subsequence:
Leetcode MEDIUM: https://leetcode.com/problems/longest-increasing-subsequence/solution/

O(n^2) dynamic programming approach
Requires a visitation of all nodes in order to consider all possible subsequences.
If we only visited the last node then we would only have longest subsequencecs that
ended at the last index. 
*/

public class LongestIncreasing {
	private static int[] dp;
	private static int[] nums;
	private static int max;

	public static int LIS(int[] n) {
		dp = new int[n.length];
		nums = n;
		max = 0;

		for (int i = 0; i < nums.length; i++)
			L(i);

		return max;
	}

	private static int L(int i) {
		if (dp[i] != 0)
			return dp[i];
		else if (i == 0)
			return 1;		// base case

		int maxLoc = -1;

		for (int x = 0; x < i; x++)
			if (nums[x] < nums[i] && (maxLoc == -1 || L(maxLoc) < L(x)))
				maxLoc = x;

		if (maxLoc == -1)
			dp[i] = 1;
		else
			dp[i] = L(maxLoc) + 1;

		max = Math.max(max, dp[i]);

		return dp[i];
	}


	public static void main(String[] args) {
		int[] nums = new int[args.length];

	 	for (int i = 0; i < args.length; i++)
	 		nums[i] = Integer.parseInt(args[i]);

	 	System.out.println(LIS(nums));
	}
}