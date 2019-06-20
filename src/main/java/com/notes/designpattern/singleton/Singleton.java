package com.notes.designpattern.singleton;

/**
 * descripiton: 兼顾线程安全和效率的写法
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/4/2
 * @time: 22:23
 * @modifier:
 * @since:
 */
public class Singleton {

    /**
     * 禁止指令重排优化
     */
    private static volatile Singleton singleton;

    /**
     * 双重锁检查
     *
     * @return
     */
    public static synchronized Singleton getInstance() {
        if (null == singleton) {
            synchronized (Singleton.class) {
                if (null == singleton) {
                    singleton = new Singleton();
                }
            }
        }

        return singleton;
    }
}
