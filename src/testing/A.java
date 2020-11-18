package testing;

public abstract class A {
    int oib;
    int abc;

    public A(int oib, int abc) {
        this.oib = oib;
        this.abc = abc;
    }

    public void printA() {
        System.out.println(oib == abc);
    }
}
