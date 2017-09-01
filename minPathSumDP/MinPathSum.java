/*
Leetcode medium: https://leetcode.com/problems/minimum-path-sum/description/

This is a standard dynamic programming problem. The only edge case we need to
seriously worry about is how to deal withe borders of the array. I initially
wanted to use Integer.MAX_VALUE but realized that if there are ints in the array
that take on that value then it's possible I don't consider all possible paths
by immediately disregarding a path as a wall.
*/
public class MinPathSum {
	private int[][] map;
	private int[][] scores;		// scores. keeps track of path score required to traverse to bottom right corner
	private int xSize;
	private int ySize;

	public MinPathSum(int[][] map) {
		this.map = map;
		xSize = map[0].length;
		ySize = map.length;

		scores = new int[ySize][xSize];

		for (int y = 0; y < ySize; y++)
			for (int x = 0; x < xSize; x++)
				scores[y][x] = -1;

		scores[ySize - 1][xSize - 1] = map[ySize - 1][xSize - 1];		// base case
	}

	// S refers to the smallest path score to get to bottom right corner
	private int S(int y, int x) {
		if (y >= ySize || x >= xSize)		// if out of bounds
			return -1;				// negative 1 means out of bounds, must handle

		if (scores[y][x] != -1)		// negative means this hasn't been calculated yet
			return scores[y][x];

		int right = S(y, x + 1);
		int down = S(y + 1, x);

		// it should never be the case that both right and down are -1 (since the target does not need this calculation, it is the base case)
		if (right == -1 || (down != -1 && down < right))		// if right is a wall, or right score not optimal
			scores[y][x] = down + map[y][x];		// add current box to score
		else									// guaranteed down is a wall or not optimal
			scores[y][x] = right + map[y][x];

		return scores[y][x];
	}

	public int getMinPathSum() {
		return S(0, 0);
	}

	public static void main(String[] args) {
		int[][] map = new int[][]{{0}};

		MinPathSum m = new MinPathSum(map);
		System.out.println(m.S(0, 0));
	}
}