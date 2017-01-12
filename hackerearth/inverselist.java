/* IMPORTANT: class must not be public. */

 
/*
 * uncomment this if you want to read input.*/
package hackerearth;
import java.io.BufferedReader;
import java.io.InputStreamReader;
 
import java.util.StringTokenizer;
 
class TestClassil {
    public static void main(String args[] ) throws Exception {
        /*
         * Read input from stdin and provide input before running
*/
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        for (int i = 0; i < N; i++) {
        	line = br.readLine();
        	int n=Integer.parseInt(line);
        	int a[]=new int[n];
        	line=br.readLine();
        	StringTokenizer strToken = new StringTokenizer(line);
        	for(int x = 0;x <n;x++){
        		a[x] = Integer.parseInt((String)strToken.nextElement());
    		}
    		int j=0;
    		for(;j<n;j++)
    		{
    			if(a[a[j]-1]!=(j+1))
    			{
    				System.out.println("not inverse");
    				break;
    			}
    		}
    		if(j==n)
    		System.out.println("inverse");
        }
      // 	System.out.println("Hello World!");
    }
}
