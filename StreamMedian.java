package misc;

import java.util.*;

public class StreamMedian {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        Queue<Integer> maxHeapLeft = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -(o1.compareTo(o2));
            }
        });
        Queue<Integer> minHeapRight = new PriorityQueue<>();

        StreamMedian streamMedian = new StreamMedian();

        while( t!=0 ){
            int num = Integer.parseInt(s.nextLine());

            if(maxHeapLeft.size()==0)
                maxHeapLeft.add(num);
            else if(maxHeapLeft.element()>num){
                maxHeapLeft.add(num);
            }else{
                minHeapRight.add(num);
            }
            if( maxHeapLeft.size() > minHeapRight.size() + 1){
                int leftTop = maxHeapLeft.remove();
                minHeapRight.add(leftTop);
            }else if( minHeapRight.size() > maxHeapLeft.size() + 1){
                int rightTop = minHeapRight.remove();
                maxHeapLeft.add(rightTop);
            }

            int currentmedian = streamMedian.findMedian(maxHeapLeft, minHeapRight);
            System.out.println(currentmedian);
            t--;
        }
    }

    private int findMedian(Queue<Integer> maxHeapLeft, Queue<Integer> minHeapRight) {
        if(maxHeapLeft.size()>minHeapRight.size()){
            return maxHeapLeft.element();
        }else if(minHeapRight.size()>maxHeapLeft.size()){
            return minHeapRight.element();
        }
        else{
            return (maxHeapLeft.element()+minHeapRight.element())/2;
        }
    }
}
