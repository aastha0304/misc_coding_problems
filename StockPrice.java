package misc;

public class StockPrice {
    int max(int a, int b){
        return a>b?a:b;
    }
    int getStockProfitDay(int[] stocks){
        int maxStocks=stocks[stocks.length-1];
        int res = 0;
        for(int i=stocks.length-2;i>-1;i--){
            if(stocks[i]>maxStocks){
                maxStocks = stocks[i];
            }else{
                res=max(res, maxStocks-stocks[i]);
            }
        }
        return res;
    }
    public static void main(String[] args){
        int a[] = {4,1000,3,171};
        //int a[] = {8,9,7,8,7,5,4,1,8,3};
//        int a[] = {8,8,2,6,9,5,4,2};
        StockPrice stockPrice = new StockPrice();

        System.out.println( stockPrice.getStockProfitDay(a) );

    }
}
