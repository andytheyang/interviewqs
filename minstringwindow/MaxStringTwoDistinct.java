// find the LONGEST substring with at MOST two distinct characters
import java.util.HashMap;

public class MaxStringTwoDistinct {
    static HashMap<Character, Integer> hash;
    
    public static boolean see(Character ch) {
        if (hash.containsKey(ch)) {
            hash.put(ch, hash.get(ch).intValue() + 1);
        } else {
            hash.put(ch, 1);
        }

        return hash.size() <= 2;
    }
    
    public static boolean unsee(Character ch) {
        if (hash.containsKey(ch)) {
            hash.put(ch, hash.get(ch).intValue() - 1);

            hash.remove(ch, 0);     // attempt to remove if our count is at 0
        } else {
            System.err.println("ERROR: execution should never reach this point");
        }

        return hash.size() <= 2;
    }
    
    public static String getAnswer(String s) {
        hash = new HashMap<>();
        
        int start = 0;
        int end = 0;
        int maxLength = 0;
        int maxStart = 0;
        
        while (end < s.length()) {
            Character ch = new Character(s.charAt(end++));
            
            if (see(ch) && end - start > maxLength) {
                maxStart = start;
                maxLength = end - start;
            }
                
            ch = new Character(s.charAt(start));

            while (start < end && hash.size() > 2) {
                ch = new Character(s.charAt(start++));
                unsee(ch);
            }
        }
        
        return s.substring(maxStart, maxStart + maxLength);
    }

	public static void main (String[] args) {
		System.out.println(getAnswer(args[0]));
	}
}