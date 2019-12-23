package misc;

import java.util.Scanner;

public class ArrayDP {
    private void printCombinationRecursive(int[] arr, int r){
        if(arr.length<r)
            return;
        int[] currentData = new int[r];
        printCombinationRecursiveUtil(arr, 0, arr.length, r, currentData, 0);
    }
    private void printCombinationRecursiveUtil(int[] arr, int aStart, int aFinish, int r, int[] data, int dataIdx){
        if(dataIdx==r)
        {
            for(int i:data)
                System.out.print(i+" ");
            System.out.println();
            return;
        }
        if(aStart>=aFinish)
            return;
        data[dataIdx]=arr[aStart];
        printCombinationRecursiveUtil(arr, aStart+1, aFinish,r,  data, dataIdx+1);
        printCombinationRecursiveUtil(arr, aStart+1, aFinish,r,  data, dataIdx);

    }
    private void printCombinationIterative(int[] a, int r){

    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String line = s.nextLine();
            String[] elems = line.split(" ");
            int[] a = new int[elems.length];
            for(int i=0;i<a.length;i++)
                a[i] = Integer.parseInt( elems[i] );
            int r = Integer.parseInt( s.nextLine() );

            Utils.display(a);
            ArrayDP arrayCombinations = new ArrayDP();
            arrayCombinations.printCombinationRecursive(a,r);
            arrayCombinations.printCombinationIterative(a,r);
            t--;
        }

    }
}
