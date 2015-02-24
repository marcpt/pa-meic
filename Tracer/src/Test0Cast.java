import ist.meic.pa.Trace;

class Testx {
	
	public Object foo(){
		String foo = new String("Foo");
		Object mario;
		mario = (String) foo;
		mario = (Object) foo;
		
		return mario;
	}
	
	public Object bar(){
		Object murta = (Object) new String("Bar");
		return murta;
	}
	
	public Object identity(Object o){
		return (String) o;
	}
	
	public void test(){
		Object mario;
		mario = (String) foo();
		mario = (Object) foo();
		mario = (Integer) 4;
		Object miguel = (String) foo();
		Trace.print(foo());
		Object b = bar();
		Trace.print(identity(b));
	}
}

public class Test0Cast {
	
	public static void main(String args[]){
		(new Testx()).test();
		
		/*
		System.out.println(" ");
		System.out.println(" ");
		Trace.printAllHistory();
		*/
		
	}
}
