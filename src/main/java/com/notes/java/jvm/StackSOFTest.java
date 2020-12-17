package com.notes.java.jvm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 栈溢出 StackOverflowError
 * jvm参数:-Xss128k 设置线程栈内存大小
 */
public class StackSOFTest {
    /**
     * 计数
     */
    AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args){
        StackSOFTest s = new StackSOFTest();
        s.stackLeak();
    }

    /**
     * 递归，疯狂的入栈，有意不让出栈
     */
    public void stackLeak(){
        int surIndex = count.getAndIncrement();
        System.out.println("curIndex = " + surIndex);
        stackLeak();
    }
} 