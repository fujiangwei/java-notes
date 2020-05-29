package com.notes.designpattern.singleton;

/**
 * 文件描述
 *
 * @ProductName: Hundsun HEP
 * @ProjectName: java-notes
 * @Package: com.notes.designpattern.singleton
 * @Description: note
 * @Author: fujw27320
 * @CreateDate: 2020/5/29 15:48
 * @UpdateUser: fujw27320
 * @UpdateDate: 2020/5/29 15:48
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 Hundsun Technologies Inc. All Rights Reserved
 **/
public enum SingletonEnum {

    INSTANCE;

    public SingletonEnum getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        SingletonEnum instance = SingletonEnum.INSTANCE;
        SingletonEnum instance2 = SingletonEnum.INSTANCE;
        System.out.println("instance:" + instance);
        System.out.println("instance2:" + instance2);
        System.out.println(instance == instance2);
    }
}