package com.notes.java.tuple;

import java.util.Optional;

public class Tuple3<A, B, C> extends Tuple {
    private A a;
    private B b;
    private C c;

    public Tuple3(A e, B t, C k) {
        this.a = e;
        this.b = t;
        this.c = k;
    }

    public Optional<A> _1() {
        return Optional.of(a);
    }

    public Optional<B> _2() {
        return Optional.of(b);
    }

    public Optional<C> _3() {
        return Optional.of(c);
    }

    @Override
    public <D> Optional<D> _4() {
        return Optional.empty();
    }

    @Override
    public <E> Optional<E> _5() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "Tuple3{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}