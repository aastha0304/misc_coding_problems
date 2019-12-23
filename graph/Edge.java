package graphs;
import misc.Coordinate;
public class Edge{
    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    int src;
    int dest;
    int cost;
    public Edge(int s, int d, int c){
        src = s;
        dest = d;
        cost = c;
    }
    @Override
    public boolean equals(Object e){
        Coordinate thisCoord = new Coordinate(this.src, this.dest);
        Edge thatEdge = (Edge)e;
        Coordinate that = new Coordinate(thatEdge.src, thatEdge.dest);
        return thisCoord.equals(that);
    }
    @Override
    public int hashCode(){
        Coordinate thisCoord = new Coordinate(this.src, this.dest);
        return thisCoord.hashCode();
    }
}
