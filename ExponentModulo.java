package misc;

import java.util.Scanner;

public class ExponentModulo {
    private int getExponentModulo(int x, int y, int n){
        int res=1;
        while(y>0){
            if( (y&1) == 1)
                res = ( res%n * x%n ) %n;
            x = x%n;
            x = (x*x)%n;
            y = y>>1;
        }
        return res;
    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String line = s.nextLine();
            String[] elems = line.split(" ");
            int a = Integer.parseInt(elems[0]);
            int b = Integer.parseInt(elems[1]);
            int c = Integer.parseInt(elems[2]);
            ExponentModulo exponentModulo = new ExponentModulo();
            System.out.println(exponentModulo.getExponentModulo(a,b,c));
            t--;
        }
    }
}
