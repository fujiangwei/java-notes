package com.notes.java.thread.threadpoolexecutor;

import ch.qos.logback.core.util.TimeUtil;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.*;

/**
 * 文件描述
 **/
public class Test {

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4,8,
                2000L, TimeUnit.SECONDS,new LinkedBlockingQueue<>());

        long begin = System.currentTimeMillis();
//        List<String> names = Lists.newArrayList();
//        Future<List<String>> resultFuture = poolExecutor.submit(() -> {
//            long b1 = System.currentTimeMillis();
//            Thread.sleep(6000);
//            names.add("fjw");
//            System.out.println(Thread.currentThread().getName() + " names >>>>>>>>>>>>> finished," + (System.currentTimeMillis() - b1));
//            return names;
//        });
//        List<String> names2 = Lists.newArrayList();
//        Future<List<String>> resultFuture2 = poolExecutor.submit(() -> {
//            long b1 = System.currentTimeMillis();
//            Thread.sleep(3000);
//            names2.add("fjw2");
//            System.out.println(Thread.currentThread().getName() + " names2 <<<<<<<<<<<<< finished," + (System.currentTimeMillis() - b1));
//            return names2;
//        });

//        List<String> result = resultFuture.get();
//        List<String> result2 = resultFuture2.get();
//        System.out.println("names -> " + names.size() + " result -> " + result.size());
//        if (names.size() > 0) {
//            System.out.println("names2 -> " + names2.size()  + " result2 -> " + result2.size());
//        }

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
        System.out.println("main thread finished," + (System.currentTimeMillis() - begin));
    }
}