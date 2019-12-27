/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class KightWalk {
  private boolean isDemarking(Cell cell){
    return cell.x==-1 && cell.y==-1;
  }
  private boolean shouldVisit(Cell cell, Set<Cell> visited, int n, int m){
    return !visited.contains(cell) && cell.x>=1 && cell.x<=n && cell.y>=1 && cell.y<=m;
  }
  private int findDistance(int sx, int sy, int dx, int dy, int n, int m){
    Cell source = new Cell(sx, sy);
    Cell dest = new Cell(dx, dy);
    if(source.equals(dest))
      return 0;
    //Cell demark = new Cell(-1, -1);
    LinkedList<Cell> queue = new LinkedList<>();
    queue.add(source);
    //queue.add(demark);
    int distance = 0;
    Set<Cell> visited = new HashSet<>();
    int[] nx = {-2, -1, 1, 2, -2, -1, 1, 2};  
    int[] ny = {-1, -2, -2, -1, 1, 2, 2, 1};  
    int x, y;
    visited.add(source);
    while(!queue.isEmpty()){
      Cell current = queue.remove();
      //System.out.println(current.x+" "+current.y);
      // if(isDemarking(current)){
      //   distance++;
      //   queue.add(demark);
      // }
      // else{
        if(current.equals(dest))
          return current.d;
        visited.add(current);
        for (int i = 0; i < 8; i++)  
        {  
            x = current.x + nx[i];  
            y = current.y + ny[i];  
            Cell next = new Cell(x, y);
            next.d = current.d+1;
            if(shouldVisit(next, visited, n, m)){
              visited.add(next);
              queue.add(next);
            }
        }
      }  
    //}
    return -1;
  }
  public static void main (String[] args) {
    Scanner s = new Scanner(System.in);
    int t = Integer.parseInt( s.nextLine() );
    KightWalk knightsTour = new KightWalk();

    while( t!=0 ){
        short n = s.nextShort();
        short m = s.nextShort();
        int sx, sy, dx, dy;
        sx=s.nextShort();
        sy=s.nextShort();
        dx=s.nextShort();
        dy=s.nextShort();
        System.out.println(knightsTour.findDistance(sx, sy, dx, dy, n, m));
        t--;
    }
  }
}
class Cell{
    int x,y,d;
    public Cell(int r,int c){
        this.x=r;
        this.y=c;
        this.d=0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!Cell.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Cell other = (Cell) obj;
        return this.x == other.x && this.y == other.y;
    }
    @Override
    public int hashCode() {
        int result = (int) (this.x ^ (this.x >>> 32));
        result = 31 * result + (int) (this.y ^ (this.y >>> 32));
        return result;
    }
}
