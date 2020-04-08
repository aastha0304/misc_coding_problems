import java.util.Scanner;

public class Knapsack01 {
    private int getMaxKnapsack(int[] a, int n, int[] wts, int w){
        int[][] m = new int[n+1][w+1];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=w;j++){
                if(i==0 || j==0)
                    m[i][j] = 0;
                else{
                    if(wts[i-1]>j)
                        m[i][j] = m[i-1][j];
                    else{
                        m[i][j] = Math.max(m[i-1][j], a[i-1]+m[i-1][w-wts[i-1]]);
                    }
                }
            }
        }
        return m[n][w];
    }

    public static void main (String[] args) {
        Knapsack01 knapsack01 = new Knapsack01();
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while( t!=0 ){
            int n = s.nextInt();
            int w = s.nextInt();
            //String[] elems = s.nextLine().split(" ");
            int[] a = new int[n];
            for(int i=0;i<n;i++)
                a[i] = s.nextInt();
            int[] wts = new int[n];
            for(int i=0;i<n;i++)
                wts[i] = s.nextInt();
            //System.out.println(maxRectangle.maxArea(a, n, m));
            System.out.println(knapsack01.getMaxKnapsack(a, n, wts, w));
            t--;
        }
    }
}
