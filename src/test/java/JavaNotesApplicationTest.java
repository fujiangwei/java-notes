import com.notes.JavaNotesApplication;
import com.notes.service.UserService;
import com.notes.utils.RedisDistributeLock;
import com.notes.utils.SpringContextUtil;
import com.notes.utils.ZKDistributeLock;
import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 文件描述 测试类
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JavaNotesApplication.class)
public class JavaNotesApplicationTest {

    @Autowired
    UserService userService;

    @Autowired
    private RedisDistributeLock redisDistributeLock;

    @Test
    public void test() {
        // 通过SpringContextUtil获取上下文内容（即spring托管的）
        UserService userService2 = SpringContextUtil.getBean("userService");
        UserService userService3 = SpringContextUtil.getBean("userService", UserService.class);
        UserService userService4 = SpringContextUtil.getBean(UserService.class);
        System.out.println(userService2.getUser() + userService3.getUser() + userService4.getUser());
    }

    @Test
    public void redisDistributeLockTest() {
        int count = 10;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        try {
            for (int i = 0; i < count; i ++) {
                new Thread(() -> {
                    boolean tryLock = redisDistributeLock.tryLock("lock_" + Thread.currentThread().getName());
                    if (tryLock) {
                        System.out.println(Thread.currentThread().getName() + "获取锁成功");
                    } else {
                        System.out.println(Thread.currentThread().getName() + "获取锁失败");
                    }
                    try {
                        Thread.sleep(RandomUtils.nextInt(2000, 5000));
                        boolean releaseLock = redisDistributeLock.releaseLock("lock_" + Thread.currentThread().getName());
                        if (releaseLock) {
                            System.out.println(Thread.currentThread().getName() + "释放锁成功");
                        } else {
                            System.out.println(Thread.currentThread().getName() + "释放锁失败");
                        }
                        countDownLatch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, "lock" + i).start();
            }
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void zkDistributeLockTest() {
        int count = 10;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        try {
            ZKDistributeLock zkDistributeLock = new ZKDistributeLock("zk_distribute_lock");
            for (int i = 0; i < count; i ++) {
                new Thread(() -> {
                    boolean tryLock = zkDistributeLock.tryLock("zk_distribute_lock");
                    try {
                        if (tryLock) {
                            Thread.sleep(RandomUtils.nextInt(2000, 5000));
//                            boolean releaseLock = zkDistributeLock.releaseLock("zk_distribute_lock");
                        }
                        countDownLatch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, "lock" + i).start();
            }
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 用来并发计数 调了多少次方法
    private static int num;
    private static AtomicInteger count = new AtomicInteger();

    @Test
    public void paralleTest() {
        TestRunnable runnable = new TestRunnable() {
            @Override
            public void runTest() throws Throwable {
                long startTime = System.currentTimeMillis();

                count.incrementAndGet();
                num = count.get();
                System.out.println(Thread.currentThread() + " runTest:" + num + " time is " + (System.currentTimeMillis() - startTime));
            }
        };

        // time 并发时间 单位秒 runnerCount 并发数 100
        int time = 10;
        int runnerCount = 100;
        TestRunnable[] testRunnables = new TestRunnable[runnerCount];
        for (int i = 0; i < runnerCount; i++) {
            testRunnables[i] = runnable;
        }

        MultiThreadedTestRunner multiThreadedTestRunner = new MultiThreadedTestRunner(testRunnables);

        try {
            long startTime = System.currentTimeMillis();
            while (true) {
                long endTime = System.currentTimeMillis();
                multiThreadedTestRunner.runTestRunnables();
                // 并发运行 放在while死循环里 当时间到了，跳出循环
                if ((endTime - startTime) > time * 1000) {
                    System.out.println("over:" + num);
                    break;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}