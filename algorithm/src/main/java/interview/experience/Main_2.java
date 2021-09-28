package interview.experience;

import java.util.Arrays;

/**
 * 两个有序数组，数组中存在重复数字，合并成一个有序数组，去除重复数字。
 * @author zhengdayue
 */
public class Main_2 {

    public static void main(String[] args) {
        int[] nums1 = {1, 5, 8, 9, 10, 12, 15};
        int[] nums2 = {2, 5, 7, 8, 9, 11, 12, 18};
        int[] nums = reSort(nums1, nums2);
        System.out.println(Arrays.toString(nums));
    }

    public static int[] reSort(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int index = 0;
        int[] temp = new int[nums1.length + nums2.length];
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                j++;
            }
            if (nums1[i] < nums2[j]) {
                temp[index++] = nums1[i++];
            } else {
                temp[index++] = nums2[j++];
            }
        }
        while (i < nums1.length) {
            temp[index++] = nums1[i++];
        }
        while (j < nums2.length) {
            temp[index++] = nums2[j++];
        }
        int[] nums = new int[index];
        for (int num = 0; num < nums.length; num++) {
            nums[num] = temp[num];
        }
        return nums;
    }
}
