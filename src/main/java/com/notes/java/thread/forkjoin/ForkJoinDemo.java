package com.notes.java.thread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * 文件描述
 *
 * @ProductName: Fork/Join
 **/
public class ForkJoinDemo {

    public static void main(String[] args) {

        int start = 1;
        int end = 1000000;
//        int sum = 0;
//        long begin = System.currentTimeMillis();
//        for(int i = start; i <= end; i ++) {
//            sum += i;
//        }
//        System.out.println("计算结果为 sum = " + sum + ",计算时长为" + (System.currentTimeMillis() - begin) + "ms");

        ForkJoinPool fjp2 = new ForkJoinPool();
        SumTask2 sumTask2 = new SumTask2(start, end);
        long begin3 = System.currentTimeMillis();
        Integer invoke = fjp2.invoke(sumTask2);
        long end3 = System.currentTimeMillis();
        System.out.println("invokeAll 结果为 sum = " + invoke + ",计算时长为" + begin3 + "-" + end3 + "---  " + (end3 - begin3) + "ms");

        ForkJoinPool fjp = new ForkJoinPool();
        long begin2 = System.currentTimeMillis();
        SumTask sumTask = new SumTask(start, end);
        ForkJoinTask<Integer> submit = fjp.submit(sumTask);
        Integer join = submit.join();
        long end2 = System.currentTimeMillis();
        System.out.println("fork 结果为 sum = " + join + ",计算时长为" + begin2 + "-" + end2 + "---   " + (end2 - begin2) + "ms");
    }
}
