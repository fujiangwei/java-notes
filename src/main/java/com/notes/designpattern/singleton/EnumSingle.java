package com.notes.designpattern.singleton;

/**
 * @Description: 枚举单例
 *      (1)自由序列化。
 *      (2)保证只有一个实例。
 *      (3)线程安全
 * @author kingson
 **/
public enum EnumSingle {

    INSTANCE;

    public static EnumSingle getInstance() {
        return INSTANCE;
    }

    public void otherMethod() {

    }
}