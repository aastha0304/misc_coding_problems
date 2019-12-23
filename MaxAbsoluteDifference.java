package misc;

import java.util.Scanner;

public class MaxAbsoluteDifference {

    private int getMaxAbsoluteDiff(int[] a){
        int res = Integer.MIN_VALUE;
        int n = a.length;
        int[] minLeft = new int[n];
        int[] maxLeft = new int[n];
        int[] minRight = new int[n];
        int[] maxRight = new int[n];

        minLeft[0]=a[0];
        maxLeft[0]=a[0];
        minRight[n-1]=a[n-1];
        maxRight[n-1]=a[n-1];
        int maxLeftSum = maxLeft[0]<0?0:maxLeft[0], minLeftSum = minLeft[0]>0?0:minLeft[0],
                maxRightSum = maxRight[n-1]<0?0:maxRight[n-1], minRightSum = minRight[n-1]>0?0:minRight[n-1];

        for(int i=1;i<n;i++){
            maxLeftSum += a[i];
            maxLeft[i] = Math.max(maxLeft[i-1], maxLeftSum);
            if(maxLeftSum<0)
                maxLeftSum=0;
            minLeftSum += a[i];
            minLeft[i] = Math.min(minLeft[i-1], minLeftSum);
            if(minLeftSum>0)
                minLeftSum=0;
        }
        for(int i=n-2;i>-1;i--){
            if(i!=n-2)
                maxRightSum += a[i+1];
            else{
                maxRightSum = a[i+1];
            }
            maxRight[i] = Math.max(maxRight[i+1], maxRightSum);
            if(maxRightSum<0)
                maxRightSum=0;
            if(i!=n-2)
                minRightSum += a[i+1];
            else{
                minRightSum = a[i+1];
            }

            minRight[i] = Math.min(minRight[i+1], minRightSum);
            if(minRightSum>0)
                minRightSum=0;
        }
        for(int i=0;i<n;i++)
            res = Math.max(res, Math.max(Math.abs(maxLeft[i] - minRight[i]), Math.abs(maxRight[i] - minLeft[i])));

//        Utils.display(minLeft);
//        Utils.display(maxLeft);
//        Utils.display(minRight);
//        Utils.display(maxRight);
        return res;
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
            MaxAbsoluteDifference maxAbsoluteDifference = new MaxAbsoluteDifference();
            System.out.println(maxAbsoluteDifference.getMaxAbsoluteDiff(a));
            t--;
        }
    }
}
