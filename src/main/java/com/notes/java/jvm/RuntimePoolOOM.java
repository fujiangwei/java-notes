package com.notes.java.jvm;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 常量池溢出
 *      存的是一些常量、字面量。如果运行时常量池内存不足，就会发生内存溢出。从jdk1.7开始，运行时常量池移动到了堆中，所以如果堆的内存不足，也会导致运行时常量池内存溢出
 * jvm参数：
 * jdk6以前：-XX:PermSize=10M -XX:MaxPermSize=10M
 * jdk7开始：-Xms10m -Xmx10m
 **/
public class RuntimePoolOOM { 
    public static void main(String[] args){
        // 保持常量的引用，防止被fullgc收集
        LinkedList big = new LinkedList();
        // 计数
        AtomicInteger count = new AtomicInteger(0);
        while(true){
            int curIndex = count.getAndIncrement();
            // 将常量添加到常量池 intern方法
            big.add(String.valueOf(curIndex).intern());
            System.out.println("curIndex = " + curIndex);
        }
    }
} 