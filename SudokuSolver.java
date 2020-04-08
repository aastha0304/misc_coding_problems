
import java.util.Scanner;

public class SudokuSolver {
    private void display(int[][] a){
        for(int[] i:a)
            display(i);
    }
    private void display(int[] a){
        for(int i:a){
            System.out.print(i+" ");
        }
        //System.out.println();
    }
    private boolean canPut(int[][] a, int i, int j, int num){
        for(int r=0;r<9;r++)
            if(a[r][j]==num)
                return false;
        for(int c=0;c<9;c++)
            if(a[i][c]==num)
                return false;
        int diagRow = 3*(i/3);
        int diagCol = 3*(j/3);
        for(int k=diagRow;k<diagRow+3;k++){
            for(int l=diagCol;l<diagCol+3;l++){
                if(a[k][l]==num)
                    return false;
            }
        }
        return true;
    }
    private boolean solveSudoku(int[][] a) {
        boolean done = false;
        int i=0,j=0;
        for(;i<9;i++){
            for(j=0;j<9;j++){
                if(a[i][j] == 0)
                    break;
            }
            if(j!=9)
                break;
        }
        if(i==9 && j==9)
            return true;
        for(int num=1;num<=9;num++){
            if(canPut(a, i, j, num)){
                a[i][j] = num;
                done = solveSudoku(a);
                if(!done)
                    a[i][j] = 0;
                else
                    return true;
            }
        }
        return done;
    }
    public static void main (String[] args) {
        SudokuSolver sudokuSolver = new SudokuSolver();
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while( t!=0 ){
            int n = 9;
            int[][] a = new int[n][n];
            for(int i=0;i<n;i++)
                for(int j=0;j<n;j++)
                    a[i][j] = s.nextInt();
            sudokuSolver.solveSudoku(a);
            sudokuSolver.display(a);
            System.out.println();
            t--;
        }
    }
}
