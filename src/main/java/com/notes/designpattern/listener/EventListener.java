package com.notes.designpattern.listener;

/**
 * 文件描述
 **/
public interface EventListener extends java.util.EventListener {
    /**
     * 事件处理
     */
    void handleEvent(EventObject event);
}
