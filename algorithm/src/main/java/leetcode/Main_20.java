package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * 有效的括号：利用栈的特性，后进先出策略，当读到左括号则入栈，当读到右括号的时候出栈判断栈顶元素是否跟当前右括号匹配。
 * @author zhengdayue
 */
public class Main_20 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        Map<Character, Character> map = new HashMap<>(4);
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        Stack<Character> stack = new Stack<>();
        boolean flag = true;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            //读到右符号，出栈，判断是否符合
            if (map.containsKey(ch)) {
                if (!stack.isEmpty()) {
                    Character pop = stack.pop();
                    if (map.get(ch) != pop) {
                        flag = false;
                        break;
                    }
                } else {
                    flag = false;
                    break;
                }
            } else {
                stack.push(ch);
            }
        }
        if (!stack.isEmpty()) {
            flag = false;
        }
        System.out.println(flag);
    }
}
