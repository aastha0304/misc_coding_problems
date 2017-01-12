package hackerearth;

import java.util.ArrayList;

public class LongMonoIncSubseq {
	int ceil(ArrayList<ArrayList<Integer>> tt, int l, int r, int num){
		int m;
		while(r-l>1){
			m=l+(r-l)/2;
			if(tt.get(m).get(tt.get(m).size()-1)>=num)
				r=m;
			else
				l=m;
		}
		return r;
	}
	void print(ArrayList<ArrayList<Integer>> tt){
		for(int j = 0;j<tt.size();j++){
			//System.out.print(j+" "+tt.get(j).size()+" ");
			for(int i=0;i<tt.get(j).size();i++){
				System.out.print(tt.get(j).get(i)+" ");
			}
		System.out.println();
		}
	}
	void remove(ArrayList<ArrayList<Integer>> tt, int sz){
		int l = tt.size();
		int i = 0;
		while(i<l && l>0){
			if(tt.get(i).size()==sz){
				tt.remove(i);
				l--;
			}else{
				i++;
			}
		}
	}
	void lis(int[] a, int n){
		if(n<=1)
			return;
		ArrayList<ArrayList<Integer>> tt = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> st = new ArrayList<Integer>();
		st.add(a[0]);
		tt.add(st);
		for(int i=1;i<n;i++){
			//print(tt);
			ArrayList<Integer> smlTmp = tt.get(0);
			int smlTmpSz = smlTmp.size();
			ArrayList<Integer> lrgTmp = tt.get(tt.size()-1);
			int lrgTmpSz = lrgTmp.size();
			if(a[i]<smlTmp.get(smlTmpSz-1)){
				System.out.println(a[i]+" case 1");
				st = new ArrayList<Integer>();
				st.add(a[i]);
				tt.set(0, st);
			}
			else if(a[i]>lrgTmp.get(lrgTmpSz-1)){
				System.out.println(a[i]+" case 2");
				st = new ArrayList<Integer>(lrgTmp);
				st.add(a[i]);
				tt.add(st);
			}
			else{
				int idx = ceil(tt, -1, tt.size()-1, a[i]);
				System.out.println(a[i]+" case 3 "+idx);
				if(idx!=0){
					st = new ArrayList<Integer>(tt.get(idx-1));
					st.add(a[i]);
					remove(tt, st.size());
					tt.add(idx, st);
				}
			}
		}
		print(tt);
	}
	public static void main(String[] args){
		int a[] =  {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
		int n = a.length;
		LongMonoIncSubseq ob = new LongMonoIncSubseq();
		ob.lis(a, n);
	}
}
