package misc;

public class Coordinate{
    int r,c;
    public Coordinate(int r,int c){
        this.r=r;
        this.c=c;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!Coordinate.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        return this.r == other.r && this.c == other.c;
    }
    @Override
    public int hashCode() {
        int result = (int) (this.r ^ (this.r >>> 32));
        result = 31 * result + (int) (this.c ^ (this.c >>> 32));
        return result;
    }
    boolean isAbove(Coordinate c2){ return this.r==c2.r-1 && this.c==c2.c; }
    boolean isBelow(Coordinate c2){
        return this.r==c2.r+1 && this.c==c2.c;
    }
    boolean isLeft(Coordinate c2){
        return this.r==c2.r && this.c==c2.c-1;
    }
    boolean isRight(Coordinate c2){
        return this.r==c2.r && this.c==c2.c+1;
    }
    boolean inBounds(int m, int n){
        return this.r<m && this.r>=0 && this.c<n && this.c>=0;
    }
}
