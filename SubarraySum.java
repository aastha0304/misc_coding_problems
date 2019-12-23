package misc;

import java.util.*;
import java.lang.*;

class SubArraySum {
    void getSubrrayRange(int[] a,int S){
        int start=0,end=0;
        int sum=a[start];
        int i=0;
        while( sum!=S && i<a.length ){
            //System.out.println("sum is "+sum+" start is "+start+"end is "+end);
            while(sum<S && end<a.length-1) {
                end++;
                sum+=a[end];
            }
            while (sum>S && start<a.length-1){
                sum-=a[start];
                start++;
            }
            i++;
        }
        if(sum==S){
            System.out.println(start+1+" "+(end+1));
            return;
        }
        System.out.println("-1");
        return;
    }

    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String line = s.nextLine();
            String[] ns = line.split(" ");
            int n = Integer.parseInt( ns[0] );
            int S = Integer.parseInt( ns[1] );
            line = s.nextLine();
            String[] elems = line.split(" ");
            int[] a = new int[n];
            for(int i=0;i<n;i++)
                a[i] = Integer.parseInt( elems[i] );
            SubArraySum subArraySum = new SubArraySum();
            subArraySum.getSubrrayRange(a,S);
            t--;
        }
    }
}