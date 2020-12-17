package com.notes.java.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述 OOM测试
 *      需要配置
 **/
public class OOMDemo {

    public static void main(String[] args) {

        List<Object> data = new ArrayList<>();
        while(true) {
            byte[] testd = new byte[1 * 1024];
            data.add(testd);
            System.out.println(data.size());
        }
    }
}