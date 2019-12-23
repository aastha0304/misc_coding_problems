package misc;

import java.util.*;

public class MaxValueMinCount {
    private int getMaxValueMinCount(int[] a){
        SortedMap<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder());
                //(o1, o2) -> o2.compareTo(o1));
        for(int i:a){
            if(map.containsKey(i)){
                map.put(i, map.get(i)+1);
            }else{
                map.put(i, 1);
            }
        }
        Utils.display(map);
        int min = map.get(map.firstKey());
        int res =  map.firstKey();
        for( int i: map.keySet()){
            if( map.get(i)<min ){
                min = map.get(i);
                res = i;
            }
        }
        return res;
    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String line = s.nextLine();
            int n = Integer.parseInt( line );
            line = s.nextLine();
            String[] elems = line.split(" ");
            int[] a = new int[n];
            for(int i=0;i<n;i++)
                a[i] = Integer.parseInt( elems[i] );
            MaxValueMinCount maxValueMinCount = new MaxValueMinCount();
            System.out.println(maxValueMinCount.getMaxValueMinCount(a));
            t--;
        }
    }
}
