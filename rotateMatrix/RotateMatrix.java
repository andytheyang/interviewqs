/*
Leetcode MEDIUM: https://leetcode.com/problems/rotate-image/description/
Rotate a matrix 90 degrees

This approach is in-place. I use a shrinking window in order to rotate the
matrix layer by layer, and save a temporary int to keep track of the
one overwritten position.
*/

public class RotateMatrix {
    public void rotate(int[][] matrix) {
        if (matrix.length == 0) // square matrix
            return;
        
        int top = 0;
        int bot = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        
        while (top < bot) {
            for (int i = 0; i < right - left; i++) {
                int temp = matrix[top][left + i];
                matrix[top][left + i] = matrix[bot - i][left];
                matrix[bot - i][left] = matrix[bot][right - i];
                matrix[bot][right - i] = matrix[top + i][right];
                matrix[top + i][right] = temp;
            }
            
            top++;      // decrease view size
            bot--;
            left++;
            right--;
        }
    }
}