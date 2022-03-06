package tianchi;

import java.util.Stack;

/**
 * 387. 比较含退格的字符串
 * @author zhengdayue
 */
public class Main_387 {

    public boolean backspaceCompare(String s, String t) {
        Stack<Character> stackS = new Stack<>();
        Stack<Character> stackT = new Stack<>();
        handle(s, stackS);
        handle(t, stackT);
        if (stackS.size() != stackT.size()) {
            return false;
        }
        while (stackS.size() != 0) {
            Character popS = stackS.pop();
            Character popT = stackT.pop();
            if (popS != popT) {
                return false;
            }
        }
        return true;
    }

    public static void handle(String str, Stack<Character> stack) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'a' && c <= 'z') {
                stack.push(c);
            } else {
                if (stack.size() != 0) {
                    stack.pop();
                }
            }
        }
    }
}
