package tianchi;

import java.util.*;

/**
 * 14. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * @author zhengdayue
 */
public class Main_14 {

    /**
     * 解析思路是：
     * 先排序，然后遍历一次数组，两个下标，一个left，一个right，指向未遍历的最小值和最大值，然后不断地往左和往右试探
     * 当满足条件则记录下来，直到试探区间不满足条件退出
     * 同时可以优化
     * 1.当重复遍历某个数的时候，需要跳过。
     * 2.当遍历的时候index数字大于0，则退出
     * 3.当right小于0，则退出
     *
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>(0);
        }
        List<List<Integer>> list = new ArrayList<>(8);
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // 当于上一个数字一样时，跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[i] > 0) {
                    break;
                }
                if (nums[right] < 0) {
                    break;
                }
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> temp = new ArrayList<>(4);
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    list.add(temp);
                    left++;
                    while (left < nums.length && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    right--;
                    while (right >= 0 && nums[right] == nums[right + 1]) {
                        right--;
                    }
                    continue;
                }
                if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return list;
    }


    // 从三层循环降到两层循环还不行，超时了。看了答案可以一层循环就解决问题
    public List<List<Integer>> threeSum1(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>(0);
        }
        // key为数字，value为该数字的次数
        Map<Integer, Integer> map = new HashMap<>(8);
        for (int i = 0; i < nums.length; i++) {
            map.merge(nums[i], 1, Integer::sum);
        }
        List<List<Integer>> list = new ArrayList<>(8);
        // 用于判断是否已存在
        Set<String> sets = new HashSet<>(8);
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                map.merge(nums[i], -1, Integer::sum);
                map.merge(nums[j], -1, Integer::sum);
                int num = (nums[i] + nums[j]) * -1;
                Integer value = map.get(num);
                List<Integer> temp = new ArrayList<>(4);
                temp.add(nums[i]);
                temp.add(nums[j]);
                temp.add(num);
                String sort = sort(temp);
                if (value != null && value != 0 && !sets.contains(sort)) {
                    list.add(temp);
                    sets.add(sort);
                }
                map.merge(nums[i], 1, Integer::sum);
                map.merge(nums[j], 1, Integer::sum);
            }
        }
        return list;
    }

    public static String sort(List<Integer> temp) {
        Collections.sort(temp);
        return temp.get(0) + "-" + temp.get(1) + "-" + temp.get(2);
    }
}
