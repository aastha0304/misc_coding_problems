package misc;

import java.util.*;

public class FunWithArrays {
    private boolean overlapping(int[][] arr1, int arr1Idx, int[][] arr2, int arr2Idx){
        boolean nonOverlapping = arr1[arr1Idx][1]<arr2[arr2Idx][0] || arr2[arr2Idx][1]<arr1[arr1Idx][0] ;
        return !nonOverlapping;
    }
    private void mergeIntervals(int[][] a){
        Arrays.sort(a, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]<o2[0])
                    return -1;
                else if(o1[0]==o2[0] && o1[1]<o2[1])
                    return -1;
                else if(o1[0]==o2[0] && o1[1]==o2[1])
                    return 0;
                else
                    return 1;
            }
        });
        //Utils.display(a);
        int[][] res = new int[a.length][2];
        res[0][0] = a[0][0];
        res[0][1] = a[0][1];
        int resCurrentIdx = 0;
        for(int i=1;i<a.length;i++){
            if(overlapping(res, resCurrentIdx, a, i)){
                res[resCurrentIdx][0] = Math.min(res[resCurrentIdx][0], a[i][0]);
                res[resCurrentIdx][1] = Math.max(res[resCurrentIdx][1], a[i][1]);
            }else{
                resCurrentIdx++;
                res[resCurrentIdx][0] = a[i][0];
                res[resCurrentIdx][1] = a[i][1];
            }
        }
        for(int i=0;i<res.length;i++){
            if(res[i][0]==0 && res[i][1]==0)
                break;
            else{
                System.out.print(res[i][0]+" "+res[i][1]+" ");
            }
        }
        System.out.println();
        //Utils.display(res);
    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        FunWithArrays funWithArrays = new FunWithArrays();
        while( t!=0 ){
            int n=s.nextShort();
            int[][] a = new int[n][2];
            for(int i=0;i<n;i++){
                a[i][0] = s.nextShort();
                a[i][1] = s.nextShort();
            }
            funWithArrays.mergeIntervals(a);
//            String line = s.nextLine();
//
//            int n = Integer.parseInt( line.split(" ")[0]);
//            int k = Integer.parseInt( line.split(" ")[1]);
//            line = s.nextLine();
//            String[] elems = line.split(" ");
//            int[] a = new int[elems.length];
//            for(int i=0;i<a.length;i++)
//                a[i] = Integer.parseInt( elems[i] );
//
//            funWithArrays.getKMaxElementsHomeGrown(a,k);
            //System.out.println(funWithArrays.getRodCuttingResult(a));
            //System.out.println();
            t--;
        }
    }
    private int getRodCuttingResult(int[] a){
        int[] dp = new int[a.length+1];
        dp[0]=0;
        for(int i=1;i<=a.length;i++){
            for(int j=0;j<i;j++){
                dp[i] = Math.max(dp[i], a[j]+dp[i-j-1]);
            }
        }
        return dp[a.length];
    }
    private void getKMaxElementsHomeGrown(int[] a, int k){
        MinHeap<Integer> minHeap = new MinHeap<>(k);
        int i=0;
        while(i<k){
            minHeap.add(a[i]);
            i++;
        }
        while(i<a.length){
            if(a[i]>minHeap.peek()) {
                minHeap.pop();
                minHeap.add(a[i]);
            }
            i++;
        }
        while(!minHeap.isEmpty()){
            System.out.print(minHeap.peek()+" ");
            minHeap.pop();
        }
    }
    private void getKMaxElements(int[] a, int k){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int i=0;
        while(i<k){
            minHeap.add(a[i]);
            i++;
        }
        while(i<a.length){
            if(a[i]>minHeap.peek()) {
                minHeap.poll();
                minHeap.add(a[i]);
            }
            i++;
        }
        int[] stack=new int[k];
        int top=-1;
        while(!minHeap.isEmpty()){
            stack[++top]=minHeap.peek();
                    minHeap.poll();
        }
        while(top>=0){
            System.out.print(stack[top]+" ");
            top--;
        }
    }
    private int getpeakElement(int[] a) {
        if(a.length>1){

            for(int i=1;i<a.length-1;i++){
                if(a[i]>=a[i-1] && a[i]>=a[i+1])
                    return i;
            }
            if(a[a.length-1]>=a[a.length-2])
                return a.length-1;
            else
                return 0;
        }
        else {
            return 0;
        }
    }
}
