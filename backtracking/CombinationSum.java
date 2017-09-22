import java.util.List;
import java.util.ArrayList;

public class CombinationSum {
	public static List<List<Integer>> comboSum(int[] nums, int target) {
		List<List<Integer>> ret = new ArrayList<>();
		backtrack(ret, new ArrayList<>(), nums, target, 0, 0);
		return ret;
	}

	// curSum = current sum of all ints in current
	// start = start position to avoid dupes
	private static void backtrack(List<List<Integer>> result, List<Integer> current, int[] nums, int target, int curSum, int start) {
		if (curSum == target) {		// if current list has the correct sum, append
			result.add(new ArrayList<>(current));
		} else {
			for (int i = start; i < nums.length; i++) {
				current.add(nums[i]);
				curSum += nums[i];

				backtrack(result, current, nums, target, curSum, start + 1);

				curSum -= nums[i];
				current.remove(current.size() - 1);
			}
		}
	}


	public static void main(String[] args) {
		int sum = Integer.parseInt(args[args.length - 1]);
		int[] nums = new int[args.length - 1];

		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(args[i]);
		}

		System.out.println(comboSum(nums, sum));

	}
}