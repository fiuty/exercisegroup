package algorithm.sort;

import java.util.Arrays;

/**
 * 直接插入排序
 * @author zhengdayue
 */
public class Straight {

    public static void main(String[] args) {
        int[] nums = new int[10];
        for (int i = 9; i >= 0; i--) {
            nums[i] = (int) (Math.random() * 5 * i);
        }
        System.out.println(Arrays.toString(nums));
        //第一个元素假设有序，如果从第二个元素起，当前元素小于左边有序最近（已排好序）元素的值，则右移，直到找到合适插入位置
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                int temp = nums[i];
                for (int j = i - 1; j>=0 && temp < nums[j]; j--) {
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
