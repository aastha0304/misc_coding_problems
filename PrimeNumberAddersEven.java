import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrimeNumberAddersEven {
    private boolean isPrime(int n){
        if(n==2)
            return true;
        int till = (int) Math.sqrt(n)+1;
        for(int i=2;i<=till;i++){
            if(n%i==0)
                return false;
        }
        return true;
    }
    private void findPrimes(int n){
        int till = (int) Math.sqrt(n)+1;
        for(int i=2;i<=till;i++){
            int num1 = i;
            int num2 = n-num1;
            if(isPrime(num1) && isPrime(num2)) {
                System.out.println(num1 + " " + num2);
                break;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        PrimeNumberAddersEven primeNumberAddersEven = new PrimeNumberAddersEven();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(input.readLine());
        for (int ix = 0; ix < testCases; ix++) {
            int n = Integer.parseInt(input.readLine());
            primeNumberAddersEven.findPrimes(n);
        }
    }
}
