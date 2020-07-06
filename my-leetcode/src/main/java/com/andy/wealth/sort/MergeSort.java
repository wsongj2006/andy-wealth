package com.andy.wealth.sort;

/**
 * 归并排序
 * 需要一个辅助数组，空间大小和数据数组一样。
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] dataArray = {1, 3, 3, 6, 7, 7, 2, 4, 9, 13, 14, 23, 11, 12, 9};
        sort(dataArray);
        System.out.println(dataArray);
    }

    public static void sort(int[] inputArray) {
        int[] tempArray = new int[inputArray.length];
        mergeSort(inputArray, tempArray, 0, inputArray.length - 1);
    }

    private static void mergeSort(int[] inputArray, int[] tempArray, int left, int right) {
        /*
        1. 首先通过递归不断对分解数组；
        2. 然后对分解后对左右两边子数组进行排序；
         */
        if (left < right) {
            mergeSort(inputArray, tempArray, left, (left + right) / 2);
            mergeSort(inputArray, tempArray, (left + right) / 2 + 1, right);
            processSort(inputArray, tempArray, left, right);
        }

    }

    private static void processSort(int[] inputArray, int[] tempArray, int left, int right) {
        int center = (left + right) / 2;
        int i = left;
        int j = center + 1;
        int tempIndex = left;
        while (i <= center && j <= right) {
            if (inputArray[i] < inputArray[j]) {
                tempArray[tempIndex++] = inputArray[i];
                i++;
            } else {
                tempArray[tempIndex++] = inputArray[j];
                j++;
            }
        }

        while (i <= center) {
            tempArray[tempIndex++] = inputArray[i++];
        }

        while (j <= right) {
            tempArray[tempIndex++] = inputArray[j++];
        }

        for (int k = left; k <= right; k++) {
            inputArray[k] = tempArray[k];
        }
    }

}
