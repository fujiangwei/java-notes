package com.notes.java.constantpool;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/4/8
 * @time: 12:38
 * @modifier:
 * @since:
 */
public class ConstPoolTest {

    /**
     * String.intern()是一个Native方法.它的作用是：
     * 如果字符串常量池中已经包含了一个等于此String对象的字符串，则返回代表池中这个字符串的String对象；
     * 否则，将此String对象包含的字符串添加到常量池中，并且返回此字符串的引用。
     *
     * @param args
     */
    public static void main(String[] args) {
        //"计算机软件"这个字符串是首次出现的
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);


        //"java"已经存在常量池中，不符合首次出现的原则
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
