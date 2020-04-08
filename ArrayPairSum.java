import java.util.Scanner;

public class ArrayPairSum {
    private boolean canDivideAPS(int[] a, int n){
        int[] mods = new int[n];
        for(int i=0;i<a.length;i++)
            mods[a[i]%n]++;
        if(mods[0]%2!=0)
            return false;
        for(int i=0;i<a.length;i++){
            int mod1 = a[i]%n;
            if(mod1!=0) {
                int mod2 = n - mod1;
                if (mods[mod2] > 0)
                    mods[mod2]--;
                else
                    return false;
            }
        }
        return true;
    }
    public static void main (String[] args) {
        ArrayPairSum arrayPairSum = new ArrayPairSum();
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while( t!=0 ){
            int n = s.nextInt();
            //String[] elems = s.nextLine().split(" ");
            int[] a = new int[n];
            for(int i=0;i<n;i++)
                a[i] = s.nextInt();
            int k = s.nextInt();
            //System.out.println(maxRectangle.maxArea(a, n, m));
            System.out.println(arrayPairSum.canDivideAPS(a, k));
            t--;
        }
    }
}
