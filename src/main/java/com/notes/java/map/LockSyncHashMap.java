package com.notes.java.map;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * descripiton: 使用Lock所手动实现HashMap同步
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/4/2
 * @time: 19:00
 * @modifier:
 * @since:
 */
public class LockSyncHashMap {

    /**
     *  重入锁
     */
    ReentrantLock lock = new ReentrantLock(Boolean.TRUE);
    Map<String, String> map;

    public LockSyncHashMap(Map<String, String> map) {
        this.map = map;
    }

    class WriteHashMap implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                map.put("a" + i, "i" + i);
                try {
                    //休眠
                    Thread.sleep(new Random().nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ReadHashMap implements Runnable {
        @Override
        public void run() {
            while (true) {
                //上锁
                lock.lock();
                try {
                    if (map.size() > 1) {
                        for (Map.Entry entry : map.entrySet()) {
                            System.out.println(entry.getKey() + ":" + entry.getValue());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //释放所
                    lock.unlock();
                }

                try {
                    Thread.sleep(new Random().nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
