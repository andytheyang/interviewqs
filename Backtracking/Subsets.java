import java.util.List;
import java.util.ArrayList;

/*
https://leetcode.com/problems/subsets/
Get all unique subsets

This approach is O(n!) backtracking
*/

public class Subsets {

	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> ret = new ArrayList<>();
		backtrack(ret, new ArrayList<>(), nums, 0);
		return ret;
	}

	private static void backtrack(List<List<Integer>> result, List<Integer> current, int[] nums, int start) {
		result.add(new ArrayList<>(current));	// add a copy

		for (int i = start; i < nums.length; i++) {	// O(n)
			if (current.contains(nums[i]))		// assuming no duplicates
				continue;

			current.add(nums[i]);
			backtrack(result, current, nums, start + 1);
			current.remove(current.size() - 1);			// remove last element
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[args.length];

		for (int i = 0; i < args.length; i++) {
			nums[i] = Integer.parseInt(args[i]);
		}

		System.out.println(subsets(nums));
	}
}