package algorithm.sort.insertionsort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 直接插入排序（属于插入排序）：每次将一个待排序的值插入到有序的子序列中，直到全部记录插入完成。
 * 在待排序序列中，初始第一个元素有序，从第二个元素开始往后不断地插入到左边有序的序列中，
 * 假设坐标为i的插入到位置k中，那么k~i-1的元素需要后移，而将i值赋值到k值中，这样来完成插入排序。
 *
 * 直接插入排序时间复杂度为n^2，直接插入排序为稳定排序（不改变重复值位置）
 * @author zhengdayue
 */
public class Straight {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = new int[10];
        //输入10个数字
        for (int i = 0; i < 10; i++) {
            nums[i] = scanner.nextInt();
        }
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
