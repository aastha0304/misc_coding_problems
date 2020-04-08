import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class StockPrice {
    private void getMaxStock(int[] a){
        int start = 0, i=1;
        List<String> results = new LinkedList<>();
        while(i<a.length){
            if(a[i]>a[i-1] || (a[i]>a[start] && a[i]==a[i-1]))
                i++;

            else {
                if (start != i - 1)
                    results.add("(" + start + " " + (i - 1) + ") ");
                start = i;
                i+=1;
            }
        }
        if (start != i - 1) {
            results.add("(" + start + " " + (i - 1) + ") ");
        }
        if(results.isEmpty()){
            System.out.print("No Profit");
        }
        else{
            for(String res: results){
                System.out.print(res);
            }
        }
        System.out.println();
    }

    public static void main (String[] args) {
        StockPrice stockPrice = new StockPrice();
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while( t!=0 ) {
            int n = s.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = s.nextInt();
            stockPrice.getMaxStock(a);
            t--;
        }
    }
}
