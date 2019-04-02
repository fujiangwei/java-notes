package com.notes.java.thread.lock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * descripiton: 队列模型，用于存放数据
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/4/2
 * @time: 21:10
 * @modifier:
 * @since:
 */
public class QueueModel {

    /**
     * 队列存放最大值
     */
    private static final int MAX_SIZE = 10;

    /**
     * 队列存放初始值
     */
    private static final AtomicInteger atomicInteger = new AtomicInteger(1);

    /**
     * 队列对象，使用LinkedList
     */
    private Queue<Integer> queue = new LinkedList();

    /**
     * 锁对象
     */
    Lock lock = new ReentrantLock();

    /**
     * 队列满状态
     */
    Condition full = lock.newCondition();

    /**
     * 队列不足状态
     */
    Condition empty = lock.newCondition();

    /**
     * 存数据到队列
     */
    public void push() {
        //上锁
        lock.lock();
        try {
            //使用while控制
            while (true) {
                //使用while控制队列满处理，即队列长度等于最大值时
                while (queue.size() == MAX_SIZE) {
                    System.out.println("queue is full...");
                    //满状态等待
                    full.await();
                }
                //不满，继续放入队列
                Integer ele = atomicInteger.getAndIncrement();
                boolean isSuc = queue.offer(ele);
                //添加成功
                if (isSuc) {
                    System.out.println("add " + ele + " to queue");
                    //空状态唤醒
                    empty.signal();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    /**
     * 从队列取数据
     */
    public void pull() {
        lock.lock();
        try {
            while (true) {
                while (queue.size() == 0) {
                    System.out.println("queue is empty...");
                    //空状态等待
                    empty.await();
                }
                //取队列头部元素
                Integer ele = queue.poll();
                if (null != ele) {
                    System.out.println("poll " + ele + " from queue");
                    //满状态唤醒
                    full.signal();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
