package com.notes.designpattern.singleton;

/**
 * @Description: 饿汗模式单例
 *      在运行这个类的时候进行一次loading，之后直接访问，比静态类多了一个内存常驻而已
 * @author kingson
 **/
public class HungerSingleton {

    /**
     * 利用静态变量来记录Singleton的唯一实例
     * 直接初始化静态变量，这样就可以确保线程安全了
     */
    private static HungerSingleton singleton = new HungerSingleton();

    private HungerSingleton() {

    }

    public static HungerSingleton getInstance(){
        return singleton;
    }
}