package com.cola.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 冒泡排序
 */
public class Bubble extends Sort {

    /**
     * 基本的冒泡排序
     * @param a
     */
    public static void sort(Comparable[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (greater(a[j], a[j + 1])) {
                    swap(a, j, j + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {4, 1, 5, 7, 3, 9};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
