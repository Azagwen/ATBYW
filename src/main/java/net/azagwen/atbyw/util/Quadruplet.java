package net.azagwen.atbyw.util;

public class Quadruplet<A, B, C, D> {
    private A first;
    private B second;
    private C third;
    private D fourth;

    public Quadruplet(A first, B second, C third, D fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    public A getFirst() {
        return this.first;
    }

    public B getSecond() {
        return this.second;
    }

    public C getThird() {
        return this.third;
    }

    public D getFourth() {
        return this.fourth;
    }
}
