package ist.meic.pa.tests;

public class B {
    private String c;

    protected int d;

    // *** Added get an set to private variable
    public String getC() {
	return c;
    }

    public void setC(String c) {
	this.c = c;
    }
    // *** 
}

class E extends B {
    boolean f;

    public int g(int h) {
	return d + h;
    }

    public int g() {
	return d + 1;
    }

    public String cR() {
	return new String("balelas");
    }

    public String cR1(int i, int e, int r) {
	return new String("balelas " + i + e + r);
    }

    public E cR2(){
	E e = new E();
	e.f = true;
	e.setC("balelas");
	e.d = 123;
	return e;
    }

    // estava a converter para minusculas!!
    public int M() {
	return d + 1;
    }

    // calling methods that do not have parameters
    public int soma1() {
	return d + 1;
    }

    // methods that require arguments of type int
    public void imps1() {
	System.out.println(d + 1);
    }



    public static long i = 10L;
}
