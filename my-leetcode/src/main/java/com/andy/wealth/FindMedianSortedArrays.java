package com.andy.wealth;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 *
 */
public class FindMedianSortedArrays {

    public static int findMedianSortedArraysByMerge(int[] input1, int[] input2) {
        int[] newArray = new int[input1.length + input2.length];
        int inputIndex1 = 0, inputIndex2 = 0, inputIndex3 = 0;
        while (inputIndex1 < input1.length && inputIndex2 < input2.length) {
            if (input1[inputIndex1] < input2[inputIndex2]) {
                newArray[inputIndex3++] = input1[inputIndex1++];
            } else {
                newArray[inputIndex3++] = input2[inputIndex2++];
            }
        }
        while (inputIndex1 < input1.length) {
            newArray[inputIndex3++] = input1[inputIndex1++];
        }

        while (inputIndex2 < input2.length) {
            newArray[inputIndex3++] = input2[inputIndex2++];
        }

        for (int val : newArray) {
            System.out.print(val + ",");
        }
        System.out.println();

        if (newArray.length % 2 == 0) {
            int mid1 = newArray[(newArray.length / 2) - 1];
            int mid2 = newArray[newArray.length / 2];
            return (mid1 + mid2) / 2;
        } else {
            return newArray[newArray.length / 2];
        }
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 3, 4, 9}, nums2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        double midValue = findMedianSortedArrays(nums1, nums2);
        System.out.println(midValue);

        int midValue1 = findMedianSortedArraysByMerge(nums1, nums2);
        System.out.println(midValue1);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int k = 0, totalLength = nums1.length + nums2.length;

        if ((totalLength) % 2 == 0) {
            int mid1 = totalLength / 2 - 1, mid2 = totalLength / 2;
            int mid1Value = getKthNumber(nums1, nums2, mid1 + 1);
            int mid2Value = getKthNumber(nums1, nums2, mid2 + 1);
            return (mid1Value + mid2Value) / 2;
        } else {
            int mid = totalLength / 2;
            int midValue = getKthNumber(nums1, nums2, mid + 1);
            return midValue;
        }
    }

    /**
     * 两个有序数组，合并成一个集合之后，寻找第K个数。
     * 但是为了提高时间复杂度，不能去合并。而是在两个有序数组中去寻找第K个数。时间复杂度要求O(log(m+n)), m和n分别为两个数组的长度。
     * 思路： 寻找第k个数，那就去排除k-1个数。每排除一个数，k就递减，直到k=1了就得到了第K个数。
     * 但是为了提高时间复杂度，需要用二分法。
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    private static int getKthNumber(int[] nums1, int[] nums2, int k) {
        int index1 = 0, index2 = 0;

        while (true) {
            //如果数组1为空数组，则直接去数组2里面取第k个数就好了
            if (index1 == nums1.length) {
                return nums2[index2 + k - 1];
            }

            //同上
            if (index2 == nums2.length) {
                return nums1[index1 + k - 1];
            }

            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, nums1.length) - 1;
            int newIndex2 = Math.min(index2 + half, nums2.length) - 1;
            if (nums1[newIndex1] < nums2[newIndex2]) {
                k = k - (newIndex1 - index1 + 1);  //减去排除掉个元素个数
                index1 = newIndex1 + 1;
            } else {
                k = k - (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }

        }
    }
}
