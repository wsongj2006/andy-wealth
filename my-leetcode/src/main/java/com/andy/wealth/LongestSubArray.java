package com.andy.wealth;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [8,2,4,7], limit = 4
 * 输出：2
 * 解释：所有子数组如下：
 * [8] 最大绝对差 |8-8| = 0 <= 4.
 * [8,2] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
 * [2] 最大绝对差 |2-2| = 0 <= 4.
 * [2,4] 最大绝对差 |2-4| = 2 <= 4.
 * [2,4,7] 最大绝对差 |2-7| = 5 > 4.
 * [4] 最大绝对差 |4-4| = 0 <= 4.
 * [4,7] 最大绝对差 |4-7| = 3 <= 4.
 * [7] 最大绝对差 |7-7| = 0 <= 4.
 * 因此，满足题意的最长子数组的长度为 2 。
 */
public class LongestSubArray {

    /**
     * 1. 滑动窗口；
     * 2. 滑动窗口内最大值和最小值之差小于等于limit,则这个滑动窗口的数组就满足条件。
     * 3. 所以将滑动窗口内的各个元素放入红黑树中，是为了方便计算出这个窗口的最大值和最小值。
     * <p>
     * 因为将元素放入红黑树的时间复杂度是O(logn), 每个元素都要放一遍，所以整个时间复杂度是O(n*logn).
     *
     * @param nums
     * @param limit
     * @return
     */
    public static int longestSubArray(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int left = 0, right = 0, n = nums.length, ret = 0;
        while (right < n) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }

            ret = Math.max(ret, right - left + 1);
            right++;
        }
        return ret;
    }

    /**
     * 1. 滑动窗口；
     * 2. 创建两个队列，queMax和queMin， queMax降序存放窗口的最大值。 queMin升序存放窗口的最小值。
     * 3. 只要计算两个队列的头元素之差，是否满足小于等于limit就行。
     *
     * 因为只在队列都头部和尾部操作，所以对队列对操作时间复杂度是O(1)。每个元素都要去操作一遍，所以整个时间复杂度是O(n)。
     * @param nums
     * @param limit
     * @return
     */
    public static int betterLongestSubArray(int[] nums, int limit) {
        int left = 0, right = 0, n = nums.length, ret = 0;
        Deque<Integer> dequeMax = new LinkedList<>();
        Deque<Integer> dequeMin = new LinkedList<>();


        while (right < n) {
            //这两个while循环是为了保证最大值放在dequeMax的最前面。以及最小值放在dequeMin的最前面
            while (!dequeMax.isEmpty() && dequeMax.peekLast() < nums[right]) {
                dequeMax.pollLast();
            }
            while (!dequeMin.isEmpty() && dequeMin.peekLast() > nums[right]) {
                dequeMin.pollLast();
            }
            dequeMax.offerLast(nums[right]);
            dequeMin.offerLast(nums[right]);

            while (!dequeMax.isEmpty() && !dequeMin.isEmpty() && dequeMax.peekFirst() - dequeMin.peekFirst() > limit) {

                if (nums[left] == dequeMin.pollFirst()) {
                    dequeMin.pollFirst();
                }

                if (nums[left] == dequeMax.peekFirst()) {
                    dequeMax.pollFirst();
                }

                left++;
            }

            ret = Math.max(ret, right - left + 1);
            right++;
        }
        return ret;
    }

    public static void main(String[] args) {
        int limit = 5;
        int a[] = {1, 4, 5, 7, 3, 9};
        int longestLenth = longestSubArray(a, limit);
        System.out.println(longestLenth);

        int betterLongestLenth = betterLongestSubArray(a, limit);
        System.out.println(betterLongestLenth);
    }

    /**
     *limit = 5
     * a[] = {1, 4, 5, 7, 3, 9}
     *
     * 刚开始一直疑惑为什么left在外部的while循环开始的时候不复位为0。有这个疑惑的原因是，right指针在往右滑动到下一个位置的时候，如果left不复位，那么新的right指针没法和left之前的元素组建子数组。
     * 比如right走到index为3的时候，这个时候因为map.lastKey() - map.firstKey() > limit，所以left会往后移动到下一个位置，这里移到1，这个位置的值是4。
     * 然后right往后走到index为4到位置，这个时候因为left是1，所以没法组件{1, 4, 5, 7, 3}的子数组。
     * 后来仔细想了想，在上一轮的比较中，当前left的上一个值0的位置的value 1和right的上一个值3的位置的value 7, 但凡子数组包含了这两个值，那这个子数组肯定就不满足要求了。所以没必要再去和left之前的元素组建子数组了。
     */


}
