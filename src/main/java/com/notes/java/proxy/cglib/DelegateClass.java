package com.notes.java.proxy.cglib;

public class DelegateClass {

    private String string;

    public DelegateClass() {
    }

    public DelegateClass(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public boolean add(String string, int i) {
        System.out.println("This is add method: " + string + ", " + i);
        return true;
    }

    public void update() {
        System.out.println("This is update method");
    }
}