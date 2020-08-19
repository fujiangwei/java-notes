package com.notes.designpattern.listener;

import java.util.Observable;
import java.util.Observer;

public class WatcherDemo implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if(arg.toString().equals("openWindows")){
            System.out.println("open");
        }
    }
}