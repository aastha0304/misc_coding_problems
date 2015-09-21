package hackerearth;

public class minRangeAnd {
	int min(int a, int b){
		return a<b?a:b;
	}
	int max(int a, int b){
		return a<b?b:a;
	}
	int maxWater(int[] a, int n){
		if(n<=1)
			return 0;
		int i =0, j=n-1, w=0, h, minh=0;
		while(i<j){
			w = max(w, a[i]&a[j]);
			while(i<n && a[i]<=a[j])i++;
			while(j>-1 && a[j]<=a[i])j--;
		}
		System.out.println(i+" "+j);
		return w;
	}
	public static void main(String[] args){
		minRangeAnd mob = new minRangeAnd();
		int a[] = {8,9,7,8,7,5,7,1,1,1};
		System.out.println(mob.maxWater(a, 10));
	}
}
