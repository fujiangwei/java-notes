package com.notes.java.thread;

/**
 * descripiton: sleep和wait
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/4/2
 * @time: 20:16
 * @modifier:
 * @since:
 */
public class Sleep2Wait implements Runnable {

    private int i = 5;

    public static void main(String[] args) throws InterruptedException {
        Sleep2Wait sleep2Wait = new Sleep2Wait();
        //启动线程,此时该线程休眠10s，因为有synchronized修饰，该线程将占用着当前线程锁
        new Thread(sleep2Wait).start();
        //测试是否有线程来执行方法handle2
        sleep2Wait.handle2();

        //结果,说明不能访问
        // handle2 i = 50
        // handle i = 60

    }

    /**
     * 方法1 +
     */
    public void handle() {
        synchronized (this) {
            i += 10;
            System.out.println("handle i = " + i);
        }
    }

    /**
     * 方法2 休眠2s *
     *
     * @throws InterruptedException
     */
    public void handle2() throws InterruptedException {
        synchronized (this) {
            // 休眠10S阻塞线程,以验证当前线程对象的锁被占用时(结果是不能访问）,是否可以访问其他同步代码块
            Thread.sleep(10000);
            i *= 10;
            System.out.println("handle2 i = " + i);
        }
    }

    @Override
    public void run() {
        handle();
    }
}
