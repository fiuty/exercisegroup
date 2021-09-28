package nowcoder;

/**
 * NC17 最长回文子串
 * 遍历字符串，中心扩展法，然后根据当前字符串往左右两边扩散，看看是不是回文字符串
 * 需要考虑bba这种情况，bb也是回文字符串，所以当紧挨着的中心元素的前后元素有相等则继续下一轮回文判断
 * @author zhengdayue
 */
public class NC_17 {

    public static void main(String[] args) {
        System.out.println(getLongestPalindrome("abc1234321ab",12));
    }

    public static int getLongestPalindrome(String A, int n) {
        int max = -1;
        for (int i = 0; i < n; i++) {
            int left = i-1;
            int right = i+1;
            while (left >= 0 && right < n) {
                //相邻着相同要继续走下一轮
                if (i - left == 1 &&A.charAt(left) == A.charAt(i)) {
                    --left;
                    continue;
                }
                //相邻着相同要继续走下一轮
                if (right - i == 1 && A.charAt(right) == A.charAt(i)) {
                    ++right;
                    continue;
                }
                if (A.charAt(left) != A.charAt(right)) {
                    break;
                }
                --left;
                ++right;
            }
            int current = right - left - 1;
            if (current > max) {
                max = current;
            }
        }
        return max;
    }
}
