import java.util.Scanner;

public class MaxZizagSeq {
    private int getMaxZizagSeq(int[][] a, int n){
        int max = Integer.MIN_VALUE;
        int[][] res = new int[a.length][a[0].length];
        for (int j = 0; j < n; j++) {
            res[0][j] = getMaxZizagSeqUtil(a, 0, j, res);
            max = Math.max(res[0][j], max);
        }
        return max;
    }
    private int getMaxZizagSeqUtil(int[][] a, int i, int j, int[][] res){
        if(i==a.length-1)
            return (res[i][j]=a[i][j]);
        if(res[i][j]!=0)
            return res[i][j];
        else {
            int localres = 0;
            for(int k=0;k<a[i].length;k++){
                if(k==j)
                    continue;
                else
                    localres = Math.max(localres, getMaxZizagSeqUtil(a, i+1, k, res));
            }
            res[i][j] = a[i][j] + localres;
        }
        return res[i][j];
    }
    public static void main (String[] args) {
        MaxZizagSeq maxZizagSeq = new MaxZizagSeq();
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while( t!=0 ){
            int n = s.nextInt();
            //String[] elems = s.nextLine().split(" ");
            int[][] a = new int[n][n];
            for(int i=0;i<n;i++)
                for(int j=0;j<n;j++)
                    a[i][j] = s.nextInt();
            //System.out.println(maxRectangle.maxArea(a, n, m));
            System.out.println(maxZizagSeq.getMaxZizagSeq(a, n));
            t--;
        }
    }
}
