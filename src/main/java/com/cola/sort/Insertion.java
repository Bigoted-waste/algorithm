package com.cola.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class Insertion extends Sort {

    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (greater(a[j-1],a[j])){
                    swap(a,j-1,j);
                }else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr={4,1,2,5,8,6,9};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
