package com.notes.java.classloader;

/**
 * descripiton: 类加载器
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/4/9
 * @time: 14:04
 * @modifier:
 * @since:
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("systemClassLoader -> " + systemClassLoader);
        ClassLoader systemParent = systemClassLoader.getParent();
        System.out.println("systemParentClassLoader -> " + systemParent);
        ClassLoader systemParentParent = systemParent.getParent();
        System.out.println("systemParentParentClassLoader -> " + systemParentParent);
    }
}
