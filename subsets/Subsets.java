import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


/* The subsets interview problem asks for all subsets of a given list / array.
 * My approach uses a binary number bitmask in order to get sets. Since there 
 * are 2^n possible subsets of an n-element array, and there are 2^n bits in
 * an n-bit number, there is a mapping between iterative bitmasks and
 * permutations of subsets.
 */
public class Subsets {

	// uses a binary number in order to get subsets with lists up to 63 items
	public static Set<HashSet<Object>> getAllSubsetsBinary(List<Object> list) {
		HashSet<HashSet<Object>> ret = new HashSet<>();
		int size = list.size();
		long maxIndex = (1 << size);		// index shouldn't exceed this
		HashSet<Object> currentSet = new HashSet<>();
		int pos;

		for (long index = 1; index < maxIndex; index++) {	// run through all bit combinations
			currentSet.clear();
			pos = 0;

			for (long i = 1; i < maxIndex; i <<= 1) {		// run through each bit in index
				if ((index & i) != 0)						// if index i is currently "selected" by index
					currentSet.add(list.get(pos));			// add item i to list
				pos++;
			}

			ret.add(new HashSet<Object>(currentSet));		// add a copy of this set to the output set
		}

		return ret;
	}

	// prints all subsets of the array [1,2,3,4]
	public static void main(String[] args) {
		ArrayList<Object> a = new ArrayList<>();
		a.add(new Integer(1));
		a.add(new Integer(2));
		a.add(new Integer(3));
		a.add(new Integer(4));
		Set<HashSet<Object>> s = getAllSubsetsBinary(a);
		Iterator<HashSet<Object>> it = s.iterator();

		while (it.hasNext())
			System.out.println(it.next());
	}
}