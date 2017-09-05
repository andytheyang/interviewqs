import java.util.Queue;
import java.util.LinkedList;

public class Valid {
	public static boolean isValid(String input) {
		Queue<Character> q = new LinkedList<>();
		Character ch;

		for (char c : input.toCharArray()) {
			if (c == '(' || c == '{' || c == '[') {
				q.add(c);
			} else if (c == ')') {
				if ((ch = q.poll()) != null && ch == '(')
					continue;
				else
					return false;
			} else if (c == '}') {
				if ((ch = q.poll()) != null && ch == '{')
					continue;
				else
					return false;
			} else if (c == ']') {
				if ((ch = q.poll()) != null && ch == ']')
					continue;
				else
					return false;
			}
		}

		return (q.size() == 0);
	}

	public static void main(String[] args) {
		System.out.println(isValid(args[0]));
	}
}