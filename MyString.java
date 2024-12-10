/**
 * A library of string functions.
 */
public class MyString {
    public static void main(String args[]) {
        String hello = "hello";
        System.out.println(countChar(hello, 'h'));
        System.out.println(countChar(hello, 'l'));
        System.out.println(countChar(hello, 'z'));
        System.out.println(subsetOf("sap","space")); //returns false
        System.out.println(subsetOf("spa","space")); //returns true
        System.out.println(subsetOf("pass","space")); //returns false
        System.out.println(subsetOf("c","space")); //returns true
        System.out.println(spacedString(hello));
        //// Put your other tests here.
        System.out.println(spacedString("silent"));
        System.out.println(randomStringOfLetters(3));
        System.out.println(randomStringOfLetters(5));
        System.out.println(randomStringOfLetters(10));
        System.out.println(remove("committee", "com"));
        System.out.println(remove("bleed", "le"));
        System.out.println(remove("cribbage", "cbbage"));
        System.out.println(remove("quality", "quality"));
        System.out.println(remove("banana", "an"));
        System.out.println(remove("hello", "lo"));
        System.out.println(insertRandomly('s', hello));
    }

    /**
     * Returns the number of times the given character appears in the given string.
     * Example: countChar("Center",'e') returns 2 and countChar("Center",'c') returns 0. 
     * 
     * @param str - a string
     * @param c - a character
     * @return the number of times c appears in str
     */
    public static int countChar(String str, char ch) {
        int counter = 0;
        for(int i=0;i<str.length();i++){
            if (str.charAt(i)==ch) {
                counter++;
            }
        }
        return counter;
    }

    /** Returns true if str1 is a subset string str2, false otherwise
     *  Examples:
     *  subsetOf("sap","space") returns true
     *  subsetOf("spa","space") returns true
     *  subsetOf("pass","space") returns false
     *  subsetOf("c","space") returns true
     *
     * @param str1 - a string
     * @param str2 - a string
     * @return true is str1 is a subset of str2, false otherwise
     */
    public static boolean subsetOf(String str1, String str2) {
         // Edge cases
    if (str1.length() > str2.length()) {
        return false; // str1 cannot be contained in a shorter str2
    }
    if (str1.length() == 0) { // Check for empty string 
        return true; // Empty string is contained in everything
    }

    // Main logic
    int [] charCount = new int[26];
    for (int i=0;i<str2.length();i++){
        charCount[str2.charAt(i)-'a']++;
    }
        for(int i=0;i<str1.length();i++){
            int indx = str1.charAt(i) - 'a';
            if (charCount[indx]==0) {
                return false;
            }
            charCount[indx]--;
        }
        return true;
    }

    /** Returns a string which is the same as the given string, with a space
     * character inserted after each character in the given string, except
     * for the last character. 
     * Example: spacedString("silent") returns "s i l e n t"
     * 
     * @param str - a string
     * @return a string consisting of the characters of str, separated by spaces.
     */
    public static String spacedString(String str) {
        String spaced = "";
        for (int i=0;i<str.length()-1;i++){
            spaced += str.charAt(i) + " ";
        }
        spaced += str.charAt(str.length()-1);
        return spaced;
    }
  
    /**
     * Returns a string of n lowercase letters, selected randomly from 
     * the English alphabet 'a', 'b', 'c', ..., 'z'. Note that the same
     * letter can be selected more than once.
     * 
     * Example: randomStringOfLetters(3) can return "zoo"
     * 
     * @param n - the number of letter to select
     * @return a randomly generated string, consisting of 'n' lowercase letters
     */
    public static String randomStringOfLetters(int n) {
        //initialization
        String word = "";
        int lower_range = (int) 'a';
        int upper_range = (int) 'z';

        for(int i=0;i<n;i++){
            word += (char) (lower_range+Math.random()*(upper_range-lower_range+1)) ;
        }
        return word;
    }
 
    /** If str1 contains str2, returns true; otherwise returns false. */
 public static boolean contains(String str1, String str2) {
    // Edge cases
    if (str2.length() > str1.length()) {
        return false; // str2 cannot be contained in a shorter str1
    }
    if (str2.length() == 0) { // Check for empty string 
        return true; // Empty string is contained in everything
    }

    // Main logic
    for (int i = 0; i <= str1.length() - str2.length(); i++) {
        boolean match = true; // Assume a match
        for (int j = 0; j < str2.length(); j++) {
            if (str1.charAt(i + j) != str2.charAt(j)) {
                match = false; // Mismatch found
                break; // Exit inner loop early
            }
        }
        if (match) {
            return true; // Match found
        }
    }

    return false; // No match found
}
    /**
     * Returns a string consisting of the string str1, minus all the characters in the
     * string str2. Assumes (without checking) that str2 is a subset of str1.
     * Example: remove("meet","committee") returns "comit" 
     * 
     * @param str1 - a string
     * @param str2 - a string
     * @return a string consisting of str1 minus all the characters of str2
     */
    public static String remove(String str1, String str2) {
        // Initialization
        String removed = "";
        boolean[] rem = new boolean[str1.length()]; // To track which characters to retain
    
        // Mark all characters in `str1` as initially retained
        for (int i = 0; i < str1.length(); i++) {
            rem[i] = true;
        }
    
        // Removing characters from `str1` based on `str2`
        for (int i = 0; i < str2.length(); i++) {
            char target = str2.charAt(i);
            for (int j = 0; j < str1.length(); j++) {
                if (str1.charAt(j) == target && rem[j]) {
                    rem[j] = false; // Mark the first matching occurrence for removal
                    break;         // Move to the next character in str2
                }
            }
        }
    
        // Constructing the new string
        for (int i = 0; i < str1.length(); i++) {
            if (rem[i]) {
                removed += str1.charAt(i); // Append retained characters
            }
        }
    
        return removed;
    }

    /**
     * Returns a string consisting of the given string, with the given 
     * character inserted randomly somewhere in the string.
     * For example, insertRandomly("s","cat") can return "scat", or "csat", or "cast", or "cats".  
     * @param ch - a character
     * @param str - a string
     * @return a string consisting of str with ch inserted somewhere
     */
    public static String insertRandomly(char ch, String str) {
         // Generate a random index between 0 and str.length()
         int randomIndex = (int) (Math.random() * (str.length() + 1));
         // Insert the character at the random index
         String result = str.substring(0, randomIndex) + ch + str.substring(randomIndex);
         return result;
    }    
}
