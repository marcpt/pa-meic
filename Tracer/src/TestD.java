import ist.meic.pa.Trace;


public class TestD {
	
	static Object o = new Object();
	int i = m(o);
	
	int m(Object o){
		return 42;
	}
	
	static void Foo(){
		Trace.print(o);
	}
	
	public static void main(String args[]){
		TestD d = new TestD();
		Trace.print(d);
		Foo();
		//Trace.printAllHistory();
	}
	
}