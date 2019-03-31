package com.notes.java.proxy.cglib;

/**
 * descripiton: 水果类，该类没有实现接口，故无法使用JDK代理，可通过CGLIB代理
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/3/31
 * @time: 20:58
 * @modifier:
 * @since:
 */
public class CglibFruit {

    public String name(String name) {
        System.out.println("this is " + name);
        return name;
    }

    public double price(double price) {
        System.out.println("price is " + price);
        return price;
    }

    public final void hello(String msg) {
        System.out.println("final method " + msg);
    }
}
