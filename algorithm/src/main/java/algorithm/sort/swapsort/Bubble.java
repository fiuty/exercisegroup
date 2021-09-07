package algorithm.sort.swapsort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 冒泡排序（属于交换排序）：从左到右或者从右到左，两两比较大小交换相邻元素，每一趟下来能保证一个元素的最终位置（最大或者最小）在最右或者最左。
 *
 * 时间复杂度n^2，为稳定排序算法。
 * @author zhengdayue
 */
public class Bubble {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = new int[10];
        for (int i = 0; i < 10; i++) {
            nums[i] = scanner.nextInt();
        }
        //从左往右，两两比较出最大的，放在最右边
        for (int i = nums.length - 1; i >= 0; i--) {
            //如果某趟冒泡无需交换，那么说明已经有序了，提前退出。
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j+1]) {
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
