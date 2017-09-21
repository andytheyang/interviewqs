import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

public class GenerateParentheses {
	public static List<String> generate(int n) {
		List<String> result = new LinkedList<>();		// for O(1) insertion
		backtrack(result, new StringBuilder(), 0, 0, n);

		return result;
	}

	private static void backtrack(List<String> result, StringBuilder current, int open, int closed, int n) {
		if (current.length() == n * 2) {
			result.add(current.toString());
			return;
		}

		if (open < n) {	// there can be n open parentheses. If we can open more, open first
			current.append('(');
			backtrack(result, current, open + 1, closed, n);
			current.setLength(current.length() - 1); 	// remove last character
		}

		if (closed < open) {			// we can only have as many closed parentheses as that many open so far
			current.append(')');
			backtrack(result, current, open, closed + 1, n);
			current.setLength(current.length() - 1); 	// remove last character
		}
	}

	public static void main(String[] args) {
		System.out.println(generate(Integer.parseInt(args[0])));
	}
}