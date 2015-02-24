package ist.meic.pa.tests;

import java.util.ArrayList;
import java.util.HashMap;

public class TestingClass {

    public static void main(String args[]) throws IllegalArgumentException,
    IllegalAccessException {

	// Test0
	// Example given in project description
	E e1 = new E();
	e1.f = true;
	e1.setC( new String("Hello World!"));
	e1.d = 42;
	new ist.meic.pa.Inspector().inspect(e1);
	
	ClassPAF testPAF = new ClassPAF();
	new ist.meic.pa.Inspector().inspect(testPAF);
	
	ClassT7 c7 = new ClassT7(); 
	new ist.meic.pa.Inspector().inspect(c7);
	
	
	D5 d5 = new D5();
	new ist.meic.pa.Inspector().inspect(d5);

	
	// Inspects an int
	int a = 3;
	new ist.meic.pa.Inspector().inspect(a);

	// Inspect an Integer
	Integer b = new Integer(7);
	new ist.meic.pa.Inspector().inspect(b);

	// Inspecting a string
	String s = new String("Hello Portugal!");
	new ist.meic.pa.Inspector().inspect(s);

	// Inspecting an ArrayList of Integer
	ArrayList<Integer> intAL = new ArrayList<Integer>();
	intAL.add(1); 
	intAL.add(2); 
	intAL.add(3); 
	intAL.add(4);
	new ist.meic.pa.Inspector().inspect(intAL);

	// Inspecting an ArrayList of Strings
	ArrayList<String> stringAL = new ArrayList<String>();
	stringAL.add("IST"); 
	stringAL.add("Alameda"); 
	stringAL.add("PA");
	new ist.meic.pa.Inspector().inspect(stringAL);
	 

	// Test1
	ClassTest1 t1 = new ClassTest1();
	t1.setA((byte) 10);
	t1.setB((short) 42);
	t1.setC(42);
	t1.setD(42L);
	t1.setE(42.5f);
	t1.setF(123.4d);
	t1.setG('a');
	t1.setH(true);
	new ist.meic.pa.Inspector().inspect(t1);

	// Test2
	ClassTest2 t2 = new ClassTest2();
	t2.a = 123;
	t2.b = 456;
	t2.c = 234;
	t2.setD(678);
	new ist.meic.pa.Inspector().inspect(t2);

	// Test3
	C3 c3 = new C3();
	new ist.meic.pa.Inspector().inspect(c3);

	// Test4

	// Test5
	ClassTest5 t5 = new ClassTest5();
	t5.a = 12;
	t5.b = new Integer(5);
	t5.q = new String("Programação");

	t5.c = new int[3];
	t5.c[0] = 23;
	t5.c[1] = 54;
	t5.c[2] = 3;
	t5.inlist = new ArrayList<Integer>();
	t5.inlist.add(6);
	t5.inlist.add(9);
	t5.bomMapa = new HashMap<Integer, String>();
	t5.bomMapa.put(1, "balelas");
	t5.bomMapa.put(3, "foo");
	t5.bomMapa.put(5, "mourinho");
	t5.etype = e1;
	new ist.meic.pa.Inspector().inspect(t5);

	/*
	 * Expected output:
	 * 
	 * a.E@1a2961b is an instance of class a.E
	 * ----------
	 * private java.lang.String c = "Hello World"
	 * protected int d = 42
	 * boolean f = true
	 */

	System.out.println("O programa principal continua feliz e contente");
	System.out.println("a executar-se e a fazer coisas lindas e engraçadas.");
	//System.out.println(e1.g(0));

    }
}