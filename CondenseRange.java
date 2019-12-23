package misc;

public class CondenseRange {
    private String  update(StringBuilder sbr, Object newStr){
        sbr.append(newStr);
        return String.valueOf(newStr);
    }
    private String condense(String str){
        String[] elements = str.split(",");
        StringBuilder sbr = new StringBuilder();
        int toExpect;
        String[] range;
        int newNum, num2;
        String last ;
        if(!elements[0].contains("-")) {
            last = update(sbr, elements[0]);
            toExpect = Integer.parseInt(elements[0])+1;
        }
        else {
            range = elements[0].split("-");
            update(sbr, range[0]);
            last = update(sbr, "-");
            toExpect = Integer.parseInt(range[1])+1;
        }
        int i;
        for(i=1;i<elements.length-1;i++){
            if(!elements[i].contains("-")) {
                newNum = Integer.parseInt(elements[i]);
                if(newNum==toExpect) {
                    if(!last.equals("-"))
                        last = update(sbr, "-");
                }
                else{
                    if(!String.valueOf(toExpect-1).equals(last)) {
                        update(sbr, toExpect - 1);
                    }
                    update(sbr, ',');
                    last = update(sbr, newNum);
                }
                toExpect = newNum + 1;
            }
            else {
                range = elements[i].split("-");
                newNum = Integer.parseInt(range[0]);
                num2 = Integer.parseInt(range[1]);
                if(newNum==toExpect) {
                    if(!last.equals("-"))
                        last = update(sbr, "-");
                }
                else{

                    if(!String.valueOf(toExpect-1).equals(last))
                        update(sbr, toExpect-1);
                    update(sbr, ',');
                    update(sbr, newNum);
                    last = update(sbr, "-");

                }
                toExpect = num2+1;
            }
        }
        if(!elements[i].contains("-")) {
            newNum = Integer.parseInt(elements[i]);
            if(newNum==toExpect) {
                if(!last.equals("-"))
                    update(sbr, "-");
            }
            else{
                if(!String.valueOf(toExpect-1).equals(last)) {
                    update(sbr, toExpect - 1);
                }
                update(sbr, ',');

            }
            update(sbr, newNum);
        }
        else {
            range = elements[i].split("-");
            newNum = Integer.parseInt(range[0]);
            num2 = Integer.parseInt(range[1]);
            if(newNum==toExpect) {
                if(!last.equals("-"))
                    update(sbr, "-");
                update(sbr, num2);
            }
            else{

                if(!String.valueOf(toExpect-1).equals(last))
                    update(sbr, toExpect-1);
                update(sbr, ","+elements[i]);

            }
        }
        return sbr.toString();
    }
    public static void main(String[] args) {
        String range="1,2,3,5,6-9,10,11-18,22,23,24";
        CondenseRange condenseRange = new CondenseRange();
        System.out.print(condenseRange.condense(range));
    }

}
