package ist.meic.pa.tests;

public class ClassTest3 {
    int a = 0;
}

class C1 extends ClassTest3 {
    int a = 1;
    int b = 1;
    int c = 2;
}

class C2 extends C1 {
    int a = 2;
    int b = 2;
}

class C3 extends C2 {
    int d = 3;
}