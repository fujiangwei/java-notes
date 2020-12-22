package com.notes.designpattern.singleton;

/**
 * @Description: 懒汗线程安全模式单例
 *      synchronized同步，效率慢
 * @author kingson
 **/
public class LazySafeSingleton {

    /**
     * 利用静态变量来记录Singleton的唯一实例
     */
    private static LazySafeSingleton singleton;

    private LazySafeSingleton() {

    }

    public static synchronized LazySafeSingleton getInstance(){
        if (null == singleton) {
            singleton = new LazySafeSingleton();
        }
        return singleton;
    }
}