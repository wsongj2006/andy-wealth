package com.andy.wealth.search;

public class MiddleSearch {

    public static boolean contain(int[] inputArray, int target) {
        return isContain(inputArray, 0, inputArray.length - 1, target);
    }

    private static boolean isContain(int[] inputArray, int left, int right, int target) {
        if (left >= right) {
            return false;
        }
        int center = (left + right) / 2;
        if (inputArray[center] == target) {
            return true;
        } else if (inputArray[center] > target) {
            return isContain(inputArray, left, center, target);
        } else {
            return isContain(inputArray, center + 1, right, target);
        }
    }

    public static void main(String[] args) {
        int[] dataArray = {1, 3, 4, 5, 7, 12, 15, 15, 17};
        System.out.println(contain(dataArray, 10));
    }

}
