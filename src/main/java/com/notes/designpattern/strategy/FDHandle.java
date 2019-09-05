package com.notes.designpattern.strategy;

/**
 * 文件描述
 **/
public class FDHandle implements CommonHandle {
    @Override
    public String handle(String curType) {
        System.out.println("复旦处理相关业务。。。。。。。");
        return "复旦666";
    }
}
