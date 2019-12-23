package misc;

import java.util.Scanner;

public class HalfSudokoValidator {
    final static short ROWS = 9;
    final static short COLUMNS = 9;
    final static short LIMIT = 9;
    void display(short[][] a){
        for (short i=0;i<a.length;i++) {
            for (short j=0;j<a[i].length;j++)
                System.out.print(a[i][j] + "\t");
            System.out.println();
        }
        System.out.println("****************************************");
    }
    private boolean isOkay(short[][] a, short r, short c){
        short[] counts = new short[10];
        for(short i=0;i<ROWS;i++) {
            if(a[r][i]!=0) {
                counts[a[r][i]]++;
                if (counts[a[r][i]] > 1)
                    return false;
            }
        }
        counts = new short[10];
        for(short i=0;i<COLUMNS;i++) {
            if(a[i][c]!=0) {
                counts[a[i][c]]++;
                if (counts[a[i][c]] > 1)
                    return false;
            }
        }
        int startX = (r/3)*3;
        int startY = (c/3)*3;
        counts = new short[10];
        for(int i=startX;i<startX+3;i++){
            for(int j=startY;j<startY+3;j++){
                if(a[i][j]!=0) {
                    counts[a[i][j]]++;
                    if (counts[a[i][j]] > 1)
                        return false;
                }
            }
        }
        return true;
    }
    private short validateSudoku(short[][] a){
        //display(a);
        for(short i=0;i<ROWS;i++) {
            for (short j=0;j<COLUMNS;j++) {
                if (a[i][j] != 0) {
                    if (!isOkay(a, i, j)) {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }
    public static void main (String[] args) {

        short t;
        Scanner s = new Scanner(System.in);
        t = Short.parseShort( s.nextLine() );
        while( t!=0 ){
            short a[][]=new short[ROWS][COLUMNS];
            for(int i=0;i<9;i++)
                for(int j=0;j<9;j++)
                    a[i][j]=s.nextShort();
            HalfSudokoValidator halfSudokoValidator = new HalfSudokoValidator();

            System.out.println(halfSudokoValidator.validateSudoku(a));
            t--;
        }
    }
}
