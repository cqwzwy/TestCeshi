package canny;

public class JDK8 {
	
	private int num;
	
	
	public static void main(String[]as) {
		
		JDK8 test=new JDK8();
		JDK8 t2=new JDK8();
		test.num=2;
		t2.num=4;
		test.sum(t2);
		System.out.println(t2.num);
		oparation add=(int a,int b)->a+b;
		oparation sub=(a,b)->a-b;
		
		oparation cel=(a,b)->{return a*b;};
		System.out.println("表达式  " +test.opera(10, 5, add) );
		
		
	}
	
	public void sum(JDK8 t) {
		t.num*=10;
	}
	
	interface oparation{
		int oparation(int a,int b);
	}
	
	interface guasda{
		
		void message(String msg);
		
	}
	
	private int opera(int a,int b,oparation math) {
		return math.oparation(a, b);
	}
	
	
}

interface test{
	default void get() {};
	void put();
}

class car implements test{

	public void put() {
		
		
	}
	
}


