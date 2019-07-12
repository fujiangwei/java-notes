package com.notes.utils;

import org.springframework.util.StopWatch;

/**
 * 文件描述 StopWatch秒表使用
 **/
public class StopWatchUtil {

    public static void main(String[] args) throws InterruptedException{

        StopWatch stopWatch = new StopWatch("任务耗时秒表工具");

        stopWatch.start("task1");
        Thread.sleep(1000);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
        stopWatch.start("task2");
        Thread.sleep(3000);
        stopWatch.stop();
        //所有任务耗时时间
        System.out.println(stopWatch.getTotalTimeMillis());
        System.out.println(stopWatch.prettyPrint());

        StopWatch stopWatch2 = new StopWatch("任务耗时秒表工具");
        stopWatch2.start("task3");
        Thread.sleep(3000);
        stopWatch2.stop();
        //所有任务耗时时间
        System.out.println(stopWatch2.getTotalTimeMillis());
        System.out.println(stopWatch2.prettyPrint());

    }
}
