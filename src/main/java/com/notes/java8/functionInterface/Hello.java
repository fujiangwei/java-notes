package com.notes.java8.functionInterface;

/**
 * 文件描述 函数式接口:
 *      有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。
 **/
@FunctionalInterface
public interface Hello {

    /**
     * abstract 方法，只能有一个
     */
    void hello();

    /**
     * 允许定义默认方法
     */
    default void hi(){
        System.out.println("this is default method");
    }

    /**
     * 允许定义静态方法
     */
    static void hei() {
        System.out.println("this is static method");
    }

    /**
     * 允许定义 java.lang.Object 里的 public 方法
     */
    @Override
    boolean equals(Object obj);
}
