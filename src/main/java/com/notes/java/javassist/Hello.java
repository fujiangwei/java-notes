package com.notes.java.javassist;

public class Hello implements IHello {
    @Override
    public int sayHello3(String a, boolean b, Object c) {
        String abc = a + b + c;
        System.out.println("a + b + c = " + abc);
        return abc.hashCode();
    }
}