package com.notes.designpattern.listener;

/**
 * 文件描述 监听模式
 **/
public class ListenerTest {

    public static void main(String[] args) {
        EventSource eventSource = new EventSource();
        eventSource.addLister(new EventListener() {
            @Override
            public void handleEvent(EventObject event) {
                System.out.println("t 1" +Thread.currentThread().getName() + " ");
                event.doEvent();
                if (event.getSource().equals("closeWindows")) {
                    System.out.println("t 1 doClose");
                }
            }
        });

        eventSource.addLister((event) -> {
            System.out.println("t 2" +Thread.currentThread().getName() + " ");
            event.doEvent();
            if (event.getSource().equals("openWindows")) {
                System.out.println("t 2 doOpen");
            }
        });

        eventSource.addLister(new EventListener() {
            @Override
            public void handleEvent(EventObject event) {
                System.out.println("t 3" +Thread.currentThread().getName() + " ");
                event.doEvent();
                if (event.getSource().equals("closeWindows")) {
                    System.out.println("t 3 doClose");
                }
            }
        });

//        eventSource.notifyListerEvents(new EventObject("openWindows"));

//        eventSource.subCloseWindows(new EventListener() {
//            @Override
//            public void handleEvent(EventObject event) {
//                event.doEvent();
//                System.out.println("t 4 doClose");
//            }
//        });

        eventSource.subOpenWindows(new EventListener() {
            @Override
            public void handleEvent(EventObject event) {
                event.doEvent();
                System.out.println("t 5 doOpen");
            }
        });

        eventSource.notifyCloseWindows();
    }
}