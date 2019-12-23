package misc;

import java.util.*;
public class ListFrequencySort {
    private void sort(LinkedList<LinkedList<Object>> list1){
        LinkedList<Object> res = new LinkedList<>();
        Map<Object, Integer> counts = new HashMap<>();
        for(LinkedList<Object> list: list1){
            for(Object c: list){
                if(counts.containsKey(c)){
                    counts.put(c, counts.get(c)+1);
                }else{
                    counts.put(c, 1);
                    res.add(c);
                }
            }
        }
        //res.sort( (Character c1, Character c2) -> counts.get(c1).compareTo(counts.get(c2)) );
        res.sort(Comparator.comparing(counts::get));
        //Collections.sort(res,
//                new Comparator<Character>() {
//            @Override
//            public int compare(Character o1, Character o2) {
//                return counts.get(o1).compareTo(counts.get(o2));
//            }
//        });

        for(Object c: res){
            System.out.print(c);
        }
        System.out.println();
    }
    public static void main(String[] args) {

        LinkedList<LinkedList<Object>> list1 = new LinkedList<>();

        LinkedList<Object> elem1 = new LinkedList<>();
        elem1.add('a');
        elem1.add('b');
        elem1.add(1);


        LinkedList<Object> elem2 = new LinkedList<>();
        elem2.add('a');
        elem2.add('b');

        LinkedList<Object> elem3 = new LinkedList<>();
        elem3.add('a');
        elem3.add('1');

        list1.add(elem1);
        list1.add(elem2);
        list1.add(elem3);


        ListFrequencySort solution = new ListFrequencySort();
        //List<Integer> res = solution.sort(list1);
        solution.sort(list1);
        System.out.println();
    }
}
