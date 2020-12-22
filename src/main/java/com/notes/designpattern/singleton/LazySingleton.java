package com.notes.designpattern.singleton;

/**
 * @Description: 懒汗模式单例
 *      缺点: 没有考虑到线程安全，可能存在多个访问者同时访问，并同时构造了多个对象的问题。
 * @author kingson
 **/
public class LazySingleton {

    /**
     * 利用静态变量来记录Singleton的唯一实例
     */
    private static LazySingleton singleton;

    private LazySingleton() {

    }

    public static LazySingleton getInstance(){
        if (null == singleton) {
            singleton = new LazySingleton();
        }
        return singleton;
    }
}