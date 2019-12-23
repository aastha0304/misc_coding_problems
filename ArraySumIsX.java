package misc;

import java.util.*;

public class ArraySumIsX {
    private boolean getSum(int[] a, int sum, Map<Integer, Integer> sumSet ){
        for(int i=0;i<a.length;i++){
            sumSet.put(a[i], sumSet.get(a[i])-1);
            int diff = sum-a[i];
            if( (sumSet.containsKey(diff)) && (sumSet.get(diff)>0) )
                return true;

        }
        return false;
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
            Map<Integer,Integer> sumSet = new HashMap<>();

            for(int i=0;i<n;i++) {
                a[i] = Integer.parseInt(elems[i]);
                if( sumSet.containsKey(a[i]) ){
                    sumSet.put(a[i], sumSet.get(a[i])+1);
                }else{
                    sumSet.put(a[i],1);
                }
            }
            ArraySumIsX arraySumIsX = new ArraySumIsX();
            System.out.println(arraySumIsX.getSum(a,S,sumSet)?"Yes":"No");
            t--;
        }
    }
}
