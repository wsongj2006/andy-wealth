package com.andy.wealth;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LengthOfLongestSubstring {

    /**
     * 解题思路，滑动窗口+hashSet判重
     * 以i为滑动窗口的左边，right为滑动窗口右边。在固定左边的前提下，移动right遍历并且放入set中。如果set已经包含了某个元素，则停止遍历，计算这个窗口的长度。
     * 移动i的时候，需要把set中i的前一个元素删除。set中只保留当前滑动窗口的元素，i的前一个元素属于上一个滑动窗口。
     *
     * @param inputStr
     * @return
     */
    public static int lengthOfLongestSubstr(String inputStr) {
        Set<Character> container = new HashSet<>();

        int maxLength = 0, right = 0;
        for (int i = 0; i < inputStr.length(); i++) {
            if (i != 0) {
                container.remove(inputStr.charAt(i - 1));
            }

            while (right < inputStr.length()) {
                if (!container.contains(inputStr.charAt(right))) {
                    container.add(inputStr.charAt(right));
                    right++;
                } else {
                    maxLength = Math.max(maxLength, right - i);
                    break;
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        int maxLength = lengthOfLongestSubstr(s);
        System.out.print(maxLength);
    }
}
