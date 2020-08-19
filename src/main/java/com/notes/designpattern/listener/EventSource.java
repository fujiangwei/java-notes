package com.notes.designpattern.listener;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件描述
 **/
public class EventSource {

    /**
     * 监听器列表，监听器的注册则加入此列表
     */
    private ConcurrentHashMap<String, Vector<EventListener>> listenerMap = new ConcurrentHashMap<String, Vector<EventListener>>();

    /**
     * 注册监听器
     * @param listener
     */
    public void addLister(EventListener listener) {
        Vector<EventListener> listeners = listenerMap.get("default");
        if (null == listeners) {
            listeners = new Vector<EventListener>();
            listeners.add(listener);
        } else {
            listeners.add(listener);
        }
        listenerMap.put("default", listeners);
    }

    /**
     * 撤销注册
     * @param listener
     */
    public void removeLister(EventListener listener) {
        Vector<EventListener> listeners = listenerMap.get("default");
        if (null == listeners) {
            System.out.println("no lister");
            return;
        }
        listeners.remove(listener);
        listenerMap.put("default", listeners);
    }

    public Integer listerSize() {
        return this.listenerMap.size();
    }

    public void notifyCloseWindows() {
        notifyListerEvents(new EventObject("closeWindows"));
    }

    public void subCloseWindows(EventListener listener) {
        System.out.println("subscribe closeWindows");
        Vector<EventListener> listeners = listenerMap.get("closeWindows");
        if (null == listeners) {
            listeners = new Vector<EventListener>();
            listeners.add(listener);
        } else {
            listeners.add(listener);
        }
        listenerMap.put("closeWindows", listeners);
    }

    public void subOpenWindows(EventListener listener) {
        System.out.println("subscribe openWindows");
        Vector<EventListener> listeners = listenerMap.get("openWindows");
        if (null == listeners) {
            listeners = new Vector<EventListener>();
            listeners.add(listener);
        } else {
            listeners.add(listener);
        }
        listenerMap.put("openWindows", listeners);
    }

    /**
     * 通知事件
     * @param eventObject
     */
    public void notifyListerEvents(EventObject eventObject) {
        Object source = eventObject.getSource();
        Vector<EventListener> listeners = listenerMap.get(source);
        if (null == listeners) {
            listeners = listenerMap.get("default");
        }
        for (EventListener eventListener : listeners) {
            eventListener.handleEvent(eventObject);
        }
    }
}