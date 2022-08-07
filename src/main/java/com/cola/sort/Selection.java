package com.cola.sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class Selection extends Sort {

    /**
     * 基本的选择排序
     *
     * @param a
     */
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (greater(a[minIndex], a[j])) {
                    minIndex = j;
                }
            }
            swap(a, i, minIndex);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {3, 1, 4, 2, 5, 9, 7, 8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
