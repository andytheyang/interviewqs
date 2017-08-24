/*
 * Dynamic programming approach to longest increasing substring
 * This isn't perfect, as I end up looping through each L(i) in order to get the
 * desired result, and is not O(n) as a result
 */

public class Longest {
	private int[] dArray;
	private int[] input;

	public Longest(int[] input) {
		dArray = new int[input.length];		// all values will be 0
		this.input = input;
	}


	public void runLIS() {
		if (input.length == 0)
			return;

		dArray[0] = 1;		// beginning will always be subseq 1	

		for (int i = 0; i < input.length; i++)
			L(i);	// start the dynamic array population
	}

	private int L(int i) {
		if (dArray[i] != 0)		// means something was already written		
			return dArray[i];	// return memoized result

		int maxPos = -1;		// contains position of maximum previous subsequence

		for (int j = 0; j < i; j++) {	// find maximum
			if (input[j] < input[i]) {		// if this number can form a valid previous chain in subsequence
				if (maxPos == -1)
					maxPos = j;
				else if (L(maxPos) < L(j))	// if current maximum is less than the score we are observing
					maxPos = j;
			}
		}

		if (maxPos == -1)		// if we didn't find any valid heads to LIS
			dArray[i] = 1;		// start new subsequence
		else {
			// int curScore = L(maxPos) + 1;

			// if (curScore > L(i - 1))		// check for subsequences that DON'T end in this current spot 
			// 	dArray[i] = curScore;
			// else
			// 	dArray[i] = L(maxPos);
			dArray[i] = L(maxPos) + 1;
		}

		return dArray[i];
	}

	public int getLISLength() {
		int max = 0;

		for (int i : dArray)
			if (i > max)
				max = i;

		return max;
	}

	public void printDArray() {
		for (int i = 0; i < dArray.length; i++)
			System.out.print(dArray[i] + " ");

		System.out.println();
	}


	public static void main (String[] args) {
		int[] input = new int[args.length];

		for (int i = 0; i < args.length; i++) {
			input[i] = Integer.parseInt(args[i]);
		}

		Longest l = new Longest(input);

		l.runLIS();
		System.out.println(l.getLISLength());
		l.printDArray();
	}
}