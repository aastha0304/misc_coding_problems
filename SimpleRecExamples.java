package hackerearth;

import java.util.ArrayList;

public class SimpleRecExamples {
	ArrayList<String> permsUtil(char[] a, int s, int e){
		ArrayList<String> r = new ArrayList<String>();
		if(s<e){
			for(int i=s;i<e;i++){
				
			}
		}
		return r;
	}
	void perms(String s){
		char[] a = s.toCharArray();
		ArrayList<String> res = new ArrayList();
		res = permsUtil(a, 0, a.length);
	}
	void subsets(int[] a){
		
	}
	public static void main(String[] args){
		SimpleRecExamples ob = new SimpleRecExamples();
		ob.perms("stuff");
		ob.subsets(new int[]{1,2,3,4,5});
	}
}
