package misc;

import java.util.Scanner;

import graphs.*;

public class FunWithGraph {

    public static void main (String[] args) {

        short t;
        Scanner s = new Scanner(System.in);
        t = Short.parseShort( s.nextLine() );
        while( t!=0 ){
            short n = s.nextShort();
            Graph graph = new WeightedDirectedGraph(n);

            short[][] a=new short[n][n];
            for(int i=0;i<n;i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = s.nextShort();
                    if(i!=j)
                        graph.addEdge(i, j, a[i][j]);
                }
            }
            graph.print();
            System.out.println(((WeightedDirectedGraph) graph).solveTSP(0));
            t--;

        }

    }
}
