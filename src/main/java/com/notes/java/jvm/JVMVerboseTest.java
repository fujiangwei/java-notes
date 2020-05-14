package com.notes.java.jvm;

import com.notes.domain.User;

/**
 * 文件描述 jvm verbose参数测试，需要配置VM options参数来测试
 * JVM -verbose[:class|gc|jni] 参数测试
 *      java -verbose:class 在程序运行的时候有多少类被加载,当虚拟机报告类找不到或类冲突时可用此参数来诊断来查看虚拟机从装入类的情况
 *      java -verbose:gc 在虚拟机发生内存回收时在输出设备显示信息，格式如下： [GC (System.gc())  4444K->1198K(121856K), 0.0207504 secs]
 *          [Full GC (System.gc())  1198K->1166K(121856K), 0.0083328 secs] 该参数用来监视虚拟机内存回收的情况。
 *      java –verbose:jni 输出native方法调用的相关情况，一般用于诊断jni调用错误信息
 **/
public class JVMVerboseTest {

    public static void main(String[] args) {
        User u = new User();
        System.gc();
    }
}