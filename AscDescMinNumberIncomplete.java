package misc;

import java.util.Arrays;
import java.util.Scanner;

public class AscDescMinNumberIncomplete {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String line = s.nextLine();
            AscDescMinNumberIncomplete ascDescMinNumber = new AscDescMinNumberIncomplete();
            System.out.println( ascDescMinNumber.getMinNumber(line) );
            t--;
        }
    }

    private String getMinNumber(String line) {
        int[] res = new int[line.length()+1];
        short idx=0;
        short start=1;
        while(start<=9) {
            res[idx]=start;
            short i=0;
            for(i=0;i<line.length();i++){
                res[idx+1]=(line.charAt(i)=='D')?res[idx]-1:res[idx]+1;
                if(res[idx+1]<=0){
                    res = new int[line.length()+1];
                    idx=0;
                    start++;
                    //get out of this 'start' value
                    break;
                }
                else
                    idx+=1;
            }
            if(i==line.length()) {// we got a value
                return Arrays.toString(res);
            }
        }
        return Arrays.toString(res);
    }
}
