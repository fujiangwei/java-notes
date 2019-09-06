package com.notes.designpattern.strategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.notes.designpattern.strategy.ResultHandle.*;

/**
 * 文件描述 策略模式
 **/
public class StrategyDemo {

    public static void main(String[] args) {
        String handle = handle(ZD_UNIVERSE);
//        System.out.println(handle);

        ResultHandle resultHandle = new ResultHandle();
        String handle2 = resultHandle.handle(SJD_UNIVERSE);
//        System.out.println(handle2);

        List<Object> emptyList = Collections.emptyList();
//        emptyList.add("123");

        String s = "1";
//        System.out.println(s.charAt(0));

        int i1 = 2;
        Integer i2 = 1;

        System.out.println(i2 != 1);
//        System.out.println(i1 > i2);
//        System.out.println(i1.compareTo(i2));

        Map<String, String> map = new HashMap<String, String>();

    }

    /**
     * switch 模式
     *
     * @param type
     * @return
     */
    public static String handle(String type) {
        switch (type) {
            case ZD_UNIVERSE:
                return ZD_UNIVERSE;
            case ZY_UNIVERSE:
                return ZY_UNIVERSE;
            default:
                return BD_UNIVERSE;
        }
    }
}
