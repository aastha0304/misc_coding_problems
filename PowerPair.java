package misc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PowerPair {
    int binSearch(int arr[], int low, int high, int x)
    {
        int mid;
        if(x <= arr[low])
            return low;
        if(x > arr[high])
            return -1;
        mid = (low + high) / 2;
        if(arr[mid] == x)
            return mid;
        else if(arr[mid] < x)
        {
            if(mid + 1 <= high && x <= arr[mid + 1])
                return mid + 1;
            else
                return binSearch(arr, mid + 1, high, x);
        }
        else
        {
            if(mid - 1 >= low && x > arr[mid - 1])
                return mid;
            else
                return binSearch(arr, low, mid - 1, x);
        }
    }
    private int getPairCount(int[] x, int[] y, boolean isUnitPresent){
        Set set = new HashSet<>( Arrays.asList(y) );

        int count = 0;
        Arrays.sort(y);
        for(int i=0;i<x.length;i++){
            if(x[i]==0)
                continue;
            else {
                int idx=binSearch(y,0,y.length-1,x[i]);
                System.out.println(x[i]+ " "+idx+" "+((idx!=-1)?y[idx]:"")+" "+count);
                if(x[i]==1) {
                    if (idx == -1)
                        count += y.length;
                    else
                        count += idx;
                }
                else{
                    if(isUnitPresent) {
                        System.out.println("1 in y");
                        count += 1;
                    }
                    if(idx!=-1){
                        System.out.println("suitable index found");
                        count += y.length-idx;
                    }
                }
            }
        }
        return count;

    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String line = s.nextLine();
            String[] arraySizes = line.split(" ");
            int M = Integer.parseInt( arraySizes[0] );
            int N = Integer.parseInt( arraySizes[1] );
            line = s.nextLine();
            String[] elems = line.split(" ");
            int[] x = new int[M];
            for(int i=0;i<M;i++)
                x[i] = Integer.parseInt( elems[i] );
            line = s.nextLine();
            elems = line.split(" ");
            int[] y = new int[N];
            boolean isUnitPresent = false;
            for(int i=0;i<N;i++) {
                y[i] = Integer.parseInt(elems[i]);
                if(y[i]==1)
                    isUnitPresent=true;
            }
            PowerPair powerPair = new PowerPair();
            System.out.println( powerPair.getPairCount(x,y, isUnitPresent) );
            t--;
        }
    }
}
