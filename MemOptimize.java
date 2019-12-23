package misc;

import java.util.*;

public class MemOptimize {

    static class SortAllocs implements Comparator<List<Integer>> {
        public int compare(List<Integer> a, List<Integer> b) {
//            System.out.println("a is "+a.get(0)*a.get(0) + a.get(1) * a.get(1) );
//            System.out.println("b is "+b.get(0)*b.get(0) + b.get(1) * b.get(1) );
//            System.out.println(Math.sqrt(a.get(0)*a.get(0) + a.get(1) * a.get(1)) -
//                            Math.sqrt(b.get(0)*b.get(0) + b.get(1) * b.get(1)) );
//            System.out.println(Double.compare(Math.sqrt(a.get(0)*a.get(0) + a.get(1) * a.get(1)),
//                    Math.sqrt(b.get(0)*b.get(0) + b.get(1) * b.get(1))));
            return Integer.compare(b.get(1), a.get(1));
        }
    }

    static int ceilSearch(int low, int high, int x, List<List<Integer>> arr) {
        int mid;
        if (x >= arr.get(low).get(1))
            return low;
        if (x < arr.get(high).get(1))
            return -1;

        mid = (low + high) / 2; /* low + (high - low)/2 */

        if (arr.get(mid).get(1) == x)
            return mid;

        else if (arr.get(mid).get(1) > x) {
            if (mid + 1 <= high && x >= arr.get(mid + 1).get(1))

                return mid + 1;
            else
                return ceilSearch(mid + 1, high, x, arr);
        } else {
            if (mid - 1 >= low && x < arr.get(mid - 1).get(1))
                return mid;
            else
                return ceilSearch(low, mid - 1, x, arr);
        }
    }

    static List<List<Integer>> optimalUtilization(
            int deviceCapacity,
            List<List<Integer>> foregroundAppList,
            List<List<Integer>> backgroundAppList)
    {

        List<List<Integer>> res = new ArrayList<>();
        Collections.sort(foregroundAppList, new SortAllocs()) ;
        Collections.sort(backgroundAppList, new SortAllocs()) ;
        Iterator<List<Integer>> iter = foregroundAppList.iterator();
        Iterator<List<Integer>> iter2 = backgroundAppList.iterator();
        while(iter2.hasNext()){
            List<Integer> item = iter2.next();
            System.out.println(item.get(0)+" "+item.get(1));

        }
        int bcgLow = 0, bcgHigh = backgroundAppList.size()-1;

        while(iter.hasNext()){
            List<Integer> item = iter.next();
            System.out.println(item.get(0)+" "+item.get(1));
            int usedMem = item.get(1);
            if(usedMem < deviceCapacity){
                int index = ceilSearch(bcgLow, bcgHigh, deviceCapacity-usedMem, backgroundAppList);
                System.out.println("index "+index);
//                int index = Collections.binarySearch(backgroundAppList, createEntry(-1,deviceCapacity-usedMem),
//                        c);
                if(index >=0){
                    res.add(createEntry(item.get(0), backgroundAppList.get(index).get(0)));
                    deviceCapacity = item.get(1)+backgroundAppList.get(index).get(1);
                }

            }
            else{
                continue;
            }
        }
        return res;
    }
    static List<Integer> createEntry(int a, int b){
        List<Integer> entry = new ArrayList<>();
        entry.add(a);
        entry.add(b);
        return entry;
    }
    public static void main(String[] args){
        int deviceCapacity  = 20;

        List<List<Integer>> foregroundAppList = new ArrayList<>();
        foregroundAppList.add(createEntry(1,8));
        foregroundAppList.add(createEntry(2,7));
        foregroundAppList.add(createEntry(3,14));

        List<List<Integer>> backgroundAppList = new ArrayList<>();
        backgroundAppList.add(createEntry(1,5));
        backgroundAppList.add(createEntry(2,10));
        backgroundAppList.add(createEntry(3,14));

        List<List<Integer>> res = optimalUtilization(deviceCapacity, foregroundAppList, backgroundAppList);
        Iterator<List<Integer>> iter = res.iterator();
        while(iter.hasNext()){
            List<Integer> item = iter.next();
            System.out.println(item.get(0)+" "+item.get(1));
        }
    }
}
