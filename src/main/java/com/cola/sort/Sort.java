package com.cola.sort;

public class Sort {
    /**
     * 比较元素是否大于w元素
     *
     * @param v
     * @param w
     * @return
     */
    public static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    /**
     * 数组元素 i 和 j 交换位置
     *
     * @param a
     * @param i
     * @param j
     */
    public static void swap(Comparable[] a, int i, int j) {
        Comparable temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * 判断 v 是否小于 w
     *
     * @param v
     * @param w
     * @return
     */
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}
