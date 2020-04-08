import java.util.Scanner;

public class NSXSumXor {
    private String isSumSXorX(int n, int s, int x){
        if (s < x) return "No";
        if ((s % 2) != (x % 2)) return "No";

        if ((n == 1) && (s == x)) return "Yes";
        if ((n == 1)) return "No";

        if (n >= 3) return "Yes";

        return "Maybe"; // n must be 2
    }
    public static void main(String[] args){
        NSXSumXor nsxSumXor = new NSXSumXor();
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while( t!=0 ){
            int n = scanner.nextInt();
            int s = scanner.nextInt();
            int x = scanner.nextInt();
            System.out.println(nsxSumXor.isSumSXorX(n, s, x));
            t--;
        }
    }
}
