package com.notes.java8.map;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 文件描述 map常用使用
 *  put与putIfAbsent区别:
 *      put在放入数据时，如果放入数据的key已经存在与Map中，最后放入的数据会覆盖之前存在的数据，
 *      putIfAbsent在放入数据时，如果存在重复的key，那么putIfAbsent不会放入值。
 *      putIfAbsent如果传入key对应的value已经存在，就返回存在的value，不进行替换。如果不存在，就添加key和value，返回key关联的旧的值即null
 **/
public class MapTest {

    public static void main(String[] args) {
        // 1.测试put 和putIfAbsent
        Map<String, String> myMap = Maps.newHashMap();
        String put = myMap.put("1", "1");
        System.out.println(put);
        // putIfAbsent 方法:如果key关联的value不存在，则关联新的value值，返回key关联的旧的值
        String s2 = myMap.putIfAbsent("1", "11");
        System.out.println(s2);
        String s1 = myMap.putIfAbsent("2", "2");
        System.out.println(s1);

        // getOrDefault
        String orDefault = myMap.getOrDefault("3", "3d");
        System.out.println(orDefault);

        // forEach
        myMap.forEach((k, v) -> System.out.println("k = " + k + " : v = " + v));

        // replace(K key, V value) 方法:如果map中存在key，则替换成value值，否则返回null
        myMap.put("3", "3");
        myMap.replace("3", "33");
        myMap.forEach((k, v) -> System.out.println("k = " + k + " : v = " + v));

        // replace(K key, V oldValue, V newValue) 方法:如果key关联的值与指定的oldValue的值相等，则替换成新的newValue
        myMap.put("4", "4");
        boolean new4 = myMap.replace("4", "4", "new4");
        boolean new44 = myMap.replace("4", "new44", "new444");
        System.out.println(new4 + " " + new44);
        myMap.forEach((k, v) -> System.out.println("k = " + k + " : v = " + v));

        // replaceAll 方法:替换Map中所有Entry的value值，这个值由旧的key和value计算得出，接收参数 (K, V) -> V
        myMap.replaceAll((k, v) -> ("new" + k) + v);
        myMap.forEach((k, v) -> System.out.println("k = " + k + " : v = " + v));

        myMap.put("5", "5");
        // remove方法：接收2个参数，key和value，如果key关联的value值与指定的value值相等（equals)，则删除这个元素
        boolean new41 = myMap.remove("5", "55");
        myMap.forEach((k, v) -> System.out.println("k = " + k + " : v = " + v));
        boolean new4new4 = myMap.remove("5", "5");
        System.out.println(new41 + " " + new4new4);
        myMap.forEach((k, v) -> System.out.println("k = " + k + " : v = " + v));

        myMap.put("6", "6");
        myMap.forEach((k, v) -> System.out.println("k = " + k + " : v = " + v));
        myMap.remove("6");
        myMap.forEach((k, v) -> System.out.println("k = " + k + " : v = " + v));

        myMap.put("7", "7");
        // computeIfAbsent 方法:如果指定的key不存在，则通过指定的K -> V计算出新的值设置为key的值
        myMap.computeIfAbsent("7", (k) -> (k) + "fjw");
        myMap.forEach((k, v) -> System.out.println("k = " + k + " : v = " + v));
        myMap.computeIfAbsent("77", (k) -> (k) + "fjw");
        myMap.forEach((k, v) -> System.out.println("k = " + k + " : v = " + v));
        // computeIfPresent 方法：如果指定的key存在，则根据旧的key和value计算新的值newValue,
        // 如果newValue不为null，则设置key新的值为newValue, 如果newValue为null, 则删除该key的值
        myMap.computeIfPresent("77", (k, v) -> (k + v) + "fjw");
        myMap.forEach((k, v) -> System.out.println("k = " + k + " : v = " + v));
        myMap.computeIfPresent("77", (k, v) -> null);
        myMap.forEach((k, v) -> System.out.println("k = " + k + " : v = " + v));

    }
}