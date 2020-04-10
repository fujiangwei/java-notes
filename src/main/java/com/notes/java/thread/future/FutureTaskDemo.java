package com.notes.java.thread.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 文件描述 futureTask异步
 **/
public class FutureTaskDemo {

    static ConcurrentHashMap<String, FutureTask<Connection>> connMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        long begin = System.currentTimeMillis();

//        MyCallable callable = new MyCallable();
//        MyRunnable runnable = new MyRunnable();
//        FutureTask<String> futureTaskCallable = new FutureTask<>(callable);
//        FutureTask<String> futureTaskRunnable = new FutureTask<>(runnable, "fjw");
//
//        // 1、使用Thread
////        new Thread(futureTaskCallable,"222222").start();
////        new Thread(futureTaskRunnable, "111111").start();
//
//        // 2、使用ExecutorService
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(futureTaskCallable);
//        executorService.execute(futureTaskRunnable);
//
//        // get方法阻塞当前线程，直到获取返回结果为止
//        String s = futureTaskCallable.get();
//        System.out.println(Thread.currentThread() + s);
//        String s1 = futureTaskRunnable.get();
//        System.out.println(Thread.currentThread() + s1);
//
//        // 关闭线程池
//        executorService.shutdown();

//        Connection fjw = getConnection("fjw");
//        System.out.println(fjw.getName());

        // 高并发测试
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 20, 1000, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        // n个线程
        for(int i = 0; i < 30; i ++) {
            poolExecutor.execute(() -> {
                try {
                    Connection connection = getConnection("fjw");
                    System.out.println(Thread.currentThread() + "----" + connection.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        poolExecutor.shutdown();

        System.out.println("main thread finished," + (System.currentTimeMillis() - begin));
    }

    /**
     * 高并发下返回唯一
      */
    private static Connection getConnection(String key) throws Exception {
        if (connMap.containsKey(key)){
            System.out.println("connFutureTask existed");
            FutureTask<Connection> connFutureTask = connMap.get(key);
            return connFutureTask.get();
        }

        FutureTask<Connection> newConnFutureTask = new FutureTask<Connection>(new Callable<Connection>() {
            @Override
            public Connection call() throws Exception {
                System.out.println("call processing......");
                Connection connection = new Connection();
                connection.setName("fjw");
                return connection;
            }
        });

        // lambda表达式
//        FutureTask<Connection> newConnFutureTask = new FutureTask<>(() -> new Connection());

        // putIfAbsent不存在则存入并返回null
        FutureTask<Connection> connFutureTask = connMap.putIfAbsent(key, newConnFutureTask);
        if (null == connFutureTask) {
            System.out.println("connFutureTask not existed");
            connFutureTask = newConnFutureTask;
            // 执行任务
            connFutureTask.run();
        }

        return connFutureTask.get();
    }

    static class Connection {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name + new Random().nextInt(1000);
        }
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