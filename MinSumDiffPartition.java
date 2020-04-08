import java.util.Arrays;
import java.util.Scanner;

public class MinSumDiffPartition {
    private int getPartitionSumDiff(int[] a, int totalSum, int sum){
        boolean subset[][] =
                new boolean[sum+1][a.length+1];

        // If sum is 0, then answer is true
        for (int i = 0; i <= a.length; i++)
            subset[0][i] = true;

        // If sum is not 0 and set is empty,
        // then answer is false
        for (int i = 1; i <= sum; i++)
            subset[i][0] = false;

        // Fill the subset table in botton
        // up manner
        for (int i = 1; i <= sum; i++)
        {
            for (int j = 1; j <= a.length; j++)
            {
                subset[i][j] = subset[i][j-1];
                if (i >= a[j-1])
                    subset[i][j] = subset[i][j] ||
                            subset[i - a[j-1]][j-1];
            }
        }

//       System.out.println(subset[sum][a.length]);
//        for (int i = 0; i <= sum; i++)
//        {
//            for (int j = 0; j <= a.length; j++)
//                System.out.print (subset[i][j]+" ");
//            System.out.println();
//        }
        for(int j=sum;j>=0;j--){
            if(subset[j][a.length]){
                int sum2 = totalSum-j;
                return Math.abs(j-sum2);
            }
        }
        //never really gets here
        return -1;
    }
    public static void main (String[] args) {
        MinSumDiffPartition minSumDiffPartition = new MinSumDiffPartition();
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        int []a;
        int sum, targetSum;
        while( t!=0 ){
            int n = s.nextInt();
            //String[] elems = s.nextLine().split(" ");
            a = new int[n];
            sum = 0;
            for(int i=0;i<n;i++) {
                a[i] = s.nextInt();
                sum += a[i];
            }
            targetSum = sum/2;
            //System.out.println(maxRectangle.maxArea(a, n, m));
            System.out.println(minSumDiffPartition.getPartitionSumDiff(a, sum, targetSum));
            t--;
        }
    }
}
