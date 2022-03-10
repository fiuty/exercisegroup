package tianchi;

import java.util.HashMap;
import java.util.Set;

/**
 * 115. 只出现一次的数字 II
 *
 * @author zhengdayue
 */
public class Main_115 {

    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.merge(nums[i], 1, Integer::sum);
        }
        Set<Integer> integers = map.keySet();
        for (Integer integer : integers) {
            if (map.get(integer) == 1) {
                return integer;
            }
        }
        return 0;
    }
}
