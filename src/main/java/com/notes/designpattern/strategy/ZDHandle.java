package com.notes.designpattern.strategy;

/**
 * 文件描述
 **/
public class ZDHandle implements CommonHandle {
    @Override
    public String handle(String curType) {
        System.out.println("浙大处理相关业务。。。。。。。");
        return "浙大666";
    }
}
