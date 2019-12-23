package misc;

import java.util.Scanner;

public class MaxSubarraySum {
    private int getMaxSubarraySum(int[] a){
        int sum=0,max=-10000001;
        for(int i=0;i<a.length;i++){
            sum+=a[i];
            if(sum>max)
                    max=sum;
            if(sum<0)
                sum=0;
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
            int[] a = new int[n];
            for(int i=0;i<n;i++)
                a[i] = Integer.parseInt( elems[i] );
            MaxSubarraySum subArray = new MaxSubarraySum();
            System.out.println(subArray.getMaxSubarraySum(a));
            t--;
        }
    }
}
