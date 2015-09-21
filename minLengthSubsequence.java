package hackerearth;

public class minLengthSubsequence {
	void print(int[] a){
		for(int i =97;i<123;i++)
			System.out.print(a[i]+" ");
		System.out.println();
	}
	void computeLps(char[] pat, int M, int[] lps)
	{
	    int len = 0;
	    int i;
	    lps[0] = 0;
	    i = 1;
	    while (i < M)
	    {
	       if (pat[i] == pat[len])
	       {
	         len++;
	         lps[i] = len;
	         i++;
	       }
	       else
	       {
	         if (len != 0)
	         {
	           len = lps[len-1];
	         }
	         else
	         {
	           lps[i] = 0;
	           i++;
	         }
	       }
	    }
	}
	int subseq(char[] s, int sidx, int slen, char[] p, int plen){
		int j = sidx, i = 0;
		while(i<plen && j<slen){
			if(p[i]==s[j]){
				i++;
			}if(i==plen){
				return j;
			}
			j++;
		}
		return -1;
	}
	int min(int a, int b){
		return a<b?a:b;
	}
	int scs(char[] s, char[] p){
		int m = p.length;
		int[] lps = new int[m];
		computeLps(p, m, lps);
		int n = s.length;
		int minm = 10000;
		int i = 0;
		while(i<n-m+1){
				int clen = subseq(s, i, n, p, m);
				if(clen!=-1){
					System.out.println(i+" "+s[i]+" "+clen+" "+s[clen]);
					minm = min(minm, clen-i);
				}
				i++;
		}
		return minm;
	}
	public static void main(String[] args){
		String a = "wtttfittttsfisstfissstfitstt";
		String b = "tfist";
		minLengthSubsequence mOb = new minLengthSubsequence();
		System.out.println(mOb.scs(a.toCharArray(), b.toCharArray()));
	}
}
