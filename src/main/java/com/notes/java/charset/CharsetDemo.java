package com.notes.java.charset;

import java.nio.charset.StandardCharsets;

/**
 * 文件描述 字符编码
 **/
public class CharsetDemo {

    public static void main(String[] args) throws Exception {
        String str = "hello";
        String ch = "中国加油";
        String chn = "你最棒";
        System.out.println("hello : " + str.length() + "-" + str.getBytes().length);
        System.out.println("ch : " + ch.length() + "-" + ch.getBytes().length);
        System.out.println("chn : " + chn.length() + "-" + chn.getBytes().length);
        System.out.println("chn-utf : " + chn.length() + "-" + chn.getBytes(StandardCharsets.UTF_8).length);
        System.out.println("chn-gbk : " + chn.length() + "-" + chn.getBytes("GBK").length);
    }

    static String uft2gbk(String uftStr) throws Exception {
        return new String(uftStr.getBytes("GBK"), "GBK");
    }

    static String gbk2uft(String gbkStr) throws Exception {
        return new String(gbkStr.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    }
}