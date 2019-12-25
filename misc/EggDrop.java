import java.util.Scanner;
public class EggDrop {
  private int getBinCoeff(int x, int n){
    int term = 1, res = 0;
    for(int i=1;i<=n;i++){
      term *= x-i+1;
      term /= i;
      res += term;
    }
    return res;
  }
  private int getMinTrial(int n, int k){
    int low=1,high=k,mid;
    while(low<high){
      mid = low+(high-low)/2;
      int binCoeff = getBinCoeff(mid, n);
      if(binCoeff<k)
        low=mid+1;
      else
        high=mid;

    }
    return low;
  }
  public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        EggDrop eggDrop = new EggDrop();
        while( t!=0 ){
            String line = s.nextLine();
            int n = Integer.parseInt( line.split(" ")[0]);
            int k = Integer.parseInt( line.split(" ")[1]);
            System.out.println(eggDrop.getMinTrial(n,k));
            t--;
        }
  }
}
