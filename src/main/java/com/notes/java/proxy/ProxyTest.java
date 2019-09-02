package com.notes.java.proxy;

import com.notes.java.proxy.asm.ASMGenerate;
import com.notes.java.proxy.cglib.CglibFruit;
import com.notes.java.proxy.cglib.CglibMethodInterceptor;
import com.notes.java.proxy.dynamic.Banana;
import com.notes.java.proxy.dynamic.Fruit;
import com.notes.java.proxy.dynamic.FruitDynamicProxy;

/**
 * descripiton: 代理测试类
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/3/31
 * @time: 20:09
 * @modifier:
 * @since:
 */
public class ProxyTest {

    public static void main(String[] args) throws Exception {
        //静态代理测试
//        FruitStaticProxy fruitStaticProxy = new FruitStaticProxy();
//        fruitStaticProxy.name("apple");
//        fruitStaticProxy.price(5.8);
//
        //JDK动态代理测试
//        FruitDynamicProxy fruitDynamicProxy = new FruitDynamicProxy(new Banana());
//        Fruit banana = (Fruit) fruitDynamicProxy.getProxyInstance();
//        //调用该方法时转到FruitDynamicProxy类的invoke方法
//        banana.name("banana");
//        banana.price(12.6);

//        FruitDynamicProxy fruitDynamicProxy2 = new FruitDynamicProxy(new Apple());
//        Fruit apple2 = (Fruit) fruitDynamicProxy2.getProxyInstance();
        //调用该方法时转到FruitDynamicProxy类的invoke方法
//        apple2.name("apple2");

        //CGLIB动态代理测试
//        CglibMethodInterceptor cmi = new CglibMethodInterceptor(new CglibFruit());
//        CglibFruit fruit = (CglibFruit) cmi.getProxyInstance();
//        fruit.name("orange");
//        fruit.price(12.666);

        ASMGenerate.generete();

    }
}
