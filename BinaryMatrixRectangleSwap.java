import java.util.Arrays;
import java.util.Scanner;

public class BinaryMatrixRectangleSwap {
    private int getMaxRecSwap(int[][] a){
        MaxRectangle.display(a);
        int [][] dp = new int[a.length][a[0].length];
        int c=0;
        for(int[] row: a){
            dp[c] = Arrays.copyOf(row, row.length);
            c++;
        }
        for(int i=a.length-2;i>=0;i--){
            for(int j=0;j<a[0].length;j++){
                if(a[i][j]==1){
                    dp[i][j] += dp[i+1][j];
                }
            }
        }
        MaxRectangle.display(dp);
        int[] rowArr;
        int max = Integer.MIN_VALUE;
        for(int[] row: dp){
            rowArr = Arrays.copyOf(row, row.length);
            Arrays.sort(rowArr);

            for(int i=0;i<rowArr.length;i++){
                max = Math.max(max,rowArr[i]*(a[0].length-i));
            }
        }
        return max;
    }
    public static void main (String[] args) {
        BinaryMatrixRectangleSwap binaryMatrixRectangleSwap = new BinaryMatrixRectangleSwap();
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while( t!=0 ){
            int r = s.nextInt();
            int c = s.nextInt();
            //String[] elems = s.nextLine().split(" ");
            int[][] a = new int[r][c];
            for(int i=0;i<r;i++) {
                for(int j=0;j<c;j++) {
                    a[i][j] = s.nextInt();
                }
            }
            //System.out.println(maxRectangle.maxArea(a, n, m));
            System.out.println(binaryMatrixRectangleSwap.getMaxRecSwap(a));
            t--;
        }
    }
}
