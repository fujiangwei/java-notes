package com.notes.java.link;

/**
 * descripiton: 链表
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/4/10
 * @time: 13:52
 * @modifier:
 * @since:
 */
public class LinkModel {

    /**
     * 头结点
     */
    Node head = null;

    public class Node {
        /**
         * 当前节点值
         */
        String value;

        /**
         * 下一个节点引用
         */
        Node next = null;

        public Node(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    /**
     * 新增一个节点,尾插法
     *
     * @param value 新节点内容
     */
    public void addNode(String value) {
        Node newNode = new Node(value);
        //头结点为空时直接赋值给头结点
        if (null == head) {
            head = newNode;
            return;
        }

        Node temp = head;
        //后面是否有节点
        while (null != temp.next) {
            temp = temp.next;
        }
        //最后的节点指向新节点
        temp.next = newNode;
    }

    /**
     * 删除第index个节点
     *
     * @param index
     */
    public boolean delNode(int index) {
        if (index < 1 || index > length()) {
            return false;
        }

        //删除头结点
        if (index == 1) {
            head = head.next;
            return true;
        }

        int delIndex = 1;
        Node preNode = head;
        Node curNode = preNode.next;
        while (null != curNode) {
            delIndex++;
            if (delIndex == index) {
                preNode.next = curNode.next;
                return true;
            }
            preNode = curNode;
            curNode = curNode.next;
        }

        return false;
    }

    /**
     * 当前列表长度
     *
     * @return 列表长度
     */
    public int length() {
        int length = 1;
        Node temp = head;
        while (null != temp.next) {
            length++;
            temp = temp.next;
        }

        return length;
    }

    /**
     * 打印列表信息
     */
    public void printLink() {
        Node temp = head;
        while (null != temp.next) {
            System.out.println(temp.value);
            temp = temp.next;
        }
        //最后节点的值
        System.out.println(temp.value);
    }

    /**
     * 递归反转
     *
     * @param head
     * @return
     */
    public Node reverse1(Node head) {
        // head看作是前一结点，head.getNext()是当前结点，reHead是反转后新链表的头结点
        if (null == head || null == head.getNext()) {
            return head;
        }

        Node reHead = reverse1(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);

        return reHead;
    }

    /**
     * 遍历反转
     *
     * @param head
     * @return
     */
    public Node reverse2(Node head) {
        if (null == head) {
            return head;
        }

        //上一节点
        Node preNode = head;
        //当前节点
        Node curNode = preNode.next;
        //用于保存当前结点的指针域（即下一结点）
        Node temp = null;
        while (null != curNode) {
            temp = curNode.getNext();
            //反转指向
            curNode.setNext(preNode);

            //上一节点变为当前节点
            preNode = curNode;
            //当前节点变成当前节点的下一节点
            curNode = temp;
        }

        //头结点变为尾结点
        head.setNext(null);

        //最后节点变为头结点返回
        return preNode;
    }

    public static void main(String[] args) {
        LinkModel list = new LinkModel();
//        list.addNode("1");
//        list.addNode("2");
//        list.addNode("3");
//        list.addNode("4");
//        list.addNode("5");
//        list.addNode("6");
//        System.out.println("linkLength:" + list.length());
//        System.out.println("head.data:" + list.head.value);
//        list.printLink();
//        list.delNode(5);
//        System.out.println("After deleteNode(4):");
//        list.printLink();

        Node head = list.new Node("0");
        Node node1 = list.new Node("1");
        Node node2 = list.new Node("2");
        Node node3 = list.new Node("3");

        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);

        // 打印反转前的链表
        Node h = head;
        while (null != h) {
            System.out.print(h.getValue() + " ");
            h = h.getNext();
        }

        head = list.reverse1(head);
        System.out.println("\n**************************");
        // 打印反转后的结果
        while (null != head) {
            System.out.print(head.getValue() + " ");
            head = head.getNext();
        }

    }
}
