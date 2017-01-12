package hackerearth;
import java.util.LinkedList;
import java.util.Random;
class CpuProps implements Comparable<CpuProps>{
	int rom;
	int ram;
	int proc;
	int blife;
	CpuProps(int a, int b, int c, int d){
		rom = a;
		ram = b;
		proc = c;
		blife = d;
	}
	@Override
	public int compareTo(CpuProps c) {
		// TODO Auto-generated method stub
		if(this.rom>c.rom)
			return 1;
		else if(this.rom<c.rom)
			return 0;
		else if(this.ram>c.ram)
			return 1;
		else if(this.ram<c.ram)
			return 0;
		else if(this.proc>c.proc)
			return 1;
		else if(this.proc<c.proc)
			return 0;
		else 
			return this.blife>c.blife?1:0;
	}
}
public class TopK {
	Heaps<CpuProps> hb;
	LinkedList<CpuProps> inheap;
	TopK(int c){
		hb = new Heaps<CpuProps>(c);
		inheap = new LinkedList<CpuProps>();
	}
	void insertMin(CpuProps n){
		if(!inheap.contains(n)){
			if(hb.toInsert(n)==1){
				inheap.add(n);
				hb.insertMin(n);
			}
			else if(hb.toInsert(n)==2){
				inheap.add(n);
				CpuProps rm = hb.min();
				inheap.remove(new CpuProps(rm.rom, rm.ram, rm.proc, rm.blife));
				hb.replaceMin(n);
			}
		}
	}
	void print(){
		for(Comparable ob: hb.harr){
			CpuProps cop = (CpuProps) ob;
			if(cop!=null){
				System.out.print(cop.rom + " " + cop.ram + " " + cop.proc + " "+cop.blife + '\t');
			}
		}
		System.out.println();
	}
	public static void main(String[] args){
			int n = 10;
			int range = 100;
			CpuProps[] ob = new CpuProps[n];
			Random ran = new Random();
			int hs = 4;
			TopK tob = new TopK(hs);
			for(int i =0;i<n;i++){
				ob[i] = new CpuProps(1 + ran.nextInt(range), 1 + ran.nextInt(range), 1 + ran.nextInt(range), 1 + ran.nextInt(range));
				tob.insertMin(ob[i]);
				tob.print();
			}
	}
}
