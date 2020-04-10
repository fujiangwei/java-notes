package com.notes.java.thread;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.*;

/**
 * 文件描述
 **/
public class Test {

    public static void main(String[] args) throws Exception {

        long begin = System.currentTimeMillis();
//        parallel();
        ConcurrentHashMap<String, String> pool = new ConcurrentHashMap<>();
//        pool.put("a", "aaa0001");
        System.out.println(pool.get("a"));
        // 不存在写入
        String s = pool.putIfAbsent("a", "aaa");

        System.out.println(s + pool.get("a"));

        System.out.println("main thread finished," + (System.currentTimeMillis() - begin));
    }

    public static void parallel() throws Exception {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4,8,
                2000L, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        List<String> names = Lists.newArrayList();
        Future<List<String>> resultFuture = poolExecutor.submit(() -> {
            long b1 = System.currentTimeMillis();
            Thread.sleep(3000);
            names.add("fjw");
            System.out.println(Thread.currentThread().getName() + " names >>>>>>>>>>>>> finished," + (System.currentTimeMillis() - b1));
            return names;
        });
        List<String> names2 = Lists.newArrayList();
        Future<List<String>> resultFuture2 = poolExecutor.submit(() -> {
            long b1 = System.currentTimeMillis();
            Thread.sleep(3000);
            names2.add("fjw2");
            System.out.println(Thread.currentThread().getName() + " names2 <<<<<<<<<<<<< finished," + (System.currentTimeMillis() - b1));
            return names2;
        });

        // get方法阻塞当前线程
        List<String> result = resultFuture.get();
        List<String> result2 = resultFuture2.get();
        System.out.println("names -> " + names.size() + " result -> ");
        if (names.size() > 0) {
            System.out.println("names2 -> " + names2.size()  + " result2 -> ");
        }

        //关闭线程池
        poolExecutor.shutdown();
    }

    public static void serial() throws Exception {
        List<String> names = Lists.newArrayList();
        long b1 = System.currentTimeMillis();
        Thread.sleep(6000);
        names.add("fjw");
        System.out.println(Thread.currentThread().getName() + " names >>>>>>>>>>>>> finished," + (System.currentTimeMillis() - b1));

        List<String> names2 = Lists.newArrayList();
        long b2 = System.currentTimeMillis();
        Thread.sleep(3000);
        names2.add("fjw2");
        System.out.println(Thread.currentThread().getName() + " names2 <<<<<<<<<<<<< finished," + (System.currentTimeMillis() - b2));
        System.out.println("names -> " + names.size());
        if (names.size() > 0) {
            System.out.println("names2 -> " + names2.size());
        }
    }


}