package com.baeldung.inline;

public class InlineMethodExample {

    public static final int NUMBERS_OF_ITERATIONS = 10000;

    public static void main(String[] args) {
        for (int i = 1; i < NUMBERS_OF_ITERATIONS; i++) {
            calculateSum(i);
        }
    }

    private static long calculateSum(int n) {
        return new ConsecutiveNumbersSum(n).getTotalSum();
    }
}
