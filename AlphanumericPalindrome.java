package misc;

import java.util.Scanner;

public class AlphanumericPalindrome {
    private boolean isAlphanumeric(char c){
        return ( c>='0' && c<='9' ) || ( c>='a' && c<='z' ) || ( c>='A' && c<='Z' );
    }
    private boolean isSame(char a, char b){
        if (a==b)
            return true;
        return ((a >= 'a' && a <= 'z') && (b == a - 32)) || ((a >= 'A' && a <= 'Z') && (b == a + 32));
    }
    private boolean isPalindrome(String line){
        int i=0,j=line.length()-1;

        while(i<j){
            while( !isAlphanumeric(line.charAt(i)) && i<line.length() && i<j)
                i++;
            while(!isAlphanumeric(line.charAt(j)) && j>-1 && i<j)
                j--;
            if( !isSame(line.charAt(i), line.charAt(j)))
                return false;
            i++;
            j--;
        }

        return true;
    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String line = s.nextLine();
            AlphanumericPalindrome alphanumericPalindrome = new AlphanumericPalindrome();
            System.out.println( alphanumericPalindrome.isPalindrome(line)? "YES":"NO" );
            t--;
        }
    }

}
