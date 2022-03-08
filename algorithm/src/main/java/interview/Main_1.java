package interview;

import java.util.Scanner;

/**
 每一行代表一台机器，线程A、B、C每次的数字代表消耗CPU的资源，
 每一台机只能串行进行，相邻两台机不能用同一个线程消耗CPU资源，
 即第一行第一台机器使用了A线程，第二行第二台机只能使用B或者C线程来运行，求运行到最后一台机消耗最少的资源。
 输入
 3
 18 5 17
 3 2 8
 47 30 20
 输出
 28
 * @author zhengdayue
 */
public class Main_1 {

    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[][] array = new int[num][3];
        for (int i = 0; i < num; i++) {
            array[i][0] = scanner.nextInt();
            array[i][1] = scanner.nextInt();
            array[i][2] = scanner.nextInt();
        }
        dfs(array, 0, array[0][0], 0);
        dfs(array, 1, array[0][1], 0);
        dfs(array, 2, array[0][2], 0);
        System.out.println(min);
    }

    private static void dfs(int[][] array, int lastThreadNum, int totalSource, int computerNum) {
        if (computerNum == array.length-1) {
            if (totalSource < min) {
                min = totalSource;
            }
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (i != lastThreadNum) {
                dfs(array, i, totalSource + array[computerNum + 1][i], computerNum + 1);
            }
        }
    }
}
