// rudimentary link shortener that maps short "urls" (really just strings) to any length strings
// uses a hashmap as backend for this
// creates tinyurls and decodes them in O(1) time

public class tinyurl {
    // public static final String prefix = "http://tinyurl.com/";
    public static final int codeLen = 6;
    public static final String codeChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
    private Map<String, String> urls;
    private Random rand;

    public tinyurl() {
        urls = new HashMap<>();
        rand = new Random();
    }

    // Encodes a URL to a shortened URL
    public String encode(String longUrl) {
        StringBuilder output;
        
        do {
            output = new StringBuilder();
            
            for (int i = 0; i < codeLen; i++) {
                output.append(codeChars.charAt(rand.nextInt(codeChars.length())));
            }
            
        } while(urls.containsKey(output.toString()));       // while the current random is in the hashmap already
        
        urls.put(output.toString(), longUrl);
        
        return output.toString();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return urls.get(shortUrl);
    }
}