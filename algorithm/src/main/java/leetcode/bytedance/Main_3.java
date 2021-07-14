package leetcode.bytedance;

import java.util.HashSet;
import java.util.Set;

/**
 * 这道题不用求出最长不重复字符串是哪一串，求长度即可，故每次可以用长度比较上一次长度。
 * @author zhengdayue
 */
public class Main_3 {

    public static void main(String[] args) {
        String a = "pwwkew";
        System.out.println(lengthOfLongestSubstring(a));
        System.out.println(two(a));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        String result = s.charAt(0)+"";
        String temp = "";
        int lastIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            if (lastIndex == i) {
                lastIndex++;
            }
            temp = s.substring(i, lastIndex);
            for (int j = lastIndex; j < s.length(); j++) {
                if (temp.contains((s.charAt(j) + ""))) {
                    break;
                } else {
                    lastIndex++;
                    temp = temp + s.charAt(j) + "";
                }
                if (result.length() < temp.length()) {
                    result = temp;
                }
            }
        }
        return result.length();
    }

    /**
     * 从第一个字符开始，开始向右滑动，往Set集合里面存字符，如果存在重复的，那么暂停，记录当前的位置，随后比较当前Set集合的大小
     * 然后在Set集合中丢弃掉当前起始字符的字符
     */
    public static int two(String s) {
        Set<Character> c = new HashSet<>(0);
        int num = 0;
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            while (index<s.length() && !c.contains(s.charAt(index))) {
                c.add(s.charAt(index));
                ++index;
            }
            num = Math.max(num, c.size());
            c.remove(s.charAt(i));
        }
        return num;
    }

}
