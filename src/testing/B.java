package testing;

public class B extends A {

    public B(int oib, int abc) {
        super(oib,abc);
    }

    public static void main(String[] args) {
        B b = new B(2,3);
        b.printA();
    }

    public void printA() {
        super.printA();
    }
}
