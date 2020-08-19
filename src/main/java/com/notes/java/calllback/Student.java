package com.notes.java.calllback;

/**
 * 文件描述
 **/
public class Student implements Runnable{

    private CallbackIntervice callbackIntervice;

    public Student(CallbackIntervice callbackIntervice) {
        this.callbackIntervice = callbackIntervice;
    }

    public void doHomework() throws Exception {
        Thread.sleep(5000);
        callbackIntervice.execute();
    }

    public void doHomework2() throws Exception {
        Thread.sleep(5000);
        callbackIntervice.callback(this);
    }

    public void doHomework3() throws Exception {
        System.out.println(Thread.currentThread().getName()+" current thread");
        // 起线程跑
        Thread t = new Thread(this);
        t.start();
    }

    public void callback() {
        System.out.println("ok, i know it");
    }

    @Override
    public void run() {
        callbackIntervice.callback(this);
    }
}