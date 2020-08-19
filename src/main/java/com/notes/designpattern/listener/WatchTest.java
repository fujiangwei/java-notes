package com.notes.designpattern.listener;

import java.util.Observable;
import java.util.Observer;

/**
 * 文件描述 观察者模式
 *
 * 事件驱动模型，将调用者和被调用者通过一个链表、回调函数来解耦掉，相互独立。
 * 整个设计模式的初衷也就是要做到低耦合，低依赖。
 * 消息中间件是什么一个模型？将生产者+服务中心（事件源）和消费者（监听器）通过消息队列解耦掉. 消息这相当于具体的事件对象，只是存储在一个队列里（有消峰填谷的作用），服务中心回调消费者接口通过拉或取的模型响应，基于这个模型，实现一个简单的消息中间件是可以的。
 * 还比如Guava ListenableFuture，采用监听器模式就解决了future.get()一直阻塞等待返回结果的问题。
 **/
public class WatchTest {
    public static void main(String[] args) {
        Watched watched = new Watched();
        WatcherDemo watcherDemo = new WatcherDemo();
        watched.addObserver(watcherDemo);
        watched.addObserver(new Observer(){
            @Override
            public void update(Observable o, Object arg) {
                if(arg.toString().equals("closeWindows")){
                    System.out.println("close");
                }
            }
        });
        //触发打开窗口事件，通知观察者
        watched.notifyObservers("openWindows");
        //触发关闭窗口事件，通知观察者
        watched.notifyObservers("closeWindows");
    }
}