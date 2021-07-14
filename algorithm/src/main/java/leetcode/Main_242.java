package leetcode;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author zhengdayue
 * @date: 2021-07-05
 */
public class Main_242 {

    public static void main(String[] args) {
        boolean anagram = isAnagram("anagram", "nagaram");
        System.out.println(anagram);
        boolean result2 = isAnagram("rat", "car");
        System.out.println(result2);

        System.out.println(isAnagram2("anagram", "nagaram"));
        System.out.println(isAnagram2("rat", "car"));
    }

    //利用hashMap,不限于26个小写字母
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<String, Integer> sMap = new HashMap<>(s.length());
        HashMap<String, Integer> tMap = new HashMap<>(t.length());
        for (int i = 0; i < s.length(); i++) {
            sMap.merge(s.charAt(i) + "", 1, Integer::sum);
        }
        for (int i = 0; i < t.length(); i++) {
            tMap.merge(t.charAt(i) + "", 1, Integer::sum);
        }
        for (String sKey : sMap.keySet()) {
            Integer num1 = sMap.get(sKey);
            Integer num2 = tMap.get(sKey);
            if (!Objects.equals(num1, num2)) {
                return false;
            }
        }
        return true;
    }

    //26个小写字母
    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        String letters = "qwertyuiopasdfghjklzxcvbnm";
        int[] nums = new int[26];
        for (int i = 0; i < 26; i++) {
            nums[i] = 0;
        }
        for (int i = 0; i < s.length(); i++) {
            int index = letters.indexOf(s.charAt(i) + "");
            nums[index] = nums[index] + 1;
        }
        for (int i = 0; i < t.length(); i++) {
            int index = letters.indexOf(t.charAt(i) + "");
            nums[index] = nums[index] - 1;
        }
        for (int i = 0; i < 26; i++) {
            if (nums[i] != 0) {
                return false;
            }
        }
        return true;
    }

}
