
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class URLShortener {
    private final Map<String, String> shortToLongUrlMap; // Maps short URL to long URL
    private final Map<String, String> longToShortUrlMap; // Maps long URL to short URL
    private final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int SHORT_URL_LENGTH = 7; // Length of the generated short URL

    public URLShortener() {
        shortToLongUrlMap = new HashMap<>();
        longToShortUrlMap = new HashMap<>();
    }

        public String shortenURL(String longURL) {
        if (longToShortUrlMap.containsKey(longURL)) {
            return longToShortUrlMap.get(longURL); // Return existing short URL
        }

        String shortURL = generateShortURL();
        
        while (shortToLongUrlMap.containsKey(shortURL)) {
            shortURL = generateShortURL();
        }

      
        shortToLongUrlMap.put(shortURL, longURL);
        longToShortUrlMap.put(longURL, shortURL);
        return shortURL;
    }

    
    public String expandURL(String shortURL) {
        return shortToLongUrlMap.getOrDefault(shortURL, "Invalid Short URL");
    }

    
    private String generateShortURL() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        URLShortener urlShortener = new URLShortener();
        System.out.println("Example : "); 
        String longURL1 = "https://www.example.com/my-long-url-1";
        String shortURL1 = urlShortener.shortenURL(longURL1);
        System.out.println("Short URL for '" + longURL1 + "' is: " + shortURL1);


        while (true) {
            System.out.println("Enter a long URL to shorten (or type 'exit' to quit):");
            String longURL = scanner.nextLine();

            if (longURL.equalsIgnoreCase("exit")) {
                break;
            }

            String shortURL = urlShortener.shortenURL(longURL);
            System.out.println("Short URL for '" + longURL + "' is: " + shortURL);

            System.out.println("Would you like to expand this short URL? (yes/no)");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("yes")) {
                String expandedURL = urlShortener.expandURL(shortURL);
                System.out.println("Expanded URL for '" + shortURL + "' is: " + expandedURL);
            }

            System.out.println(); 
        }

        scanner.close(); 
        System.out.println("Thank you for using the Link Shortener!");
    }
}
