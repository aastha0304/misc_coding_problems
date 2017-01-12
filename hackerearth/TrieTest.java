package hackerearth;
class TrieNode{
	int v;
	TrieNode[] children;
}
class Trie{
	TrieNode root;
	TrieNode getNode(){
		TrieNode nd = new TrieNode();
		nd.v=0;
		nd.children = new TrieNode[26];
		for(int i=0;i<26;i++){
			nd.children[i] = null;
		}
		return nd;
	}
	Trie(){
		root=getNode();
	}
	void insert(char[] s){
		TrieNode tn = root;
		for(int i =0;i<s.length;i++){
			if(tn.children[s[i]-'a']==null)
				tn.children[s[i]-'a']=getNode();
			tn=tn.children[s[i]-'a'];
		}
		tn.v = 1;
	}
	void print(TrieNode t, int level){
		if(t==null)
			return;
		//System.out.print(root.v);
		for(int i =0;i<t.children.length;i++){
			if(t.children[i]!=null){
				System.out.println((char)(i+'a')+" "+level+" "+t.children[i].v);
				print(t.children[i], level+1);
			}
		}
	}
	void print(){
		print(root, 0);
	}
	int searchUtil(char[] s){
		System.out.println(s);
		TrieNode tn = root;
		int lvl = 0;
		while(lvl<s.length && tn.children[s[lvl]-'a']!=null){
			tn=tn.children[s[lvl]-'a'];
			lvl++;
		}
		if(lvl==s.length && tn.v==1)
			return 1;
		else
			return 0;
	}
	int search(String s){
		return searchUtil(s.toCharArray());
	}
}
public class TrieTest {
	public static void main(String[] args){
		Trie root = new Trie();
		char[][] a = {"the".toCharArray(),"a".toCharArray(),"there".toCharArray(),"answer".toCharArray(),"any".toCharArray(),"by".toCharArray(),"bye".toCharArray(), "their".toCharArray()};
		for(int i = 0;i<a.length;i++){
			root.insert(a[i]);
		}
		root.print();
		System.out.println(root.search("the")+" the");
		System.out.println(root.search("there")+" there");
		System.out.println(root.search("an")+" an");
		System.out.println(root.search("and")+" and");
	}
}
