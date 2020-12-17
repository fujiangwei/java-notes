package com.notes.java.jvm;

import com.notes.domain.User;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 堆内存溢出 OOM
 * jvm参数：-Xms20m -Xmx20m
 */
public class HeapOOMTest {
    public static void main(String[] args){
        // 作为GC Root
        LinkedList list = new LinkedList();
        // 计数
        AtomicInteger count = new AtomicInteger(0);
        // 循环创建对象
        while(true){
            int curIndex = count.getAndIncrement();
            list.add(new User());
            System.out.println("curIndex = " + curIndex);
        }

    }
    static String aa = "aa";
    public void test() {
        aa.split("");
    }
} 