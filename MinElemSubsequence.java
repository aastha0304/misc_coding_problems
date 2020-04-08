import java.util.*;

public class MinElemSubsequence {
    private int binSearch(int[] idxArr, int[] a, int l, int h, int num){
        while(l+1<h){
            int m = l+ (h-l)/2;
            if(num < a[idxArr[m]])
                h = m;
            else if(num > a[idxArr[m]])
                l = m;
            //already there
            else
                return -1;
        }
        return h;
    }
    private int getLDSDiff(int[] a, List<Integer> lis){
        int[] ldsArr = new int[a.length];
        Arrays.fill(ldsArr, -1);
        int len = 1, idx = a.length-1;
        Set<Integer> lisIdx = new HashSet<>(lis);
        while(lisIdx.contains(idx))
            idx--;
        ldsArr[0] = idx;
        while(idx>-1){
            if(!lisIdx.contains(idx)) {
                if (a[idx] < a[ldsArr[0]])
                    ldsArr[0] = idx;
                else if (a[idx] > a[ldsArr[len - 1]])
                    ldsArr[len++] = idx;
                else{
                    int middleIdx = binSearch(ldsArr, a, -1, len - 1, a[idx]);
                    if(middleIdx!=-1)
                        ldsArr[middleIdx] = idx;
                }
            }
            idx--;
        }
        return (a.length-lisIdx.size()-len);
    }
    private void getElemNotPartOfSubSeq(int[] a){
        ArrayList<LinkedList<Integer>>[] dp = new ArrayList[a.length];
        dp[0] = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        list.addLast(0);
        dp[0].add(list);
        int maxSize = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=1;i<a.length;i++){
            dp[i] = new ArrayList<>();
            for(int j=0;j<i;j++){
                //lis
                if(a[i]>a[j] ){
                    //all are smaller size
                    if(dp[i].isEmpty() || dp[i].get(0).size() <= dp[j].get(0).size()) {
                        dp[i] = new ArrayList<>();
                        for (LinkedList<Integer> item : dp[j]) {
                            LinkedList<Integer> newList = new LinkedList<>();
                            newList.addAll(item);
                            newList.addLast(i);
                            dp[i].add(newList);
                        }
                    }
                    //encountered something like 1,2,4,3,5; at 5 need 1,2,4,5 abd 1,2,3,5
                    else if(dp[j].get(0).size() == dp[i].get(0).size()-1){
                        for (LinkedList<Integer> item : dp[j]) {
                            LinkedList<Integer> newList = new LinkedList<>();
                            newList.addAll(item);
                            newList.addLast(i);
                            dp[i].add(newList);
                        }
                    }
                }
            }
            if(dp[i].isEmpty()){
                list = new LinkedList<>();
                list.addLast(i);
                dp[i].add(list);
            }
            maxSize = Math.max(dp[i].get(0).size(), maxSize);
        }
        for(ArrayList<LinkedList<Integer>> arrayList: dp) {
            for (LinkedList<Integer> linkedList : arrayList) {
                if (linkedList.size() == maxSize) {
                    int diff = getLDSDiff(a, linkedList);
                    if(diff==0) {
                        System.out.println(min==Integer.MAX_VALUE?0:min);
                        return;
                    }
                    min = Math.min( diff, min);
                }
            }
        }
        System.out.println(min==Integer.MAX_VALUE?0:min);
    }

    public static void main (String[] args) {
        MinElemSubsequence minElemSubsequence = new MinElemSubsequence();
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while( t!=0 ){
            int n = s.nextInt();
            int[] a = new int[n];
            for(int i=0;i<n;i++)
                a[i] = s.nextInt();
            minElemSubsequence.getElemNotPartOfSubSeq(a);
            t--;
        }
    }
}
