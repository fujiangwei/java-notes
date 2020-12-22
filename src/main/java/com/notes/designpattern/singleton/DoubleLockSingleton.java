package com.notes.designpattern.singleton;

/**
 * descripiton: 懒汉的双重加锁机制,兼顾线程安全和效率的写法
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/4/2
 * @time: 22:23
 * @modifier:
 * @since:
 */
public class DoubleLockSingleton {

    /**
     * 禁止指令重排优化
     */
    private static volatile DoubleLockSingleton singleton;

    /**
     *  双重校验锁法
     *
     * @return
     */
    public static DoubleLockSingleton getInstance() {
        if (null == singleton) {
            // 加锁同步
            synchronized (DoubleLockSingleton.class) {
                if (null == singleton) {
                    singleton = new DoubleLockSingleton();
                }
            }
        }

        return singleton;
    }
}
