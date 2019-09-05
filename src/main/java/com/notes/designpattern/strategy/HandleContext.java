package com.notes.designpattern.strategy;

/**
 * 文件描述
 **/
public class HandleContext {

    private CommonHandle commonHandle;

    public HandleContext(CommonHandle commonHandle) {
        this.commonHandle = commonHandle;
    }

    public String handle(String curType) {
        return this.commonHandle.handle(curType);
    }
}
