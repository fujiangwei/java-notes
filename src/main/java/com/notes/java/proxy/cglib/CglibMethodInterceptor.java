package com.notes.java.proxy.cglib;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * descripiton: CglibMethodInterceptor实现接口MethodInterceptor，
 * 代理对象的所有非final方法调用会被转发到该类的intercept()方法。
 * 在需要使用Fruit的时候，通过CGLIB动态代理获取代理对象。
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/3/31
 * @time: 21:00
 * @modifier:
 * @since:
 */
public class CglibMethodInterceptor implements MethodInterceptor {

    private CglibFruit fruit;

    public CglibMethodInterceptor(CglibFruit fruit) {
        this.fruit = fruit;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("CglibMethodInterceptor method " + method.getName() + " --- 参数 " + objects);

        Object o1 = methodProxy.invokeSuper(o, objects);

        return o1;
    }

    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        //设置代理对象字节
        enhancer.setSuperclass(this.fruit.getClass());
        //设置回调方法
        enhancer.setCallback(this);
        //创建代理对象
        Object o = enhancer.create();
        return o;
    }
}
