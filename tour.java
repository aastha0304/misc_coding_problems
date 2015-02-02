import java.io.BufferedReader;
 
/* IMPORTANT: class must not be public. */
 
/*
 * uncomment this if you want to read input.*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.StringTokenizer;
 
 
 class TestClass {
    public static void main(String args[] ) throws Exception {
        /*
         * Read input from stdin and provide input before running
*/
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        Hashtable<String,Integer> ht=new Hashtable<String,Integer>();
        for (int i = 0; i < N; i++) {
        	line = br.readLine();
        	ht.put(line, i);
        }
        StringTokenizer strToken;
        long cost[][]=new long[N][N];
        for(int i=0;i<N;i++){
        	cost[i][i]=0;
        	line = br.readLine();
        	strToken = new StringTokenizer(line);
        	for(int j=0;j<N;j++)
        		cost[i][j]=Integer.parseInt((String)strToken.nextElement());
        		
        }
        line = br.readLine();
        int V = Integer.parseInt(line);
        int s=0,d;
        long c=0;
        for(int i=0;i<V;i++){
        	line = br.readLine();
        	d=ht.get(line);
        	c+=cost[s][d];
        	s=d;
        }
        System.out.println(c);
    }
}
