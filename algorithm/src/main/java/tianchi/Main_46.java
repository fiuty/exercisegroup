package tianchi;

/**
 * 46. 最大子数组和
 *
 * @author zhengdayue
 */
public class Main_46 {

    // 动态规划
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int tempMax = Math.max(nums[i] + pre, nums[i]);
            pre = tempMax;
            if (max < tempMax) {
                max = tempMax;
            }
        }
        return max;
    }
}
