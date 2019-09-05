package com.notes.designpattern.strategy;

/**
 * 文件描述
 **/
public class BDHandle implements CommonHandle {
    @Override
    public String handle(String curType) {
        System.out.println("北大处理相关业务。。。。。。。");
        return "北大666";
    }
}
