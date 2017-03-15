import java.math.BigInteger;

public class fibonacci {
	public static int fibRecursive(int n) {
		if (n == 1 || n == 2)
			return 1;
		return fibRecursive(n - 1) + fibRecursive(n - 2);
	}

	public static long fibIterative(int n) {
		if (n == 1 || n == 2)
			return 1;

		long first = 1;
		long second = 1;
		long current = 1;

		for (int i = 3; i <= n; i++) {
			System.out.println(current);
			current = first + second;
			first = second;
			second = current;
		}

		return current;
	}

	public static BigInteger fibIterativeScaleable(long n) {
		if (n == 1 || n == 2)
			return new BigInteger("1");

		BigInteger first = new BigInteger("1");
		BigInteger second = new BigInteger("1");
		BigInteger current = null;

		for (long i = 3; i <= n; i++) {
			// System.out.println(current);
			current = first.add(second);
			first = second;
			second = current;
		}

		return current;
	}


	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("usage: java fibonacci [n]");
			System.exit(1);
		}

		System.out.println(fibIterativeScaleable(Integer.parseInt(args[0])));
		// System.out.println(fibRecursive(500));
	}
}