package com.andy.wealth;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 */
public class LongestPalindrome {

    private static void longestPalindrome(String inputString) {
        boolean[][] dp = new boolean[inputString.length()][inputString.length()];
        int right = 0, left = 0;
        String substring = "";
        for (int i = 0; i < inputString.length(); i++) {
            right = i;
            left = i;
            while (right < inputString.length() && left >= 0) {
                if (right - left == 0) {
                    dp[left][right] = true;
                } else if (right - left == 2 && inputString.charAt(left) == inputString.charAt(right)) {
                    dp[left][right] = true;
                } else if (inputString.charAt(left) == inputString.charAt(right) && dp[left + 1][right - 1]) {
                    dp[left][right] = true;
                }

                if (dp[left][right] && (right - left + 1) >= substring.length()) {
                    substring = inputString.substring(left, right + 1);
                }

                left = left - 1;
                right = right + 1;
            }

        }

        System.out.println("The Palindromic String: " + substring);
    }

    public static void main(String[] args) {
        String s = "babad";
        longestPalindrome(s);
    }
}

