package com.notes.java8.base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 文件描述 Base64使用
 **/
public class Base64Demo {
    public static void main(String[] args) {
        String text = "base64 in java8 lib";
        //编码
        String encode = Base64.getEncoder()
                .encodeToString(text.getBytes(StandardCharsets.UTF_8));
        System.out.println(encode);

        //解码
        String decode = new String(Base64.getDecoder().decode(encode), StandardCharsets.UTF_8);
        System.out.println(decode);
    }
}
