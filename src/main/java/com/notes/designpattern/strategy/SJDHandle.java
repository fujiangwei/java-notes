package com.notes.designpattern.strategy;

/**
 * 文件描述
 **/
public class SJDHandle implements CommonHandle{
    @Override
    public String handle(String curType) {
        System.out.println("上交大处理相关业务。。。。。。。");
        return "上交大666";
    }
}
