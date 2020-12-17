package com.notes.java.pair;

import javafx.util.Pair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.AbstractMap;

/**
 * @Description: Pair配对，在一些方法返回值两个值时适合使用配对,比如返回夫妻两个、返回一对双胞胎等等。
 *  说明：Pair不能当作Controller层的返回值，或者入参。会出问题，因为它不是标准的javaBean，序列化和反序列化会出问题。
 *      一般用于系统内部，比如Service方法直接、工具方法之间传递数据，是一把利器
 **/
public class PairTest {

    public static void main(String[] args) {
        // 1.javafx.util 包中
        Pair<Integer, String> pair = new Pair<>(1, "fujw");
        System.out.println(pair.getKey() + " - " + pair.getValue());

        // 2.JDK自带内部类：AbstractMap.SimpleEntry 和AbstractMap.SimpleImmutableEntry
        AbstractMap.SimpleEntry<String, Integer> entry = new AbstractMap.SimpleEntry<>("fujw", 18);
        System.out.println(entry.getKey() + " - " + entry.getValue());

        // 3.Apache Commons：组件提供的Pair（下问列出专门篇幅讲述，推荐使用）
        // 实际返回的是不可变的ImmutablePair。
        org.apache.commons.lang3.tuple.Pair<Integer, String> apair = org.apache.commons.lang3.tuple.Pair.of(1, "pair");
        System.out.println(apair.getKey() + " = " + apair.getValue());
        System.out.println(apair.getLeft() + " = " + apair.getRight());
        System.out.println(apair.toString());
        // ImmutablePair不可变
        org.apache.commons.lang3.tuple.Pair<Integer, String> iapair = org.apache.commons.lang3.tuple.ImmutablePair.of(100, "ImmutablePair");
        System.out.println(iapair.getKey() + " = " + iapair.getValue());
        System.out.println(iapair.getLeft() + " = " + iapair.getRight());
        System.out.println(iapair.toString());
        // 报错：java.lang.UnsupportedOperationException
        // iapair.setValue("hhhh");
        // MutablePair可变
        org.apache.commons.lang3.tuple.Pair<Integer, String> mapair = org.apache.commons.lang3.tuple.MutablePair.of(200, "MutablePair");
        System.out.println(mapair.getKey() + " = " + mapair.getValue());
        System.out.println(mapair.getLeft() + " = " + mapair.getRight());
        System.out.println(mapair.toString());
        ((MutablePair<Integer, String>) mapair).setLeft(300);
        ((MutablePair<Integer, String>) mapair).setRight("sMutablePair");
        System.out.println(mapair.getKey() + " = " + mapair.getValue());
        System.out.println(mapair.getLeft() + " = " + mapair.getRight());
        System.out.println(mapair.toString());
        //setValue同setRight方法
        mapair.setValue("ssMutablePair");
        System.out.println(mapair.getKey() + " = " + mapair.getValue());
        System.out.println(mapair.getLeft() + " = " + mapair.getRight());
        System.out.println(mapair.toString());
        // 4.扩展使用：Triple、MutableTriple、ImmutableTriple 一次性返回三个对象
        Triple<String, Integer, String> tpair = Triple.of("姓名", 10, "邮箱");
        String name = tpair.getLeft();
        Integer age = tpair.getMiddle();
        String mail = tpair.getRight();
        System.out.println(name);
        System.out.println(age);
        System.out.println(mail);

    }
}