import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MaximumSubarrayOfK {
    private static void display(Queue<Integer> q, int[] a){
        System.out.println("Queue state");
        Queue<Integer> copy = new LinkedList<>();
        copy.addAll(q);
        while(!copy.isEmpty()){
            System.out.print(a[copy.poll()]+" ");
        }
        System.out.println();
    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String[] elems = s.nextLine().split(" ");
            int n = Integer.parseInt( elems[0] );
            int k = Integer.parseInt( elems[1] );
            elems = s.nextLine().split(" ");
            int[] a = new int[n];
            int i = 0;
            Deque<Integer> queue = new LinkedList<>() ;
            while(i<k){
                a[i] = Short.parseShort( elems[i] );
                while(!queue.isEmpty() && a[queue.getLast()] < a[i])
                    queue.removeLast();
                queue.addLast(i);
                i++;
            }
            //display(queue, a);
            while(i<a.length){
                a[i] = Short.parseShort( elems[i] );
                System.out.print(a[queue.getFirst()]+" ");

                while( !queue.isEmpty() && queue.getFirst() < i+1-k )
                    queue.removeFirst();
                //display(queue, a);
                while (!queue.isEmpty() && a[queue.getLast()] < a[i])
                    queue.removeLast();
                queue.addLast(i);
                //display(queue, a);
                i++;
            }
            System.out.print(a[queue.getFirst()]+" ");

            System.out.println();
            t--;
        }
    }
}
