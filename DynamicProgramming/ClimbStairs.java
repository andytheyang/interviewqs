public class ClimbStairs {
	private static int[] dp;

	public static int climbStairs(int n) {
		dp = new int[n + 1];

		return C(n);
	}

	private static int C(int n) {
		if (n == 0)
			return 1;
		else if (n < 0)
			return 0;
		else if (dp[n] != 0)
			return dp[n];

		dp[n] = C(n - 1) + C(n - 2);
		return dp[n];
	}

	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		System.out.println(climbStairs(num));
	}
}