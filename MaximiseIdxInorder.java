package misc;

import java.util.Scanner;

public class MaximiseIdxInorder {
    private int maximiseIdxInorder(long[] a){
        int max=0;
        long[] maxAfter = new long[a.length];
        long[] minBefore = new long[a.length];
        minBefore[0] = a[0];
        maxAfter[a.length-1]=a[a.length-1];
        for(int i=1;i<a.length;i++){
            minBefore[i]=Math.min(a[i],minBefore[i-1]);
        }
        for(int i=a.length-2;i>-1;i--){
            maxAfter[i] = Math.max(a[i],maxAfter[i+1]);
        }
        int i=0,j=0;
        while(i<a.length && j<a.length){
            if(maxAfter[j]>=minBefore[i]) {
                if(j-i>max)
                    max=j-i;
                j++;
            }
            else
                i++;
        }
        return max;
    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String line = s.nextLine();
            int n = Integer.parseInt( line );
            line = s.nextLine();
            String[] elems = line.split(" ");
            long[] a = new long[n];
            for(int i=0;i<n;i++)
                a[i] = Long.parseLong( elems[i] );
            MaximiseIdxInorder maximiseIdxInorder = new MaximiseIdxInorder();
            System.out.println(maximiseIdxInorder.maximiseIdxInorder(a));
            t--;
        }
    }
}
