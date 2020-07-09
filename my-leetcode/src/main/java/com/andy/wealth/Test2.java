package com.andy.wealth;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 * 示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 */
public class Test2 {
    public static void main(String[] args) {
        int value = reverse(-11474836);
        System.out.println(value);
    }


    /*
    1. 反转，就是把末尾的数字依次按顺序放到前面；那么第一个关键到问题是怎么把最后到数字取出来，我们可以通过对10取模，比如1234%10得到最后的数字4，然后123%10得到最后的数字3；
    2. 因为有符号整数值的范围是[-2^31, 2^31-1], 如果转换后的值不在这个范围内，Int类型就没法赋值了。所以得提前判断。
        得在还没到最大值到时候就去判断是否会超过Int范围。
     */
    private static int reverse(int input) {
        int lastNum = 0; //最后一个数字
        int restNum = input;  //去掉最后一个数字之后剩下的
        int result = 0; //最终返回值

        //我们需要在还没达到最大值的时候就开始判断，所以判断少一位的最大值
        int min = Integer.MIN_VALUE / 10;
        int max = Integer.MAX_VALUE / 10;
        int minLastNum = Integer.MIN_VALUE % 10;
        int maxLastNum = Integer.MAX_VALUE % 10;

        while (restNum != 0) {
            lastNum = restNum % 10;
            result = result * 10 + lastNum;
            restNum = restNum / 10;

            if (result > max || (result == max && restNum == maxLastNum)) {
                return 0;
            }
            if (result < min || (result == min && restNum == minLastNum)) {
                return 0;
            }
        }
        return result;
    }
}
