import java.util.Scanner;

public class ExcelColNo {
    private String getExcelCol(int n){
        String s  ="";

        while(n>0){
            char c = (char) ('A'+((n-1)%26));
            s = c+s;
            n = (n-1)/26;
        }
        return s;
    }
    public static void main (String[] args) {
        ExcelColNo excelColNo = new ExcelColNo();
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt(s.nextLine());
        while (t != 0) {
            String line = s.nextLine();
            int n = Integer.parseInt(line);
            System.out.println(excelColNo.getExcelCol(n));
            t--;
        }
    }
}
