package com.notes.designpattern.singleton;

/**
 * @Description:  静态类内部加载
 *      静态内部类不会在单例加载时就加载，而是在调用getInstance()方法时才进行加载，达到了类似懒汉模式的效果
 *      这种方法又是线程安全的
 * @author kingson
 **/
public class StaticInnerSingleton {

    private static class StaticInnerSingletonContainer {
        private static StaticInnerSingleton singleton = new StaticInnerSingleton();
    }

    private StaticInnerSingleton() {}

    public static StaticInnerSingleton getInstance() {
        return StaticInnerSingletonContainer.singleton;
    }
}