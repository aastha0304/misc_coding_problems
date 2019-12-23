package misc;

import java.util.Scanner;

public class DistinctOccSubseq {
    int countDistinctBInARec(char[] a, char[] b){
        return countDistinctBInARecUtil(a,b,0,0);
    }
    int countDistinctBInARecUtil(char[] a, char[] b, int aCurrent, int bCurrent){

        if(bCurrent>=b.length)
            return 1;
        else if(aCurrent>=a.length)
            return 0;

        if(a[aCurrent]==b[bCurrent]) {
            int with = countDistinctBInARecUtil(a, b, aCurrent + 1, bCurrent + 1);
            int without = countDistinctBInARecUtil(a, b, aCurrent + 1, bCurrent);
            return with + without;
        }else{
            return countDistinctBInARecUtil(a,b,aCurrent+1,bCurrent);
        }
    }
    int countDistinctBInA(char[] a, char[] b){
        int [][] m = new int[a.length+1][b.length+1];
        for(int i=0;i<=a.length;i++)
            m[i][0]=1;
        for(int i=1;i<=b.length;i++)
            m[0][i]=0;
        for(int i=1;i<=a.length;i++){
            for(int j=1;j<=b.length;j++) {
                if(a[i-1]!=b[j-1]){
                    m[i][j]=m[i-1][j];
                }
                else{
                    m[i][j]=m[i-1][j]+m[i-1][j-1];
                }
            }
        }
        return m[a.length][b.length];
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            char[] a = s.nextLine().toCharArray();
            char[] b = s.nextLine().toCharArray();
            DistinctOccSubseq distinctOccSubseq = new DistinctOccSubseq();
            System.out.println( distinctOccSubseq.countDistinctBInA(a,b) );
            System.out.println( distinctOccSubseq.countDistinctBInARec(a,b) );
            t--;
        }
    }
}
