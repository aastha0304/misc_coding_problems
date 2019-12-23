package misc;

import java.util.Map;

public class Utils {
    public static void display(int[] a){
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+"\t");
        }
        System.out.println();
    }
    public static void display(int[][] a){
        for (int[] ints : a) {
            for (int j : ints)
                System.out.print(j + "\t");
            System.out.println();
        }
    }
    public static void display(short[][] a){
        for (short i=0;i<a.length;i++) {
            for (short j=0;j<a[i].length;j++)
                System.out.print(a[i][j] + "\t");
            System.out.println();
        }
    }
    static void display(Map<Integer, Integer> map){
        for(int i:map.keySet()){
            System.out.print(i+":"+map.get(i)+" ");
        }
        System.out.println();
    }
}
