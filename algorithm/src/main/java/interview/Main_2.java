package interview;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 输入整数字符串，中间用“,”隔开数字，求重新排序输出最大整数
 输入
 22,221
 输出
 22221
 输入
 4578,4384,9999,123
 输出
 999945784384123
 * @author zhengdayue
 */
public class Main_2 {

    private static List<BigDecimal> list;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        String[] split = next.split(",");
        int size = 1;
        for (int i = split.length; i >= 1; i--) {
            size *= i;
        }
        list = new ArrayList<>(size);
        for (int i = 0; i < split.length; i++) {
            String current = split[i];
            split[i] = "visted";
            dfs(split, 1, current);
            split[i] = current;
        }
        Collections.sort(list);
        System.out.println(list.get(list.size() - 1));
    }

    private static void dfs(String[] split, int count, String numStr) {
        if (count == split.length) {
            list.add(new BigDecimal(numStr));
            return;
        }
        for (int i = 0; i < split.length; i++) {
            String current = split[i];
            if (!current.equals("visted")) {
                split[i] = "visted";
                dfs(split, count + 1, numStr + current);
                split[i] = current;
            }
        }
    }
}
