package misc;

import java.util.Scanner;

public class StringWith3CharRules {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );

        while( t!=0 ){
            int n = Integer.parseInt(s.nextLine());
            StringWith3CharRules stringWith3CharRules = new StringWith3CharRules();

            System.out.println( stringWith3CharRules.getNumberOfStrings(n));
            t--;
        }
    }

    private int getNumberOfStrings(int n) {
        int res;
        if(n<=0)
            return n;
        if(n==1)
            return 3;
        else{
            //all are a
            res=1;
            //1 c, rest a
            res+=n;
            //2 c, rest a
            res+=n*(n-1)/2;
            //1 b, rest a
            res+=n;
            //1 c, 1 b, rest a
            res+=n*(n-1);
            //1 b, 2 c, rest a
            res+=n*(n-1)*(n-2)/2;
            return res;
        }
    }
}
