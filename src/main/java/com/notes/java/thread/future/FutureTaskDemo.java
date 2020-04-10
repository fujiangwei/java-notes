package com.notes.java.thread.future;

import java.util.concurrent.*;

/**
 * 文件描述 futureTask异步
 **/
public class FutureTaskDemo {

    public static void main(String[] args) throws Exception {
        long begin = System.currentTimeMillis();

        MyCallable callable = new MyCallable();
        MyRunnable runnable = new MyRunnable();
        FutureTask<String> futureTaskCallable = new FutureTask<>(callable);
        FutureTask<String> futureTaskRunnable = new FutureTask<>(runnable, "fjw");

        // 1、使用Thread
//        new Thread(futureTaskCallable,"222222").start();
//        new Thread(futureTaskRunnable, "111111").start();

        // 2、使用ExecutorService
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(futureTaskCallable);
        executorService.execute(futureTaskRunnable);

        // get方法阻塞当前线程，直到获取返回结果为止
        String s = futureTaskCallable.get();
        System.out.println(Thread.currentThread() + s);
        String s1 = futureTaskRunnable.get();
        System.out.println(Thread.currentThread() + s1);

        // 关闭线程池
        executorService.shutdown();

        System.out.println("main thread finished," + (System.currentTimeMillis() - begin));
    }

    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread() + " 111111");
        }
    }

    static class MyCallable implements Callable<String> {
        @Override
        public String call() {
            System.out.println(Thread.currentThread() + " 222222");
            return "fujiangwei";
        }
    }
}