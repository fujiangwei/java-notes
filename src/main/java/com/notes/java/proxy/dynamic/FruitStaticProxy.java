package com.notes.java.proxy.dynamic;

/**
 * descripiton: 苹果静态代理类,作为Apple的代理，和Apple类一样实现了Fruit接口
 * 静态代理的代理关系在编译时就确定了，静态代理实现简单，适合于代理类较少且确定的情况，
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/3/31
 * @time: 19:53
 * @modifier:
 * @since:
 */
public class FruitStaticProxy implements Fruit {

    //指定具体代理类型
    Fruit apple = new Apple();

    @Override
    public String name(String name) {
        System.out.println("FruitStaticProxy static proxy " + name);
        return apple.name(name);
    }

    @Override
    public double price(double price) {
        System.out.println("FruitStaticProxy Apple price is " + price);
        return price;
    }
}
