package com.notes.java.thread.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 文件描述 读写锁
 **/
public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) throws Exception{
        Count count = new Count();
        int countNum = 100000;
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch cdl = new CountDownLatch(countNum);
        for(int i = 0; i < countNum; i ++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    count.ins();
////                    System.out.println(Thread.currentThread().getName() + "---" + count.num);
//                    cdl.countDown();
//                }
//            }, "t" + i).start();
            executorService.execute(() -> {
                count.ins();
//                System.out.println(Thread.currentThread().getName() + "---" + count.num);
                cdl.countDown();
            });

        }

        //主线程等待
        cdl.await();
        System.out.println(">>>>>>>" + count.num);
        if(!executorService.isShutdown()) {
            System.out.println("isShutdown ...");
            executorService.shutdown();
        }
    }

    static class Count{

        private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

        volatile int num;

        public void ins() {
            rwLock.writeLock().lock();
            try {
//                System.out.println("read begin " + Thread.currentThread().getName());
//                Thread.sleep(500);
                num ++;
//                System.out.println("read end " + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                rwLock.writeLock().unlock();
            }
        }

        public void get() {
            rwLock.readLock().lock();
            try {
                System.out.println("read begin " + Thread.currentThread().getName());
                Thread.sleep(500);
                System.out.println("read end " + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                rwLock.readLock().unlock();
            }
        }
    }
}