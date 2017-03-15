import java.util.Arrays;

public class first {
	public static final int NUM_CHARS = 26;
	// assumes only lowercase a-z
	// returns char ascii 0 if nothing
	public static char firstNonRepeatingChar(String s) {
		boolean charOccured[] = new boolean[NUM_CHARS];				// represents if a char has been seen
		Arrays.fill(charOccured, false);			// fill everything with false
		char current;

		for (int i = 0; i < s.length(); i++) {
			current = s.charAt(i);

			if (current < 'a' || current > 'z')		// if out of range, no result
				break;

			if (charOccured[current - 'a'])			// current - a because current is ascii and we must 0-address
				return current;						// return current character if it's already been visited
			else
				charOccured[current - 'a'] = true;	// mark as visited
		}

		return 0;
	}

	public static void main(String[] args) {
		System.out.println(firstNonRepeatingChar("abcdefghijklmnopqrstuvwxyz"));				// char 0
		System.out.println(firstNonRepeatingChar("hello world"));								// l
		System.out.println(firstNonRepeatingChar("please land me an internship at workday"));	// e
	}
}