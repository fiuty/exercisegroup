package leetcode.sword;

/**
 * @author zhengdayue
 */
public class Main_58_1 {

    public static void main(String[] args) {
        System.out.println(reverseLeftWords("lrloseumgh",6));
        System.out.println(reverseLeftWords1("lrloseumgh", 6));
    }

    /**
     * 若用String字符串拼接的话效率最低，用StringBuilder还好
     * String字符串是不可变对象，每次用String+的话都是新建对象，效率低下
     */
    public static String reverseLeftWords(String s, int n) {
        StringBuilder newStr = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = n; i < chars.length; i++) {
            newStr.append(chars[i]);
        }
        for (int i = 0; i < n; i++) {
            newStr.append(chars[i]);
        }
        return newStr.toString();
    }

    /**
     * 效率最高
     */
    public static String reverseLeftWords1(String s, int n) {
        return s.substring(n)+s.substring(0, n);
    }
}
