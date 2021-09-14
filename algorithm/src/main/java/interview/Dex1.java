package interview;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 员工年龄排序
 * 输入任意个年龄，年龄之间按照小写逗号,分割，最后按照从小到大输出排序后的年龄
 * 如输入：18,50,49,19,20,31,55
 * 输出：18,19,20,31,49,50,55
 *
 * 分析时间复杂度 每一趟归并需要遍历n个数，总共需要log2(n)趟，故时间复杂度为O(nlog2(n))
 * 空间复杂度借助辅助数组为O(n)
 * @author zhengdayue
 */
public class Dex1 {

    public static void main(String[] args) {
        //输入任意个年龄，年龄之间按照小写逗号,分割
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        String[] ageStrings = s.split(",");
        int[] ages = new int[ageStrings.length];
        for (int i = 0; i < ages.length; i++) {
            ages[i] = Integer.parseInt(ageStrings[i]);
        }
        merge(ages, 0, ages.length-1);
        System.out.println(Arrays.toString(ages));
    }

    //归并排序，先分后治
    public static void merge(int[] nums, int i, int j) {
        if (i < j) {
            int mid = (i + j) / 2;
            //分，持续拆分到最小为一个元素无法再分，然后走合并
            merge(nums, i, mid);
            merge(nums, mid + 1, j);
            //治
            mergeSolution(nums, i, mid+1, j);
        }
    }

    /**
     * 归并排序的合并排序
     * @param nums 待排序数组
     * @param i 左边起始坐标
     * @param mid 右边起始坐标
     * @param j 右边最大坐标
     */
    public static void mergeSolution(int[] nums, int i, int mid, int j) {
        //辅助数组
        int[] tempNums = new int[nums.length];
        for (int index = i; index <= j; index++) {
            tempNums[index] = nums[index];
        }
        //左边i最大值
        int iMax = mid;
        //移动下标，循环中每次会移动一位，选取较小的排序
        int moveIndex = i;
        //循环条件是左边坐标i不能超过右边起始坐标mid，而右边起始坐标mid也不能超过最大的j
        while (i < iMax && mid <= j) {
            if (tempNums[i] > tempNums[mid]) {
                nums[moveIndex++] = tempNums[mid++];
            } else {
                nums[moveIndex++] = tempNums[i++];
            }
        }
        //补充剩下的
        while (i < iMax) {
            nums[moveIndex++] = tempNums[i++];
        }
        //补充剩下的
        while (mid <= j) {
            nums[moveIndex++] = tempNums[mid++];
        }
    }
}
