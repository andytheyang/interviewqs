/*
Leetcode MEDIUM: https://leetcode.com/problems/search-a-2d-matrix/description/
Search 2d matrix

This runs in O(log(m)log(n)) time for an M x N matrix, with O(1) space.
This algorithm is relatively simple, two binary searches, one of them modified
to search within ranges. The outer binary search determines which "block"
(inner integer array) the target resides in, and searches by checking the
target against the minimum and maximum values in examined blocks. Once a block
is found, then conventional binary search is used in the block.
This program is not particularly difficult, but is a good insight into my
ability to catch edge cases. I initially made one with while(start <= end), 
which caused a stackoverflow when values were not in the matrix.
*/

public class SearchMatrix {
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0)        // this means there are no elements contained in the matrix
            return false;
        
        int start = 0;
        int end = matrix.length;
        int search;
        
        while (start < end) {      // exit when not in any major block
            search = (start + end) / 2;
            
            if (target > matrix[search][matrix[search].length - 1]) {       // above this range
                start = search + 1;         // inclusive, so move up out of considered range
            } else if (target < matrix[search][0]) {        // under the range
                end = search;               // exclusive, so put search as upper limit
            } else {    // within range            
                int block = search;         // save our block number
                // repurpose start end search for inner bst
                start = 0;
                end = matrix[block].length;
                
                while (start < end) {
                    search = (start + end) / 2;
                    
                    if (target > matrix[block][search]) {       // above this range
                        start = search + 1;         // inclusive, so move up out of considered range
                    } else if (target < matrix[block][search]) {        // under the range
                        end = search;               // exclusive, so put search as upper limit
                    } else {    // within range
                        return true;   
                    }
                }
                
                return false;
            }
        }
        
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[4][];
        matrix[0] = new int[] {2,3,6,7};
        matrix[1] = new int[] {10,11,12,13};
        matrix[2] = new int[] {15,16,17,18};
        matrix[3] = new int[] {20,21,22,23};

        System.out.println(searchMatrix(matrix, 5));    // false, test outside of range in block
        System.out.println(searchMatrix(matrix, 14));    // false, test outside block range
        System.out.println(searchMatrix(matrix, 1));    // false, test min
        System.out.println(searchMatrix(matrix, 25));    // false, test max
        System.out.println(searchMatrix(matrix, 17));    // true



    }
}