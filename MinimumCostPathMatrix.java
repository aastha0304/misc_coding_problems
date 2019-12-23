package misc;

import java.util.*;
import graphs.Edge;
public class MinimumCostPathMatrix {
    private int MCPSlow(short[][] a){
        boolean[][] visited = new boolean[a.length][a[0].length];
        return MCPUtil(a, 0, 0, a.length-1, a.length-1, 0, visited, Integer.MAX_VALUE);
    }
    private boolean canVisit(int x, int y, boolean[][] visited){
        return inBounds(x,y, visited.length) && !visited[x][y];
    }
    private int MCPUtil(short[][] a, int startX, int startY, int endX, int endY, int cost,
                        boolean[][] visited, int ans ){
        if(startX == endX && startY == endY){
            ans = Math.min(ans, cost+a[startX][startY]);
        }else{
            visited[startX][startY] = true;
            int fromUp = Integer.MAX_VALUE, fromDown = Integer.MAX_VALUE,
                    fromLeft = Integer.MAX_VALUE, fromRight = Integer.MAX_VALUE;
            cost += a[startX][startY];
            if(canVisit(startX-1,startY, visited)){
                fromUp = MCPUtil(a, startX-1, startY, a.length-1, a.length-1, cost, visited, Integer.MAX_VALUE);
            }
            if(canVisit(startX+1,startY, visited)){
                fromDown = MCPUtil(a, startX+1, startY, a.length-1, a.length-1, cost, visited, Integer.MAX_VALUE);
            }
            if(canVisit(startX,startY-1, visited)){
                fromLeft = MCPUtil(a, startX, startY-1, a.length-1, a.length-1, cost, visited, Integer.MAX_VALUE);
            }
            if(canVisit(startX,startY+1, visited)){
                fromRight = MCPUtil(a, startX, startY+1, a.length-1, a.length-1, cost, visited, Integer.MAX_VALUE);
            }
            ans = Math.min(fromUp, Math.min(fromDown, Math.min(fromLeft, fromRight)));
            visited[startX][startY] = false;
        }
        return ans;
    }

    private Coordinate getMinVertex(int startX, int startY, short[][] a, boolean[][] visited){
        int fromUp = Integer.MAX_VALUE, fromDown = Integer.MAX_VALUE,
                fromLeft = Integer.MAX_VALUE, fromRight = Integer.MAX_VALUE;
        Coordinate res=null;
        int min=Integer.MAX_VALUE;
        if(canVisit(startX-1,startY, visited)){
            fromUp = a[startX-1][startY];
            if(fromUp<min) {
                min = fromUp;
                res = new Coordinate(startX-1, startY);
            }
        }
        if(canVisit(startX+1,startY, visited)){
            fromDown = a[startX+1][startY];
            if(fromDown<min){
                min = fromDown;
                res = new Coordinate(startX+1, startY);
            }else if(startX+1 == visited.length-1 && startY == visited[0].length-1){
                return new Coordinate(startX+1, startY);
            }
        }
        if(canVisit(startX,startY-1, visited)){
            fromLeft = a[startX][startY-1];
            if(fromLeft<min){
                min = fromLeft;
                res = new Coordinate(startX, startY-1);
            }
        }
        if(canVisit(startX,startY+1, visited)){
            fromRight = a[startX][startY+1];
            if(fromRight<min){
                min = fromRight;
                res = new Coordinate(startX, startY+1);
            }else if(startX == visited.length-1 && startY+1 == visited[0].length-1){
                return new Coordinate(startX, startY+1);
            }
        }
        return res;
    }
    private boolean inBounds(int x, int y, int size){
        return x>=0 && x<size && y>=0 && y<size;
    }
    //tried greedy, greedy doesnt work :(
    private int MCPGreedy(short[][] a){
        boolean[][] visited = new boolean[a.length][a[0].length];

        List<Coordinate> q = new LinkedList<>();
        Coordinate end = new Coordinate(a.length-1, a[0].length-1);
        int cost=0;
        q.add(new Coordinate(0,0));
        while(!q.isEmpty()){
            Coordinate current = ((LinkedList<Coordinate>) q).removeFirst();
            cost += a[current.r][current.c];
            visited[current.r][current.c] = true;
            Coordinate next = getMinVertex(current.r, current.c, a, visited);
            System.out.println("Current: "+current.r+" "+current.c+" "+"Next: "+next.r+" "+next.c);
            if(next != null){
                if(next.equals(end)) {
                    cost += a[a.length - 1][a[0].length - 1];
                    break;
                }
                else{
                    q.add(next);
                }
            }
        }
        return cost;
    }
    private int MCP(short[][] a){
        int[][] distance = new int[a.length][a[0].length];
        for(int[] s: distance){
            Arrays.fill(s, Integer.MAX_VALUE);
        }
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        List<Edge> explored = new LinkedList<>();
        explored.add(new Edge(0,0,0));
        distance[0][0] = a[0][0];
        while(!explored.isEmpty()){
            Edge current = ((LinkedList<Edge>) explored).removeFirst();
            explored.remove(current);
            for(int i=0;i<4;i++){
                int x = current.getSrc() + dx[i];
                int y = current.getDest() + dy[i];
                if(inBounds(x, y, a.length)){
                    if(distance[x][y]>distance[current.getSrc()][current.getDest()]+a[x][y]){
                        if(distance[x][y]!=Integer.MAX_VALUE){
                            Edge edge = new Edge(x, y, distance[x][y]);
                            explored.remove(edge);
                        }
                        distance[x][y] = distance[current.getSrc()][current.getDest()]+a[x][y];
                        explored.add(new Edge(x, y, distance[x][y]));
                    }
                }
            }
        }
        Utils.display(distance);
        return distance[a.length-1][a[0].length-1];
    }
    public static void main (String[] args) {

        short t;
        Scanner s = new Scanner(System.in);
        t = Short.parseShort( s.nextLine() );
        MinimumCostPathMatrix minimumCostPathMatrix = new MinimumCostPathMatrix();
        while( t!=0 ){
            short n = s.nextShort();

            short[][] a=new short[n][n];
            for(int i=0;i<n;i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = s.nextShort();
                }
            }
            System.out.println(minimumCostPathMatrix.MCP(a));
            t--;
        }

    }
}