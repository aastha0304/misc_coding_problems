import java.util.Scanner;
public class SpecialKeyboard {
  private int getMaxA(int n){
    if(n<=6)
      return n;
    else{
      int i;
      int[] best = new int[n+1];
      for(i=1;i<=6;i++){
        best[i] = i;
      }
      while(i<=n){
        for(int x=i-3;x>0;x--){
          best[i] = Math.max(best[i], best[x-1]*(i-x));
        }
        i++;
      }
      return best[n];
    }
  }
   public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        Main specialKeyboard = new Main();
        while( t!=0 ){
            short n = s.nextShort();
            System.out.println(
              specialKeyboard.getMaxA(n));
            t--;
        }
  }
}
