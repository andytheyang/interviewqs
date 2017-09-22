/*
Leetcode MEDIUM: https://leetcode.com/problems/container-with-most-water/description/
Container with most water

This approach utilizes a two pointer approach to solve the problem in O(n) time.
The two pointers follow these rules:
1. Pointers start from the edges and move inwards. We will consider all
containers in order of possibility of increase in max size
2. Only the lesser of the two pointer heights needs to be moved closer.
(moving a larger height will just lose capacity)
3. Calculation will be complete when the pointers meet


*/

public class Container {
	public static int maxArea(int[] heights) {
		if (heights.length < 2)
			return 0;

		int leftPtr = 0;
		int rightPtr = heights.length - 1;
		int maxArea = 0;

		while (leftPtr < rightPtr) {
			int minHeight = min(heights[leftPtr], heights[rightPtr]);		// find minimum barrier on either side
			int area = (rightPtr - leftPtr) * minHeight;					// width * height

			if (area > maxArea)		// update max area
				maxArea = area;

			if (minHeight == heights[leftPtr])		// if left one is min
				leftPtr++;							// move it up
			else
				rightPtr--;							// otherwise move the other one up
		}

		return maxArea;
	}

	public static int min(int a, int b) {
		if (a < b)
			return a;
		else
			return b;
	}

	public static void main(String[] args) {
		int[] heights = new int[args.length];

		for (int i = 0; i < args.length; i++) {
			heights[i] = Integer.parseInt(args[i]);
		}

		System.out.println(maxArea(heights));
	}
}