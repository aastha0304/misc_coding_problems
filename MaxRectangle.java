import java.util.Scanner;
import java.util.Stack;

public class MaxRectangle {
    public static void display(int[][] a){
        for(int[] i:a)
            display(i);
    }
    public static void display(int[] a){
        for(int i:a){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    private int getHistRec(int R,int C,int row[])
    {
        Stack<Integer> result = new Stack<>();
        int top_val;
        int max_area = 0;
        int area = 0;
        int i = 0;
        while (i < C)
        {
            if (result.empty() || row[result.peek()] <= row[i])
                result.push(i++);

            else
            {
                top_val = row[result.peek()];
                result.pop();
                area = top_val * i;
                if (!result.empty())
                    area = top_val * (result.empty()?i:i - result.peek() - 1 );
                max_area = Math.max(area, max_area);
            }
        }
        while (!result.empty())
        {
            top_val = row[result.peek()];
            result.pop();
            area = top_val * (result.empty()?i:i - result.peek() - 1 );
            max_area = Math.max(area, max_area);
        }
        return max_area;
    }
    private int maxArea(int[][] a, int n, int m){
        //Note that this works by treating every row as histogram base and tries to find maximum rectangle in histogram
        //Max rectange in Histogram is not the same as water-fill problem
        display(a);
        System.out.println("*************");
        int res = Integer.MIN_VALUE;
        int i=0,j;
        res = Math.max(res, getHistRec(a.length, a[i].length, a[i]));
        display(a[i]);
        for(i=1;i<a.length;i++){
            for(j=0;j<a[i].length;j++){
                if(a[i][j]==1)
                    a[i][j] += a[i-1][j];
            }
            display(a[i]);
            res = Math.max(res, getHistRec(a.length, a[i].length, a[i]));
        }
        return res;
    }
    private boolean isSafe(int row, int col, int rowmax, int colmax){
        return row>=0 && row<rowmax && col >=0 && col < colmax;
    }
    private int getMaxSqLen(int[][] a){
        int res = 0, d,l,u;
        for(int i=0;i<a.length;i++){
            //display(a[i]);
            for(int j=0;j<a[i].length;j++){
                if(a[i][j]==1){
                    d = 0;
                    l = 0;
                    u = 0;
                    if(isSafe(i-1, j-1, a.length, a[i].length)){
                        d = a[i-1][j-1];
                    }
                    if(isSafe(i-1, j,a.length, a[i].length)){
                        u = a[i-1][j];
                    }
                    if(isSafe(i,j-1,a.length, a[i].length)){
                        l = a[i][j-1];
                    }
                    a[i][j] = Math.min(d, Math.min(u,l)) + 1;
                    res = Math.max(res, a[i][j]);
                }
            }
        }
        return res;
    }
    public static void main (String[] args) {
        MaxRectangle maxRectangle = new MaxRectangle();
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String[] elems = s.nextLine().split(" ");
            int n = Integer.parseInt( elems[0] );
            int m = Integer.parseInt( elems[1] );
            elems = s.nextLine().split(" ");
            int[][] a = new int[n][m];
            for(int i=0;i<n;i++)
                for(int j=0;j<m;j++)
                    a[i][j] = Short.parseShort( elems[(i)*m+(j)] );
            //System.out.println(maxRectangle.maxArea(a, n, m));
            System.out.println(maxRectangle.getMaxSqLen(a));
            t--;
        }
    }
}
