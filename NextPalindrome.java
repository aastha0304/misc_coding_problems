package misc;

import java.util.Scanner;

public class NextPalindrome {
    private static boolean isAll9(String s){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) != '9')
                return false;
        }
        return true;
    }
    private static String build9Palindrom(int len){
        StringBuilder s = new StringBuilder();
        s.append('1');
        for(int i=0;i<len-1;i++)
            s.append('0');
        s.append(1);
        return s.toString();
    }
    private static boolean isPalindrome(String s){
        int len = s.length();
        for(int i=0;i<len/2;i++){
            if(s.charAt(i) != s.charAt(len-1-i))
                return false;
        }
        return true;
    }
    private static void fillCrap(char[] arr){
        for(int i=0;i<arr.length;i++)
            arr[i] = 'a';
    }
    private static String buildPalindromeAgain(String s){
        char[] res = new char[s.length()];
        fillCrap(res);
        boolean done = false;
        int len = s.length();
        int l,r;
        if( len%2 == 0){
            l = len/2 -1;
            r = len/2;
        }else{
            l = len/2;
            r = len/2;
        }
        while(!done && l>=0 && r<len){
            while(s.charAt(l) == '9'){
                res[l] = '0';
                if(r!=l){
                    res[r] = '0';
                }
                l--;
                r++;
            }
            res[l] = (char)  ( (int) (s.charAt(l) + 1) );
            res[r] = (char)  ( (int) (s.charAt(l) + 1) );
            done = true;
        }
        for(int i=0;i<len;i++){
            if(res[i] == 'a')
                res[i] = s.charAt(i);
        }
        return new String(res);
    }
    private static String buildPalindrome(String s){
        char[] res = new char[s.length()];
        fillCrap(res);
        int len = s.length();
        int mid = len / 2;
        int l = mid - 1;
        int r = (len % 2 == 0) ? mid : mid + 1;

        boolean leftsmaller = false;

        while (l >= 0 && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }

        if (l < 0 || s.charAt(l) < s.charAt(r) ){
            leftsmaller = true;
        }

        // Copy the mirror of left to tight
        while (l >= 0){
            res[l] = res[r] = s.charAt(l);
            l--;
            r++;
        }

        if (leftsmaller) {
            int carry = 1;

            if (len % 2 == 1) {
                res[mid] = s.charAt(mid) == '9' ? '0' : (char) ((int) (s.charAt(mid) + 1));
                carry = s.charAt(mid) == '9' ? 1 : 0;
            }
            l = mid - 1;
            r = (len % 2 == 0 ? mid : mid + 1);

            while (l >= 0) {
                res[l] = carry > 0 ? s.charAt(l) == '9' ? '0' : (char) ((int) (s.charAt(l) + 1)) : s.charAt(l);
                carry = carry > 0 ? s.charAt(l) == '9' ? 1 : 0 : 0;
                res[r] = res[l];
                l--;
                r++;
            }
        }
        for(int i=0;i<len;i++){
            if(res[i] == 'a')
                res[i] = s.charAt(i);
        }
        return new String(res);
    }
    private static String nextPal(String s){
        if ( isAll9(s) ){
            return build9Palindrom(s.length());
        }else {
            //remember to remove all leading 0s
            StringBuilder sbr = new StringBuilder();
            int i=0;
            while(s.charAt(i) == '0')
                i++;
            for(int j=i;j<s.length();j++)
                sbr.append(s.charAt(j));
            if(isPalindrome(sbr.toString())){
                return buildPalindromeAgain(sbr.toString());
            }else{
                return buildPalindrome(sbr.toString());
            }
        }
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String pol = s.nextLine();
            System.out.println( nextPal(pol) );
            t--;
        }
    }
}
