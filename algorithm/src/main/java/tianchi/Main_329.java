package tianchi;

/**
 * @author zhengdayue
 */
public class Main_329 {

    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            // 当遇到不等的
            if (s.charAt(left) != s.charAt(right)) {
                // 剩下两个元素
                if (right - left == 1) {
                    return true;
                }
                return palindromeResult(s.substring(left, right)) || palindromeResult(s.substring(left + 1, right+1));
            }
            left++;
            right--;
        }
        // 剩下一个元素，left == right，跳出循环
        return true;
    }

    public static boolean palindromeResult(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
