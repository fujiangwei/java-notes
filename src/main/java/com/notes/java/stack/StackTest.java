package com.notes.java.stack;

import java.util.Stack;

/**
 * Stack
 * 接口实现：
 * <p>
 * class Stack<E> extends Vector<E> {......}
 * 常用的api函数如下：
 * <p>
 * boolean             isEmpty() // 判断当前栈是否为空
 * synchronized E             peek() //获得当前栈顶元素
 * synchronized E             pop() //获得当前栈顶元素并删除
 * E             push(E object) //将元素加入栈顶
 * synchronized int           search(Object o)  //查找元素在栈中的位置，由栈低向栈顶方向数
 * <p>
 * 2.LinkedList实现
 * 接口实现：
 * <p>
 * public class LinkedList<E>
 * extends AbstractSequentialList<E>
 * implements List<E>, Deque<E>, Cloneable, java.io.Serializable{......}
 * LinkedList 是一个继承于AbstractSequentialList的双向链表。它也可以被当作堆栈、队列或双端队列进行操作。
 * LinkedList 实现 List 接口，能对它进行队列操作。
 * LinkedList 实现 Deque 接口，即能将LinkedList当作双端队列使用。
 * <p>
 * 当LinkedList被当做栈来使用时，常用api及对应关系如下：
 * <p>
 * 栈方法        等效方法
 * push(e)      addFirst(e)
 * pop()        removeFirst()
 * peek()       peekFirst()
 * isEmpty()  //判断是否为空
 */
public class StackTest {

    public static void main(String[] args) {
        System.out.println("=============stack(FILO)=============");
        Stack<String> stack = new Stack<String>();
        stack.push("A");
        stack.push("B");
        stack.add("3");
        System.out.println(stack);
        String pop = stack.pop();
        System.out.println(pop + stack);
        String peek1 = stack.peek();
        System.out.println(peek1 + stack);
    }

}