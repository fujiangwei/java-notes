package com.notes.java.javassist;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomHandler implements InvocationHandler {

    private Object obj;

    public CustomHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("pre method");
        Object result = method.invoke(obj, args);
        System.out.println("post method");
        return result;
    }

}