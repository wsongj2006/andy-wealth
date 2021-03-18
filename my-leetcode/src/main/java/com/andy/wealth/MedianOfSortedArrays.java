package com.andy.wealth;

public class MedianOfSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 4, 9}, nums2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        double midValue = findMedianofSortedArrays(nums1, nums2);
        System.out.println(midValue);
    }

    public static double findMedianofSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        if (totalLength % 2 == 0) {
            int k = totalLength / 2;
            int mid1 = getKthValue(nums1, nums2, k);
            int mid2 = getKthValue(nums1, nums2, k + 1);
            return (mid1 + mid2) / 2;
        } else {
            int k = totalLength / 2;
            return getKthValue(nums1, nums2, k);
        }
    }

    public static int getKthValue(int[] nums1, int[] nums2, int k) {
        int offset1 = 0, offset2 = 0, length1 = nums1.length, length2 = nums2.length;
        while (true) {
            if (length1 == 0) {
                return nums2[k - 1];
            }
            if (length2 == 0) {
                return nums1[k - 1];
            }

            if (k == 1) {
                return Math.min(nums1[offset1], nums2[offset2]);
            }

            int half = k / 2;
            int newIndex1 = Math.min(offset1 + half, length1) - 1;
            int newIndex2 = Math.min(offset2 + half, length2) - 1;
            if (nums1[newIndex1] < nums2[newIndex2]) {
                k = k - (newIndex1 - offset1 + 1);
                offset1 = newIndex1 + 1;
            } else {
                k = k - (newIndex2 - offset2 + 1);
                offset2 = newIndex2 + 1;
            }
        }
    }
}
