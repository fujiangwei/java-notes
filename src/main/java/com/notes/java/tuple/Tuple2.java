package com.notes.java.tuple;

import java.util.Optional;

public class Tuple2<A, B> extends Tuple {
    private A a;
    private B b;

    Tuple2(A a, B b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Optional<A> _1() {
        return Optional.of(a);
    }

    @Override
    public Optional<B> _2() {
        return Optional.of(b);
    }

    @Override
    public <C> Optional<C> _3() {
        return Optional.empty();
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
        return "Tuple2{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}