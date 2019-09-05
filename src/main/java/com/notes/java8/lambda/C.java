package com.notes.java8.lambda;

/**
 * 文件描述,测试继承及实现具有相同默认方法的父类或接口问题
 **/
public class C extends D implements A, B {

    @Override
    public String hi() {
        return "C.hi";
    }

    @Override
    public String greet() {
        return "C.greet";
    }

    @Override
    public String hh() {
        return "C.hh";
    }

    /**
     * 子类优先继承父类的方法， 如果父类没有相同签名的方法，才继承接口的默认方法。
     * 编译报错解决1：覆盖法
     */
//    @Override
//    public void hello() {
//        System.out.println("C.hello");
//    }

    /**
     * 编译报错解决2：指定实现的父接口
     */
//    @Override
//    public void hello() {
//        A.super.hello();
////        B.super.hello();
//    }

}
