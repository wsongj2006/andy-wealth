package com.andy.wealth;


import com.andy.wealth.bean.Address;

import java.lang.reflect.Method;
import java.util.*;

public class test {

    public static void main(String[] args) throws IllegalAccessException {
        int[] a = {2, 4, 1, 1, 6, 3, 9, 5, 4, 10, 10, 7, 13, 11, 16, 8, 8};
        quickSort3(a, 0, a.length - 1);
        printArray(a);
        //List<List<Integer>> lists = threeSum(a, 15);
        //System.out.println(lists);


    }

    private static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
    }

    private static int[] getTargetArray(int[] inputs, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int index1 = 0, index2 = 0;

        for (int i = 0; i < inputs.length; i++) {
            map.put(inputs[i], i);
        }

        for (int i = 0; i < inputs.length; i++) {
            int k = target - inputs[i];
            if (map.containsKey(k) && i != map.get(k)) {
                index1 = i;
                index2 = map.get(k);
                System.out.println(index1 + "---" + index2);
                break;
            }
        }

        int[] results = new int[2];
        results[0] = index1;
        results[1] = index2;

        return results;
    }

    private static int getSubStr(String inputStr) {
        int left = 0, right = 0;
        Map<Character, Integer> map = new HashMap<>();
        int maxSubLength = 0;
        while (left <= right && right < inputStr.length()) {
            int tempSubLength = 0;
            if (map.get(inputStr.charAt(right)) != null) {
                System.out.println(right + "---" + left);
                if (maxSubLength < (right - left)) {
                    maxSubLength = (right - left);
                }
                left = map.get(inputStr.charAt(right)) + 1;
                map.remove(inputStr.charAt(right));
            } else {
                map.put(inputStr.charAt(right), right);
                right++;
            }

        }
        if (maxSubLength < (right - left)) {
            maxSubLength = (right - left);
        }

        return maxSubLength;
    }

    private static String getLongestPalindrome(String inputStr) {
        boolean[][] pd = new boolean[inputStr.length()][inputStr.length()];
        int right = 0, left = 0;
        String subStr = "";
        for (int i = 0; i < inputStr.length(); i++) {
            right = i;
            left = i;
            while (left >= 0 && right < inputStr.length()) {
                if (right - left == 0) {
                    pd[left][right] = true;
                } else if (right - left == 2 && inputStr.charAt(right) == inputStr.charAt(left)) {
                    pd[left][right] = true;
                } else if (pd[left + 1][right - 1] && inputStr.charAt(right) == inputStr.charAt(left)) {
                    pd[left][right] = true;
                }

                if (pd[left][right] && subStr.length() < (right - left + 1)) {
                    subStr = inputStr.substring(left, right + 1);
                }
                left = left - 1;
                right = right + 1;
            }
        }
        return subStr;
    }

    /**
     * 整数反转
     *
     * @param input
     * @return
     */
    private static int getReverseInt(int input) {
        int i = input, rec = 0;
        Long x = 0l;
        while (i != 0) {

            rec = i % 10; //每次取末尾的数字出来
            i = i / 10;

            x = x * 10 + rec; //x为每次反转后的值

            if (x < Integer.MIN_VALUE || x > Integer.MAX_VALUE) {
                return 0;
            }
        }

        return x.intValue();
    }


    private static int getIntFromString(String input) {
        int startIndex = 0;
        if (input.startsWith("-")) {
            startIndex = 1;
        }
        for (int i = startIndex; i < input.length(); i++) {
            char c = input.charAt(i);

        }

        return 0;
    }


    public static void quickSort(int[] a, int l, int r) {

        if (l < r) {
            int i, j, x;

            i = l;
            j = r;
            x = a[i];
            while (i < j) {
                while (i < j && a[j] > x)
                    j--; // 从右向左找第一个小于x的数
                if (i < j)
                    a[i++] = a[j];
                while (i < j && a[i] < x)
                    i++; // 从左向右找第一个大于x的数
                if (i < j)
                    a[j--] = a[i];
            }
            a[i] = x;
            quickSort(a, l, i - 1); /* 递归调用 */
            quickSort(a, i + 1, r); /* 递归调用 */
        }
    }

    private static void quickSort1(int[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int l = lo, h = hi;
        int x = a[l];
        while (true) {
            while (l < h && a[h] > x) {
                h--;
            }
            if (l < h) {
                a[l] = a[h];
                l++;
            }

            while (l < h && a[l] < x) {
                l++;
            }
            if (l < h) {
                a[h] = a[l];
                h--;
            }

            if (l >= h) {
                break;
            }
        }
        a[l] = x;
        quickSort1(a, lo, l - 1);
        quickSort1(a, l + 1, hi);
    }

    private static void quickSort2(int a[], int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int l = lo, h = hi;
        int x = a[l];
        while (true) {
            while (l < h & a[h] > x) {
                h--;
            }
            if (l < h) {
                a[l] = a[h];
                l++;
            }

            while (l < h && a[l] < x) {
                l++;
            }
            if (l < h) {
                a[h] = a[l];
                h--;
            }

            if (l >= h) {
                break;
            }
        }
        a[l] = x;
        quickSort2(a, lo, l - 1);
        quickSort2(a, l + 1, hi);
    }

    private static List<List<Integer>> threeSum(int[] a, int target) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(a);
        for (int first = 0; first < a.length - 2; first++) {
            if (first > 0 && a[first] == a[first - 1]) {
                continue;
            }
            int left = first + 1;
            int right = a.length - 1;
            while (true) {
                if (left < right && a[left] == a[left - 1]) {
                    left++;
                    continue;
                }

                if ((a[first] + a[left] + a[right]) > target) {
                    right--;
                } else if ((a[first] + a[left] + a[right]) < target) {
                    left++;
                } else {
                    List<Integer> tempList = new ArrayList<>();
                    tempList.add(a[first]);
                    tempList.add(a[left]);
                    tempList.add(a[right]);
                    results.add(tempList);
                    break;
                }

                if (left >= right) {
                    break;
                }

            }
        }

        return results;
    }

    private static void quickSort3(int[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int l = lo, h = hi;
        int x = a[lo];

        while (true) {
            while(l < h && a[h] > x) {
                h--;
            }
            if (l < h) {
                a[l] = a[h];
                l++;
            }

            while (l < h && a[l] < x) {
                l++;
            }
            if (l < h) {
                a[h] = a[l];
                h--;
            }

            if (l >= h) {
                break;
            }
        }
        if (l >= h) {
            a[l] = x;
            quickSort3(a, lo, l-1);
            quickSort3(a, l+1, hi);
        }
    }
}
