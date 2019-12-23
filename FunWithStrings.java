package misc;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FunWithStrings {
    private void replaceACAndB(char[] str){
        boolean STATE = false;
        for(char c:str){
            if(c=='a'){
                if(STATE){
                    System.out.print('a');
                    STATE=false;
                    System.out.print('a');
                }else{
                    STATE=true;
                }
            }else if(c=='b'){
                continue;
            }else if(c=='c'){
                if(STATE){
                    STATE=false;
                    continue;
                }else{
                    System.out.print('c');
                }
            }else{
                if(STATE) {
                    System.out.print('a');
                    STATE = false;
                }
                System.out.print(c);
            }
        }
        System.out.println();
    }
    private void replaceACAndBLimited(char[] a){
        int i = 0;
        while(i<a.length){
            if(a[i]=='b') {
                i+=1;
            }else if(a[i]=='a'){
                if(i+1<a.length && a[i+1]=='c'){
                    i+=2;
                }else{
                    System.out.print(a[i]);
                    i+=1;
                }
            }else{
                System.out.print(a[i]);
                i+=1;
            }
        }
        System.out.println();
    }
    private String largestChild(String[] words, String str){
        int max = 0;
        String res = "";
        int[] allChars = new int[256];
        for(char c: str.toCharArray()){
            allChars[c]++;
        }
        for(String word: words){
            int i=0;
            for(i=0;i<word.length();i++){
                allChars[word.charAt(i)]--;
                if(allChars[word.charAt(i)]<0)
                    break;
            }
            if(i==word.length() && word.length()>max){
                max = Math.max(word.length(), max);
                res = word;
            }
            allChars = new int[256];
            for(char c: str.toCharArray()){
                allChars[c]++;
            }
        }
        return res;
    }
    private boolean isSwappable(char[] arr1, char[] arr2){
        int swapIdx1 = -1, swapIdx2 = -1, changes = 0;
        for(int i=0;i<arr1.length;i++){
            if(arr1[i]!=arr2[i]){
                changes ++;
                if(changes>2)
                    return false;
                if(swapIdx1==-1)
                    swapIdx1 = i;
                else {
                    swapIdx2 = i;
                }
            }
        }
        return changes==2 && arr1[swapIdx1] == arr2[swapIdx2] && arr1[swapIdx2] == arr2[swapIdx1];
    }
    private int getMinPalindromeTF(String s){
        int[][] res = new int[s.length()][s.length()];
        int low, high, diff;
        for(diff=1;diff<s.length();diff++){
            for(low=0,high=diff;high<s.length();high++,low++){
                res[low][high] = s.charAt(low)==s.charAt(high)?res[low+1][high-1]:
                        Math.min(res[low][high-1], res[low+1][high])+1;
            }
        }
        return res[0][s.length()-1];
    }
    private String getMinStringAllChars(String string, String chars){
        int[] stringFreq = new int[26];
        int[] charsFreq = new int[26];
        int start=0;

        for(char c: chars.toCharArray()){
            charsFreq[c-'a']++;
        }
        int charsCount=0, minLength = Integer.MAX_VALUE;
        String res="";
        for(int i=0;i<string.length();i++){
            int idx = string.charAt(i)-'a';
            stringFreq[idx]++;
            if(charsFreq[idx]>0 && stringFreq[idx]<=charsFreq[idx])
                charsCount++;
            if (charsCount == chars.length()) {
                idx = string.charAt(start)-'a';
                while (charsFreq[idx]==0 ||
                        stringFreq[idx] > charsFreq[idx]) {
                    if(stringFreq[idx] > charsFreq[idx])
                        stringFreq[idx]--;
                    start++;
                    idx = string.charAt(start)-'a';
                }
                if (i + 1 - start < minLength) {
                    minLength = i + 1 - start;
                    res = string.substring(start, i+1);
                }
            }
        }
        return res;
    }
    private int getMinDistinctLength(String string){
        Set<Character> distincts = new HashSet<>();
        for(char c:string.toCharArray()){
            distincts.add(c);
        }
        StringBuilder sbr = new StringBuilder();
        for(char c: distincts)
            sbr.append(c);
        return getMinStringAllChars(string, sbr.toString()).length();
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        FunWithStrings funWithStrings = new FunWithStrings();
        while( t!=0 ){
            String string = s.nextLine();
            System.out.println(funWithStrings.getMinDistinctLength(string));
//            System.out.println(funWithStrings.getMinPalindromeTF(string));
//            String string2 = s.nextLine();
//            System.out.println(funWithStrings.getMinStringAllChars(string, string2));

//            System.out.println(funWithStrings.isSwappable(string1.toCharArray(), string2.toCharArray()));

//            //this one returns aa for aababc input
//            funWithStrings.replaceACAndB(string.toCharArray()) ;
//            //if you dont want that try this
//            funWithStrings.replaceACAndBLimited(string.toCharArray()) ;
//            s.nextLine();
//            String[] words = s.nextLine().split(" ");
//            String motherStr = s.nextLine();
//            System.out.println(funWithStrings.largestChild(words, motherStr));

            t--;
        }
    }
}
