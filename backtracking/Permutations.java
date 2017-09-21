import java.util.List;
import java.util.ArrayList;

/*
Leetcode MEDIUM: https://leetcode.com/problems/permutations/description/
Given a set of distinct numbers, return all permutations.

This approach is O(n^3), but can be easily optimized to O(n^2) with a proper
lookup hashtable instead of using current.contains() in order to check if 
elements were previously added.
*/

public class Permutations {

	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> ret = new ArrayList<>();
		backtrack(ret, new ArrayList<>(), nums);
		return ret;
	}

	private static void backtrack(List<List<Integer>> result, List<Integer> current, int[] nums) {
		if (current.size() == nums.length) {		// valid result
			List<Integer> validPermutation = new ArrayList<>(current);
			System.out.println(validPermutation);
			result.add(validPermutation);	// add a copy
		} else {
			for (int i = 0; i < nums.length; i++) {	// O(n)
				if (current.contains(nums[i]))		// assuming no duplicates
					continue;

				current.add(nums[i]);
				backtrack(result, current, nums);
				current.remove(current.size() - 1);			// remove last element
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[args.length];

		for (int i = 0; i < args.length; i++) {
			nums[i] = Integer.parseInt(args[i]);
		}

		permute(nums);
	}
}