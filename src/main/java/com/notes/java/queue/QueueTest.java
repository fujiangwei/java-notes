package com.notes.java.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Queue
 * java中虽然有Queue接口，单java并没有给出具体的队列实现类，而Java中让LinkedList类实现了Queue接口，所以使用队列的时候，一般采用LinkedList。因为LinkedList是双向链表，可以很方便的实现队列的所有功能。
 * <p>
 * Queue使用时要尽量避免Collection的add()和remove()方法，而是要使用offer()来加入元素，使用poll()来获取并移出元素。它们的优点是通过返回值可以判断成功与否，add()和remove()方法在失败的时候会抛出异常。 如果要使用前端而不移出该元素，使用
 * element()或者peek()方法。
 * <p>
 * java中定义队列 一般这样定义： Queue<E> queue = new LinkedList<E>();
 * <p>
 * 当采用LinkedList来实现时，api的使用和对用关系如下：
 * <p>
 * 队列方法       等效方法
 * offer(e)      offer(e)/offerLast(e)  //进队列，将元素加入队列末尾
 * poll()        poll()/pollFirst()  //获取队列头的元素并移除
 * peek()        peek()/peekFirst()  //获取队列头的元素
 * isEmpty() //判断是否为空
 */
public class QueueTest {

    public static void main(String[] args) {
        System.out.println("=============queue(FIFO先进先出)=============");
        //public class LinkedList<E>
        //    extends AbstractSequentialList<E>
        //    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
        Queue<String> queue = new LinkedList<>();
        //添加元素
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.add("1");
        queue.add("2");
        System.out.println(queue);
        //移除第一个元素
//        queue.remove();
//        System.out.println(queue);
        //弹出元素
        String poll = queue.poll();
        String poll1 = queue.poll();
        System.out.println(poll + poll1 + queue);
        String peek = queue.peek();
        System.out.println(peek + queue);
    }

}