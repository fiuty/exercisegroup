package nowcoder;


import java.util.HashMap;
import java.util.Stack;

/**
 * NC137 表达式求值
 * 需要将中缀表达式转换为后缀表达式（逆波兰式），中缀表达式就是我们正常的书写方式
 * 转为后缀表达式之后遇到符号就弹出栈两个元素然后再入栈，最后剩下的就是表达式值
 * 转后缀表达式的方法：遇到数字输出，遇到符号根据优先级，如果栈顶的低于现在的符号，则入栈，否则弹出栈再入现在的符号
 * （左括号优先级最高，当读到）右括号时弹出符号，直到弹出（为止，输出的即为后缀表达式
 * @author zhengdayue
 */
public class NC_137 {

    public static void main(String[] args) {
        System.out.println(solve("(12*(3-4))*5"));
    }

    //"(12*(3-4))*5"
    //先将中缀表达式转为后缀表达式，再求值
    public static int solve (String s) {
        HashMap<String, Integer> map = new HashMap<>(8);
        map.put("(", 3);
        map.put("*", 2);
        map.put("+", 1);
        map.put("-", 1);
        map.put(")", 0);
        String number = "0123456789";
        StringBuilder out = new StringBuilder("");
        Stack<String> stack = new Stack<>();
        //数字，如果是大于2位数的数字继续叠加
        StringBuilder num = new StringBuilder();
        boolean flagNum = false;
        //负数符号判断
        boolean flagNge = false;
        for (int i = 0; i < s.length(); i++) {
            //是数字
            if (number.contains(s.charAt(i) + "")) {
                if (flagNum) {
                    num.append(s.charAt(i));
                } else {
                    num = new StringBuilder(s.charAt(i) + "");
                    flagNum = true;
                }
            } else {
                if (!num.toString().equals("")) {
                    out.append(num.toString()).append(" ");
                    num = new StringBuilder();
                }
                flagNum = false;
                while (true) {
                    if (!stack.isEmpty()) {
                        String peek = stack.peek();
                        Integer stackPeekNum = map.get(peek);
                        Integer currentNum = map.get(s.charAt(i) + "");
                        //当前为右括号），直到栈顶为左括号为止才跳出（
                        if (stackPeekNum == 3 && currentNum == 0) {
                            stack.pop();
                            break;
                        }
                        if (currentNum > stackPeekNum) {
                            stack.add(s.charAt(i) + "");
                            break;
                        } else {
                            //如果栈顶是左括号（，且当前不是右括号），直接把当前字符入栈
                            if (stackPeekNum == 3) {
                                stack.add(s.charAt(i) + "");
                                break;
                            }
                            out.append(peek).append(" ");
                            stack.pop();
                        }
                    } else {
                        stack.add(s.charAt(i) + "");
                        break;
                    }
                }
            }
        }
        if (!num.toString().equals("")) {
            out.append(num.toString()).append(" ");
        }
        while (!stack.isEmpty()) {
            out.append(stack.pop()).append(" ");
        }
        String[] chars = out.toString().split(" ");
        Stack<Integer> stackNumber = new Stack<>();
        String chs = "+-*";
        for (int i = 0; i < chars.length; i++) {
            String ch = chars[i];
            //符号
            if (chs.contains(ch)) {
                Integer num2 = stackNumber.pop();
                Integer num1 = stackNumber.pop();
                if (ch.equals("+")) {
                    stackNumber.add(num1 + num2);
                } else if (ch.equals("-")) {
                    stackNumber.add(num1 - num2);
                } else if (ch.equals("*")) {
                    stackNumber.add(num1 * num2);
                }
            } else {
                stackNumber.add(Integer.parseInt(ch));
            }
        }
        return stackNumber.pop();
    }
}
