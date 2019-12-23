package misc;

import java.util.Scanner;

public class FunWithIslands {

    private boolean shouldVisit(char[][] a, int i, int j, boolean[][] visited){
        return i>=0 && i<a.length && j>=0 && j<a[0].length && a[i][j]=='X' && !visited[i][j];
    }

    private int dfs(char[][] a, int i, int j, boolean[][] visited){
        int islandLen = 1;
        visited[i][j]=true;
        if(shouldVisit(a,i-1,j,visited))
            islandLen += dfs(a,i-1,j,visited);
        if(shouldVisit(a,i,j-1,visited))
            islandLen += dfs(a,i,j-1,visited);
        if(shouldVisit(a,i+1,j,visited))
            islandLen += dfs(a,i+1,j,visited);
        if(shouldVisit(a,i,j+1,visited))
            islandLen+= dfs(a,i,j+1,visited);
        return islandLen;
    }

    private int countIslands(char[][] a){
        int count=0;
        boolean[][] visited=new boolean[a.length][a[0].length];
        for(short i=0;i<a.length;i++){
            for(short j=0;j<a[i].length;j++){
                if(shouldVisit(a,i,j,visited)){
                    count++;
                    dfs(a, i, j, visited);
                }
            }
        }
        return count;
    }

    private int maxIsland(char[][] a){
        int max=0, current;
        boolean[][] visited=new boolean[a.length][a[0].length];
        for(short i=0;i<a.length;i++){
            for(short j=0;j<a[i].length;j++){
                if(shouldVisit(a,i,j,visited)){
                    max =Math.max(max, dfs(a, i, j, visited));
                }
            }
        }
        return max;
    }

    private boolean shouldVisit(short[][] a, int x, int y, short o){
        return x>=0 && x<a.length && y>=0 && y<a[x].length && a[x][y]==o;
    }

    private void floodFillDFS(short[][] a, int x, int y, short k, short o) {
        a[x][y] = k;
        if(shouldVisit(a,x-1,y,o))
            floodFillDFS(a,x-1,y,k,o);
        if(shouldVisit(a,x+1,y,o))
            floodFillDFS(a,x+1,y,k,o);
        if(shouldVisit(a,x,y-1,o))
            floodFillDFS(a,x,y-1,k,o);
        if(shouldVisit(a,x,y+1,o))
            floodFillDFS(a,x,y+1,k,o);
    }

    private void floodFill(short[][] a, short x, short y, short k, short o){
        a[x][y] = k;
        floodFillDFS(a,x,y,k,o);
    }

    private void display(short[][] a){
        for(short i=0;i<a.length;i++)
            for(short j=0;j<a[i].length;j++)
                System.out.print(a[i][j]+ " ");

    }
    public static void main (String[] args) {
        short t;
        Scanner s = new Scanner(System.in);
        t = Short.parseShort( s.nextLine() );
        String[] line;
        FunWithIslands funWithIslands = new FunWithIslands();

        while( t!=0 ){
            line = s.nextLine().split(" ");
            short n = Short.parseShort(line[0]);
            short m = Short.parseShort(line[1]);
//            char[][] a = new char[n][m];
//            String[] temp = s.nextLine().split(" ");
//            int i=0;
//            for(String str: temp){
//                a[i] = str.toCharArray();
//                i++;
//            }
//            System.out.println(funWithIslands.countIslands(a));
//            System.out.println(funWithIslands.maxIsland(a));
            String[] temp = s.nextLine().split(" ");
            short[][] a = new short[n][m];
            for(short i=0;i<n;i++)
                for(short j=0;j<m;j++)
                    a[i][j]=Short.parseShort(temp[i*m+j]);
            temp = s.nextLine().split(" ");
            short x=Short.parseShort(temp[0]);
            short y=Short.parseShort(temp[1]);
            short k=Short.parseShort(temp[2]);
            short originalCol = a[x][y];
            funWithIslands.floodFill(a,x,y,k,originalCol);
            funWithIslands.display(a);
            System.out.println();
            t--;
        }
    }

}
