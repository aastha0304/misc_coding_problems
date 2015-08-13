package hackerearth;
class class2{
	int one,zero;
	class2(){
		one=0;zero=0;
	}
	class2 add(class2 c){
		class2 r=new class2();
		r.zero = zero+c.zero;
		r.one = one+c.one;
		return r;
	}
	class2(int a, int b){
		zero=a;
		one=b;
	}
	class2(class2 a){
		zero=a.zero;
		one=a.one;
	}
	void copy(class2 c){
		one = c.one;
		zero = c.zero;
	}
	void print(int i){
		System.out.println(i+" "+zero+" "+one);
	}
}
public class Reco {
	class2 cob;
	class2[] coba;
	Reco(){
		cob = new class2();
		coba =  new class2[101];
		coba[0] = new class2(1,0);
		coba[1] = new class2(0,1);
		coba[2] = new class2(2,1);
		for(int i =3;i<=100;i++){
			coba[i]=new class2((coba[i-3].add(coba[i-2].add(coba[i-1]))));
		}
		
	}
	void reco(int n)
	{
	    if (n<=0) {cob.zero++;return;}
	    if (n==1) {cob.one++;return;}
	    else
	    {
	        reco(n-1);
	        reco(n-2);
	        reco(n-3);
	        return;
	    }
	}
	
	class2 reco1(int n){
		if(n<=0){return coba[0];}
		return coba[n];
		
	}
	void print(int i){
		cob.print(i);
	}
	public static void main(String[] args){
		for(int i =-2;i<10;i++){
			Reco ob1 = new Reco();
			ob1.reco(i);
			ob1.print(i);
		}
		System.out.println();
		Reco ob1 = new Reco();
		for(int i =-2;i<10;i++){
			ob1.reco1(i).print(i);
		}
		
	}
}
