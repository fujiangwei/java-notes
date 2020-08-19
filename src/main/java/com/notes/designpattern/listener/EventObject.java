package com.notes.designpattern.listener;

/**
 * 文件描述
 **/
public class EventObject extends java.util.EventObject {
    private static final long serialVersionUID = 1L;

    public EventObject(Object source) {
        super(source);
    }

    public void doEvent(){
        System.out.println("event source :"+ this.getSource());
    }
}