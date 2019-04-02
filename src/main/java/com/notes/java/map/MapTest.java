package com.notes.java.map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * descripiton: Map遍历方式
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/3/31
 * @time: 23:42
 * @modifier:
 * @since:
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String, String> tmap = new HashMap<String, String>(5);

        tmap.put("a", "aa");
        tmap.put("b", "bb");
        tmap.put("c", "cc");
        tmap.put("d", "dd");
        tmap.put("e", "ee");

        System.out.println("************ 1 entrySet ************");
        for (Map.Entry<String, String> map : tmap.entrySet()) {
            String key = map.getKey();
            String value = map.getValue();
            System.out.println(key + ":" + value);
        }

        System.out.println("************ 2 iterator ************");
        Iterator<Map.Entry<String, String>> iterator = tmap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey() + ":" + next.getValue());
        }

        //只需要map中的键或者值，可以通过keySet或values来实现遍历
        System.out.println("************ 3 keySet 通过键找值遍历（效率低）************");
        Set<String> keySet = tmap.keySet();
        for (String key : keySet) {
            String value = tmap.get(key);
            System.out.println(key + ":" + value);
        }

        System.out.println("************ 4 values ************");
        Collection<String> values = tmap.values();
        for (String value : values) {
            System.out.println(value);
        }

        System.out.println("************ 5 java8 forEach ************");
        tmap.forEach((key, value) ->
                System.out.println(key + ":" + value)
        );

        //同步Map
        ConcurrentHashMap<String, String> chm = new ConcurrentHashMap<>();

        //lock手动同步Map测试
        LockSyncHashMap lockSyncHashMap = new LockSyncHashMap(new HashMap<>());
//        new Thread(lockSyncHashMap.new WriteHashMap()).start();
//        new Thread(lockSyncHashMap.new ReadHashMap()).start();

        System.out.println("*******LinkedHashMap*******");
        //第三个参数设置true，表示按照访问顺序排序。每次访问一个元素（get或put），被访问的元素都被放到最后。
        LinkedHashMap linkedHashMap = new LinkedHashMap(16, 0.75f, true);
        linkedHashMap.put("1", "1");
        linkedHashMap.put("2", "2");
        linkedHashMap.put("3", "3");
        loopLinkedHashMap(linkedHashMap);
        linkedHashMap.get("1");
        loopLinkedHashMap(linkedHashMap);
        linkedHashMap.put("4", "4");
        loopLinkedHashMap(linkedHashMap);
        linkedHashMap.get("3");
        loopLinkedHashMap(linkedHashMap);
        linkedHashMap.put("5", "5");
        loopLinkedHashMap(linkedHashMap);
        linkedHashMap.get("2");
        loopLinkedHashMap(linkedHashMap);

    }

    public static void loopLinkedHashMap(LinkedHashMap<String, String> map) {
        if (map.isEmpty()) {
            return;
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.print(entry + "\t");
        }
        System.out.println();

    }

}
