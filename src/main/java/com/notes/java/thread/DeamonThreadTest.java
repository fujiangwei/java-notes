package com.notes.java.thread;

/**
 * @Description: 守护线程和用户进程
 *
 *      java线程分为用户线程和守护线程，线程的daemon属性为true表示是守护线程，false表示是用户线程。
 *      守护线程是一种特殊的线程，在后台默默地完成一些系统性的服务，比如垃圾回收线程、JIT线程都是守护线程。
 *      与之对应的是用户线程，用户线程可以理解为是系统的工作线程，它会完成这个程序需要完成的业务操作。
 *      如果用户线程全部结束了，意味着程序需要完成的业务操作已经结束了，系统可以退出了。
 *      所以当系统只剩下守护进程的时候，java虚拟机会自动退出。
 *
 *      针对于守护线程的特点，java 守护线程通常可用于开发一些为其它用户线程服务的功能。
 *          比如说心跳检测，事件监听等。Java 中最有名的守护进程当属GC(垃圾回收)
 *
 *      java中的线程分为用户线程和守护线程
 *      程序中的所有的用户线程结束之后，不管守护线程处于什么状态，java虚拟机都会自动退出
 *      调用线程的实例方法setDaemon()来设置线程是否是守护线程
 *      setDaemon()方法必须在线程的start()方法之前调用，在后面调用会报异常，并且不起效
 *      线程的daemon默认值和其父线程一样
 **/
public class DeamonThreadTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i ++) {
            DeamonThread userThread = new DeamonThread("uhxc *** " + i);
            userThread.setDaemon(Boolean.FALSE);
            userThread.start();
        }


        for (int i = 0; i < 10; i ++) {
            DeamonThread deamonThread = new DeamonThread("shxc === " + i);
            deamonThread.setDaemon(Boolean.TRUE);
            deamonThread.start();
        }


        System.out.println("main thread end >>>>>>>>>>>>>> ");
    }

    public static class DeamonThread extends Thread {
        public DeamonThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(this.getName() + " begin execute," + (this.isDaemon() ? "i am deamon thread" : "i am user thread"));
//            while (true) {
//
//            }
        }
    }
}