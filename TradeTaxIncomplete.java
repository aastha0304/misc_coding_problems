package misc;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



public class TradeTaxIncomplete {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int tradesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> trades = IntStream.range(0, tradesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        String result = TradeTaxIncomplete.calculateTax(trades);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
    public static String calculateTax(List<String> trades) {
        int currentShares = 0;
        List<Integer> purchases = new LinkedList<>();
        Map<Integer, Double> prices = new HashMap<>();
        String prevYr="0";
        // Write your code here
        double txn=0;
        for(String s:trades){
            String[] event = s.split(",");
            String date = event[0];
            String ticker = event[1];
            char type = event[2].charAt(0);
            int qty = Integer.parseInt(event[3]);

            if(prevYr.equals("0")||!date.split("-")[0].equals(prevYr)){
                txn = 0;
            }
            double price = Double.parseDouble(event[4]);
            prices.put(qty, price);
            if(type=='B'){
                if(currentShares<0){
                   // while(!purchases.isEmpty() && )
                }
                currentShares += qty;

            }else{
                //tax
                //no short-sell
                if(qty<=currentShares){
                    int toSell = 0;
                    int old;
                    while(!purchases.isEmpty() && toSell<=qty){
                        toSell = ((LinkedList<Integer>) purchases).getFirst();
                        if(toSell<=qty){
                            toSell = ((LinkedList<Integer>) purchases).removeFirst();
                            txn += (price-prices.get(toSell))*toSell;
                        }else{
                            toSell = qty;
                            old = ((LinkedList<Integer>) purchases).removeFirst();
                            ((LinkedList<Integer>) purchases).addFirst(old-toSell);
                            double cost=prices.get(old);
                            txn+= (price-cost)*toSell;
                            prices.remove(old);
                            prices.put(old-toSell, cost);
                        }
                    }
                }else{
                    //short-sell
                    while(!purchases.isEmpty()){
                        int ifAny = ((LinkedList<Integer>) purchases).removeFirst();
                        txn += (price-prices.get(ifAny))*ifAny;
                    }
                }
                currentShares -= qty;
            }
            prevYr = date.split("-")[0];
        }
        if(txn>0)
            return "$"+String.valueOf(0.25*txn);
        else
            return "$0.00";
    }

}
