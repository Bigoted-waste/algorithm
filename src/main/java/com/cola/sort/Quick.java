package com.cola.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class Quick extends Sort {

    /*
     * 对数组内的元素进行排序
     */
    public static void sort(Comparable[] a) {
        int lo = 0;
        int hi = a.length - 1;
        sort(a, lo, hi);
    }

    /*
     * 对数组a中从索引lo到hi之间的元素进行排序
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;

        // 对a数组中，从lo到hi的元素进行切分
        int partition = partition(a, lo, hi);
        // 对左边分组中的元素进行排序
        sort(a, lo, partition - 1);
        // 对右边分组中的元素进行排序
        sort(a, partition + 1, hi);
    }


    /*
        对数组a中，从索引 lo 到 hi 之间的元素进行分组，并返回分组界限对应的索引
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        // 把最左边的元素作为基准值
        Comparable key = a[lo];
        // 定义一个左指针，初始指向最左边的元素
        int left = lo;
        // 定义一个右指针，初始指向最右边的元素
        int right = hi + 1;

        // 进行切分
        while (true) {
            // 先从右往左扫描，找到一个比基准值小的元素
            while (less(key, a[--right])) {
                if (right == lo) {
                    break;
                }
            }

            // 再从左往右边扫描，找一个比基准值大的元素
            while (less(a[++left], key)) {
                if (left == hi) {
                    break;
                }
            }

            if (left >= right){
                break;
            }else {
                swap(a,left,right);
            }
        }
        swap(a,lo,right);
        return right;
    }

    public static void main(String[] args) {
        Integer[] arr={6,1,2,7,9,3,4,5,8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
