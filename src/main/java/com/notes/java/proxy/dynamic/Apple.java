package com.notes.java.proxy.dynamic;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/3/31
 * @time: 19:51
 * @modifier:
 * @since:
 */
public class Apple implements Fruit {

    @Override
    public String name(String name) {
        System.out.println("this is " + name);
        return name;
    }

    @Override
    public double price(double price) {
        System.out.println("Apple price is " + price);
        return price;
    }
}
