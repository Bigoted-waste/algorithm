package com.cola.sort;

import java.util.Arrays;

public class Merge extends Sort {

    // 归并所需要的辅助数组
    private static Comparable[] assist;

    /*
     * 对数组内的元素进行排序
     */
    public static void sort(Comparable[] a) {
        assist = new Comparable[a.length];
        int lo = 0;
        int hi = a.length - 1;
        sort(a, lo, hi);
    }

    /*
     * 对数组a中从索引 lo 到索引 hi 之间的元素进行排序
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;

        int mid = lo + (hi - lo) / 2;

        // 对 lo 到 mid 之间的元素进行排序
        sort(a, lo, mid);
        // 对 mid+1 到 hi 之间的元素进行排序
        sort(a, mid + 1, hi);
        // 对 lo 到 mid这组数据和 mid+1 到 hi这组数据进行归并
        merge(a, lo, mid, hi);
    }

    /*
     *  从索引 lo 到索引 mid 为一个子数组，从索引 mid+1 到索引 hi为另一个子数组，
     * 把数组 a 中的这两个子组的数据合并成一个有序的大数组(从索引 lo 到 索引 hi)
     */
    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        // 定义一个指针，指向第一组数据的第一个元素
        int p1 = lo;
        // 定义一个指针，指向第二组数据的第一个元素
        int p2 = mid + 1;

        // 比价左边小组和右边小组中元素大小，哪个小，就把哪个数据填充到assist数组中
        while (p1 <= mid && p2 <= hi) {
            if (less(a[p1], a[p2])) {
                assist[i++] = a[p1++];
            } else {
                assist[i++] = a[p2++];
            }
        }

        /*
         *   上面的循环结束后，如果推出循环的条件是 p1<=mid ,则证明左边小组中的数据已经归并完毕，如果推出
         * 循环的条件是 p2<=hi，则证明右边小组的数据已经填充完毕；
         */
        while (p1<=mid){
            assist[i++] = a[p1++];
        }
        while (p2<=hi){
            assist[i++] = a[p2++];
        }

        // assist 数组中，从lo到hi的元素是有序的，在把数据拷贝到a数组中对应的索引处。
        for (int index = lo; index <= hi; index++) {
            a[index] = assist[index];
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
