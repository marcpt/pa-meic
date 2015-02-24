import ist.meic.pa.Trace;


public class TestC {
	
	Object o = new Object();
	int i = m(o);
	
	int m(Object o){
		return 42;
	}
	
	public static void main(String args[]) throws InterruptedException{
		TestC c = new TestC();
		Trace.print(c);
		
		//Trace.printAllHistory();
	}
	
}
