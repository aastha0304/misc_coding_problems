import java.util.Arrays;
import java.util.Comparator;

public class LexicShortest {
    public static String findStem(String arr[])
    {
        // Determine size of the array
        int n = arr.length;

        // Take first word from array as reference
        String s = arr[0];
        int len = s.length();

        String res = "";

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {

                // generating all possible substrings
                // of our reference string arr[0] i.e s
                String stem = s.substring(i, j);
                int k = 1;
                for (k = 1; k < n; k++)

                    // Check if the generated stem is
                    // common to all words
                    if (!arr[k].contains(stem))
                        break;

                // If current substring is present in
                // all strings and its length is greater
                // than current result
                if (k == n && res.length() < stem.length())
                    res = stem;
            }
        }

        return res;
    }
    private static String findMin(String[] words){
        int n = words.length;
        Arrays.sort(words);
        System.out.println("\nThe lexicographical order of the words is: ");
        for(int i = 0; i < n; i++) {
            System.out.println(words[i]);
        }
        return findStem(words);
    }
    public static void main(String[] args){
        String[] strs = {"hackereaths", "google"};
        System.out.println(findMin(strs));
    }
}
