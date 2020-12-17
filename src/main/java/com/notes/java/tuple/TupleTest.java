package com.notes.java.tuple;

/**
 * 文件描述
 **/
public class TupleTest {
    public static void main(String[] args) {
        Tuple t2 = Tuple.of(1, "one");
        System.out.println(t2);
        System.out.println(t2._1().get() + "," + t2._2().get());

        Tuple t3 = Tuple.of(1, "one", "一");
        System.out.println(t3);
        System.out.println(t3._1().get() + "," + t3._2().get() + "," + t3._3().get());

        Tuple t4 = Tuple.of(1, "one", "一", "壹");
        System.out.println(t4);
        System.out.println(t4._1().get() + "," + t4._2().get() + "," + t4._3().get() + "," + t4._4().get());

        Tuple t5 = Tuple.of(1, "one", "一", "壹", "yi");
        System.out.println(t5);
        System.out.println(t5._1().get() + "," + t5._2().get() + "," + t5._3().get() + "," + t5._4().get() + "," + t5._5().get());
    }
}