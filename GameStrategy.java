package misc;

import java.util.Scanner;

public class GameStrategy {
    int getMaxCoins(int[] a){
        //does a recursive call based on max of minimum value for player 1 in next turn
        //return getMaxCoinsRecurse(a, 0, a.length-1);
        //now trying to maximise based on sum for player1
        int[][] m = new int[a.length][a.length];
        int sum = 0;
        for(int i:a)
            sum+=i;
        getMaxCoinsMemUtil(a, 0, a.length-1, m, sum);
        return m[0][a.length-1];
    }
    int getMaxCoinsMemUtil(int[] a, int start, int end, int[][] m, int sum){
        if(start==end)
            return a[start];
        if(end==start+1)
            return Math.max(a[start], a[end]);
        m[start][end] = Math.max(sum-getMaxCoinsMemUtil(a, start+1, end, m, sum-a[start]),
                sum-getMaxCoinsMemUtil(a, start, end-1, m, sum-a[end]));
        return m[start][end];
    }
    int getMaxCoinsRecurse(int[] a, int start, int end){
        if(start==end)
            return a[start];
        else if(end==start+1)
            return Math.max(a[start],a[end]);
        else{
            int atStart = a[start];
            int fromStart = Math.min(getMaxCoinsRecurse(a, start+1, end-1),
                    getMaxCoinsRecurse(a, start+2, end));
            int atEnd = a[end];
            int fromEnd = Math.min(getMaxCoinsRecurse(a, start+1, end-1),
                    getMaxCoinsRecurse(a, start, end-2));
            return Math.max(atStart+fromStart, atEnd+fromEnd);
        }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt(s.nextLine());
        GameStrategy gameStrategy = new GameStrategy();
        while (t != 0) {
            int n=s.nextShort();
            int[] a = new int[n];
            for(int i=0;i<n;i++)
                a[i] = s.nextShort();
            System.out.println(gameStrategy.getMaxCoins(a));
            t--;
        }
    }
}
