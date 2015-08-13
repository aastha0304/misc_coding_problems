package hackerearth;

import java.lang.reflect.Array;
import java.util.LinkedList;
class Heaps<T extends Comparable<T>>{
	T[] harr;
	int capacity;
	int sz;
	@SuppressWarnings("unchecked")
	Heaps(int c){
		capacity=c;
	    harr = (T[]) new Comparable[capacity];
		sz=0;
	}
	int left(int i) {return (2*i+1);}
	int right(int i) {return (2*i+2);}
	int parent(int i) { return (i-1)/2; }
	void minheapify(int i){
		//print();
		int l = left(i);
		int r = right(i);
		int smll = i;
		if(l<sz && harr[l].compareTo(harr[i])==0){
			smll = l;
		}
		if(r<sz && harr[r].compareTo(harr[smll])==0){
			smll = r;
		}
		
		if(smll!=i){
			T t = harr[i];
			harr[i] = harr[smll];
			harr[smll] = t;
			minheapify(smll);
		}
	}
	void insertMin(T n){
		if(sz<capacity){
			sz++;
		    int i = sz - 1;
		    harr[i] = n;
		    while (i != 0 && harr[parent(i)].compareTo(harr[i])>0)
		    {
		       T t = harr[i];
		       harr[i] = harr[parent(i)];
		       harr[parent(i)] = t;
		       i = parent(i);
		    }
		}
	}
	void print(){
		for(int i =0;i<sz;i++){
			System.out.print(harr[i]+" ");
		}
		System.out.println();
	}
	void replaceMin(T n){
		harr[0] = n;
		minheapify(0);
		//print();
	}
	int toInsert(T n){
		if(sz<capacity)
			return 1;
		else if( n.compareTo(harr[0])>0)
			return 2;
		return 0;
	}
	T min(){
		return harr[0];
	}
}
public class TopKUnique {
	Heaps<Integer> hb;
	LinkedList<Integer> inheap;
	TopKUnique(int c){
		hb = new Heaps<Integer>(c);
		inheap = new LinkedList<Integer>();
	}
	void insertMin(int n){
		if(!inheap.contains(n)){
			if(hb.toInsert(n)==1){
				inheap.add(n);
				hb.insertMin(n);
			}
			else if(hb.toInsert(n)==2){
				inheap.add(n);
				inheap.remove(new Integer(hb.min()));
				hb.replaceMin(n);
			}
		}
	}
	void print(){
		hb.print();
	}
	public static void main(String[] args){
		int a[] = {89,78,7,5,4,10,83, 88, 26, 4, 9, 22, 7, 47, 35, 9, 23, 5, 882, 600, 19, 95, 44};
		int sz = 4;
		TopKUnique hb = new TopKUnique(sz);
		for(int i = 0;i<a.length;i++){
			hb.insertMin(a[i]);
			hb.print();
		}
	}
}