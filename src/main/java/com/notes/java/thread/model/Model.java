package com.notes.java.thread.model;

public interface Model {

    Runnable newRunnableConsumer();

    Runnable newRunnableProducer();
}