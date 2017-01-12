package hackerearth;

/* IMPORTANT: class must not be public. */

/*
 * uncomment this if you want to read input.*/
import java.io.BufferedReader;
import java.io.InputStreamReader;

 class TestClasssg {
	static int findP(int[] parents, int u){
		if(u!=parents[u])
			parents[u] = findP(parents, parents[u]);
		return parents[u];
	}
	static boolean isnotCycle(int[] parents, int v, int u){
		v = findP(parents, v);
		u = findP(parents, u);
		if(v==u)
			return false;
		parents[v] = u;
		return true;
	}
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine(); // to read multiple integers line
		String[] strs = line.trim().split("\\s+");
        int n = Integer.parseInt(strs[0]);
        int m = Integer.parseInt(strs[1]);
        int[] parents = new int[n+1];
        boolean[] a = new boolean[m+1];
        int edges[][] = new int[m+1][2];
        for(int i = 1;i<=n;i++){
        	parents[i] = i;
        }
        int k = 0;
        for (int i = 1; i <= m; i++) {
            line = br.readLine();
            strs = line.trim().split("\\s+");
            edges[i][0] = Integer.parseInt(strs[0]);
            edges[i][1] = Integer.parseInt(strs[1]);
        }
        for(int i=m;i>0;i--){
        	a[i] = isnotCycle(parents, edges[i][0], edges[i][1]);
        	if(a[i])
        		k++;
        }
        System.out.println(m-k);
        for(int i=1;i<=m;i++){
        	if(!a[i]){
        		System.out.println(i);
        	}
        }
    }
}