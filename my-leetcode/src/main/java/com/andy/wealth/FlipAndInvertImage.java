package com.andy.wealth;

/**
 * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 * <p>
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 * <p>
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[[1,1,0],[1,0,1],[0,0,0]]
 * 输出：[[1,0,0],[0,1,0],[1,1,1]]
 * 解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
 * 然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：[[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * 输出：[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 解释：首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
 * 然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 */
public class FlipAndInvertImage {

    /**
     * 思路：设计两个指针，left和right.
     * 对于矩阵的每一行，元素个数为n。让left + right = n -1.
     * 假设在第i行：
     * 如果a[i][left] == a[i][right] == 0, 则翻转后 a[i][left] == a[i][right] == 0， 再反转后a[i][left] == a[i][right] == 1。
     * 如果a[i][left] == 1, a[i][right] == 0, 则翻转后 a[i][left] = 0, a[i][right] = 1, 再反转后，a[i][left] = 1, a[i][right] = 0
     * 所以规则是，如果在一行里面，left上的值和right上的值相等，则最后得到的就是反值。
     * 如果left上的值和right上的值不相等，则最后得到的值保持原来的样子。
     *
     * @param inputArray
     * @return
     */
    public static int[][] flipAndInvertImage(int[][] inputArray) {
        for (int i = 0; i < inputArray.length; i++) {
            int left = 0, right = inputArray[i].length - 1;
            while (left <= right) {
                if (inputArray[i][left] == inputArray[i][right]) {
                    inputArray[i][left] = inputArray[i][left]^1;
                    inputArray[i][right] = inputArray[i][right]^1;
                }


                left++;
                right--;
            }
        }

        return inputArray;
    }

    public static void main(String[] args) {
        int[][] a = {{1,1,0,0},{1,0,0,1},{0,1,1,1},{1,0,1,0}};
        int[][] b = flipAndInvertImage(a);
        System.out.println(b);
    }

}
