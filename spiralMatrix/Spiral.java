/*
Leetcode MEDIUM: https://leetcode.com/problems/spiral-matrix-ii/description/

Spiral Matrix II
This is a fairly object-oriented approach to spiralmatrix, using a directional
array to keep direction changes down to a simple increment and modulus. Runs
in O(n^2) time (fastest possible), where n is the input n.
*/
public class Spiral {
	public static final int[][] directions = new int[][] {{0,1},{1,0},{0,-1},{-1,0}};		// directions for y,x in clockwise turning direction

	public static int[][] makeSpiral(int n) {
		if (n <= 0)
			return new int[0][0];

		int dir = 0;	// pointing to the right
		int x = 0;
		int y = 0;
		int[][] ret = new int[n][n];		// all will be init to 0
		int target = n * n;					// square number will be the max value to be written
		int current = 1;
		int newY;
		int newX;

		do {
			ret[y][x] = current++;
			newY = y + directions[dir][0];	// these are "proposed coordinates"
			newX = x + directions[dir][1];

			if (newY >= n || newX >= n || newY < 0 || newX < 0 || ret[newY][newX] != 0) {	// if out of bounds OR value already written (we don't want to overwrite)
				dir = (dir + 1) % 4;		// change direction
				newY = y + directions[dir][0];		// update proposed coordinates
				newX = x + directions[dir][1];
			}

			y = newY;
			x = newX;

		} while (current <= target);		// once we've reached target, we know that we filled everything in there

		return ret;
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);

		int[][] spiral = makeSpiral(n);

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				System.out.print(spiral[y][x] + " ");
			}
			System.out.println();
		}
	}
}
