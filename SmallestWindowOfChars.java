package misc;

import java.util.Scanner;

public class SmallestWindowOfChars {
    public static String smallestWindow(String s, String t){
        if(s.length()<t.length())
            return "-1";
        // Your code here
        short[] sCount = new short[26];
        short[] tCount = new short[26];
        for(short i=0;i<t.length();i++)
            tCount[t.charAt(i)-97]++;
        short start=0;
        short end=0;
        short tChars = 0;
        String minString=s;
        boolean neverGotHere = true;
        while(end<s.length()){
            int endIdx = s.charAt(end)-97;
            sCount[endIdx]++;
            if( sCount[endIdx]<=tCount[endIdx] )
                    tChars++;

            if(tChars==t.length()){
                neverGotHere = false;
                int startIdx = s.charAt(start)-97;
                while( (sCount[startIdx]>tCount[startIdx]) || (tCount[startIdx] == 0) ) {
                    if(sCount[startIdx]>tCount[startIdx])
                        sCount[startIdx]--;
                    start++;
                    startIdx = s.charAt(start)-97;
                }
                minString = (minString.length()>end-start+1)?s.substring(start,end+1):minString;
            }
            end++;
        }
        return neverGotHere? "-1":minString;
    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String S = s.nextLine();
            String T=s.nextLine();
            SmallestWindowOfChars smallestWindowOfChars = new SmallestWindowOfChars();
            System.out.println( smallestWindowOfChars.smallestWindow(S,T) );
            t--;
        }
    }
}
