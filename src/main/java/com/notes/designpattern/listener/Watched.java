package com.notes.designpattern.listener;

import java.util.Observable;

/**
 * 
 * 类Watched.java的实现描述：被观察者，相当于事件监听的事件源和事件对象。又理解为订阅的对象
 * 主要职责：注册/撤销观察者（监听器），接收主题对象（事件对象）传递给观察者（监听器），具体由感兴趣的观察者（监听器）执行
 */
public class Watched extends Observable {

    @Override
    public void notifyObservers(Object arg) {
        // 为避免并发冲突，设置了changed标志位changed =true，则当前线程可以通知所有观察者，内部同步块会完了会设置为false；
        // 通知过程中，正在新注册的和撤销的无法通知到。
        super.setChanged();
        // 事件触发，通知所有感兴趣的观察者
        super.notifyObservers(arg);
    }
}