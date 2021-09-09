package leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * 每日温度:遍历，每次往后查第几个
 * 优化：利用栈的特性，只需遍历一次温度集，当遇到当前温度大于栈顶，则pop出栈，计算下标多少天数，
 * 然后再循环判断此时栈顶温度是否满足条件，不然会遗漏被压入栈的温度。这样一来保证了在栈里面的温度都是前者温度高于后者入栈的，
 * 需要等待遍历数组来解救出栈，如果没出栈则后续的温度不会高于栈内的温度了，默认int数组也是0满足输出条件。
 *
 * @author zhengdayue
 */
public class Main_739 {

    public static void main(String[] args) {
        int[] t = {73,74,75,71,69,72,76,73};
        System.out.println(Arrays.toString(dailyTemperaturesBetter(t)));
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        int[] nums = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            int currentTemperature = temperatures[i];
            //每次往后查第几个
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > currentTemperature) {
                    nums[i] = j - i;
                    break;
                }
            }
        }
        return nums;
    }

    //利用栈，优化版
    public static int[] dailyTemperaturesBetter(int[] temperatures) {
        int[] nums = new int[temperatures.length];
        Stack<Integer> indexStack = new Stack<>();
        indexStack.push(0);
        for (int i = 1; i < temperatures.length; i++) {
            Integer pop = indexStack.peek();
            //当当前温度大于栈顶时，循环弹出。
            while (!indexStack.isEmpty() && temperatures[pop] < temperatures[i]) {
                nums[pop] = i - pop;
                //栈顶出栈
                indexStack.pop();
                if (!indexStack.isEmpty()) {
                    pop = indexStack.peek();
                }
            }
            //压入最新的温度
            indexStack.push(i);
        }
        return nums;
    }
}
