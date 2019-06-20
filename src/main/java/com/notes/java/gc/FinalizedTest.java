package com.notes.java.gc;

public class FinalizedTest {

    private final static int ONE_MB = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
//        System.out.println(FinalizedEscapeTestCase.caseForEscape);
//        FinalizedEscapeTestCase.caseForEscape = new FinalizedEscapeTestCase();
//        System.out.println(FinalizedEscapeTestCase.caseForEscape);
//        FinalizedEscapeTestCase.caseForEscape = null;
//        System.gc();
//        Thread.sleep(100);
//        System.out.println(FinalizedEscapeTestCase.caseForEscape);

        // -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -verbose:gc
        // eden有8MB的存储控件（通过参数配置），
        // 前6MB的数据优先分配到eden区域,当下一个2MB存放时，
        // 因空间已满，触发一次GC，但是这部分数据因为没有回收（引用还在，当赋值为null后则不会转移），
        // 数据会被复制到s0区域，但是s0区域不够存储，因此直接放入老生代区域，新的2MB数据存放在eden区域
//        @SuppressWarnings("unused")
//        byte[] testCase1, testCase2, testCase3, testCase4;
//        testCase1 = new byte[2 * ONE_MB];
//        testCase2 = new byte[2 * ONE_MB];
//        testCase3 = new byte[2 * ONE_MB];
////     testCase1 = null;
////     testCase2 = null;
////     testCase3 = null;
//        testCase4 = new byte[2 * ONE_MB];

        // -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -verbose:gc
        //没有触发GC日志，而数据是直接进入老生代的
//        @SuppressWarnings("unused")
//        byte[] testCase1, testCase2, testCase3, testCase4;
//        testCase1 = new byte[8 * ONE_MB];
//     testCase2 = new byte[2*ONE_MB];
//     testCase3 = new byte[2*ONE_MB];
//     testCase1 = null;
//     testCase2 = null;
//     testCase3 = null;
//     testCase4 = new byte[2*ONE_MB];

        @SuppressWarnings("unused")
        byte[] testCase1, testCase2, testCase3, testCase4;
        testCase1 = new byte[1 * ONE_MB / 4];
        testCase2 = new byte[7 * ONE_MB + 3 * ONE_MB / 4];
        testCase2 = null;
        testCase3 = new byte[7 * ONE_MB + 3 * ONE_MB / 4];
        testCase3 = null;
        testCase4 = new byte[ONE_MB];
    }
}