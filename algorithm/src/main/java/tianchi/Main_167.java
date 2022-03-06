package tianchi;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 167. 基本计算器 II
 * @author zhengdayue
 */
public class Main_167 {

    public static void main(String[] args) {
        System.out.println(calculate("3+2*2"));
    }

    public static int calculate(String s) {
        String[] array = handleArray(s);
        Stack<Integer> nums = new Stack<>();
        Stack<String> characters = new Stack<>();
        String lastCh = null;
        for (int i = 0; i < array.length; i++) {
            String ch = array[i];
            // 如果遇到负号- 的话，需要对下一个数字进行*-1负数处理，这里入栈要从负号转为加号
            if (ch.equals("+") || ch.equals("-")) {
                lastCh = ch;
                characters.push("+");
            } else if (ch.equals("*") || ch.equals("/")) {
                Integer numB = Integer.parseInt(array[++i] + "");
                Integer numA = nums.pop();
                if (ch.equals("*")) {
                    nums.push(numA * numB);
                    continue;
                }
                nums.push(numA / numB);
            } else {
                int num = Integer.parseInt(ch + "");
                if (lastCh != null && lastCh.equals("-")) {
                    num = num * -1;
                }
                nums.push(num);
            }
        }
        while (characters.size() != 0) {
            String ch = characters.pop();
            Integer numB = nums.pop();
            Integer numA = nums.pop();
            if (ch.equals("+")) {
                nums.push(numA + numB);
                continue;
            }
            nums.push(numA - numB);
        }
        return nums.pop();
    }

    // 不能直接遍历符号s，要考虑两位数以上的数字，把数字考虑成单独的一个符号。
    public static String[] handleArray(String s) {
        // 去空格
        s = s.replace(" ", "");
        // 转化为数组
        String temp = s;
        // 保存符号
        List<String> chs = new ArrayList<>(8);
        for (int i = 0; i < s.length(); i++) {
            String tempCh = s.charAt(i) + "";
            if ("+-/*".contains(tempCh)) {
                chs.add(tempCh);
            }
        }
        String replaceS = temp.replace("+"," + ")
                .replace("-", " + ")
                .replace("*", " + ")
                .replace("/", " + ");
        String[] chArray = replaceS.split(" ");
        int index = 0;
        for (int i = 0; i < chArray.length; i++) {
            if (chArray[i].equals("+")) {
                chArray[i] = chs.get(index++);
            }
        }
        return chArray;
    }
}
