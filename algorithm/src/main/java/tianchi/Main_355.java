package tianchi;

import java.util.HashMap;

/**
 * @author zhengdayue
 */
public class Main_355 {

    public int numJewelsInStones(String jewels, String stones) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < stones.length(); i++) {
            map.merge(stones.charAt(i), 1, Integer::sum);
        }
        int count = 0;
        for (int i = 0; i < jewels.length(); i++) {
            Integer num = map.get(jewels.charAt(i));
            if (num != null) {
                count += num;
            }
        }
        return count;
    }
}
