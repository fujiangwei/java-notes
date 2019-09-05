package com.notes.designpattern.strategy;

import com.notes.java8.stream.ReqParamDTO;

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

//        map.put("1", "!");
//        System.out.println(map.get("2"));

        ReqParamDTO reqParamDTO = new ReqParamDTO();
        Map<String, Object> reqParamMap = new HashMap<String, Object>(5);
        reqParamDTO.setReqParamMap(reqParamMap);

        test(reqParamDTO);
        System.out.println(reqParamMap.get("12"));
    }

    public static void test(ReqParamDTO reqParamDTO) {
        Map<String, Object> reqParamMap = reqParamDTO.getReqParamMap();
        reqParamMap.put("1", 111);
        reqParamMap.put("12", 1212);
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
