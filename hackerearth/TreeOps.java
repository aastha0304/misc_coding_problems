package hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class TreeOps {
	void fillHgt(int a[], int i, int hgt[])
	{
	    if (hgt[i]!=0)
	        return;
	    if (a[i] == -1)
	    {
	        hgt[i] = 1;
	        return;
	    }
	    if (hgt[a[i]] == 0)
	        fillHgt(a, a[i], hgt);
	    hgt[i] = hgt[a[i]]  + 1;
	}
	int maxDepth(int[] parents, int N) { 
		if(N<=1)
			return N;
		int hgt[] = new int[N];
	    for (int i = 0; i < N; i++)
	        hgt[i] = 0;
	    for (int i = 0; i < N; i++)
	        fillHgt(parents, i, hgt);
	    int mx = hgt[0];
	    for (int i=1; i<N; i++)
	        mx=max(mx, hgt[i]);
	    return mx;
	}
	private int max(int a, int b){
		return a>b?a:b;
	}
	int maxChildren(int[] parents,int N) {
		if(N<=1)
			return 0;
		int[] pc = new int[N];
		int mx = 0;
		for(int i =0;i<N;i++)
		{
			if(parents[i]!=-1){
				pc[parents[i]]++;
				mx = max(pc[parents[i]], mx);
			}
		}
		return mx;
	}
	private int getDepth(int[] a, int n, int node){
		if(a[node]==-1)
			return 1;
		else{
			return 1+getDepth(a, n, a[node]);
		}
	}
	private int caUtil(int[] a, int n, int node1, int lvl1, int node2, int lvl2){
		if(lvl1==1)
			return node1;
		else{
			while(a[node2]!=-1 && lvl2>lvl1){
				node2=a[node2];
				lvl2--;
			}
			while(node1!=node2 && a[node1]!=-1 && a[node2]!=-1){
				node1=a[node1];
				node2=a[node2];
			}
			return node1;
		}
	}
	int commonAncestor(int[] parents, int N, int node1, int node2) {
		if(N<=1)
			return -1;
		int n1 = getDepth(parents, N, node1);
		int n2 = getDepth(parents, N, node2);
		if(n1<n2){
			return caUtil(parents, N, node1, n1, node2, n2);
		}else{
			return caUtil(parents, N, node2, n2, node1, n1);
		}
	}
	public static void main(String[] args) throws IOException{
		int n;
		int[] a;
		int[] nodes = new int[2];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        n = Integer.parseInt(line);
       	line=br.readLine();
       	a =new int[n];
        StringTokenizer strToken = new StringTokenizer(line);
        for(int x = 0;x <n;x++){
        		a[x] = Integer.parseInt((String)strToken.nextElement());
    	}
       	line=br.readLine();
       	strToken = new StringTokenizer(line);
        for(int x = 0;x <2;x++){
        		nodes[x] = Integer.parseInt((String)strToken.nextElement());
    	}
        TreeOps ob = new TreeOps();
        System.out.println(ob.maxDepth(a, n));
        System.out.println(ob.maxChildren(a, n));
        System.out.println(ob.commonAncestor(a, n, nodes[0], nodes[1]));
	}
}
