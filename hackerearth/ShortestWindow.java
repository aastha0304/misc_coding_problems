package hackerearth;

public class ShortestWindow {
	int[] shortestWindowLength(char[] a, char[] p){
		int[] pcnt = new int[256];
		int[] hcnt = new int[256];
		int pl = 0;
		for(int i=0;i<p.length;i++){
			pcnt[p[i]]++;
			pl++;
		}
		int plt=0;
		int s=0, f=0;
		int min = 100000;
		for(int i=0;i<a.length;i++){
			if(pcnt[a[i]]==0)continue;
			if(pcnt[a[i]]>0){
				hcnt[a[i]]++;
				if(hcnt[a[i]]<=pcnt[a[i]])
					plt++;
			}
			if(plt==pl){
				f=i;
				while(pcnt[a[s]]==0 || hcnt[a[s]]>pcnt[a[s]]){
					hcnt[a[s]]--;
					s++;
				}
				//System.out.println(s+" "+f);
				if(f-s+1<min){
					min=f-s+1;
				}
			}
		}
		return new int[]{min, f, s};
	}
	public static void main(String[] args){
		ShortestWindow sob = new ShortestWindow();
		char[] a = "adobecodebanc".toCharArray();
		char[] p = "abc".toCharArray();
		System.out.println(sob.shortestWindowLength(a,p)[0]);
	}
}
