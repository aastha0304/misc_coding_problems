package hackerearth;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

class mAryNode{
	mAryNode papa;
	ArrayList <mAryNode> bachche;
	boolean locked;
	int lockedBachche;
	int val;
	mAryNode getNode(int v){
		mAryNode root = new mAryNode();
		root.val = v;
		root.papa = null;
		root.bachche = new ArrayList<mAryNode>();
		root.locked = false;
		root.lockedBachche = 0;
		return root;
	}
	void insert(mAryNode c){
		this.bachche.add(c);
		c.papa = this;
	}
	void preorder(){
		if(this==null)
			return;
		System.out.println(this.val);
		for(mAryNode chld : this.bachche){
			chld.preorder();
		}
	}
	boolean lock(){
		if(this.locked==false && this.lockedBachche==0 && !this.lockedPapaLog()){
			this.updatePapaLog(true);
			this.locked = true;
			return true;
		}
		return false;
	}
	boolean unlock(){
		if(this.locked){
			this.locked = false;
			this.updatePapaLog(false);
			return true;
		}
		return false;
	}
	boolean lockedPapaLog(){
		mAryNode c = this.papa;
		while(c!=null){
			if(c.locked)
				return true;
			c = c.papa;
		}
		return false;
	}
	void updatePapaLog(boolean inc){
		mAryNode c = this.papa;
		while(c!=null){
			if(inc)
				c.lockedBachche++;
			else
				c.lockedBachche--;
			c = c.papa;
		}
	}
	void levelorder(){
		Deque<mAryNode> q = new LinkedList<mAryNode>();
		q.add(this);
		q.add(getNode(-1));
		while(!q.isEmpty()){
			mAryNode n = q.getFirst();
			
			q.pop();
			if(n.val==-1){
				if(q.isEmpty())
					break;
				else{
					System.out.println();
					q.add(getNode(-1));
				}
			}else{
				System.out.print(n.val+" ");
				for(mAryNode chld : n.bachche){
					q.add(chld);
				}
			}
		}
	}
}
class mTree{
	mAryNode root;
	mTree(){
		mAryNode ob = new mAryNode();
		root = ob.getNode(0);
	}
	void insert(mAryNode p, mAryNode c){
		p.insert(c);
	}
	void traverse(){
		root.levelorder();
	}
	void createTree(){
		mAryNode ob = new mAryNode();
		mAryNode tmp1 = ob.getNode(1);
		mAryNode tmp2 = ob.getNode(2);
		mAryNode tmp3 = ob.getNode(3);
		mAryNode tmp4 = ob.getNode(4);
		mAryNode tmp5 = ob.getNode(5);
		mAryNode tmp6 = ob.getNode(6);
		mAryNode tmp7 = ob.getNode(7);
		mAryNode tmp8 = ob.getNode(8);
		mAryNode tmp9 = ob.getNode(9);
		mAryNode tmp10 = ob.getNode(10);
		mAryNode tmp11 = ob.getNode(11);
		insert(root, tmp1);
		insert(root, tmp4);
		insert(root, tmp5);
		insert(root, tmp7);
		insert(tmp1, tmp3);
		insert(tmp1, tmp8);
		insert(tmp4, tmp2);
		insert(tmp5, tmp11);
		insert(tmp5, tmp9);
		insert(tmp9, tmp6);
		insert(tmp9, tmp10);
		//traverse();
	}
	void checkLockUnlock(){
		System.out.println(root.lock());
		System.out.println(root.lock());
		System.out.println(root.lock());
		System.out.println(root.unlock());
		System.out.println(root.bachche.get(0).bachche.get(1).lock());
		System.out.println(root.bachche.get(0).bachche.get(0).unlock());
		System.out.println(root.bachche.get(0).bachche.get(0).lock());
		System.out.println(root.bachche.get(0).bachche.get(0).unlock());
		System.out.println(root.bachche.get(0).bachche.get(1).lock());
		System.out.println(root.bachche.get(0).bachche.get(1).unlock());
		System.out.println(root.bachche.get(2).bachche.get(1).bachche.get(1).unlock());
		System.out.println(root.bachche.get(2).bachche.get(1).bachche.get(1).lock());
		System.out.println(root.bachche.get(2).bachche.get(1).bachche.get(1).lock());
		System.out.println(root.bachche.get(2).bachche.get(1).bachche.get(1).unlock());
	}
}
public class LockmAryTree {
	public static void main(String[] args){
		mTree mob = new mTree();
		mob.createTree();
		mob.checkLockUnlock();
	}
}
