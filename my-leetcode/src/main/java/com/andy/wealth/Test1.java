package com.andy.wealth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。你可以假设每种输入只会对应一个答案。
 * 但是，数组中同一个元素不能使用两遍。
 * <p>
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class Test1 {

    /*
    这个题目得从两方面考虑：
    1. 数组里面的元素有重复；
    2. 数组里面的元素都不重复；
    所以解答也得从这两方面去寻找对应的solution.
     */

    public static void main(String[] args) {
        int[] inputArray = {2, 3, 5, 1, 9, 8, 10, 9};
        List<Integer> indexResultArray = findIndexWithDuplicate(inputArray, 12);
        System.out.println(indexResultArray);
    }

    /**
     * 数组里面的元素都不重复。
     * 以空间换时间的方式，首先将所有的元素，以及元素对应的数组下标存入map中；
     * 然后再遍历数组，从map中去寻找是否有相应的元素；
     *
     * @param inputArray
     * @param total
     * @return
     */
    private static List<Integer> findIndexWithoutDuplicate(int[] inputArray, int total) {
        List<Integer> indexResultList = new ArrayList();
        Map<Integer, Integer> valueMap = initializeMap(inputArray);

        for (int i = 0; i < inputArray.length; i++) {
            if (indexResultList.contains(i)) {
                continue;
            }
            int targetNum = total - inputArray[i];
            if (valueMap.containsKey(targetNum)) {
                indexResultList.add(i);
                indexResultList.add(valueMap.get(targetNum));
            }
        }
        return indexResultList;
    }

    private static Map<Integer, Integer> initializeMap(int[] inputArray) {
        //设置map的容量避免resize
        int size = (int) (inputArray.length / 0.75);
        Map<Integer, Integer> valueMap = new HashMap<>(size);

        for (int i = 0; i < inputArray.length; i++) {
            valueMap.put(inputArray[i], i);
        }
        return valueMap;
    }

    /**
     * 数组里面的元素重复。
     * 类似的使用map，但是map里面的value是一个list对象，存储的是相同值的不同下标。
     *
     * @param inputArray
     * @return
     */
    private static List<Integer> findIndexWithDuplicate(int[] inputArray, int total) {
        List<Integer> indexResultList = new ArrayList();
        Map<Integer, List<Integer>> valueMap = initializeMapWithDuplicate(inputArray);
        for (int i = 0; i < inputArray.length; i++) {
            if (indexResultList.contains(i)) {
                continue;
            }
            int target = total - inputArray[i];
            if (valueMap.containsKey(target)) {
                List<Integer> tempIndexList = valueMap.get(target);
                indexResultList.add(i);
                indexResultList.addAll(tempIndexList);
            }

        }
        return indexResultList;
    }


    private static Map<Integer, List<Integer>> initializeMapWithDuplicate(int[] inputArray) {
        int size = (int) (inputArray.length / 0.75);
        Map<Integer, List<Integer>> valueMap = new HashMap<>(size);

        for (int i = 0; i < inputArray.length; i++) {
            if (valueMap.containsKey(inputArray[i])) {
                //把相同值的不同下标放入List中
                List<Integer> indexList = valueMap.get(inputArray[i]);
                indexList.add(i);
            } else {
                List<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                valueMap.put(inputArray[i], indexList);
            }
        }
        return valueMap;
    }


}
