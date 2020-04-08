import java.util.Scanner;
class Coordinate{
    int x;
    int y;
    Coordinate(int r, int c){
        x = r;
        y = c;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.x;
        hash = 71 * hash + this.y;
        return hash;
    }
    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;

        if (!(o instanceof Coordinate))
            return false;

        Coordinate otherPoint = (Coordinate) o;
        return otherPoint.x == x && otherPoint.y == y;
    }
}
public class MinmPointsGrid {
    private int useGFGFunc(int [][] points, int R, int C){
        MaxRectangle.display(points);

        int dp[][] = new int[R][C];
        int m = R, n = C;

        // Base case
        dp[m-1][n-1] = points[m-1][n-1] > 0? 1:
                Math.abs(points[m-1][n-1]) + 1;

        // Fill last row and last column as base to fill
        // entire table
        for (int i = m-2; i >= 0; i--)
            dp[i][n-1] = Math.max(dp[i+1][n-1] - points[i][n-1], 1);
        for (int j = n-2; j >= 0; j--)
            dp[m-1][j] = Math.max(dp[m-1][j+1] - points[m-1][j], 1);

        // fill the table in bottom-up fashion
        for (int i=m-2; i>=0; i--)
        {
            for (int j=n-2; j>=0; j--)
            {
                int min_points_on_exit = Math.min(dp[i+1][j], dp[i][j+1]);
                dp[i][j] = Math.max(min_points_on_exit - points[i][j], 1);
            }
        }
        MaxRectangle.display(dp);

        return dp[0][0];
    }
    private int getMinCost(int[][] a){
        MaxRectangle.display(a);
        Coordinate coordinate = new Coordinate(0, 0);
        Coordinate dest = new Coordinate(a.length-1, a[0].length-1);
        Coordinate current = coordinate, prev = null;
        int max , reqd = Integer.MIN_VALUE, sum = a[0][0];
        if(a[0][0]<0)
            reqd = (a[0][0]*-1)+1;
        else
            reqd = 1;
        while(!current.equals(dest)){
            System.out.println("current value "+current.x+" "+current.y+" "+reqd+" "+sum);
            max = Integer.MIN_VALUE;
            prev = current;
            if(prev.x<a.length-1){
                System.out.println("next coudl be "+(prev.x+1)+" "+prev.y);
                max = Math.max(max, a[prev.x+1][prev.y]);
                if(max ==  a[prev.x+1][prev.y]){
                    current = new Coordinate(prev.x+1, prev.y);
                }
            }
            if(prev.y<a[0].length-1){
                System.out.println("next coudl be "+(prev.x)+" "+(prev.y+1));
                max = Math.max(max, a[prev.x][prev.y+1]);
                if(max ==  a[prev.x][prev.y+1]){
                    current = new Coordinate(prev.x, prev.y+1);
                }
            }
            sum += a[current.x][current.y];
            if(sum<0){
                reqd = Math.max(reqd, (sum*-1)+1);
            }
        }
//        for(int i=0;i<a.length;i++){
//            for(int j=0;j<a[0].length;j++){
//                max = Integer.MIN_VALUE;
//                if(i>0)
//                    max = Math.max(max, a[i-1][j]);
//                if(j>0)
//                    max = Math.max(max, a[i][j-1]);
//                if(max != Integer.MIN_VALUE)
//                    a[i][j] = max + a[i][j];
//            }
//        }
//        MaxRectangle.display(a);
//        return a[a.length-1][a[0].length-1]>0?0:1+(a[a.length-1][a[0].length-1]*-1);
        return reqd;
    }
    public static void main (String[] args) {
        MinmPointsGrid minmPointsGrid = new MinmPointsGrid();
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
            System.out.println(minmPointsGrid.useGFGFunc(a, r, c)); //.getMinCost(a));
            t--;
        }
    }
}