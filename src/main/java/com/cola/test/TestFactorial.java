package com.cola.test;

public class TestFactorial {

    public static void main(String[] args) {
        long result = factorial(5);
        System.out.println(result);
    }

    public static long factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
