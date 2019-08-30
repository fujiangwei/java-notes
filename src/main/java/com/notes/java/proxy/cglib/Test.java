package com.notes.java.proxy.cglib;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.reflect.FastClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 文件描述
 **/
public class Test {
    public static void main(String[] args) throws Exception {
        // 保留生成的FastClass类文件
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\cache\\CGLib\\FastClass");

        Class delegateClass = DelegateClass.class;

        // Java Reflect
        // 反射构造类
        Constructor delegateConstructor = delegateClass.getConstructor();
        // 创建委托类实例,无参构造器
        DelegateClass delegateInstance = (DelegateClass) delegateConstructor.newInstance();

        // 反射构造类
        Constructor delegateConstructor2 = delegateClass.getConstructor(String.class);
        // 创建委托类实例,无参构造器
        DelegateClass delegateInstance2 = (DelegateClass) delegateConstructor2.newInstance("ks");
        System.out.println(delegateInstance2.getString());

        // 反射方法类
        Method addMethod = delegateClass.getMethod("add", String.class, int.class);
        // 调用方法
        addMethod.invoke(delegateInstance, "Tom", 30);

        Method updateMethod = delegateClass.getMethod("update");
        updateMethod.invoke(delegateInstance);

        FastClass fastClass = FastClass.create(DelegateClass.class);
        FastClass fastClass2 = FastClass.create(DelegateClass.class);
        DelegateClass delegateClass1 = (DelegateClass)fastClass.newInstance();
        int index = fastClass.getIndex("add", new Class[]{String.class, int.class});
        fastClass.invoke(index, delegateClass1, new Object[]{"Jack", 28});

        DelegateClass delegateClass2 = (DelegateClass) fastClass.newInstance(new Class[]{String.class}, new Object[]{"Cat"});
        System.out.println(delegateClass2.getString());
        fastClass.invoke("add", new Class[]{String.class, int.class}, delegateClass2, new Object[]{"Hason", 36});
    }
}
