package hackerearth;

public class SpiralMatrix {
	void printCircular(int r, int n){
		int[][] a=new int[r][n];
		int m = 1;
		int rs=0,cs=0,rf=r-1,cf=n-1, i,j;
		while(m<=r*n){
			for(i=cs;i<=cf;i++){
				if(a[rs][i]==0)
					a[rs][i]=m++;
			}
			for(j=rs;j<=rf;j++){
				if(a[j][cf]==0)
					a[j][cf]=m++;
			}
			for(i=cf;i>=cs;i--){
				if(a[rf][i]==0)
					a[rf][i]=m++;
			}
			for(j=rf;j>=rs;j--){
				if(a[j][cs]==0)
					a[j][cs]=m++;
			}
			rs++;
			rf--;
			cs++;
			cf--;
		}
		Graph gob=new Graph();
		gob.display(a, r, n);
	}
	public static void main(String[] args){
		int m=6, n = 4;
		SpiralMatrix sob = new SpiralMatrix();
		sob.printCircular(m, n);
	}
}
