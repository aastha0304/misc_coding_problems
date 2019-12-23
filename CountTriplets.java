package misc;

import java.util.Scanner;

public class CountTriplets {
    static int sumSize = 1000000;

    private int getTripletCount(int[] a){
        int[] sums = new int[sumSize+1];
        int count=0;
        for(int i=0;i<a.length;i++)
            for(int j=i+1;j<a.length;j++)
                if(a[i]+a[j]<=sumSize)
                        sums[a[i] + a[j]] ++;

        for(int i=0;i<a.length;i++)
            count += sums[a[i]];

        return count>0?count:-1;
    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            int n = Integer.parseInt( s.nextLine() );
            String line = s.nextLine();
            String[] elems = line.split(" ");
            int[] a = new int[n];
            for(int i=0;i<n;i++)
                a[i] = Integer.parseInt( elems[i] );
            CountTriplets countTriplets = new CountTriplets();
            System.out.println(countTriplets.getTripletCount(a));
            t--;
        }
    }
}
