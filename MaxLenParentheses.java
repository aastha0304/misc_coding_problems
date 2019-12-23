package misc;

import java.util.Scanner;

class Tuple{
    char type;
    int dimension;
    Tuple(char typ, int dime){
        type = typ;
        dimension = dime;
    }
}
public class MaxLenParentheses {
    static int top=-1;
    private void push(Tuple[] stack, Tuple tuple){
        top+=1;
        stack[top] = tuple;
    }
    private Tuple peek(Tuple[] stack){
        return stack[top];
    }
    private Tuple pop(Tuple[] stack){
        Tuple lastTop = stack[top];
        top-=1;
        return lastTop;
    }
    private boolean isEmpty(Tuple[] stack){
        return top==-1;
    }
    private void reset(){
        top = -1;
    }
    private int getmaxLenParentheses(String line){
        int res=0;
        Tuple[] stack = new Tuple[line.length()];
        Tuple tuple;
        reset();
        for(int i=0;i<line.length();i++){
            if(line.charAt(i)=='('){
                tuple = new Tuple('(', i);
                push(stack, tuple);
            }else{ // char is ')
                while(!isEmpty(stack) && stack[top].type!='('){
                    pop(stack);
                }
                if(!isEmpty(stack)){
                    //valid brace string starting at '(' corresponding to current ')'
                    int validSubstring = i-stack[top].dimension+1;
                    pop(stack);
                    if(!isEmpty(stack)){ //hoping just current top will continue
                        if(stack[top].type=='l'){
                            validSubstring += stack[top].dimension;
                            pop(stack);
                        }
                    }
                    tuple = new Tuple('l', validSubstring);
                    push(stack, tuple);
                    res = Math.max(res, validSubstring);
                }else{
                    //nothing valid for this ')'
                }
            }
        }

        return res;
    }

    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String line = s.nextLine();
            MaxLenParentheses maxLenParentheses = new MaxLenParentheses();
            System.out.println( maxLenParentheses.getmaxLenParentheses(line) );
            t--;
        }
    }
}
