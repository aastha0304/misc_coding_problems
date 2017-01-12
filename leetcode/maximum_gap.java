public class Solution {
    public int maximumGap(int[] nums) {
        TreeMap<Integer, Integer> tm = new TreeMap();
        if(nums.length<2)
            return 0;
        for ( int n : nums){
            tm.put(n, n);
        }
        Set set = tm.entrySet();
        // Get an iterator
        Iterator i = set.iterator();
        // Display elements
        int prev = 0;
        int res = 0;
        int ct = 0;
        while(i.hasNext()) {
             Map.Entry me = (Map.Entry)i.next();
             
             if( ct != 0)
                res = res>(int)me.getKey()-prev?res:(int)me.getKey()-prev;
             ct++;
             prev = (int)me.getKey();
        }
        return res;
    }
}
