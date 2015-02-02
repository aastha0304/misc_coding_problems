import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
 
 
class TestClass {
	private static int lca(ArrayList<Integer> b,int a[]){
		int l=b.size();
		int f,s,p;
		while((l=b.size())>1){
			Collections.sort(b);
			f=b.remove(l-1);
			s=b.remove(l-2);
			p=getParent(f,s,a);
			b.add(p);
		}
		return b.get(0);
	} 
	private static int getParent(int f,int s,int a[]){
		if(f==s)
			return f;
		else if(a[f-1]==s)
			return s;
		else{
			return getParent(a[f-1],a[s-1],a);
		}
	}
    public static void main(String args[] ) throws Exception {
        /*
         * Read input from stdin and provide input before running
  */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
       	line=br.readLine();
       	int a[]=new int[N-1];
        StringTokenizer strToken = new StringTokenizer(line);
        for(int x = 0;x <N-1;x++){
        		a[x] = Integer.parseInt((String)strToken.nextElement());
    	}
       	line=br.readLine();
        int M = Integer.parseInt(line);
		line=br.readLine();
       	ArrayList<Integer> b=new ArrayList<Integer>();
        strToken = new StringTokenizer(line);
        for(int x = 0;x <M;x++){
        		b.add(Integer.parseInt((String)strToken.nextElement()));
    	}
    	System.out.println(lca(b,a));
    }
}
