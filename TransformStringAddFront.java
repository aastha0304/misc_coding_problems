import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class TransformStringAddFront {
    private int minXFormStepsBFSSlow(String a, String b){
        int count = 0;
        Set<String> tested = new HashSet<>();
        if(a.equals(b))
            return 0;
        else if(a.length()!=b.length())
            return -1;
        else{
            LinkedList<String> queue = new LinkedList<>();
            queue.addLast(a);
            queue.addLast("\n");
            while(!queue.isEmpty()){
                String str = queue.removeFirst();
                //System.out.println("current string "+str);

                //System.out.print(parent.c);
                if(str.equals(b))
                    return count;
                else if(!str.equals("\n")) {
                    tested.add(str);
                    for(int i=0;i<str.length();i++){
                        StringBuilder sbr = new StringBuilder();
                        String newStr = sbr.append((char)str.charAt(i)).append(str.substring(0, i)).append(str.substring(i+1)).toString();
                       // System.out.println("new string  "+newStr);
                        if(!tested.contains(newStr))
                            queue.addLast(newStr);
                    }
                }
                else{
                    if( !queue.isEmpty() && !queue.peekFirst().equals("\n")) {
                        queue.addLast("\n");
                        count ++;
                    }
                }
            }
        }
        return -1;
    }
    private int findLenLCS( char[] X, char[] Y )
    {
        int m = X.length, n=Y.length;
        int L[][] = new int[m+1][n+1];

    /* Following steps build L[m+1][n+1] in bottom up fashion. Note
         that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
        for (int i=0; i<=m; i++)
        {
            for (int j=0; j<=n; j++)
            {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (X[i-1] == Y[j-1])
                    L[i][j] = L[i-1][j-1] + 1;
                else
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
            }
        }
        return L[m][n];
    }
    private int minXFormStepsMemHogging(String a, String b){
        if(a.equals(b))
            return 0;
        else if(a.length()!=b.length())
            return -1;
        else{
            int lenOfLSC = findLenLCS(a.toCharArray(), b.toCharArray());
            return a.length()-lenOfLSC;
        }
    }
    private int minXFormSteps(String a, String b){
        if(a.equals(b))
            return 0;
        else if(a.length()!=b.length())
            return -1;
        int count [] = new int [256];
        for(int i = 0; i < a.length(); i++)
        {
            count[a.charAt(i)]++;
            count[b.charAt(i)]--;
        }
        for(int i = 0; i < 256; i++)
            if(count[i] != 0)
                return -1;
        int i=a.length()-1, j=a.length()-1,res=0;
        while(i>=0 && j>=0){
            if(a.charAt(i)!=b.charAt(j))
                res++;
            else
                j--;
            i--;
        }
        return res;
    }
    public static void main(String[] args) throws IOException {
        TransformStringAddFront transformStringAddFront = new TransformStringAddFront();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(input.readLine());
        for (int ix = 0; ix < testCases; ix++) {
            String[] array = input.readLine().trim().split(" ");
            if(array.length==0 || array.length==1)
                System.out.println(-1);
            else {
                String a = array[0];
                String b = array[1];
                System.out.println(transformStringAddFront.minXFormSteps(a, b));
            }
        }
    }
}
