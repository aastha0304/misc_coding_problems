package hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Graph {
	int min = 1000;
	LinkedList<Integer> ls;
	int[][] create_mat(int n){
		int N = n*n;
		int[][] a = new int[n][n];
		int c = 1;
		Random ran = new Random();
		int c1 = 1+ran.nextInt(N-1);
		int c2 = 1+ran.nextInt(N-1);
		int c3 = 1+ran.nextInt(N-1);
		int c4 = 1+ran.nextInt(N-1);
		int b1 = 1+ran.nextInt(N-1);
		int b2 = 1+ran.nextInt(N-1);
		int b3 = 1+ran.nextInt(N-1);
		for(int i =0;i<n;i++){
			for(int j =0;j<n;j++){
				if(c==c1 || c==c2 || c==c3 || c==c4){
					a[i][j] = 2;
				}else if(c==b1 || c==b2 || c==b3)
					a[i][j] = -1;
				c++;
			}
		}
		a[0][0] = 0;
		return a;
	}
	void display(int[][] a, int m, int n){
		for(int i = 0;i<m;i++){
			for(int j = 0;j<n;j++){
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}
	boolean isBlocked(int[][] mat, int i, int n){
		int r = i/n;
		int c = i%n;
		if(mat[r][c]==-1)
			return true;
		return false;
	}
	int[][] create_graph(int[][] mat, int n){
		int N = n*n;
		int[][] a = new int[N][N];
		for(int i =0;i<N;i++)
			Arrays.fill(a[i], min);
		for(int i=0;i<N;i++){
			int r = i/n;
			int c = i%n;
			if(!isBlocked(mat, i, n)){
				if(i-n>=0 && !isBlocked(mat, i-n, n))
					a[i][i-n]=1;
				if(i+n<N && !isBlocked(mat, i+n, n))
					a[i][i+n]=1;
				if(c!=0 && c!=(n-1)){
					if(i-1>=0 && !isBlocked(mat, i-1, n))
						a[i][i-1]=1;
					if(i+1<N && !isBlocked(mat, i+1, n))
						a[i][i+1]=1;
				}else if(c==0){
					if(i+1<N && !isBlocked(mat, i+1, n))
						a[i][i+1]=1;
				}else{
					if(i-1>=0 && !isBlocked(mat, i-1, n))
						a[i][i-1]=1;
				}
			}
			
		}
		return a;
	}
	LinkedList<Integer> get_cheese(int[][] mat, int n){
		LinkedList<Integer> a = new LinkedList<Integer>();
		for(int i =0;i<n;i++){
			for(int j=0;j<n;j++)
				if(mat[i][j]==2)
					a.add(new Integer(i*n+j));
		}
		return a;
	}
	int minm(int a, int b){
		return a<b?a:b;
	}
	int[][] floyd_warshall(int[][] a, int N){
		int[][] g = a;
		for(int k=0;k<N;k++){
			for(int i=0;i<N;i++){
				g[i][i] = 0;
				for(int j =0;j<N;j++){
					g[i][j] = minm(g[i][j], g[i][k]+a[k][j]);
				}
			}
		}
		return g;
	}
	public static void main(String[] args) throws IOException{
		int n = 4;
		Graph gob = new Graph();
		int[][] mat = gob.create_mat(n);
		gob.display(mat, n, n);
		LinkedList<Integer> tvst = gob.get_cheese(mat, n);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = 0;
		int[][] gr = gob.create_graph(mat, n);
		//gob.display(gr, n*n, n*n);
		int[][] fw = gob.floyd_warshall(gr, n*n);
		gob.display(fw, n*n, n*n);
		String line = br.readLine();
		int t = Integer.parseInt(line);
		while(t-->0){
			line = br.readLine();
			int e = Integer.parseInt(line);
			//gob.find_shortest_unique(gr, n*n, tvst, s, e); cant work by visiting just once
			gob.find_floyd_combo(fw, n*n, tvst, s, e);
		}
	}
	void findShortestUniqueUtil(int[][] gr, int N, LinkedList<Integer> tvst, int s, int e, int[] v, int ns, LinkedList<Integer> r){
		if(v[s]==1)
			return;
		if(s==e && tvst.isEmpty())
			{
				ls = r;
				min = min<ns?min:ns;
			}
		boolean intvst = false;
		if(tvst.contains(new Integer(s))){
			intvst = true;
			tvst.remove(new Integer(s));
		}
		v[s]=1;
		r.add(s);
		for(int i=0;i<N;i++){
			//System.out.println(s+" "+i+" "+e);
			if(gr[s][i]==1 && v[i]==0){
				findShortestUniqueUtil(gr, N, tvst, i, e, v, ns+1, r);
			}
		}
		v[s]=0;
		r.remove(new Integer(s));
		if(intvst)
			tvst.add(new Integer(s));
	}
	void find_shortest_unique(int[][] gr, int N, LinkedList<Integer> tvst, int s, int e) {
		int[] v = new int[N];
		LinkedList<Integer> r = new LinkedList<Integer>();
		findShortestUniqueUtil(gr, N, tvst, s, e, v, 0, r);
		System.out.println(min);
		printList(ls);
	}
	private void printList(LinkedList<Integer> ls2) {
		// TODO Auto-generated method stub
		for(int i =0;i<ls2.size();i++)
			System.out.print(ls2.get(i)+" ");
		System.out.println();
	}
	LinkedList<LinkedList<Integer>> get_perms(LinkedList<Integer> tvst){
		LinkedList<LinkedList<Integer>> l = new LinkedList<LinkedList<Integer>>();
		int len = tvst.size();
		if(len==1){
			LinkedList<Integer> s = new LinkedList();
			s.add(new Integer(tvst.element()));
			l.add(s);
			return l;
		}
		for(int i=0;i<len;i++){
			int n = tvst.get(i);
			tvst.remove(i);
			LinkedList<LinkedList<Integer>> r = get_perms(tvst);
			for(LinkedList sl:r){
				sl.addLast(n);
				l.add(sl);
			}
			tvst.add(i, n);
		}
		return l;
	}
	void find_floyd_combo(int[][] gr, int N, LinkedList<Integer> tvst, int s, int e){
		LinkedList<LinkedList<Integer>> tvstCombo = get_perms(tvst);
		LinkedList<Integer> f = new LinkedList<Integer>();
		int nmin = 10000000;
		int ls = tvst.size();
		for(LinkedList<Integer> l: tvstCombo){
			int csum = gr[s][l.get(0)];
			for(int i=1;i<ls;i++){
				csum+=gr[l.get(i-1)][l.get(i)];
			}
			csum+=gr[l.getLast()][e];
			if(csum<nmin){
				nmin = csum;
				f = l;
			}
		}
		System.out.println(nmin);
		printList(f);
	}
}
