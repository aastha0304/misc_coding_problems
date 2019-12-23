package misc;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;
import java.util.Objects;

class AnimalPair {
    static AnimalPair ap;
    int a;
    int b;
    private AnimalPair(int a, int b){
        this.a=a;
        this.b=b;
    }
    private AnimalPair(){

    }
    public static AnimalPair getAnimalPair(int x, int y){
        if(ap==null)
            ap=new AnimalPair(x, y);
        else{
            ap.a = x;
            ap.b = y;
        }
        return ap;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!AnimalPair.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final AnimalPair other = (AnimalPair) obj;
        return this.a==other.a && this.b==other.b;
    }
    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
//create groups so that some specified elements dont occur together
public class AngryAnimals {
    public static long angryAnimals(int n, List<Integer> a, List<Integer> b) {

        Set<Set<Integer>> groups = new HashSet<>();
        /* create groups of all sizes */
        for(int size=1;size<=n;size++){

            for(int j=1;j+size-1<=n;j++){
                Set<Integer>  set = new HashSet<>();
                set.add(j);
                for(int k=j+1;k-j+1<=size;k++){
                    set.add(k);
                }
                boolean isSafe = true;
                for(int i=0;i<a.size();i++){
                    if(set.contains(a.get(i)) && set.contains(b.get(i))){
                        isSafe = false;
                        break;
                    }
                }
                if(isSafe) {
//                    for(int k:set){
//                        System.out.print(k+" ");
//                    }
//                    System.out.println();
                    groups.add(set);
                }
            }
        }
        return groups.size();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int aCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = IntStream.range(0, aCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int bCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> b = IntStream.range(0, bCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        long result = AngryAnimals.angryAnimalsFast(n, a, b);
                //AngryAnimalsSlow.angryAnimals(n, a, b);
        System.out.println(result);
        //bufferedWriter.write(String.valueOf(result));
       // bufferedWriter.newLine();

        bufferedReader.close();
        //bufferedWriter.close();
    }

    private static long angryAnimalsFast(int n, List<Integer> a, List<Integer> b) {
        long dontGroup = 0;
        Set<AnimalPair> checkedAnimals = new HashSet<>();
        for(int i=0;i<a.size();i++){
            int x = a.get(i);
            int y = b.get(i);
            dontGroup += getGroupsTogether(n, x, y, checkedAnimals);

            System.out.println("groups with "+x+" and "+y+" are "+dontGroup);
        }
        return (n*(n+1)/2)-dontGroup;
    }

    private static long getGroupsTogether(int n, int a, int b, Set<AnimalPair> checked){
        int res = 0;
        int range = b-a+1;
        while(range<=n){
            for(int i=1;i<=a && i+range-1<=n;i++){
                AnimalPair ap=AnimalPair.getAnimalPair(i, i+range-1);
                if(!checked.contains(ap)){
                    if(contains(i, i+range-1, a) && contains(i, i+range-1, b)) {
                        System.out.println(i+" "+(i+range-1)+" contain "+a+" and "+b);
                        res++;
                    }
                    checked.add(ap);
                }
            }
            range++;
        }
        return res;
    }

    private static boolean contains(int start, int end, int num){
        return start<=num&&num<=end;
    }
}
