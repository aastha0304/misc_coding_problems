package misc;

import java.util.Scanner;

public class ReversePolish {
    static int top = -1;
    private static void stackStatus(char[] stack){
        System.out.println(" top is at " + top);
        System.out.println("Here come the stack elements ");
        for(int i=0;i<=top;i++){
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }
    private static void push(char[] orStack, char val){
        top += 1;
        orStack[top] = val;
    }
    private static char pop(char[] orStack){
        char val = orStack[top];
        top -= 1;
        return val;
    }
    private static String revPol(String s){

        char[] orStack = new char[s.length()];
        StringBuilder res = new StringBuilder();
        for(int i=0;i<s.length()-1;i++){
            switch(s.charAt(i)) {
                case '(' :  push(orStack, '(');
                    break;
                case '^' :  push(orStack, '^');
                    break;
                case '*' :  while(orStack[top] == '^'){
                                res.append(pop(orStack));
                            }
                            push(orStack, '*');
                    break;
                case '/' :  while(orStack[top] == '^'){
                                res.append(pop(orStack));
                            }
                            push(orStack, '/');
                    break;
                case '+' :  while(orStack[top] == '^' || orStack[top] == '*' || orStack[top] == '/'){
                                res.append(pop(orStack));
                            }
                            push(orStack, '+');
                    break;
                case '-' :  while(orStack[top] == '^' || orStack[top] == '*' || orStack[top] == '/'){
                                res.append(pop(orStack));
                            }
                            push(orStack, '-');
                    break;
                case ')' :  while(orStack[top] != '('){
                                res.append(pop(orStack));
                            }
                            pop(orStack);
                    break;

                default  :  res.append(s.charAt(i));
            }
        }
        while(top>=0){
            char rem = pop(orStack);
            if ( rem != '(' )
                res.append(rem);
        }
        return res.toString();

    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String pol = s.nextLine();
            System.out.println( revPol(pol) );
            t--;
        }
    }
}
