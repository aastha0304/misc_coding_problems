package graphs;

import java.util.*;

public class StringCircle {
    private boolean isStringCircle(String[] a){
        //int charCount = 0;

        Graph graph = new Graph(26);
        for(String s: a){
            int start = s.charAt(0)-'a';
            int end = s.charAt(s.length()-1)-'a';
            graph.addEdge(start, end);
        }
        return graph.isEulerianCycle();
    }

    public static void main (String[] args) {

        short t;
        Scanner s = new Scanner(System.in);
        t = Short.parseShort( s.nextLine() );
        StringCircle stringCircle = new StringCircle();
        while( t!=0 ){
            s.nextLine();

            String str = s.nextLine();
            String[] a = str.split(" ");

            System.out.println(stringCircle.isStringCircle(a));
            t--;
        }

    }
}
