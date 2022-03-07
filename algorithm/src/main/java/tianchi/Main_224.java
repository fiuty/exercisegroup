package tianchi;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zhengdayue
 */
public class Main_224 {

    public static void main(String[] args) {
        System.out.println(decodeString("3[a2[c]]"));
    }

    public static String decodeString(String s) {
        // 先拆成数组
        String[] array = handle(s);
        // 数字栈
        Stack<Integer>numStack = new Stack<>();
        // 符号栈
        Stack<String>stringStack = new Stack<>();
        // 字母、[]符号
        String ch = "qwertyuiopasdfghjklzxcvbnm[]";
        for (int i = 0; i < array.length; i++) {
            String str = array[i];
            // 符号
            if (ch.contains(str)) {
                // ]符号,弹出字符直到遇到[，同时弹出数字k
                if (str.equals("]")) {
                    handleMul(numStack, stringStack);
                } else {
                    stringStack.add(str);
                }
            } else {
                // 数字
                numStack.add(Integer.parseInt(str));
            }
        }
        StringBuilder back = new StringBuilder();
        while (!stringStack.isEmpty()) {
            back.append(stringStack.pop());
        }
        return back.reverse().toString();
    }

    // 弹出字符直到遇到[，同时弹出数字k
    private static void handleMul(Stack<Integer>numStack, Stack<String>stringStack) {
        // 倍数
        Integer pop = numStack.pop();
        StringBuilder str = new StringBuilder();
        while (true) {
            String ch = stringStack.pop();
            if (ch.equals("[")) {
                break;
            }
            str.append(ch);
        }
        // 需要k次的字符串
        StringBuilder kTimeStr = new StringBuilder(str.reverse().toString());
        // 累计k次的字符串
        StringBuilder totalStr = new StringBuilder();
        for (int i = 0; i < pop; i++) {
            totalStr.append(kTimeStr);
        }
        // 把字符压入字符栈
        String s = totalStr.toString();
        for (int i = 0; i < s.length(); i++) {
            stringStack.add(s.charAt(i) + "");
        }
    }

    // 先拆成数组
    public static String[] handle(String s) {
        // k个数
        int kCount = 0;
        // 数字个数，用于求出数组长度
        int numCount = 0;
        // k集合
        List<Integer> kList = new ArrayList<>(8);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char currentCh = s.charAt(i);
            if (isNum(currentCh)) {
                str.append(currentCh);
                numCount++;
                // 当前符号是数字，并且上一个符号不是数字(排除0的情况，0没有上一个字符)
                if (i == 0 || !isNum(s.charAt(i - 1))) {
                    kCount++;
                }
            } else {
                String num = str.toString();
                // 不是数字
                if (num.length() != 0) {
                    kList.add(Integer.parseInt(num));
                    // 重置
                    str = new StringBuilder();
                }
            }
        }
        // 数组大小
        int arraySize = s.length() - numCount + kCount;
        String[] array = new String[arraySize];
        int index = 0;
        int numIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9') {
                array[index++] = ch + "";
                continue;
            }
            // 如果是数字，并且上一个不是数字，那么赋值k
            if (isNum(ch) && (i == 0 || !isNum(s.charAt(i - 1)))) {
                array[index++] = kList.get(numIndex++).toString();
            }
        }
        return array;
    }

    public static boolean isNum(char ch) {
        return ch >= '0' && ch <= '9';
    }
}
