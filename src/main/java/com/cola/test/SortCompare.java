package com.cola.test;

import com.cola.sort.Shell;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SortCompare {

    // 调用不同的测试方法，完成测试来计算 时间复杂度
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("reverse_arr.txt")));
        String line = null;
        while ((line=reader.readLine())!=null){
            int i = Integer.parseInt(line);
            list.add(i);
        }
        reader.close();

        Integer[] a = new Integer[list.size()];
        list.toArray(a);
        testShell(a);
    }

    public static void testShell(Integer[] a){
        long start = System.currentTimeMillis();
        Shell.sort(a);
        long end = System.currentTimeMillis();
        System.out.println("希尔排序执行的时间为："+(end-start)+"毫秒");
    }
}
