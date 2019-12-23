package misc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class ListAddition {

    private LinkedList<Integer> add(LinkedList<Integer> num1, LinkedList<Integer> num2){
        LinkedList<Integer> res ;
        if(num1.size() >= num2.size())
            res = addUtil(num1, num2);
        else res = addUtil(num2, num1);
        return res;
    }
    private LinkedList<Integer> addUtil(LinkedList<Integer> larger, LinkedList<Integer> smaller){

        while(smaller.size() < larger.size()){
            smaller.addFirst(0);
        }
        LinkedList<Integer> res = new LinkedList<>();

        LinkedList<Integer> reversed1 = larger;
        Collections.reverse(reversed1);
        LinkedList<Integer> reversed2 = smaller;
        Collections.reverse(reversed2);

        int carry = 0;
        int modSum = 0;
        for(int i=0;i<reversed1.size();i++){
            modSum = (reversed1.get(i)+reversed2.get(i)+carry)%10;
            carry = (reversed1.get(i)+reversed2.get(i)+carry)/10;
            res.addFirst(modSum);
        }
        if(carry!=0)
            res.addFirst(carry);

        return res;
    }
    public static void main(String[] args) {

        LinkedList<Integer> num1 = new LinkedList<>();
        LinkedList<Integer> num2 = new LinkedList<>();
        num1.add(1)    ;
        num1.add(9)    ;
        num1.add(9);
        num1.add(1);

        num2.add(5)    ;
        num2.add(1)    ;
        num2.add(1)    ;
        num2.add(6)    ;
        num2.add(7)    ;
        //num2.add(8)    ;    



        ListAddition solution = new ListAddition();
        List<Integer> res = solution.add(num1, num2);
        for(int c: res){
            System.out.print(c);
        }
        System.out.println();
    }
}