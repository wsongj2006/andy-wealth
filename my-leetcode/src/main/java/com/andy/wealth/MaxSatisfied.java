package com.andy.wealth;

/**
 * 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 * <p>
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 * <p>
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 * <p>
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * 输出：16
 * 解释：
 * 书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 */
public class MaxSatisfied {

    /**
     * 思路：
     * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
     * 1. 先算出满意的客户是多少个。
     * 2. 滑动窗口，窗口大小是X=3。寻找不满意客户总数最大的滑动窗口。
     * 3. 将不满意客户总数转变为满意客户总数。
     */
    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        if (customers.length != grumpy.length) {
            return 0;
        }

        int totalSatisfied = 0;
        int right = X - 1, left = 0;

        for (int i = 0; i < grumpy.length; i++) {
            if (grumpy[i] == 0) {
                totalSatisfied += customers[i];
            }
        }

        int windowSatisfiedValue = 0;

        //计算第一个窗口内不满意客户的总数
        for (int i = 0; i < X; i++) {
            if (grumpy[i] == 1) {
                windowSatisfiedValue += customers[i];
            }
        }

        /**
         * 这里最开始没看懂，一点都没有滑动窗口的意思。
         * 但是其实是这样的，上面代码第一次窗口计算得到了第一个窗口的值，
         * 然后每滑动一次，也就是i每叠加一次，就把最左边的值从上次窗口计算得到的值里面去掉，再把最右边的值加到上次窗口得到的值。
         * 也就是把上次窗口的值减掉最左边的，同时再加上最右边的，就得到了当前窗口的值。当然这里最左边和最右边的值的计算是根据是不是满意的客户来得到的。
         *
         */
        int thisWindowSatisfiedValue = windowSatisfiedValue;
        for (int i = X; i < customers.length; i++) {
            //最左边如果是不满意的客户，那它在上轮窗口计算中已经加进来了，所以在这个窗口中应该把它去掉。
            if (grumpy[i - X] == 1) {
                thisWindowSatisfiedValue -= customers[i - X];
            }

            //最右边如果是不满意的客户，就直接加上。因为最右边肯定不在上轮窗口的范围内
            if (grumpy[i] == 1) {
                thisWindowSatisfiedValue += customers[i];
            }
            windowSatisfiedValue = Math.max(windowSatisfiedValue, thisWindowSatisfiedValue);
        }

        return totalSatisfied + windowSatisfiedValue;
    }

    public static void main(String[] args) {
        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy = {0, 1, 0, 1, 0, 1, 0, 1};
        int X = 3;

        int maxSatisfied = maxSatisfied(customers, grumpy, X);
        System.out.println(maxSatisfied);
    }
}
