package misc;

import java.util.Scanner;

public class Algebra {
    private void solveEquation(long total, long extra ){
        long withMaddie = (total-extra)/2;
        long withGia = withMaddie+extra;
        System.out.println(withGia);
        System.out.println(withMaddie);
    }
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        long total = Long.parseLong( s.nextLine() );
        long extra =  Long.parseLong( s.nextLine() );
        Algebra algebra = new Algebra();
        algebra.solveEquation(total, extra);
    }
}
