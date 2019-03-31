package com.notes.java.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * descripiton: 水果动态代理类,实现接口InvocationHandler，代理对象的所有方法调用都会被转发到该类的invoke()方法。
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/3/31
 * @time: 19:58
 * @modifier:
 * @since:
 */
public class FruitDynamicProxy implements InvocationHandler {

    private Fruit fruit;

    public FruitDynamicProxy(Fruit fruit) {
        this.fruit = fruit;
    }

    /**
     * 获取代理对象实例
     * newProxyInstance()会返回一个实现了指定接口的代理对象，对该对象的所有方法调用都会转发给InvocationHandler.invoke()方法
     *
     * @return
     */
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                //代理对象的类加载器
                fruit.getClass().getClassLoader(),
                //代理对象需要实现的接口，可以是多个
                fruit.getClass().getInterfaces(),
                //方法调用的实际对象，
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("FruitDynamicProxy方法名 " + method.getName() + " ---参数 " + Arrays.toString(args));

        //代理对象调用相应方法的返回结果
        Object invoke = method.invoke(fruit, args);

        return invoke;
    }
}
