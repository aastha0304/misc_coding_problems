package misc;

public class Sum2Arrays {

    void display(int[] a){
        for(int i=0;i<a.length;i++)
            System.out.print(a[i]+"\t");
        System.out.println();
    }
    int[] fillUp(int[] a, int len, int val){
        int [] res = new int[len];
        int i;
        for(i=0;i<len-a.length;i++)
            res[i]=val;
        for(int j=0;i<len;j++) {
            res[i] = a[j];
            i++;
        }
        return res;
    }
    int[] sum2ArraysUtil(int[] larger, int[] smaller){

        int res[] = new int[larger.length];
        //fill up/substitute smaller
        int[] padded = fillUp(smaller, larger.length,0);
        int c=0;
        for(int i=larger.length-1;i>=0;i--){
            res[i] = (larger[i]+padded[i]+c)%10;
            c = (larger[i]+padded[i]+c)/10;
        }
        if(c==1){
            return fillUp(res, res.length+1, 1);
        }
        return res;
    }
    int[] sum2Arrays(int[] a, int[] b){
        if(a.length>b.length)
            return sum2ArraysUtil(a,b);
        return sum2ArraysUtil(b,a);
    }
    public static void main(String[] args){
        int a[] = {9,9};
        int b[] = {9,9,9,9};
//        int a[] = {1,2,3};
//        int b[] = {4};//7
        Sum2Arrays sum2Arrays = new Sum2Arrays();

        int[] result = sum2Arrays.sum2Arrays(a,b);
        sum2Arrays.display(result);
    }
}