package hackerearth;

import java.util.Deque;
import java.util.LinkedList;

class Node{
	int a;
	Node next;
	Node child;
	Node(int n){
		a=n;
		next = child = null;
	}
}
class LList{
	Node head;
	void insert(int n){
		Node nnode = new Node(n);
		if(head==null)
			head = nnode;
		else{
			nnode.next = head;
			head = nnode;
		}
	}
	void print(Node p){
		if(p==null)
			return;
		System.out.print(p.a+" ");
		print(p.next);
		print(p.child);
	}
	void print(){
		print(head);
	}
}
public class ListTwist {
	LList createList(int[] a){
		int sz = a.length;
		LList ls = new LList();
		for(int i=0;i<sz;i++){
			ls.insert(a[i]);
		}
		return ls;
	}
	void printLevel(Node ls){
		Deque q = new LinkedList<Node>();
		while(ls!=null){
			System.out.print(ls.a+" ");
			if(ls.child!=null)
			{
				q.add(ls.child);
			}
			ls=ls.next;
		}
		q.add(new Node(-1));
		while(!q.isEmpty()){
			Node s = (Node) q.pop();
			if(s.a==-1){
				Node r = (Node) q.peek();
				if(r!=null)
					q.add(new Node(-1));
				System.out.println();
			}else{
			while(s!=null){
				System.out.print(s.a+" ");
				if(s.child!=null)
				{
					q.add(s.child);
				}
				s=s.next;
			}}
		}
		return;
	}
	void flatten(Node ls){
		if(ls==null)
			return;
		Deque q = new LinkedList<Node>();
		Node tail=ls;
		while(tail.next!=null){
			if(tail.child!=null){
				q.add(tail.child);
				tail.child=null;
			}
			tail=tail.next;
		}
		while(!q.isEmpty()){
			Node s = (Node)q.pop();
			tail.next=s;
			while(tail.next!=null){
				if(tail.child!=null){
					q.add(tail.child);
					tail.child=null;
				}
				tail=tail.next;
			}
		}
	}
	public static void main(String[] args){
		ListTwist lob = new ListTwist();
		int arr1[] = {10, 5, 12, 7, 11};
	    int arr2[] = {4, 20, 13};
	    int arr3[] = {17, 6};
	    int arr4[] = {9, 8};
	    int arr5[] = {19, 15};
	    int arr6[] = {2};
	    int arr7[] = {16};
	    int arr8[] = {3};
	    int n = 8;
		LList la[] = new LList[n];
		la[0]=lob.createList(arr1);
		la[1]=lob.createList(arr2);
		la[2]=lob.createList(arr3);
		la[3]=lob.createList(arr4);
		la[4]=lob.createList(arr5);
		la[5]=lob.createList(arr6);
		la[6]=lob.createList(arr7);
		la[7]=lob.createList(arr8);
		la[0].head.next.child = la[1].head;
	    la[0].head.next.next.next.child = la[2].head;
	    la[2].head.child = la[3].head;
	    la[3].head.child = la[4].head;
	    la[1].head.next.child = la[5].head;
	    la[1].head.next.next.child = la[6].head;
	    la[6].head.child = la[7].head;
		lob.printLevel(la[0].head);
	    lob.flatten(la[0].head);
	    lob.printLevel(la[0].head);
	}
}
