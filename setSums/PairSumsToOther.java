/*
This question was found on Intuit SWE Interview Questions on Glassdoor:
https://www.glassdoor.com/Interview/asked-how-to-check-what-pairs-of-ints-in-an-array-could-add-up-to-other-elements-in-an-array-QTN_2235607.htm

Which pairs of ints add up to other elements in an array?
This solution is O(n^2) and uses a HashSet in order to check whether two
integers sum up to another element in the array. I use a pseudo-backtracking
approach in order to consider all possible pairs.

*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;
import java.util.Set;

public class PairSumsToOther {
    public static Set<List<Integer>> getPairs(int[] array) {
        // List<List<Integer>> ret = new ArrayList<>();
        HashSet<List<Integer>> ret = new HashSet<>();
        HashSet<Integer> hash = new HashSet<>();

        for (int i : array) {       // pre-populate hash for O(1) lookup
            hash.add(i);
        }

        int first, second;

        for (int f = 0; f < array.length - 1; f++) {        // run through all possible first numbers
            first = array[f];
            hash.remove(f);

            for (int s = f + 1; s < array.length; s++) {
                second = array[s];
                hash.remove(second);            // remove from considered results

                if (hash.contains(first + second) &&            // if sum is valid and exists
                    !ret.contains(Arrays.asList(first, second)) &&  // if first combination is not there
                    !ret.contains(Arrays.asList(second, first))) {  // if second combination is not there
                    ret.add(Arrays.asList(first, second));  // create new result and store in ret
                }

                hash.add(second);               // return to considered results
            }

            hash.add(first);
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] input = new int[args.length];

        for (int i = 0; i < args.length; i++) {
            input[i] = Integer.parseInt(args[i]);
        }

        System.out.println(getPairs(input));
    }
}