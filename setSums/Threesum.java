import java.util.HashSet;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Threesum {
	public static List<List<Integer>> get3Sum(int[] array) {
		ArrayList<List<Integer>> ret = new ArrayList<>();
		Arrays.sort(array);		// sort so we can avoid duplicates (O(nlog(n)))

		int prevnum = 0;

		for (int firstLoc = 0; firstLoc < array.length; firstLoc++) {		// consider all possible first elements in ascending order
			int first = array[firstLoc];

			if (firstLoc != 0 && first == prevnum)
				continue;			// skip if this is a repeat number

			prevnum = first;		// we are at a unique number now, update prevnum with newest unique
			HashSet<Integer> pairHash = new HashSet<>();

			for (int secondLoc = firstLoc + 1; secondLoc < array.length; secondLoc++) {
				if (pairHash.contains(0 - (first + array[secondLoc]))) {		// if there is a third number that when added to the original two, makes 0
					ArrayList<Integer> add = new ArrayList<>();
					add.add(first);
					add.add(array[secondLoc]);
					add.add(0 - (first + array[secondLoc]));

					System.out.println("set found " + add.toString());
					ret.add(add);
				}

				pairHash.add(array[secondLoc]);		// add second item to seen
			}
		}

		return ret;
	}

	public static void main(String[] args) {
		int[] input = new int[args.length];
		
		for (int i = 0; i < args.length; i++) {
			input[i] = Integer.parseInt(args[i]);
		}

		get3Sum(input);
	}

}