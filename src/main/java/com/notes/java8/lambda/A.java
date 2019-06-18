package com.notes.java8.lambda;

/**
 * 文件描述
 **/
public interface A {

    String hi();

    String greet();

    default void hello() {
        System.out.println("A.hello");
    }

}
