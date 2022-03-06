package tianchi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengdayue
 */
public class Main_25 {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int count = 1;
        int current = nums[0];
        List<Integer>list = new ArrayList<>(8);
        list.add(current);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == current) {
                continue;
            }
            current = nums[i];
            count++;
            list.add(current);
        }
        for (int i = 0; i < count; i++) {
            nums[i] = list.get(i);
        }
        return count;
    }
}
