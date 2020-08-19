package com.notes.java.calllback;

/**
 * 文件描述
 **/
public class Teacher implements CallbackIntervice {
    @Override
    public void execute() {
        System.out.println("i am ok");
    }

    @Override
    public void callback(Student student) {
        try {
            System.out.println("i am ok 123");
            Thread.sleep(2000);
        }catch (Exception e) {

        }

        student.callback();
    }
}