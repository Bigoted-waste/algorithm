package com.cola.linear;


import java.util.Iterator;

/**
 * 队列
 */
public class Queue<T> implements Iterable<T> {

    // 首节点
    private Node head;

    // 最后一个元素
    private Node last;

    // 队列中元素的个数
    private int N;

    public Queue() {
        head = new Node(null, null);
        last = null;
        N = 0;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 返回队列中元素的个数
     *
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * 向队列中插入元素t
     *
     * @param t
     */
    public void enqueue(T t) {
        if (last == null) {
            last = new Node(t, null);
            head.next = last;
        } else {
            Node oldLast = last;
            last = new Node(t, null);
            oldLast.next = last;
        }
        N++;
    }

    /**
     * 从队列中拿出一个元素
     *
     * @return
     */
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }

        Node oldFirst = head.next;
        head.next = oldFirst.next;
        N--;

        if (isEmpty()) {
            last = null;
        }
        return oldFirst.item;
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
        return new QIterator();
    }

    private class QIterator implements Iterator{

        private Node n=head;

        @Override
        public boolean hasNext() {
            return n.next!=null;
        }

        @Override
        public Object next() {
            Node node = n.next;
            n = n.next;
            return node.item;
        }
    }
}

class QueueTest{
    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        for (String s : queue) {
            System.out.print(s+" ");
        }
        System.out.println("------------------------------");
        System.out.println("出列了元素"+queue.dequeue());
        System.out.println("队列的长度"+queue.size());
        for (String s : queue) {
            System.out.print(s+" ");
        }
    }
}