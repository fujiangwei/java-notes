package com.notes.java.distribute.lock;

/**
 * 文件描述 Mysql实现分布式锁
 **/
public class MysqlDistributeLock extends AbstractDistributeLock {
    @Override
    public boolean tryLock(String value) {
        return false;
    }

    @Override
    public boolean releaseLock(String value) {
        return false;
    }
}