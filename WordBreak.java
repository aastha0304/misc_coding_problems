import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordBreak {
	static void printMeaningfulSentence(String input, Set<String> dict){
		input = String.join("", input.split(" "));
		List<String> res = new LinkedList<>();
		//printMeaningfulHelper(input, dict, input.length(), new String(), res);
	    //System.out.println(res.isEmpty()?"Fails":"");
	    printMeaningfulDynamic(input, dict, input.length());
	}
	private static void printMeaningfulDynamic(String input, Set<String> dict, int length) {
		int[][] res = new int[length+1][length+1];
		for(int i=0;i<res.length;i++)
			for(int j=0;j<res[0].length;j++)
				res[i][j]=-1;
		res[0][0]=0;
		boolean fails = true;
		for(int i=1;i<length+1;i++) {
			if(dict.contains(input.substring(0, i))) {
				//if(i==length)
					//return true;
				res[0][i] = 0;
			}
		}
		for(int i=1;i<res.length;i++) {
			for(int j=i+1;j<res[0].length;j++) {
					if(dict.contains(input.substring(i, j))) {
							res[i][j]=1;
					}
					if(j==length) {
						fails = false;
					}
			}
		}
		//display(res);
		if(!fails) {
			List<String> result = new LinkedList<>();
			dfs(input, res, "", length);
		}else {
			System.out.println("fails");
		}
	}
	private static void dfs(String input, int[][] res, String result, int len) {
		// TODO Auto-generated method stub
		if(len<=0) {
			System.out.println(result);
			return;
		}
		for(int i=len;i>=0;i--) {
			if(res[i][len]!=-1) {
				dfs(input.substring(0,i),res,input.substring(i, len)+" "+result,i);
			}
		}
	}
	private static void display(int[][] wordMat) {
		for(int i=0;i<wordMat.length;i++) {
			for(int j=0;j<wordMat[0].length;j++)
				System.out.print(wordMat[i][j]+",");	
			System.out.println();
		}
	}
	private static void printMeaningfulHelper(String input, Set<String> dict, int end, 
			String result, List<String> res) {
		for(int i=1;i<end+1;i++) {
			String currentWord = input.substring(0, i);
			if(dict.contains(currentWord)) {
				if(i == end) {
					result += currentWord;
					System.out.print(result);
					System.out.println();
					res.add(result);
				} 
				printMeaningfulHelper(input.substring(i), dict, end-i, result+currentWord+" ", res);
			}
		}
	}
	private static void pop(List<String> result) {
		int lastOne = result.size();
		result.remove(lastOne-1);
	}
	static boolean isMeaningfulHelper(String s, Set<String> dict, int end){
		boolean res = false;
	    for(int i=1;i<end+1;i++){
	        String currentWord = s.substring(0, i);
	        if(dict.contains(currentWord))
	        {
	            if(i==end)
	                res = true;
	            else
	            	res = isMeaningfulHelper(s.substring(i), dict, end-i);
	        }
	    }
	    return res;
	}
	public static void main(String[] args) {
		String input = "ilikeicecreamandmango";
		Set<String> dict = new HashSet<>();
		String[] words = { "i", "like", "sam", "sung", "samsung", "mobile", "ice", 
				  "cream", "icecream", "man", "go", "mango", "arm", "is", "hurting", 
				  "cat", "cats", "sand", "and", "dog", "love"};
		dict.addAll(new HashSet<String>(Arrays.asList(words)));
		
		System.out.println(isMeaningful(input, dict));
		printMeaningfulSentence(input, dict);
	}
	private static boolean isMeaningful(String input, Set<String> dict) {
		input = String.join("", input.split(" "));
		return isMeaningfulDynamic(input, dict, input.length());
		//return isMeaningfulHelper(input, dict, input.length());
	}
	private static boolean isMeaningfulDynamic(String input, Set<String> dict, int length) {
		boolean[] res = new boolean[length+1];
		res[0] = true;
		for(int i=1;i<length+1;i++) {
			if(dict.contains(input.substring(0, i))) {
				if(i==length)
					return true;
				res[i] = true;
			}
			if(res[i]) {
				for(int j=i+1;j<length+1;j++) {
					if(dict.contains(input.substring(i, j))) {
						res[j]=true;
					}
					if(j==length && res[j])
						return true;
				}
			}
		}
		return res[length];
	}
	private static void display(boolean[] res) {
		System.out.println();
		for(boolean v: res) {
			System.out.print(v);
		}
		System.out.println();
	}
}
