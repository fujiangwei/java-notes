package com.notes.java.security;

import java.util.Random;

/**
 * 文件描述
 **/
public class FindBugsDemo {

    public static void main(String[] args) {
        System.out.println(generateSecretToken());
    }

    public static String generateSecretToken() {
        Random r = new Random();
        return Long.toHexString(r.nextLong());
    }
}