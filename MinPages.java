package misc;

import java.util.Scanner;

public class MinPages {
    private long sum(int[] a, int start, int end){
        long sum = 0;
        for(int i=start;i<end;i++){
            sum += a[i];
        }
        return sum;
    }
    private int getStudentsRequired(long numPages, int [] a){
        int studentsRequired = 1;
        long currSum=0;
        for(int i=0;i<a.length;i++){
            if(numPages<a[i])
                return a.length+1;
            if(currSum+a[i]<=numPages){
                currSum += a[i];
            }else{
                studentsRequired++;
                currSum =  a[i];
            }
        }
        return studentsRequired;
    }
    private long getMinPages(int[] a, int k) {
        if(a.length<k)
            return -1;
        long start = 0, end = sum(a, 0, a.length);
        long min = Integer.MAX_VALUE;
        long pagesConsidered;
        while(start <= end){
            pagesConsidered = start + (end-start)/2;
            int studentsRequired = getStudentsRequired(pagesConsidered, a);
            if(studentsRequired <= k){
                if(min > pagesConsidered)
                    min = pagesConsidered;
                end = pagesConsidered-1;
            }
            else{
                start = pagesConsidered+1;
            }
        }
        return min;
    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            int n = Integer.parseInt( s.nextLine() );
            String[] elems = s.nextLine().split(" ");
            int[] a = new int[n];
            for(int i=0;i<n;i++)
                a[i] = Integer.parseInt( elems[i] );
            int k = Integer.parseInt( s.nextLine() );
            MinPages minPages = new MinPages();
            System.out.println(minPages.getMinPages(a,k));
            t--;
        }
    }
}
