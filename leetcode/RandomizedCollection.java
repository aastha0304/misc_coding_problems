import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomizedCollection {
    List<Integer> coll;
    Set<Integer> already;
    int s;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        coll = new ArrayList<Integer>();
        already = new HashSet<Integer>();
        s=0;
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        coll.add(val);
        s++;

        if(already.contains(val))
        		return false;
        else{
        		already.add(val);
        		return true;
        }
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        boolean r = coll.remove((Object) val);
        already.remove((Object) val);
        if(r){
        		s--;
        		return true;
        }
        return false;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
    		Random r = new Random();
        return (int) coll.get(r.nextInt(s));
    }
}
