package misc;

import java.util.*;

public class ClosestNeighbor {
    static class SortAllocs implements Comparator<List<Integer>> {
        public int compare(List<Integer> a, List<Integer> b) {
//            System.out.println("a is "+a.get(0)*a.get(0) + a.get(1) * a.get(1) );
//            System.out.println("b is "+b.get(0)*b.get(0) + b.get(1) * b.get(1) );
//            System.out.println(Math.sqrt(a.get(0)*a.get(0) + a.get(1) * a.get(1)) -
//                            Math.sqrt(b.get(0)*b.get(0) + b.get(1) * b.get(1)) );
//            System.out.println(Double.compare(Math.sqrt(a.get(0)*a.get(0) + a.get(1) * a.get(1)),
//                    Math.sqrt(b.get(0)*b.get(0) + b.get(1) * b.get(1))));
            return Double.compare(Math.sqrt(a.get(0)*a.get(0) + a.get(1) * a.get(1)),
                    Math.sqrt(b.get(0)*b.get(0) + b.get(1) * b.get(1))) ;
        }
    }
    static List<List<Integer>> nearestVegetarianRestaurant(int totalRestaurants,
                                                    List<List<Integer>> allLocations,
                                                    int numRestaurants)
    {
        Collections.sort(allLocations, new SortAllocs()) ;
        Iterator<List<Integer>> iter = allLocations.iterator();
//        while(iter.hasNext()){
//            List<Integer> item = iter.next();
//            System.out.println(item.get(0)+" "+item.get(1));
//        }
        return allLocations.subList(0, numRestaurants);
    }

    static List<Integer> createEntry(int a, int b){
        List<Integer> entry = new ArrayList<>();
        entry.add(a);
        entry.add(b);
        return entry;
    }
    public static void main(String[] args){
        int totalRestaurants = 3;
        List<List<Integer>> allLocations = new ArrayList<>();
        allLocations.add(createEntry(1,-3));
        allLocations.add(createEntry(1,2));
        allLocations.add(createEntry(3,4));
        int numRestaurants = 1;
        List<List<Integer>> res = nearestVegetarianRestaurant(totalRestaurants, allLocations, numRestaurants);
        Iterator<List<Integer>> iter = res.iterator();
        while(iter.hasNext()){
            List<Integer> item = iter.next();
            System.out.println(item.get(0)+" "+item.get(1));
        }
    }
}

