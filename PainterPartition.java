package misc;

import java.util.*;
import java.lang.*;

public class PainterPartition {
    void display(int[][] res){
        for(int i=0;i<res.length;i++) {
            for (int j = 0; j < res[0].length; j++)
                System.out.print(res[i][j] + " ");
            System.out.println();
        }
    }
    private int sum(short[] a, int start, int end){
        int sum = 0;
        for(int i=start;i<=end;i++)
            sum+=a[i];
        return sum;
    }
    private int getMinTime(short[] a, int k){
        int[][] res = new int[a.length+1][k+1];
        res[1][1]=a[0];
        for(int l=1;l<=k;l++)
            res[1][l]=a[0];
        for(int i=2;i<=a.length;i++)
            res[i][1]=res[i-1][1]+a[i-1];
        for(short i=2;i<=a.length;i++){
            for(short l=2;l<=k;l++){
                int minOnIWithKParts = Integer.MAX_VALUE;
                for(int j=1;j<=i;j++){
                    int prevPartitionRes = res[j][l-1];
                    int sumRemainingFromPrevPartition = sum(a, j, i-1);
                    int currentContender=Math.max(prevPartitionRes, sumRemainingFromPrevPartition);
                    if(minOnIWithKParts>currentContender)
                        minOnIWithKParts = currentContender;
                }
                res[i][l] = minOnIWithKParts;
            }
        }
        //display(res);
        return res[a.length][k];
    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String line = s.nextLine();
            String[] ns = line.split(" ");
            short k = Short.parseShort( ns[0] );
            short n = Short.parseShort( ns[1] );
            line = s.nextLine();
            String[] elems = line.split(" ");
            short[] a = new short[n];
            for(int i=0;i<n;i++)
                a[i] = Short.parseShort( elems[i] );
            PainterPartition painterPartition = new PainterPartition();
            System.out.println(painterPartition.getMinTime(a,k));
            t--;
        }
    }
}