package misc;

import java.util.Scanner;

public class RotateMatrix {
    public static void display(short[][] a){
        for (short i=0;i<a.length;i++) {
            for (short j=0;j<a[i].length;j++)
                System.out.print(a[i][j] + " ");
        }
    }
    private void swap(short[][] a, short x1, short y1, int x2, int y2){
        short t=a[x2][y2];
        a[x2][y2]=a[x1][y1];
        a[x1][y1]=t;
    }
    private void reverseRows(short[][] a, short len){
        for(short i=0;i<a.length;i++){
            for(short j=0;j<a[0].length/2;j++){
                swap(a,i,j,i,a[0].length-1-j);
            }
        }
    }
    private void reverseCols(short[][] a, short len){
        for(short j=0;j<=a[0].length;j++) {
            for (short i = 0; i < a.length / 2; i++) {
                swap(a,i,j,a.length-1-i,j);
            }
        }
    }
    private void transpose(short[][] a){
        for(short i=0;i<a.length;i++){
            for(short j=0;j<=i;j++){
                swap(a,i,j,j,i);
            }
        }
    }
    private void rotateMatrixLeft90(short[][] a, short n){
        transpose(a);
        reverseCols(a,n);
    }
    private void rotateMatrixRight90(short[][] a,short n){
        transpose(a);
        reverseRows(a,n);
    }
    public static void main (String[] args) {

        short t;
        Scanner s = new Scanner(System.in);
        t = Short.parseShort( s.nextLine() );
        while( t!=0 ){
            short n = s.nextShort();
            short[][] a=new short[n][n];
            for(int i=0;i<n;i++)
                for(int j=0;j<n;j++)
                    a[i][j]=s.nextShort();
            RotateMatrix rotateMatrix = new RotateMatrix();
            //rotateMatrix.transpose(a);
            rotateMatrix.rotateMatrixRight90(a,n);
            //Utils.display(a);
            display(a);
            t--;
        }
    }
}
