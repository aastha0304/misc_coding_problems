package misc;

import java.util.*;
import java.lang.*;
import java.util.Scanner;

public class LifeUEverything
{
    public static void main (String[] args) throws java.lang.Exception
    {
        List<Integer> arr = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        while ( s.hasNextLine() ) {
            try {
                arr.add(Integer.parseInt(s.nextLine()));
            }catch(Exception e){
                break;
            }
        }

        int i=0, num;
        while( (num = arr.get(i++) ) != 42){
            System.out.println(num);
        }
    }
}