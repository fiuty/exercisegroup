package tianchi;

import java.util.Arrays;

/**
 * 136. 多数元素
 *
 * @author zhengdayue
 */
public class Main_136 {

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[(nums.length - 1) / 2];
    }

}
