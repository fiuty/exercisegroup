package leetcode.bytedance;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengdayue
 */
public class Main_1 {

    public static void main(String[] args) {
        int [] nums = {2,7,11,15};
        int target = 9;
        int[] ints = twoSum(nums, target);
        System.out.println(Arrays.toString(ints));

        int [] nums1 = {3,2,4};
        int target1 = 6;
        int[] ints1 = twoSum(nums1, target1);
        System.out.println(Arrays.toString(ints1));

        int [] nums2 = {3,3};
        int target2 = 6;
        int[] ints2 = twoSum(nums2, target2);
        System.out.println(Arrays.toString(ints2));
    }

    public static int[] twoSum(int[] nums, int target) {
        for(int i=0;i<=nums.length-2;i++){
            for(int j=i+1;j<=nums.length-1;j++){
                if(nums[i]+nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
