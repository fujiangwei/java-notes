package com.notes.java.calllback;

/**
 * 文件描述
 *  <blockquote>模块间调用<br>在一个应用系统中，无论使用何种语言开发，必然存在模块之间的调用，调用的方式分为几种：
 *  <br>（1）同步调用<br>同步调用是最基本并且最简单的一种调用方式，类A的方法a()调用类B的方法b()，一直等待b()方法执行完毕，a()方法继续往下走。这种调用方式适用于方法b()执行时间不长的情况，因为b()方法执行时间一长或者直接阻塞的话，a()方法的余下代码是无法执行下去的，这样会造成整个流程的阻塞。
 *  <br>（2）异步调用<br>异步调用是为了解决同步调用可能出现阻塞，导致整个流程卡住而产生的一种调用方式。类A的方法方法a()通过新起线程的方式调用类B的方法b()，代码接着直接往下执行，这样无论方法b()执行时间多久，都不会阻塞住方法a()的执行。但是这种方式，由于方法a()不等待方法b()的执行完成，在方法a()需要方法b()执行结果的情况下（视具体业务而定，有些业务比如启异步线程发个微信通知、刷新一个缓存这种就没必要），必须通过一定的方式对方法b()的执行结果进行监听。在Java中，可以使用Future+Callable的方式做到这一点，
 *  <br>（3）回调<br>最后是回调，回调的思想是：<br>类A的a()方法调用类B的b()方法<br>类B的b()方法执行完毕主动调用类A的callback()方法<br>这样一种调用方式组成了上图，也就是一种双向的调用方式。
 *  <br>总结起来，回调的核心就是回调方将本身即this传递给调用方，这样调用方就可以在调用完毕之后告诉回调方它想要知道的信息。回调是一种思想、是一种机制，至于具体如何实现，如何通过代码将回调实现得优雅、实现得可扩展性比较高。
 **/
public class CallbackTest {

    public static void main(String[] args) throws Exception {
        // 1.单向同步回调
        Student student = new Student(new Teacher());
//        student.doHomework();

        // 2.同向同步回调
//        student.doHomework2();
//        System.out.println("it is over");

        // 3.异步回调
        student.doHomework3();
        System.out.println("it is over again");
    }
}