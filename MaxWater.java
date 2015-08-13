package hackerearth;

public class MaxWater {
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
			h=j-i;
			minh = min(a[i],a[j]);
			w = max(w, minh*h);
			while(i<n && a[i]<=minh)i++;
			while(j>-1 && a[j]<=minh)j--;
		}
		return w;
	}
	public static void main(String[] args){
		MaxWater mob = new MaxWater();
		int a[] = {8,9,7,8,7,5,7,1,1,1};
		System.out.println(mob.maxWater(a, 10));
	}
}
