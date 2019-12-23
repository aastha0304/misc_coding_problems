package misc;

import java.util.Scanner;

public class MissingNumber {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String line = s.nextLine();
            int n = Integer.parseInt( line );
            line = s.nextLine();
            String[] elems = line.split(" ");
            int[] a = new int[n];
            long sum=0;
            long expectedSum=(n*(n+1))/2;
            for(int i=0;i<n-1;i++) {
                a[i] = Integer.parseInt(elems[i]);
                sum+=a[i];
            }
            System.out.println(expectedSum-sum);
            t--;
        }
    }
}
