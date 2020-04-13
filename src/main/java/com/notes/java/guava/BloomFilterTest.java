package com.notes.java.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 文件描述 布隆过滤器
 *      bloomfilter就类似于一个hash set，用于快速判某个元素是否存在于集合中，其典型的应用场景就是快速判断一个key是否存在于某容器，不存在就直接返回。
 **/
public class BloomFilterTest {

    private static final int CAPACITY = 1000000;
    private static final int KEY = 1314;

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), CAPACITY);

    static {
        for (int i = 0; i < CAPACITY; i ++){
            bloomFilter.put(i);
        }
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        if (bloomFilter.mightContain(KEY)) {
            System.out.println("过滤KEY为" + KEY);
        }

        long end = System.nanoTime();
        System.out.println("布隆过滤器消耗时间:" + (end - start));

        int sum = 0;
        // 10000条数据错判率测试
        for (int i = CAPACITY + 20000; i < CAPACITY + 30000; i ++) {
            // 错判率
            if (bloomFilter.mightContain(i)) {
//                System.out.println("Contain(i):" + i);
                sum += 1;
            }
        }
        System.out.println("错判率为:" + sum);
    }
}