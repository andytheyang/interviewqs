import java.util.HashSet;
import java.util.Arrays;

public class PairSum {
	// O(n) runtime, O(n) space
	public static int[] getPairHash(int[] nums, int sum) {	// find pair of nums that sum to sum
		HashSet<Integer> seen = new HashSet<>();

		for (int i : nums) {
			if (seen.contains(new Integer(sum - i)))
				return new int[] {i, sum - i};

			seen.add(new Integer(i));
		}

		return null;
	}

	// O(nlogn) runtime (sort and n* logn binary searches), O(1) space
	public static int[] getPairInPlace(int[] nums, int sum) {
		Arrays.sort(nums);		// java standard sort is a variation of quicksort, O(nlogn)

		for (int i : nums) {
			if (binarySearch(nums, sum - i))
				return new int[] {i, sum - i};
		}

		return null;
	}

	// O(n^2) runtime, O(1) space
	public static int[] getPairCombination(int[] nums, int sum) {
		for (int i = 0; i < nums.length; i++)
			for (int j = i; j < nums.length; j++)
				if (nums[i] + nums[j] == sum)
					return new int[] {nums[i], nums[j]};

		return null;
	}

	// iterative binary s earch of an array. Offers O(log n) search time
	private static boolean binarySearch(int[] nums, int target) {
		int start = 0;			// inclusive
		int end = nums.length;	// exclusive
		int index;

		while (start <= end - 1) {		// if start >= end - 1 then our search range is exhausted
			index = (start + end) / 2;

			if (target > nums[index])
				start = index + 1;		// exclude index since we've already considered it
			else if (target < nums[index])
				end = index;			// end is exclusive
			else						// we found it!
				return true;
		}

		return false;
	}

	public static void main(String[] args) {
		int sum = Integer.parseInt(args[0]);
		int[] nums = new int[args.length - 1];

		for (int i = 1; i < args.length; i++) {
			nums[i - 1] = Integer.parseInt(args[i]);
		}

		System.out.println(Arrays.toString(getPairHash(nums, sum)));
		System.out.println(Arrays.toString(getPairInPlace(nums, sum)));
		System.out.println(Arrays.toString(getPairCombination(nums, sum)));
		// System.out.println(binarySearch(nums, sum));
	}
}