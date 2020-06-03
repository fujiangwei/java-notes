package com.notes.java.thread.threadpoolexecutor;

import com.google.common.collect.Lists;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.concurrent.*;

/**
 * 文件描述
 **/
public class Test {
    /**
     * 使用线程池
     */
    static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 20, 2000L,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public static void main(String[] args) throws Exception {

        // 1、串行测试
        StopWatch watch = new StopWatch();
        watch.start("串行");
        String name = getName();
        int age = getAge();
        String birthday = getBirthday();
        watch.stop();
        System.out.println(name + "-" + age + "-" + birthday + ", 串行 耗时：" + watch.getTotalTimeMillis());
        // 并行测试1
        StopWatch watch2 = new StopWatch();
        watch2.start("并行");
        // 此方式get方法阻塞了后面的服务，用法不对
        String submit = poolExecutor.submit(() -> getName()).get();
        Integer submit1 = poolExecutor.submit(() -> getAge()).get();
        String submit2 = poolExecutor.submit(() -> getBirthday()).get();
        watch2.stop();
        System.out.println(submit + "-" + submit1 + "-" + submit2  + ", 并行1 耗时：" + watch2.getTotalTimeMillis());

        // 并行测试2
        StopWatch watch3 = new StopWatch();
        watch3.start("并行2");
        Future<String> future = poolExecutor.submit(() -> getName());
        Future<Integer> future1 = poolExecutor.submit(() -> getAge());
        Future<String> future2 = poolExecutor.submit(() -> getBirthday());
        String submit3 = future.get();
        Integer submit4 = future1.get();
        String submit5 = future2.get();
        watch3.stop();
        System.out.println(submit3 + "-" + submit4 + "-" + submit5 + ", 并行3 耗时：" + watch3.getTotalTimeMillis());

        // 并行测试3
        StopWatch watch4 = new StopWatch();
        watch4.start("并行3");
        Callable<String> taskName = () -> getName();
        Callable<String> taskBirthday = () -> getBirthday();
        List<Callable<String>> taskList = Lists.newArrayList();
        taskList.add(taskName);
        taskList.add(taskBirthday);
        List<Future<String>> futures = poolExecutor.invokeAll(taskList);

        StringBuilder sb = new StringBuilder();
        for (Future<String> resultFuture : futures) {
            sb.append(resultFuture.get()).append("-");
        }
        watch4.stop();
        System.out.println(sb.toString() + ", 并行3 耗时：" + watch4.getTotalTimeMillis());

        poolExecutor.shutdown();
    }

    public static String getName () throws Exception {
        TimeUnit.SECONDS.sleep(5);

        return "阿三";
    }

    public static int getAge () throws Exception {
        TimeUnit.SECONDS.sleep(15);

        return 18;
    }

    public static String getBirthday () throws Exception {
        TimeUnit.SECONDS.sleep(8);

        return "1001";
    }
}