package com.notes.designpattern.singleton;

/**
 * 文件描述 枚举单例
 **/
public enum SingletonEnum {

    INSTANCE;

    public SingletonEnum getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        SingletonEnum instance = SingletonEnum.INSTANCE;
        SingletonEnum instance2 = SingletonEnum.INSTANCE;
        System.out.println("instance:" + instance);
        System.out.println("instance2:" + instance2);
        System.out.println(instance == instance2);
    }
}