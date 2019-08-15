package com.notes.designpattern.strategy;

/**
 * 文件描述
 **/
public class ZYHandle implements CommonHandle{
    @Override
    public String handle(String curType) {
        System.out.println("中邮处理相关业务。。。。。。。");
        return "浙邮666";
    }
}
