package interview;

import java.util.Arrays;

/**
 * 华为一面笔试题，10-20分钟手写
 * 不重复数字全排列
 * 比如1,2,3
 * 输出
 * [1, 2, 3]
 * [1, 3, 2]
 * [2, 1, 3]
 * [2, 3, 1]
 * [3, 1, 2]
 * [3, 2, 1]
 * 这道题也算是深度优先搜索题目，只不过区别在于深度下一个访问节点/方向上比较难想到
 * 其实下一个方向就是所有数字并且未访问过的数字即可继续往里边走
 * 故往里边继续走的判断逻辑是for所有数字并且未被访问过的数字即可！
 * @author zhengdayue
 */
public class NumPullPermutation {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        //判断数字是否访问过了
        boolean[] flags = new boolean[nums.length];
        //用于存储全排列数字，最后输出
        int[] out = new int[nums.length];
        //第一轮，第一个数字为所有数字遍历一次，然后再深搜下去
        for (int i = 0; i < nums.length; i++) {
            flags[i] = true;
            //初始值第一位数字
            out[0] = nums[i];
            fullPermutation(nums, flags, out, 1);
            flags[i] = false;
        }
    }

    public static void fullPermutation(int[] nums, boolean[] flags, int[] out, int currentIndex) {
        if (currentIndex == nums.length) {
            System.out.println(Arrays.toString(out));
        }
        //继续遍历所有数字（方向/深度）并且未访问过的数字即可满足全排列条件
        for (int i = 0; i < nums.length; i++) {
            if (!flags[i]) {
                flags[i] = true;
                out[currentIndex] = nums[i];
                fullPermutation(nums, flags, out, currentIndex+1);
                flags[i] = false;
            }
        }
    }
}
