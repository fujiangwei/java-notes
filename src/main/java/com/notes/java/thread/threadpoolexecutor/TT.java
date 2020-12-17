package com.notes.java.thread.threadpoolexecutor;

import com.notes.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 文件描述
 **/
public class TT {

    public static void main(String[] args) throws Exception{
        Executors.newFixedThreadPool(5);
        Lock lock = new ReentrantLock();
        AtomicInteger atomicInteger = new AtomicInteger(1);

        atomicInteger.compareAndSet(1, 0);

//        Cipher c = Cipher.getInstance("DESede/ECB/PKCS5Padding");
////        c.init(Cipher.ENCRYPT_MODE, 1, 2);
//        byte[] cipherText = c.doFinal("12".getBytes());

//        String userInput = "2f %3s s.2";
//        String format = "The customer: %s %s has the balance %4$." + userInput + "f";
//        System.out.println(format);

        List<User> users1 = new ArrayList<User>();
        for (int i = 0; i < 10; i ++) {
            User user = new User();
            user.setId(i);
            user.setName("name " + i);
            users1.add(user);
        }

        List<User> users2 = new ArrayList<User>();
        User user2 = new User();
        for (int i = 0; i < 10; i ++) {
            user2.setId(i);
            user2.setName("name " + i);
            users2.add(user2);
        }

        System.out.println(11111111);

        System.out.println(Long.SIZE);

        User task = null;
        while (task != null || (task = getTask()) != null) {

            System.out.println("1234");

        }

    }

    private static User getTask() {
        return new User();
    }


    /**
     * 分布式环境中要使用分布式锁，单机的话用普通的锁（synchronized、Lock）。
     * @param key
     * @param jedis
     * @param lockKey
     * @param uniqueId
     * @param expireTime
     * @return
     */
    public String getWithLock(String key, RedisProperties.Jedis jedis, String lockKey, String uniqueId, long expireTime) {
        // 通过key获取value
        String value = "test"; //redisService.get(key);
        if (StringUtils.isEmpty(value)) {
            // 分布式锁，详细可以参考https://blog.csdn.net/fanrenxiang/article/details/79803037
            // 封装的tryDistributedLock包括setnx和expire两个功能，在低版本的redis中不支持
            try {
                // 获取分布式锁
                boolean locked = true; //redisService.tryDistributedLock(jedis, lockKey, uniqueId, expireTime);
                if (locked) {
                    value = "111";//userService.getById(key);
//                    redisService.set(key, value);
//                    redisService.del(lockKey);
                    return value;
                } else {
                    // 其它线程进来了没获取到锁便等待50ms后重试
                    Thread.sleep(50);
                    getWithLock(key, jedis, lockKey, uniqueId, expireTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return value;
            } finally {
                // 释放锁
                //redisService.releaseDistributedLock(jedis, lockKey, uniqueId);
            }
        }
        return value;
    }
}