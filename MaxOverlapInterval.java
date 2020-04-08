import java.util.Arrays;
import java.util.Scanner;

public class MaxOverlapInterval {
    //basically what it seems to be doing is, incrementing and comparing as people come; and decrementing as people go
    private void getMaxOverlap(int[] starts, int[] ends){
        Arrays.sort(starts);
        Arrays.sort(ends);
        int maxCount = Integer.MIN_VALUE, time = 0;
        int counter = 0, exitIdx = 0, entryIdx = 0;
        while(exitIdx < ends.length && entryIdx < starts.length){
            //overlap
            if(starts[entryIdx] <= ends[exitIdx]){
                counter++;
                if(counter > maxCount){
                    maxCount = counter;
                    time = starts[entryIdx];
                }
                entryIdx ++;
            }else{
                counter--;
                exitIdx++;
            }
        }
        System.out.println(maxCount+" "+time);
    }
    public static void main (String[] args) {
        MaxOverlapInterval maxOverlapInterval = new MaxOverlapInterval();
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            int n = Integer.parseInt( s.nextLine().trim() );
            String[] startStrs = s.nextLine().split("\\s+");
            String[] endStrs = s.nextLine().split("\\s+");

            int[] starts = new int[n];
            int[] ends = new int[n];
            for(int i=0;i<n;i++){
                starts[i] = Integer.parseInt(startStrs[i]);
                ends[i] = Integer.parseInt(endStrs[i]);
            }
            maxOverlapInterval.getMaxOverlap(starts, ends);
            t--;
        }
    }
}
