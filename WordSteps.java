package misc;

import java.util.*;

public class WordSteps {
    int diff(String word1, String word2){
        int res=0;
        for(int i=0;i<word1.length();i++){
            if(word1.charAt(i)!=word2.charAt(i))
                res++;
        }
        return res;
    }
    Set<String> findNeighbors(String word, Set<String> checked, Set<String> words){
        Set<String> res = new HashSet<>();
        for(String aWord: words){
            if(!checked.contains(aWord)){
                //implement
                if(diff(word, aWord)==1)
                    res.add(aWord);
            }
        }
        return res;
    }
    int getStepCount(String starting, String ending, Set<String> words){
        LinkedList<String> bfs=new LinkedList<>();
        Set<String> checked = new HashSet<>();
        if(starting.equals(ending))
            return 0;
        int res=1;
        bfs.addLast(starting);
        //assuming delim is not a dictionary word
        bfs.addLast("delim");
        while(!bfs.isEmpty()){
            String currentWord=bfs.removeFirst();
            if(currentWord.equals("delim")){
                res++;
                bfs.addLast("delim");
            }else{
                if(currentWord.equals(ending))
                    return res;
                else{
                    if(!checked.contains(currentWord)) {
                        checked.add(currentWord);
                        Set<String> neighbors = findNeighbors(currentWord, checked, words);
                        for (String neighbor : neighbors) {
                            bfs.addLast(neighbor);
                        }
                    }
                }
            }
        }
        return res;
    }
    public static void main(String[] args){
        Set<String> words = new HashSet<>();
        words.add("TOON");
        words.add("POON");
        words.add("PLEE");
        words.add("SAME");
        words.add("POIE");
        words.add("PLIE");
        words.add("POIN");
        words.add("PLEA");

        String starting="TOON";
        String ending="TOON";
        WordSteps wordSteps = new WordSteps();
        System.out.println(wordSteps.getStepCount(starting, ending, words));
    }
}
