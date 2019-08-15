package com.notes.designpattern.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件描述
 **/
public class ResultHandle {

    public static final String ZD_UNIVERSE = "浙大";
    public static final String FD_UNIVERSE = "复旦";
    public static final String SJD_UNIVERSE = "上交大";
    public static final String ZY_UNIVERSE = "中邮";
    public static final String BD_UNIVERSE = "北大";

    private HandleContext handleContext;

    private static Map<String, CommonHandle> handleMap = new HashMap<String, CommonHandle>(5);
    static {
        handleMap.put(ZD_UNIVERSE, new ZDHandle());
        handleMap.put(FD_UNIVERSE, new FDHandle());
        handleMap.put(SJD_UNIVERSE, new SJDHandle());
        handleMap.put(ZY_UNIVERSE, new ZYHandle());
        handleMap.put(BD_UNIVERSE, new BDHandle());
    }

    public String handle(String curType) {
        handleContext = new HandleContext(handleMap.get(curType));
        return handleContext.handle(curType);
    }
}
