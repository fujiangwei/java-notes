package com.notes.java8.stream;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * 文件描述
 **/
@Slf4j
public class TraservalTest {

    List<Integer> dataList = null;
    List<Integer> forList = null;
    List<Integer> forEachList = null;
    List<Integer> streamList = null;

    final CountDownLatch latch = new CountDownLatch(1);

    @Before
    public void  init() {
        System.out.println("init......");
        dataList = Lists.newArrayList();
        forList = Lists.newArrayList();
        forEachList = Lists.newArrayList();
        streamList = Lists.newArrayList();
        for (int i = 0; i < 10000000; i ++) {
            dataList.add(i);
        }
        System.out.println("init end");
    }

    public String testFor() {
        return start("for");
    }

    public String testForEach() {
        return start("foreach");
    }

    public String testStream() {
        return start("stream");
    }

    private String start(String flag) {
        StopWatch stopWatch = new StopWatch("test");
        stopWatch.start();
//        long start = System.currentTimeMillis();
        if (StringUtils.equals("for", flag)) {
            for (int i = 0; i < dataList.size(); i ++) {
                forList.add(dataList.get(i));
            }
        } else if (StringUtils.equals("forEach", flag)) {
//            dataList.forEach(forEachList::add);
//            for(Integer i : dataList) {
////                forEachList.add(i);
////            }
            dataList.forEach(x ->  forEachList.add(x));
        } else {
//            dataList.stream()
//                    .forEach(streamList::add);
//            dataList.stream()
//                    .forEach(x -> streamList.add(x));
            streamList = dataList.stream()
                    .map(x -> x).collect(Collectors.toList());
        }

        stopWatch.stop();
//        System.out.println(flag + "耗时：" + stopWatch.getTotalTimeMillis());
        return flag + ":" + stopWatch.getTotalTimeMillis();
//        Long time = System.currentTimeMillis() - start;
////        System.out.println(flag + "耗时：" + time);
//        return flag + ":" + time;
    }

    @Test
    public void doTest() throws Exception {
        for (int i = 0; i < 10; i ++) {
            System.out.println("第" + (i + 1) + "次测试结果:   " + testFor() + "===" + testForEach() + "====" + testStream());
        }
    }
}
