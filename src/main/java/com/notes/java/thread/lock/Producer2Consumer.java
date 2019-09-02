package com.notes.java.thread.lock;

import java.util.concurrent.TimeUnit;

/**
 * descripiton: 使用lock实现生产者和消费者模式
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/4/2
 * @time: 20:57
 * @modifier:
 * @since:
 */
public class Producer2Consumer {
    public static void main(String[] args) throws InterruptedException {
        QueueModel queueModel = new QueueModel();

        Thread pt1 = new Thread(new Producer(queueModel), "pt1");
//        Thread pt2 = new Thread(new Producer(queueModel));
//        Thread pt3 = new Thread(new Producer(queueModel));
//        Thread pt4 = new Thread(new Producer(queueModel));
        Thread ct1 = new Thread(new Consumer(queueModel), "ct1");
        Thread ct2 = new Thread(new Consumer(queueModel));
        Thread ct3 = new Thread(new Consumer(queueModel));

        //启动线程
        pt1.start();
//        pt2.start();
//        pt3.start();
//        pt4.start();
        ct1.start();
        ct2.start();
        ct3.start();

        //休眠5s
        TimeUnit.SECONDS.sleep(5);
    }
}

/**
 * 生产者
 */
class Producer implements Runnable {

    private QueueModel queueModel;

    public Producer(QueueModel queueModel) {
        this.queueModel = queueModel;
    }

    @Override
    public void run() {
        try {
            System.out.println("ptName :" + Thread.currentThread().getName());
            queueModel.push();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable {

    private QueueModel queueModel;

    public Consumer(QueueModel queueModel) {
        this.queueModel = queueModel;
    }

    @Override
    public void run() {
        try {
            System.out.println("ctName :" + Thread.currentThread().getName());
            queueModel.pull();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

