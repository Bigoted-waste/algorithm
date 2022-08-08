package com.cola.linear;

import java.util.Iterator;

/**
 * 栈
 */
public class Stack<T> implements Iterable<T> {

    // 首节点
    private Node head;

    // 栈中元素的个数
    private int N;

    public Stack() {
        head = new Node(null, null);
        N = 0;
    }

    /**
     * 判断当前栈中元素个数是否为0
     *
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 把t元素压入栈
     *
     * @param t
     */
    public void push(T t) {
        Node oldNext = head.next;
        Node node = new Node(t, oldNext);
        head.next = node;
        N++;
    }

    /**
     * 弹出栈顶元素
     *
     * @return
     */
    public T pop() {
        Node oldNext = head.next;
        if (oldNext == null) {
            return null;
        }

        head.next = head.next.next;
        N--;
        return oldNext.item;
    }

    /**
     * 获取栈中元素的个数
     *
     * @return
     */
    public int size() {
        return N;
    }


    private class Node {
        public T item;
        public Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new SIterator();
    }

    private class SIterator implements Iterator<T> {

        private Node n = head;

        @Override
        public boolean hasNext() {
            return n.next!=null;
        }

        @Override
        public T next() {
            Node node = n.next;
            n = n.next;
            return node.item;
        }
    }
}

class StackTest{
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");

        for (String s : stack) {
            System.out.print(s+" ");
        }
        System.out.println();
        System.out.println("---------------");
        System.out.println("弹出的元素："+stack.pop());
        for (String s : stack) {
            System.out.print(s+" ");
        }
        System.out.println();
        System.out.println("当前栈的长度"+stack.size());
    }
}
