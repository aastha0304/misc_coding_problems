package hackerearth;

import java.util.ArrayList;

public class LCS {
	int max(int a, int b){
		return a>b?a:b;
	}
	int lcs(char[] aa, char[] ba){
		int[][] arr = new int[aa.length+1][ba.length+1];
		
		for(int i = 0;i<=aa.length;i++){
			for(int j = 0;j<=ba.length;j++){
				if(i==0||j==0){
					arr[i][j]=0;
				}
				else if(aa[i-1]==ba[j-1]){
					arr[i][j] = 1+arr[i-1][j-1];
				}else{
					arr[i][j] = max(arr[i-1][j], arr[i][j-1]);
				}
			}
		}
		int i = aa.length, j = ba.length;
		ArrayList<Character> res = new ArrayList<Character>();
		while(i>0 && j>0){
			if(aa[i-1]==ba[j-1]){
				res.add(aa[i-1]);
				i--;
				j--;
			}else if(arr[i][j-1]>arr[i-1][j]){
				j--;
			}else{
				i--;
			}
		}
		System.out.println(res.toString());
		return arr[aa.length][ba.length];
	}
	public static void main(String[] args){
		String a = "tttfittttsfisstfissstfist";
		String b = "tfits";
		LCS lOb = new LCS();
		System.out.println(lOb.lcs(a.toCharArray(), b.toCharArray()));
	}
}
