package misc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PhoneStringPerms {
    void printPhoneStrings(String digits, Map<Character, String> phoneMap){
        if(digits.length()!=0) {
            List<String> result = printPhoneStringsUtil(digits.toCharArray(), 0, phoneMap);
            for (String oneString : result) {
                System.out.println(oneString);
            }
        }
    }

    List<String> printPhoneStringsUtil(char[] digits, int current, Map<Character, String> map ){
        if(current==digits.length-1){
            List<String> res = new LinkedList<>();
            String value = map.get(digits[current]);
            if(value!=null){
                for(char c: value.toCharArray()){
                    res.add(String.valueOf(c));
                    //res = {"d","e","f"}
                }
            }
            return res;
        }else{
            List<String> next = printPhoneStringsUtil(digits, current+1, map);

            String value = map.get(digits[current]);
            List<String> res = new LinkedList<>();
            if(value!=null){
                for(char c: value.toCharArray()){
                    StringBuilder oneResult = new StringBuilder();
                    for(String nextString: next){
                        oneResult.append(c);
                        oneResult.append(nextString);
                        res.add(oneResult.toString());
                        oneResult = new StringBuilder();
                    }
                }
            }
            return res;
        }
    }
    public static void main(String[] args) {

        Map<Character, String> phoneMap = new HashMap<>();
        phoneMap.put('1', "abc");
        phoneMap.put('2', "def");
        phoneMap.put('3', "ghi");

        String testInput = "123";
        PhoneStringPerms phoneStringPerms = new PhoneStringPerms();
        phoneStringPerms.printPhoneStrings(testInput, phoneMap);
    }
}
