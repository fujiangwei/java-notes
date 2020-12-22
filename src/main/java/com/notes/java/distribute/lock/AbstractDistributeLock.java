package com.notes.java.distribute.lock;

/**
 * 文件描述
 **/
public abstract class AbstractDistributeLock {

    /**
     * 获取锁
     */
    public abstract boolean tryLock (String value);
    /**
     * 释放锁
     */
    public abstract boolean releaseLock (String value);
}