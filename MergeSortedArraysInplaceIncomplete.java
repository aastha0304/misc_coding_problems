package misc;

import java.util.Scanner;

public class MergeSortedArraysInplaceIncomplete {
    private void mergeSortedArrays(int[] a,int[] b){


    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String line = s.nextLine();
            String[] ns = line.split(" ");
            int n = Integer.parseInt( ns[0] );
            int S = Integer.parseInt( ns[1] );
            line = s.nextLine();
            String[] elems = line.split(" ");
            int[] a = new int[n];
            for(int i=0;i<n;i++)
                a[i] = Integer.parseInt( elems[i] );
            line = s.nextLine();
            elems = line.split(" ");
            int[] b = new int[n];
            for(int i=0;i<S;i++)
                b[i] = Integer.parseInt( elems[i] );
            MergeSortedArraysInplaceIncomplete mergeSortedArraysInplaceIncomplete = new MergeSortedArraysInplaceIncomplete();
            mergeSortedArraysInplaceIncomplete.mergeSortedArrays(a,b);

            t--;
        }
    }
}
