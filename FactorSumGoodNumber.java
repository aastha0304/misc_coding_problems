import java.util.Scanner;

public class FactorSumGoodNumber {
    static int MAX = 10000001;
    static long[] sumFactors = new long[MAX];

    static int factor[] = new int[MAX];
    static void generatePrimeFactors()
    {
        factor[1] = 1;
        for (int i = 2; i < MAX; i++)
            factor[i] = i;
        for (int i = 4; i < MAX; i += 2)
            factor[i] = 2;

        for (int i = 3; i * i < MAX; i++) {

            if (factor[i] == i) {
                for (int j = i * i; j < MAX; j += i) {

                    if (factor[j] == j)
                        factor[j] = i;
                }
            }
        }
    }//1+2+2+3+2+4+2+4+3
    static long getSumFactorsTillN(int n){
        for(int i=1;;i++){
            factor[i] = calculateNoOFactors(i);
            sumFactors[i] = factor[i]+sumFactors[i-1];
            System.out.println(i+" "+factor[i]+" "+sumFactors[i]);
            if(sumFactors[i]>=n)
                return i;
        }

    }
    static int calculateNoOFactors(int n)
    {
        if (n == 1)
            return 1;

        int ans = 1;

        long dup = factor[n];

        int c = 1;
        int j = n / factor[n];

        while (j != 1) {
            if (factor[j] == dup)
                c += 1;
            else {
                dup = factor[j];
                ans = ans * (c + 1);
                c = 1;
            }
            j = j / factor[j];
        }
        ans = ans * (c + 1);

        return ans;
    }
    private static void fillFactors(int n){
        for(int i=1;i<=n;i++){
            if(factor[i]==0)
                factor[i] = calculateNoOFactors(i);
            sumFactors[i] = factor[i]+sumFactors[i-1];
        }
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt(s.nextLine());
        generatePrimeFactors();
        while (t != 0) {
            String line = s.nextLine();
            int n = Integer.parseInt(line);
            sumFactors[0] = 0;
            factor[0] = 0;
            System.out.println(getSumFactorsTillN(n));
            t--;
        }
    }
}
