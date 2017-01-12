/* IMPORTANT: class must not be public. */
 
/*
 * uncomment this if you want to read input.*/
package hackerearth;
import java.io.BufferedReader;
import java.io.InputStreamReader;
 
import java.util.Hashtable;
 
class TestClassgc {
	public static Hashtable<Long,Long> h=new Hashtable<Long,Long>();
	
    public static void main(String args[] ) throws Exception {
        /*
         * Read input from stdin and provide input before running*/
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
                System.out.println(get(Long.parseLong(line)));
                //long n=Long.parseLong(line);
                
            }
    }
    private static long get(long n){
    	if(n==0 || n==1)
    	{
    		h.put(n,n);
    	}
    	else{
    		if(!h.containsKey(n)){
    			h.put(n,max(n,get(n/2)+get(n/3)+get(n/4)));
    		}}
    	return h.get(n);
    }
    private static long max(long a,long b){
    	return a>b?a:b;
    }
    
}
public class Bytelandiangc{
	
}