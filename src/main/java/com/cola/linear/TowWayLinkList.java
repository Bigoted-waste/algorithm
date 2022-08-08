package com.cola.linear;

import java.util.Iterator;

/**
 * 双向链表
 */
public class TowWayLinkList<T> implements Iterable<T> {

    // 首节点
    private Node head;

    // 尾节点
    private Node last;

    // 链表长度
    private int N;

    public TowWayLinkList() {
        last = null;
        head = new Node(null, null, null);
        N = 0;
    }

    /**
     * 清空链表
     */
    public void clear() {
        last = null;
        head.next = last;
        head.pre = null;
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
     * 判断当前链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 插入元素t
     *
     * @param t
     */
    public void insert(T t) {
        if (last == null) {
            last = new Node(t, head, null);
            head.next = last;
        } else {
            Node oldLast = last;
            Node node = new Node(t, oldLast, null);
            oldLast.next = node;
            last = node;
        }

        // 长度+1
        N++;
    }

    /**
     * 向指定位置i处插入元素
     *
     * @param i
     * @param t
     */
    public void insert(int i, T t) {
        if (i < 0 || i > N) {
            throw new RuntimeException("位置不合法！");
        }

        // 找到位置i的前一个节点
        Node pre = head;
        for (int index = 0; index < i; index++) {
            pre = pre.next;
        }

        // 当前节点
        Node curr = pre.next;
        // 构建新节点
        Node newNode = new Node(t, pre, curr);
        curr.pre = newNode;
        pre.next = newNode;

        N++;
    }

    /**
     * 获取指定位置i处的元素
     *
     * @param i
     * @return
     */
    public T get(int i) {
        if (i < 0 || i > N) {
            throw new RuntimeException("位置不合法！");
        }

        Node curr = head.next;
        for (int index = 0; index < i; index++) {
            curr = curr.next;
        }
        return curr.item;
    }

    /**
     * 找到元素t在链表中第一次出现的位置
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

    /**
     * 删除位置i处的元素，并返回该元素
     *
     * @param i
     * @return
     */
    public T remove(int i) {
        if (i < 0 || i > N) {
            throw new RuntimeException("位置不合法！");
        }

        Node pre = head;
        for (int index = 0; index < i; index++) {
            pre = pre.next;
        }

        // i位置的元素
        Node curr = pre.next;
        // i位置的下一个元素
        Node curr_next = curr.next;

        pre.next = curr_next;
        curr_next.pre = pre;

        N--;
        return curr.item;
    }

    /**
     * 获取第一个元素
     *
     * @return
     */
    public T getFirst() {
        if (isEmpty()) {
            return null;
        }
        return head.next.item;
    }

    /**
     * 获取最后一个元素
     *
     * @return
     */
    public T getLast() {
        if (isEmpty()) {
            return null;
        }
        return last.item;
    }

    private class Node {
        // 数据
        public T item;

        // 指向上一个节点
        public Node pre;

        // 指向下一个节点
        public Node next;

        public Node(T item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new TIterator();
    }

    private class TIterator implements Iterator {

        private Node n = head;

        @Override
        public boolean hasNext() {
            return n.next != null;
        }

        @Override
        public Object next() {
            n = n.next;
            return n.item;
        }
    }
}

class TowWayLinkListTest{
    public static void main(String[] args) {
        TowWayLinkList<String> list = new TowWayLinkList<>();
        list.insert("詹姆斯");
        list.insert("科比");
        list.insert("杜兰特");
        list.insert(0,"库里");
        list.insert(2,"东契奇");

        for (String s : list) {
            System.out.print(s+" ");
        }
        System.out.println();
        System.out.println("--------------------------");
        System.out.println(list.get(2));
        System.out.println("--------------------------");
        System.out.println(list.remove(3));
        System.out.println("--------------------------");
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
    }
}
