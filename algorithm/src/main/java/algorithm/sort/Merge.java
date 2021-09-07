package algorithm.sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 归并排序：归并排序核心是分治，将分治的思想提现得淋漓尽致。先分后治，即把待排序数组先分成两半，然后递归，最后合并两边。
 * 递归的结束条件是无法再拆分数组，左边无法拆，右边无法拆，只剩下两个元素比较大小合并，然后一直往上合并，最后组成排序好的数组。
 *
 * 归并排序的时间复杂度为nlog2(n)，是一个稳定的排序算法。
 * @author zhengdayue
 */
public class Merge {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = new int[4];
        //输入10个数字
        for (int i = 0; i < 4; i++) {
            nums[i] = scanner.nextInt();
        }
        mergeSort(nums, 0, 3);
        System.out.println(Arrays.toString(nums));
    }

    public static void mergeSort(int[] nums, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            //拆分
            mergeSort(nums, low, mid);
            mergeSort(nums, mid + 1, high);
            //合并
            merge(nums, low, mid, high);
        }
    }

    public static void merge(int[] nums, int low, int mid, int high) {
        //辅助数组
        int[] tempNums = new int[nums.length];
        for (int i = low; i <= high; i++) {
            tempNums[i] = nums[i];
        }
        //左边有序数组的第一个元素
        int leftIndex = low;
        //右边有序数据的第一个元素
        int rightIndex = mid + 1;
        //当前数组的第一个下标
        int index = low;
        //左边有序，右边也有序，组成新的有序数组，每次取出最小的那个
        while (leftIndex <= mid && rightIndex <= high && index <= high) {
            if (nums[leftIndex] > nums[rightIndex]) {
                nums[index++] = tempNums[rightIndex++];
            } else {
                nums[index++] = tempNums[leftIndex++];
            }
        }
        //剩下的元素补充完整
        while (leftIndex <= mid) {
            nums[index++] = tempNums[leftIndex++];
        }
        while (rightIndex <= high) {
            nums[index++] = tempNums[rightIndex++];
        }
    }
}
