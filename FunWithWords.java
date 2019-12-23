package misc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FunWithWords {
    private boolean canBreakWord(Set<String > words, char[] str){
        return canBreakWordUtil(words, String.valueOf(str), str, 0);
    }
    private boolean canBreakWordUtil(Set<String> words,  String str, char[] a, int idx){
        if(idx>=a.length)
            return false;
        else if(words.contains(str.substring(idx))){
            return true;
        }else{
            boolean included=false;
            for(int i=idx;i<str.length()-1;i++){
                if(words.contains(str.substring(idx, i+1))){
                    included |= canBreakWordUtil(words, str, a, i+1);
                }
            }
            //excluded |= canBreakWordUtil(words, str, a, idx+1);
            return included;
        }
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        FunWithWords funWithWords = new FunWithWords();
        while( t!=0 ){
            s.nextLine();
            Set<String> words = new HashSet<> (Arrays.asList(s.nextLine().split(" ")));
            String str = s.nextLine();
            System.out.println(funWithWords.canBreakWord(words, str.toCharArray()));
            //System.out.println(funWithWords.canBreakWordDP(words, str.toCharArray()));
            t--;
        }
    }
}
