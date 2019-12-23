package misc;

import java.util.Scanner;

public class ShortestCommonSupersequence {
    private String getShortestCommonSuperSequence(String s1, String s2){
        int[][] lcs = new int[s1.length()][s2.length()];
        for(int i=0;i<s1.length();i++){
            for(int j=0;j<s2.length();j++){
                if(i==0)
                    lcs[i][j]=
            }
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt(s.nextLine());
        ShortestCommonSupersequence shortestCommonSupersequence = new ShortestCommonSupersequence();
        while (t != 0) {
            String str1 = s.nextLine();
            String str2 = s.nextLine();
            System.out.println(shortestCommonSupersequence.getShortestCommonSuperSequence(str1, str2) );
            t--;
        }
    }

}
