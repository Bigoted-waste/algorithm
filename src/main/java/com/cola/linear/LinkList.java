package com.cola.linear;

import java.util.Iterator;

/**
 * 单向链表
 *
 * @param <T>
 */
public class LinkList<T> implements Iterable<T> {

    // 头节点
    private Node head;

    // 链表长度
    private int N;

    public LinkList() {
        head = new Node(null, null);
    }

    /**
     * 清空链表
     */
    public void clear() {
        head.next = null;
        head.item = null;
        N = 0;
    }

    /**
     * 获取链表的长度
     *
     * @return
     */
    public int length() {
        return N;
    }

    /**
     * 判断链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 获取指定位置i处的元素
     */
    public T get(int i) {
        if (i < 0 || i >= N) {
            throw new RuntimeException("位置不合法！");
        }

       Node n = head.next;
        for (int index = 0; index < i; index++) {
            n = n.next;
        }
        return n.item;
    }

    /**
     * 向链表中添加元素t
     *
     * @param t
     */
    public void insert(T t) {
        // 遍历到最后一个节点
        Node n = head;
        while (n.next != null) {
            n = n.next;
        }
        Node newNode = new Node(t, null);
        n.next = newNode;
        // 链表长度+1
        N++;
    }

    /**
     * 向指定位置 i处、添加元素
     *
     * @param i
     * @param t
     */
    public void insert(int i, T t) {
        if (i < 0 || i > N) {
            throw new RuntimeException("位置不合法！");
        }

        Node pre = head;
        for (int index = 0; index < i; index++) {
            pre = pre.next;
        }

        // 位置 i 的节点
        Node curr = pre.next;
        // 构建新的节点，让新节点指向位置 i 的节点
        Node newNode = new Node(t, curr);
        // 让之前的节点指向新节点
        pre.next = newNode;

        N++;
    }

    /**
     * 删除指定位置i处的元素，并返回被删除的元素
     *
     * @param i
     * @return
     */
    public T remove(int i) {
        if (i < 0 || i > N) {
            throw new RuntimeException("位置不合法！");
        }

        // 寻找i之前的元素
        Node pre = head;
        for (int index = 0; index < i; index++) {
            pre = pre.next;
        }

        // 要删除的节点
        Node curr = pre.next;
        // 前一个节点指向下一个节点，删除当前节点
        pre.next = curr.next;

        // 长度-1
        N--;
        return curr.item;
    }

    /**
     * 查找元素t在链表中第一次出现的位置
     *
     * @param t
     * @return
     */
    public int indexOf(T t) {
        Node n = head;
        for (int i = 0; n.next != null; i++) {
            n = n.next;
            if (n.item.equals(t)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public Iterator<T> iterator() {
        return new LIterator();
    }

    private class LIterator implements Iterator<T> {

        private Node n;

        public LIterator() {
            this.n = head;
        }

        @Override
        public boolean hasNext() {
            return n.next != null;
        }

        @Override
        public T next() {
            n = n.next;
            return n.item;
        }
    }


    private class Node {

        // 存储元素
        T item;

        // 指向下一个节点
        Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}

class LinkListTest{
    public static void main(String[] args) {
        LinkList<String> list = new LinkList<>();
        list.insert(0,"科比");
        list.insert(1,"詹姆斯");
        list.insert(2,"杜兰特");
        list.insert(3,"库里");

        for (String s : list) {
            System.out.print(s+" ");
        }
        System.out.println();
        System.out.println(list.length());
        System.out.println("------------------------");

        System.out.println(list.get(0));
        System.out.println("------------------------");

        String remove = list.remove(1);
        System.out.println(remove);
        System.out.println(list.length());
        System.out.println("------------------------");
        for (String s : list) {
            System.out.print(s+" ");
        }
    }
}
