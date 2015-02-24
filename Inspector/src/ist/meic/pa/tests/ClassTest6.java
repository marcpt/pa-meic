package ist.meic.pa.tests;

public class ClassTest6 {
    int a = 0;
}

class D1 extends ClassTest6 {
    int a = 1;
    int b = 1;
    int c = 2;
    int z = 69;
}

class D2 extends D1 {
    int a = 2;
    int b = 2;
}

class D3 extends D2 {
    int d = 3;
}

class D4 extends D3 {
    int d = 3;
}

class D5 extends D4 {
    int d = 3;
}