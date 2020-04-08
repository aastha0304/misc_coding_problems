import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class TrieNode{
    int size = 256;
    boolean isEnd;
    TrieNode[] arr;
    char c;

    TrieNode(char c){
        this.c = c;
        this.isEnd = false;
        this.arr = new TrieNode[size];
    }

}
class Trie{

    TrieNode root;

    Trie(){
        this.root = new TrieNode('\0');
    }

    public void insert(String word){
        TrieNode parent = this.root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            //System.out.println("inserting "+c);
            if(parent.arr[c]==null)
                parent.arr[c] = new TrieNode(c);
            parent = parent.arr[c];
            if(i==word.length()-1)
                parent.isEnd = true;
        }
    }

    public void display(){
        LinkedList<TrieNode> q = new LinkedList<>();
        q.addLast(root);
        TrieNode dummy = new TrieNode('\n');
        q.addLast(dummy);
        while(!q.isEmpty()){
            TrieNode parent = q.removeFirst();
            //System.out.print(parent.c);
            if(parent.c!='\n') {
                for (int i = 0; i < 256; i++) {
                    if (parent.arr[i] != null) {
                        System.out.print((char) (i) + " ");
                        q.add(parent.arr[i]);
                    }
                }
            }else{
                System.out.println();
                if( !q.isEmpty() && q.peekFirst().c!='\n')
                    q.add(dummy);
            }
        }
    }
}
public class WordBoggle {
    private String[] wordBoggle(char[][] a, String[] dict) {
        Trie trie = new Trie();
        Set<String> result = new HashSet<>();
        Map<String, Integer> words = new HashMap<>();

        boolean[][] visited = new boolean[a.length][a[0].length];
        for(String word: dict){
            if(words.containsKey(word))
                words.put(word, words.get(word)+1);
            else
                words.put(word, 1);
            trie.insert(word);
        }
        //trie.display();
        for(int i=0;i<a.length;i++) {
            for (int j = 0; j < a[i].length; j++) {
                //a word start
                if (trie.root.arr[a[i][j]] != null) {
                    trieDFS(a, i, j, trie.root.arr[a[i][j]], String.valueOf(a[i][j]), result, visited);
                }
            }
        }
        List<String> res = new ArrayList<>();
        for(String word: result){
            int count = words.get(word);
            while(count!=0){
                res.add(word);
                count--;
            }
        }
        String[] resArr = new String[res.size()];
        return res.toArray(resArr);
    }
    private void trieDFS(char[][] a, int i, int j, TrieNode node, String word, Set<String> result, boolean[][] visited){
        //System.out.println(word+" "+a[i][j]+" "+i+" "+j+" "+node.c);
//        if(node == null)
//            return;
        if(node.isEnd) {
            result.add(word);

        }
        visited[i][j] = true;
        for (int dx = -1; dx < 2; dx++) {
            for (int dy = -1; dy < 2; dy++) {
                int x1 = i + dx;
                int y1 = j + dy;
                if (!(x1==i && y1==j) && isSafe(x1, y1, a, visited) && node.arr[a[x1][y1]]!=null){
                    //System.out.println(a[x1][y1]+" "+x1+" "+y1+" "+node.arr[a[x1][y1]].c);
                    trieDFS(a, x1, y1, node.arr[a[x1][y1]], word+a[x1][y1], result, visited);

                }
            }
        }
        visited[i][j] = false;

    }
    private boolean isSafe(int i, int j, char[][] a, boolean[][]visited){
        return i>=0 && i<a.length && j>=0 && j<a[i].length && !visited[i][j];
    }

    private void dfs(char[][] a, int i, int j, boolean[][] visited,
                        String wordSoFar, String word,
                        int currentlen, int maxLen,
                        Set<String> result,
                        Set<String> dict, Set<String> checked){
        System.out.println(wordSoFar+" "+word+" "+a[i][j]+" "+i+" "+j+" "+currentlen+" "+maxLen);
        if(currentlen > word.length()) {
            //checked.add(word);
            return;
        }
        if(dict.contains(wordSoFar)) {
            result.add(wordSoFar);
            System.out.println(wordSoFar);
            checked.add(wordSoFar);
            //dict.remove(wordSoFar);
        }
        visited[i][j] = true;
        for (int dx = -1; dx < 2; dx++) {
            for (int dy = -1; dy < 2; dy++) {
                    int x1 = i + dx;
                    int y1 = j + dy;
                    if (!(x1==i && y1==j) && isSafe(x1, y1, a, visited)) {
                        dfs(a, x1, y1, visited, wordSoFar+a[x1][y1], word,
                                currentlen+1, maxLen,
                                result, dict, checked);
                    }
            }
        }
        visited[i][j] = false;
    }

    private String[] playByDFSSlow(char[][] a, String[] dict){
        Set<String> result = new HashSet<>();
        Set<String> words = new HashSet<>(Arrays.asList(dict));
        boolean[][] visited = new boolean[a.length][a[0].length];
        int maxLen = 0;
        for(String word: words){
            maxLen = Math.max(word.length(), maxLen);
        }
        Set<String> done = new HashSet<>() ;
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[i].length;j++){
                for(String word: words){
                    if(!done.contains(word) && a[i][j]==word.charAt(0)){
                        dfs(a, i, j, visited, String.valueOf(a[i][j]), word,1, maxLen, result, words, done);
                    }
                }
            }
        }
        String[] res = new String[result.size()];
        return result.toArray(res);
    }


    public static void main(String[] args) throws IOException {
        WordBoggle wordBoggle = new WordBoggle();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(input.readLine());
        for (int ix = 0; ix < testCases; ix++) {
            input.readLine();
            String[] array = input.readLine().split(" ");
            //Set<String> dict = new HashSet<>(Arrays.asList(array));
            int[] matrix = Arrays.stream(input.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            char[][] a = new char[matrix[0]][matrix[1]];
            int x = 0;
            for (int i = 0; i < matrix[0]; i++) {
                String[] line = input.readLine().split(" ");
                for (int j = 0; j < matrix[1]; j++) {
                    a[i][j] = line[j].charAt(0);
                }
            }
            //String[] words = new String[dict.size()];
            String[] res = wordBoggle.wordBoggle(a, array) ;

            if (res.length != 0) {
                for (String str : res) {
                    System.out.print(str + " ");
                }
                System.out.println();
            } else {
                System.out.println("-1");
            }
        }
    }
}
