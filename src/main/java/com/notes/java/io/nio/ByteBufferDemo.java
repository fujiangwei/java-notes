package com.notes.java.io.nio;

import java.nio.ByteBuffer;

/**
 * 文件描述
 **/
public class ByteBufferDemo {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        System.out.println("写模式：capacity：" + byteBuffer.capacity() + "-limit：" + byteBuffer.limit() + "-position：" + byteBuffer.position());
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 2);
        byteBuffer.put((byte) 3);
        System.out.println("写模式：capacity：" + byteBuffer.capacity() + "-limit：" + byteBuffer.limit() + "-position：" + byteBuffer.position());

        // 切换为读取模式，否则读取将从写当前的position位置开始,读取会异常
        byteBuffer.flip();
        byte b = byteBuffer.get();
        System.out.println(b);
        System.out.println("读模式：capacity：" + byteBuffer.capacity() + "-limit：" + byteBuffer.limit() + "-position：" + byteBuffer.position());
        byte b2 = byteBuffer.get();
        System.out.println(b2);
        System.out.println("读模式：capacity：" + byteBuffer.capacity() + "-limit：" + byteBuffer.limit() + "-position：" + byteBuffer.position());
//        byte b3 = byteBuffer.get();
//        System.out.println(b3);
//        System.out.println("读模式：capacity：" + byteBuffer.capacity() + "-limit：" + byteBuffer.limit() + "-position：" + byteBuffer.position());

        // 读取异常
//        byte b4 = byteBuffer.get();
        // 清除已读数据
        byteBuffer.compact();
        System.out.println("写模式：capacity：" + byteBuffer.capacity() + "-limit：" + byteBuffer.limit() + "-position：" + byteBuffer.position());

        byteBuffer.put((byte) 4);
        byteBuffer.put((byte) 5);
        byteBuffer.put((byte) 6);
        System.out.println("写模式：capacity：" + byteBuffer.capacity() + "-limit：" + byteBuffer.limit() + "-position：" + byteBuffer.position());
    }

}