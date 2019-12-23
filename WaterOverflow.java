package misc;

import java.util.Scanner;

public class WaterOverflow {
    private float getWaterContent(int k, int r, int c){
        float[][] water = new float[r][c];
        //adjust for array indexing
        r = r-1;
        c = c-1;
        water[0][0]=k;
        for(int i=1;i<=r;i++){
            for(int j=0;j<=c;j++){
                if(i-1>=0) {
                    if (water[i - 1][j] > 1)
                        water[i][j] += (water[i - 1][j]-1) / 2;
                    if(j-1>=0 && water[i-1][j-1]>1)
                        water[i][j] += (water[i - 1][j-1]-1) / 2;
                }
            }
        }
        return water[r][c];
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt(s.nextLine());
        WaterOverflow waterOverflow = new WaterOverflow();
        while (t != 0) {
            int k= s.nextShort();
            int i=s.nextShort();
            int j=s.nextShort();
            System.out.println(waterOverflow.getWaterContent(k,i,j));
            t--;
        }
    }
}
