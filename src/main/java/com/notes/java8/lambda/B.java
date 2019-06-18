package com.notes.java8.lambda;

/**
 * 文件描述
 **/
public interface B {

    String hi();

    String hh();

    default void hello() {
        System.out.println("B.hello");
    }

}
