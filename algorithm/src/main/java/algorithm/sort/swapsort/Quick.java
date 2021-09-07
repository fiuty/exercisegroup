package algorithm.sort.swapsort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 快速排序（属于交换排序）：对冒泡排序的改进，基本思想是分治法，每次取出一个数，将待排序数组切分成两个，取出的数作为中点切分，
 * 每趟排序后都有一个元素（基准元素）被放到最终位置上，当数组被切分到只剩下一个元素时，即结束，此时已经排好序了。
 *
 * 严蔚敏教材对于取出的基数，都是以数组第一个元素作为基数。
 *
 * 时间复杂度为n*log2(n),n为每次移动找出mid合适位置，log2(n)为把数组一分为二。快速排序是不稳定的排序算法。
 * 在排序算法中快速排序性能较好。
 * @author zhengdayue
 */
public class Quick {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = new int[10];
        for (int i = 0; i < 10; i++) {
            nums[i] = scanner.nextInt();
        }
        quickSort(nums, 0, 9);
        System.out.println(Arrays.toString(nums));
    }

    public static void quickSort(int[] nums, int low, int high) {
        if (low < high) {
            int mid = partition(nums, low, high);
            quickSort(nums, low, mid - 1);
            quickSort(nums, mid + 1, high);
        }
    }

    public static int partition(int[] nums, int low, int high) {
        // 严蔚敏教材对于取出的基数，都是以数组第一个元素作为基数。
        int mid = nums[low];
        // 这种写法比较难理解，最后low和high都是最终mid的坐标
        // 先从右往左，再从左往右，保证右边大，左边小，保证右边比mid大，左边比mid小，mid一直被丢来丢去寻找合适位置
        // 最后low和high会相等，即为mid的有序下标了。
        while (low < high) {
            while (low < high && nums[high] >= mid) {
                --high;
            }
            nums[low] = nums[high];
            nums[high] = mid;
            while (low < high && nums[low] <= mid) {
                ++low;
            }
            nums[high] = nums[low];
            nums[low] = mid;
        }
        return low;
    }
}
