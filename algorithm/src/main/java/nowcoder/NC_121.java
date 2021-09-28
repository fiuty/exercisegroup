package nowcoder;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * NC121 字符串的排列
 * 输入一个长度为 n 字符串，打印出该字符串中字符的所有排列，你可以以任意顺序返回这个字符串数组。
 * 例如输入字符串abc,则输出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 *
 * 深搜，深搜的方向是没被访问过的节点，遍历全部节点，辅助flag判断是否被前面的访问过。
 * @author zhengdayue
 */
public class NC_121 {

    public static HashSet<String> sets;

    public static void main(String[] args) {
        ArrayList<String> abc = Permutation("");
        for (int i = 0; i < abc.size(); i++) {
            System.out.println(abc.get(i));
        }
    }

    public static ArrayList<String> Permutation(String str) {
        if (str.equals("")) {
            return new ArrayList<>(0);
        }
        int length = str.length();
        int size = 1;
        for (int i = length; i >= 2; i--) {
            size *= i;
        }
        sets = new HashSet<>(size);
        boolean[] flag = new boolean[length];
        char[] chars = str.toCharArray();
        char[] out = new char[length];
        for (int i = 0; i < length; i++) {
            flag[i] = true;
            out[0] = chars[i];
            dfsPermutation(chars, flag, out, 1);
            flag[i] = false;
        }
        ArrayList<String> list = new ArrayList<>(sets.size());
        list.addAll(sets);
        return list;
    }

    public static void dfsPermutation(char[] str, boolean[] flag, char[] out, int currentIndex) {
        if (currentIndex == str.length) {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < out.length; i++) {
                s.append(out[i]);
            }
            sets.add(s.toString());
            return;
        }
        for (int i = 0; i < str.length; i++) {
            if (!flag[i]) {
                flag[i] = true;
                out[currentIndex] = str[i];
                dfsPermutation(str, flag, out, currentIndex+1);
                flag[i] = false;
            }
        }
    }
}
