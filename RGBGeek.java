import java.util.Scanner;

//https://practice.geeksforgeeks.org/problems/geek-and-its-colored-strings/0
public class RGBGeek {
    private long getRGBPerms(int n, int x, int y, int z){
        int rem = n - (x+y+z);
        long res = 0;
        for(int i=0;i<=rem;i++){
            for(int j=0;j<=rem;j++){
                int k = rem-(i+j);
                if(k<0)
                    break;
                res = res + factorial(n)/(factorial(x+i)*factorial(y+j)*factorial(z+k));
            }
        }
        return res;
    }
    private long factorial(int n){
        long res = 1;
        if (n==0) return res;
        for (int i=1; i<=n; i++) {
            res = res * i;
        }
        return res;
    }
    public static void main (String[] args) {
        RGBGeek rgbGeek = new RGBGeek();
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
            while( t!=0 ){
                int n = s.nextInt();
                int r = s.nextInt();
                int g = s.nextInt();
                int b = s.nextInt();
                System.out.println(rgbGeek.getRGBPerms(n,r,g,b));
                t--;
        }
    }
}
