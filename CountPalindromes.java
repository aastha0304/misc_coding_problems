import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class CountPalindromes {
    private int getAllPals(String a){
        Set<String> pals = new HashSet<>();
        char[] s = a.toCharArray();
        for(int i=0;i<a.length();i++){
            //odd length
            for (int j = 0; j <= i; j++) {
                if(i+j<0 || i+j>=a.length()){
                    break;
                }
                //palindrome check
                if(s[i-j]==s[i+j]){
                    if ((i + j + 1) - (i - j) > 0) {
                        String sub = a.substring((i - j), (i + j + 1));
                        pals.add(sub);
                    }
                }else
                    break;
            }
            //even length
            for (int j = 0; j <= i; j++) {
                if(i+j+1<0 || i+j+1>=a.length()){
                    break;
                }
                //palindrome check
                if(s[i-j]==s[i+j+1]){
                    if ((i + j + 1+1) - (i - j) > 0) {
                        String sub = a.substring((i - j), (i + j + 1+1));
                        pals.add(sub);
                    }
                }else
                    break;
            }
        }
//        for(String str: pals){
//            System.out.print(str+" ");
//        }
        return pals.size();
    }
    public static void main(String[] args) throws IOException {
        CountPalindromes countPalindromes = new CountPalindromes();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(input.readLine());
        for (int ix = 0; ix < testCases; ix++) {
            String a = input.readLine().trim();
            System.out.println(countPalindromes.getAllPals(a));
        }
    }
}
