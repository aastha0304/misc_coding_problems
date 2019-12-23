import java.util.Scanner;

public class ShortestCommonSubsequence {
  private int getSCS(String s1, String s2){
        int[][] lcs = new int[s1.length()+1][s2.length()+1];
        for(int i=0;i<=s1.length();i++){
          for(int j=0;j<=s2.length();j++){
            if(i==0 || j==0)
              lcs[i][j]=0;
            else if(s1.charAt(i-1)==s2.charAt(j-1))
              lcs[i][j]=1+lcs[i-1][j-1]  ;
            else
              lcs[i][j]=Math.max(lcs[i-1][j], lcs[i][j-1]);  
          }
        }
        int res = lcs[s1.length()][s2.length()];
        res = s1.length()+s2.length()-res;
        return res;
    } 
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt(s.nextLine());
        Main scs = new Main();
        while( t!=0 ){
            
            String[] strings = s.nextLine().split(" ");
            String string1 = strings[0];
            String string2 = strings[1];
            System.out.println(scs.getSCS(string1, string2));
            t--;
            
        }
    }
}
