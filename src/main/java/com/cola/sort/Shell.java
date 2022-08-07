package com.cola.sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class Shell extends Sort {

    public static void sort(Comparable[] a) {
        // 确定增长量的最大值
        int h = 1;
        while (h < a.length / 2) {
            h = h * 2 + 1;
        }

        while (h > 0) {
            for (int i = h; i < a.length; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (greater(a[j - h], a[j])) {
                        swap(a, j - h, j);
                    }
                }
            }
            h /= 2;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {9, 1, 2, 5, 7, 4, 8, 6, 3, 5};
        Shell.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
