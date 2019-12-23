package misc;

import java.util.Scanner;

public class FunWithKnapsacks {
    private int getWeightedKnapsackDuplicates(int[] val, int[] wt, int w){
        int[] dp = new int[w+1];
        for(int i=0;i<=w;i++){
            for(int j=0;j<val.length;j++){
                if(wt[j]<=i)
                    dp[i]=Math.max(dp[i], dp[i-wt[j]]+val[j]);
            }
        }
        return dp[w];
    }
    private int getWeightedKnapsackOnce(int[] val, int[] wt, int w){
        int[][] dp=new int[val.length+1][w+1];
        for(int i=0;i<=val.length;i++){
             dp[i][0]=0;
        }
        for(int i=0;i<=w;i++){
            dp[0][i]=0;
        }
        for(int i=1;i<=val.length;i++){
            for(int j=1;j<=w;j++){
                if(wt[i-1]<=j)
                    dp[i][j]=Math.max(val[i-1]+ dp[i-1][j-wt[i-1]], dp[i-1][j]);
                else
                    dp[i][j]=dp[i-1][j];
            }
        }
        return dp[val.length][w];
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String line = s.nextLine();

            int n = Integer.parseInt( line.split(" ")[0]);
            int w = Integer.parseInt( line.split(" ")[1]);
            line = s.nextLine();
            String[] elems = line.split(" ");
            int[] val = new int[n];
            for(int i=0;i<n;i++)
                val[i] = Integer.parseInt( elems[i] );
            line = s.nextLine();
            elems = line.split(" ");
            int[] wt = new int[n];
            for(int i=0;i<n;i++)
                wt[i] = Integer.parseInt( elems[i] );
            FunWithKnapsacks funWithKnapsacks = new FunWithKnapsacks();
            System.out.println(funWithKnapsacks.getWeightedKnapsackDuplicates(val, wt, w));
            System.out.println(funWithKnapsacks.getWeightedKnapsackOnce(val, wt, w));
            t--;
        }
    }
}
