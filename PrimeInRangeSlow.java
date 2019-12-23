package misc;

import java.lang.*;
import java.util.Scanner;

public class PrimeInRangeSlow
{

    private static void initialise(int[] arr){
        for(int i=2;i<arr.length;i++)
            arr[i] = 1;
    }

    private static void findPrimes(int start, int end){
        int sqrt = (int) Math.sqrt(end);
        int[] all = new int[sqrt+1];
        int[] primes = new int[sqrt+1];
        initialise(all);

        int primeCtr = 0;
        for(int i=2;i<=sqrt;i++){
            if(all[i] == 1) {
                primes[primeCtr++] = i;
               // primeCtr+=1;
            }
            for (int iMultiples = i+i;iMultiples <= sqrt;iMultiples+=i){
                all[iMultiples] = 0;
            }
        }

        int[] primesInRange = new int[end-start+2];
        int[] allInRange = new int[end-start+2];
        initialise(allInRange);
        for(int i=0;i<=end-start+1;i++){
            System.out.print(allInRange[i]+" ");
        }
        System.out.println();
        int primeCtrRange = 0;
        for(int i=0;i<primeCtr;i++){
            if(allInRange[primes[i]] == 1)
                primesInRange[primeCtrRange++] = primes[i];
            for (int iMultiples = primes[i]+primes[i];iMultiples <= sqrt;iMultiples+=primes[i]){
                allInRange[iMultiples] = 0;
            }
        }
        for(int i=0;i<primeCtrRange;i++){
            System.out.print(primesInRange[i]+" ");
        }
        System.out.println();
    }

    public static void main (String[] args)
    {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String rangeStr = s.nextLine();
            String[] rangeElems = rangeStr.split(" ");
            int start = Integer.parseInt( rangeElems[0] );
            int end = Integer.parseInt( rangeElems[1] );
            findPrimes(start, end);
            t--;
        }
    }
}