package com.notes.utils;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 文件描述 zk实现分布式锁
 *      zookeeper的四种节点类型:
 *          1、持久化节点 ：所谓持久节点，是指在节点创建后，就一直存在，直到有删除操作来主动清除这个节点——不会因为创建该节点的客户端会话失效而消失。
 *          2、持久化顺序节点：这类节点的基本特性和上面的节点类型是一致的。额外的特性是，在ZK中，每个父节点会为他的第一级子节点维护一份时序，会记录每个子节点创建的先后顺序。基于这个特性，在创建子节点的时候，可以设置这个属性，那么在创建节点过程中，ZK会自动为给定节点名加上一个数字后缀，作为新的节点名。这个数字后缀的范围是整型的最大值。基于持久顺序节点原理的经典应用-分布式唯一ID生成器。
 *          3、临时节点：和持久节点不同的是，临时节点的生命周期和客户端会话绑定。也就是说，如果客户端会话失效，那么这个节点就会自动被清除掉。注意，这里提到的是会话失效，而非连接断开。另外，在临时节点下面不能创建子节点，集群zk环境下，同一个路径的临时节点只能成功创建一个，利用这个特性可以用来实现master-slave选举。
 *          4、临时顺序节点：相对于临时节点而言，临时顺序节点比临时节点多了个有序，也就是说每创建一个节点都会加上节点对应的序号，先创建成功，序号越小。其经典应用场景为实现分布式锁。
 *
 *      基本思路如下：
 *          1、在你指定的节点下创建一个锁目录lock
 *          2、线程X进来获取锁在lock目录下，并创建临时有序节点；
 *          3、线程X获取lock目录下所有子节点，并获取比自己小的兄弟节点，如果不存在比自己小的节点，说明当前线程序号最小，顺利获取锁；
 *          4、此时线程Y进来创建临时节点并获取兄弟节点 ，判断自己是否为最小序号节点，发现不是，于是设置监听（watch）比自己小的节点（这里是为了发生上面说的羊群效应）；
 *          5、线程X执行完逻辑，删除自己的节点，线程Y监听到节点有变化，进一步判断自己是已经是最小节点，顺利获取锁。
 **/
public class ZKDistributeLock extends AbstractDistributeLock {

    public static Logger log = LoggerFactory.getLogger(ZKDistributeLock.class);

    /**
     * 可重入排它锁
     */
    private InterProcessMutex interProcessMutex;

    /**
     * 竞争资源标志
     */
    private String lockName;

    /**
     * 根节点
     */
    private static final String ROOT_PATH = "/distributed/lock/";

    private static CuratorFramework curatorFramework;

    private static String ZK_URL = "localhost:2181";

    static {
        curatorFramework = CuratorFrameworkFactory.newClient(ZK_URL, new ExponentialBackoffRetry(1000, 3));
        curatorFramework.start();
    }

    public ZKDistributeLock(String lockName) {
        try {
            this.lockName = lockName;
            interProcessMutex = new InterProcessMutex(curatorFramework, ROOT_PATH + lockName);
        }catch (Exception e){
            log.error("initial InterProcessMutex exception = " + e);
        }
    }

    @Override
    public boolean tryLock(String value) {
        int flag = 0;
        try {
            // 重试2次，每次最大等待2s，也就是最大等待4s
            while (!interProcessMutex.acquire(2, TimeUnit.SECONDS)) {
                flag ++;
                if (flag > 1) {
                    break;
                }
            }

            if(flag > 1){
                log.info("Thread:{} 获取锁失败", Thread.currentThread().getName());
                return false;
            } else {
                log.info("Thread:{} 获取锁成功", Thread.currentThread().getName());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean releaseLock(String value) {
        try {
            if (null != interProcessMutex && interProcessMutex.isAcquiredInThisProcess()) {
                interProcessMutex.release();
                curatorFramework.delete().inBackground().forPath(ROOT_PATH + lockName);
                log.info("Thread:{} 释放锁成功", Thread.currentThread().getName());
                return true;
            }
        } catch (Exception e) {
            log.info("Thread:{} 释放锁异常 {}", Thread.currentThread().getName(), e);
        }

        return false;
    }
}