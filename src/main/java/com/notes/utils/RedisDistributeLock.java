package com.notes.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * 文件描述 redis实现分布式锁
 **/
@Component
public class RedisDistributeLock extends AbstractDistributeLock {

    @Autowired
    private JedisPool jedisPool;

    private static final String LOCKED_KEY = "distribute_lock_key";
    /**
     * 获取锁资源成功
     */
    private static final String LOCKED_SUCCESS = "OK";
    /**
     * 释放锁成功
     */
    private static final Long RELEASE_SUCCESS = 1L;
    /**
     * NX表示当key不存在时才set值
     */
    private static final String NX = "NX";
    /**
     * XX表示当key存在时才set值
     */
    private static final String XX = "XX";
    /**
     * 过期时间
     */
    private static final String EXPIRE_TIME = "EX";

    @Override
//    public boolean tryLock(String value) {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            // setnx不存在新增，否则啥也不做
//            Long result = jedis.setnx(LOCKED_KEY, "2020");
//            if (result.intValue() == 1) {
//                // 30min过去时间,
//                // 问题场景：该方式如果在执行完上一步之后服务器出问题，设置的锁将无法释放（客户端无法释放，超时设置又失败），可能会导致死锁
//                jedis.expire(LOCKED_KEY, 1800);
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            close(jedis);
//        }
//        return false;
//    }
    public boolean tryLock(String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            // 1000s
            String result = jedis.set(LOCKED_KEY, value, NX, EXPIRE_TIME, 10000);
            if (StringUtils.equals(LOCKED_SUCCESS, result)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }

        return false;
    }

    @Override
//    public boolean releaseLock(String value) {
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            //问题场景：A/B两线程，A查找LOCKED_KEY存在，准备删除key,
//            // 此时B线程查找key时，A线程的key刚好失效，且A还未进行删除操作，B则获取锁成功写入数据，此时A继续执行删除key操作，将B获取的锁对象删除
//            if (jedis.exists(LOCKED_KEY)) {
//                jedis.del(LOCKED_KEY);
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            close(jedis);
//        }
//        return false;
//    }
    public boolean releaseLock(String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String luaScript = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object eval = jedis.eval(luaScript, Lists.newArrayList(LOCKED_KEY), Lists.newArrayList(value));
            if (RELEASE_SUCCESS.equals(eval)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return false;
    }

    private void close(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }
}