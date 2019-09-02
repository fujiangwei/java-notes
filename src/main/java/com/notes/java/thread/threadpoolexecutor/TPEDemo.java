package com.notes.java.thread.threadpoolexecutor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 文件描述 ThreadPoolExecutor使用
 **/
public class TPEDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(20, 100, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(30));
        for (int i = 0; i < 10; i++) {
//            threadPoolExecutor1.execute(new MyRunnable(atomicInteger, 1000L));
        }

        // 拒绝策略
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), (task, pool) ->
                System.out.println("系统繁忙，请稍后再试，即线程池已满，处理不了这么多任务")
        );
        // 1、若当前任务<=核心线程数,则直接使用核心线程进行处理。
        // 2、否则将大于核心线程的任务放入当前队列中（有限队列类型），若队列长度能容纳当前多出的任务数数，则不启用新线程进行处理。
        // 3、若大于核心线程的任务数大于队列容量，则启用新线程来处理多出的任务，当处理任务的活跃线程总数大于最大线程数时，则抛出异常。
        // 4、当大于核心线程数的线程的存活时间超过设置值时，线程池将会恢复到核心线程池数。

        for (int i = 0; i < 16; i++) {
//            threadPoolExecutor.execute(new MyRunnable(atomicInteger, 1000L));
        }
//        printExecutorInfo(threadPoolExecutor);
//        //模拟超过存活时间
//        Thread.sleep(20000L);
//        printExecutorInfo(threadPoolExecutor);
        //创建5个线程的线程池
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
//            es.execute(new MyRunnable(atomicInteger,1000L));
        }

        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(10);
        //任务时间小于间隔时间以间隔时间为周期
//        scheduledPool.scheduleAtFixedRate(new MyRunnable(1000L), 0, 2, TimeUnit.SECONDS);
        //任务时间大于间隔时间以任务时间为周期
//        scheduledPool.scheduleAtFixedRate(new MyRunnable(5000L), 0, 2, TimeUnit.SECONDS);
        //scheduleWithFixedDelay下一周期开始时间为上一任务执行完时间加上间隔时间执行完后的时间点
//        scheduledPool.scheduleWithFixedDelay(new MyRunnable(1000L), 0, 2, TimeUnit.SECONDS);

        //三种常用队列SynchronousQueue; LinkedBlockingDeque; ArrayBlockingQueue;
        //当LinkedBlockingDeque塞满时，新增的任务会直接创建新线程来执行，当创建的线程数量超过最大线程数量时会抛异常。
        //SynchronousQueue没有数量限制。因为他根本不保持这些任务，而是直接交给线程池去执行。当任务数量超过最大线程数时会直接抛异常。
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
////        execute(executor, 3, atomicInteger, 1000L);
////        printExecutorInfo(executor);
////        execute(executor, 3, atomicInteger, 1000L);
////        printExecutorInfo(executor);
////        Thread.sleep(8000);
////        printExecutorInfo(executor);

        //当任务数超过核心线程数时，会将超出的任务放在队列中，只会创建3个线程重复利用。
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
//        printExecutorInfo(executor);
//        execute(executor, 3, atomicInteger, 1000L);
//        printExecutorInfo(executor);
//        execute(executor, 3, atomicInteger, 1000L);
//        printExecutorInfo(executor);
//        Thread.sleep(8000);
//        printExecutorInfo(executor);

        //队列是SynchronousQueue，超出核心线程的任务会创建新的线程来执行，这些线程是非核心线程，在任务完成后在设置超时时间后5秒就会被回收，最终线程池只有三个线程。
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
//        execute(executor, 3, atomicInteger, 1000L);
//        printExecutorInfo(executor);
//        execute(executor, 3, atomicInteger, 1000L);
//        printExecutorInfo(executor);
//        Thread.sleep(8000);
//        printExecutorInfo(executor);

        //LinkedBlockingDeque有大小限制时就会受最大线程数影响，否则不受影响
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
//        execute(executor, 3, atomicInteger, 1000L);
//        printExecutorInfo(executor);
//        execute(executor, 3, atomicInteger, 1000L);
//        printExecutorInfo(executor);
//        Thread.sleep(8000);
//        printExecutorInfo(executor);

        //核心线程是3个，当加入第四个任务的时候，就把第四和第五个放在队列中。加入第六个任务时，因为队列满了，就创建新线程执行，创建了线程4。
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(2));
//        execute(executor, 3, atomicInteger, 1000L);
//        printExecutorInfo(executor);
//        execute(executor, 3, atomicInteger, 1000L);
//        printExecutorInfo(executor);
//        Thread.sleep(8000);
//        printExecutorInfo(executor);

        //核心线程是3个，当加入第四个任务的时候，就把第四个放在队列中。加入第五个任务时，因为队列满了，就创建新线程执行，创建了线程4。
        //当加入第六个线程时，也会尝试创建线程，但是因为已经达到了线程池最大线程数，所以直接抛异常了。
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(1), (task, pool) -> {
//            System.out.println("我忙不过来呀。。。。。。" + Thread.currentThread().getName());
//        });
//        execute(executor, 3, atomicInteger, 1000L);
//        printExecutorInfo(executor);
//        execute(executor, 3, atomicInteger, 1000L);
//        printExecutorInfo(executor);
//        Thread.sleep(8000);
//        printExecutorInfo(executor);

        //在添加第五个任务时就报错了，因为SynchronousQueue不会保存任务，收到一个任务就去创建新线程。
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), (task, pool) -> {
            System.out.println("我忙不过来呀。。。。。。" + Thread.currentThread().getName());
        });
        execute(executor, 3, atomicInteger, 1000L);
        printExecutorInfo(executor);
        execute(executor, 3, atomicInteger, 1000L);
        printExecutorInfo(executor);
        Thread.sleep(8000);
        printExecutorInfo(executor);

    }

    private static void printExecutorInfo(ThreadPoolExecutor executor) {
        System.out.println("corePoolSize:" + executor.getCorePoolSize()
                + "---poolSize:" + executor.getPoolSize()
                + "---queueSize:" + executor.getQueue().size());
    }

    private static void execute(ThreadPoolExecutor executor, int size, AtomicInteger atomicInteger, Long sleepTime) {
        for (int i = 0; i < size; i++) {
            executor.execute(new MyRunnable(atomicInteger, sleepTime));
        }
    }

    public static String format(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    static class MyRunnable implements Runnable {
        private Long sleepTime;
        private AtomicInteger atomicInteger;

        public MyRunnable(AtomicInteger atomicInteger, Long sleepTime) {
            this.atomicInteger = atomicInteger;
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            String time = format(LocalDateTime.now());
            System.out.println("第" + atomicInteger.getAndIncrement() + "个线程执行时间：" + time +
                    " 线程ID： " + Thread.currentThread().getId());
            try {
                //模拟业务时间
                Thread.sleep(this.sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
