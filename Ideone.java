package misc;

import java.util.*;
        import java.lang.*;
        import java.io.*;

class Ideone {

    static int process(int mat[][], int aux[][],int M, int N)
    {
        for (int i = 0; i < N; i++)
            aux[0][i] = mat[0][i];

        for (int i = 1; i < M; i++)
            for (int j = 0; j < N; j++)
                aux[i][j] = mat[i][j] +
                        aux[i-1][j];

        for (int i = 0; i < M; i++)
            for (int j = 1; j < N; j++)
                aux[i][j] += aux[i][j-1];
        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++) {
                System.out.print(aux[i][j]+ " ");
            }
            System.out.println();
        }
        return 0;
    }

    static int sum(int aux[][], int X1,
                   int Y1, int X2, int Y2)
    {
        int res = aux[X2][Y2];

        if (X1 > 0)
            res = res - aux[X1-1][Y2];

        if (Y1 > 0)
            res = res - aux[X2][Y1-1];

        if (X1 > 0 && Y1 > 0)
            res = res + aux[X1-1][Y1-1];

        return res;
    }


    public static void main (String[] args) {
        int mat[][] = new int[100][100];
        Scanner s = new Scanner(System.in);
        int m = s.nextInt();
        int n = s.nextInt();
        Random r = new Random();

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++) {
                mat[i][j] = r.nextInt(5);
                System.out.print(mat[i][j]+ " ");
            }
            System.out.println();
        }

        int aux[][] = new int[m][n];

        process(mat,aux,m,n);

        int q = s.nextInt();
        for(int k=1;k<=q;k++)
        {
            int x1 = s.nextInt();
            int y1 = s.nextInt();
            int x2 = s.nextInt();
            int y2 = s.nextInt();
            System.out.println(sum(aux, x1, y1, x2, y2));

        }

    }
}