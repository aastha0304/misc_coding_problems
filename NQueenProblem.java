
import java.util.Scanner;

public class NQueenProblem {
    private boolean isSafe(int[][] board, int r, int c){
        for(int i=0;i<c;i++){
            if(board[r][i]==1)
                return false;
        }
        for(int i=r,j=c;i>=0 && j>=0;i--,j--){
            if(board[i][j]==1)
                return false;
        }
        for (int i=r,j=c;i<board.length&&j>=0;i++,j--)
            if (board[i][j] == 1)
                return false;
        return true;
    }
    private boolean solveUtil(int[][] board, int n, int col, int[] sol){
        if(col>=n) {
            System.out.print("[");
            for(int i:sol){
                System.out.print(i+" ");
            }
            System.out.print("] ");
            //System.out.println();
            return true;
        }
        boolean nextQueen=false;
        for(int i=0;i<board.length;i++){
            if(isSafe(board, i, col)){
                board[i][col] = 1;
                sol[col] = i+1;
                nextQueen = solveUtil(board, n, col + 1, sol);
                board[i][col] = 0;
            }
        }
        return nextQueen;
    }
    private void solve(int n){
        if(n==2 || n==3) {
            System.out.println("-1");
            return;
        }
        int[][] board = new int[n][n];
        int[] sol = new int[n];
        int col = 0;
        for(int i=0;i<board.length;i++) {
            if(isSafe(board, i, col)) {
                board[i][col] = 1;
                sol[col] = i+1;
                if(solveUtil(board, n, col + 1, sol));
                board[i][col] = 0;
                //sol[col] = 0;
            }
        }
        System.out.println();
    }
    public static void main (String[] args) {
        NQueenProblem nQueenProblem = new NQueenProblem();
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while (t != 0) {
            int n = s.nextInt();
            nQueenProblem.solve(n);
            t--;
        }
    }

}
