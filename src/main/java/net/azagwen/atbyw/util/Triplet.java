package net.azagwen.atbyw.util;

public class Triplet<A, B, C> {
    private A first;
    private B second;
    private C third;

    public Triplet(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
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
}
