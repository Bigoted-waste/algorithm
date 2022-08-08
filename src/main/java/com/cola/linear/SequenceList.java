package com.cola.linear;


import java.io.PrintStream;
import java.util.Iterator;

/**
 * 容量可变的顺序表
 */
public class SequenceList<T> implements Iterable<T> {

    // 存储元素的数组
    private T[] arr;
    // 记录当前顺序表中的元素个数
    private int N;

    public SequenceList(int capacity) {
        arr = (T[]) new Object[capacity];
        N = 0;
    }

    /**
     * 将一个线性表置为空表
     */
    public void clear() {
        N = 0;
    }

    /**
     * 判断当前线性表是否为空表
     *
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 获取线性表的长度
     *
     * @return
     */
    public int length() {
        return N;
    }

    /**
     * 获取制定位置的元素
     *
     * @param i
     * @return
     */
    public T get(int i) {
        if (i < 0 || i > N) {
            throw new RuntimeException("当前元素不存在！");
        }
        return arr[i];
    }

    /**
     * 向线性表中添加元素 t
     *
     * @param t
     */
    public void insert(T t) {
        if (N == arr.length) {
            // 扩容为原来的两倍
            resize(arr.length * 2);
        }
        arr[N++] = t;
    }

    /**
     * 在 i 索引处插入元素 t
     *
     * @param i
     * @param t
     */
    public void insert(int i, T t) {
        if (i < 0 || i > N) {
            throw new RuntimeException("插入的位置不合法！");
        }

        if (N == arr.length) {
            // 扩容为原来的两倍
            resize(arr.length * 2);
        }

        // 把 i 位置空出来，i位置及其后面的元素依次向后移动一位
        for (int index = N; index > i; index--) {
            arr[index] = arr[index - 1];
        }

        arr[i] = t;
        N++;
    }

    /**
     * 删除指定位置i处的元素，并返回该元素
     *
     * @param i
     * @return
     */
    public T remove(int i) {
        if (i < 0 || i > N - 1) {
            throw new RuntimeException("当前要删除的元素不存在！");
        }

        // 记录i位置处的元素
        T result = arr[i];

        // 把i位置后面的元素都向前移动一位
        for (int index = i; index < N - 1; index++) {
            arr[index] = arr[index + 1];
        }

        N--;

        // 当元素已经不足数组大小的 1/4，则重制数组的大小
        if (N > 0 && N < arr.length / 4) {
            resize(arr.length / 2);
        }
        return result;
    }

    /**
     * 查找t元素第一次出现的位置
     *
     * @param t
     * @return
     */
    public int indexOf(T t) {
        if (t == null) {
            throw new RuntimeException("查找的元素不合法！");
        }

        for (int i = 0; i < N; i++) {
            if (arr[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 扩容
     *
     * @param newSize
     */
    private void resize(int newSize) {
        T[] temp = arr;
        arr = (T[]) new Object[newSize];
        for (int i = 0; i < N; i++) {
            arr[i] = temp[i];
        }
    }

    public int capacity() {
        return arr.length;
    }


    /**
     * 打印当前线性表的元素
     */
    public void showArr() {
        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /*
     *  SequenceList支持foreach循环，则需要做如下操作
     * 1、让SequenceList 实现 Iterable接口，重写iterator方法
     * 2、在SequenceList 内部提供一个内部类SIterator，实现Iterator接口，重写 HasNext和next方法
     */
    @Override
    public Iterator<T> iterator() {
        return new SIterator();
    }

    private class SIterator implements Iterator {
        private int cur;

        public SIterator() {
            this.cur = 0;
        }

        @Override
        public boolean hasNext() {
            return cur < N;
        }

        @Override
        public T next() {
            return arr[cur++];
        }
    }
}

class SequenceListTest {
    public static void main(String[] args) {
        SequenceList<String> s1 = new SequenceList<String>(3);
        s1.insert("科比");
        s1.insert("詹姆斯");
        s1.insert("乔丹");
        s1.insert(1, "东契奇");
        s1.insert("库里");

        // 测试遍历
        for (String s : s1) {
            System.out.println(s);
        }
        System.out.println("-----------------------------------");
        System.out.println("当前线性表的容量："+s1.capacity());

        // 测试获取
        String getResult = s1.get(1);
        System.out.println("获取索引1处的结果为：" + getResult);

        // 测试删除
        String removeResult = s1.remove(0);
        System.out.println("删除的元素是：" + removeResult);

        // 测试清空
        s1.clear();
        System.out.println("清空后的线性表中的元素个数为：" + s1.length());
    }
}
