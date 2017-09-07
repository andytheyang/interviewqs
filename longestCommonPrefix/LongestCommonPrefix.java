public class LongestCommonPrefix {
	public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        
        char c = ' ';
        int len = -1;
        int strNum;
        
        do {
            len++;
            
            for (strNum = 0; strNum < strs.length; strNum++) {
                String cur = strs[strNum];
                
                if (len >= cur.length())        // handles empty strings
                    break;
                if (strNum == 0)                // if first string, store prev char
                    c = cur.charAt(len);
                else if (cur.charAt(len) != c)  // if char doesn't match
                    break;
            }
        } while (strNum == strs.length);
        
        return strs[0].substring(0, len);
    }

    public static void main(String[] args) {
    	System.out.println(longestCommonPrefix(args));
    }
}