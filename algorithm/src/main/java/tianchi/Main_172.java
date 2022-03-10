package tianchi;

/**
 * 171. 2 的幂
 *
 * @author zhengdayue
 */
public class Main_172 {

    public boolean isPowerOfTwo(int n) {
        int[] array = new int[31];
        array[0] = 1;
        for (int i = 1; i <= 30; i++) {
            array[i] = 2 * array[i - 1];
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == n) {
                return true;
            }
        }
        return false;
    }
}
