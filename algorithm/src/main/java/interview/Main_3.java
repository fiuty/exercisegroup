package interview;

import java.util.Scanner;

/**
 1代表电脑，0代表墙，坐标上下左右可以互联，求n*m矩阵互联的电脑最大个数
 输入样例
 2 2
 1 0
 1 1
 输出
 3
 * @author zhengdayue
 */
public class Main_3 {

    public static int max = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int[][] map = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                map[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (map[i][j] == 1) {
                    map[i][j] = 0;
                    dfs(map, i, j, 1);
                    map[i][j] = 1;
                }
            }
        }
        System.out.println(max);
    }

    private static void dfs(int[][] map, int x, int y, int count) {
        // 向上
        boolean up = (x - 1) >= 0 && map[x - 1][y] == 1;
        // 向下
        boolean down = (x + 1) < map.length && map[x + 1][y] == 1;
        // 向左
        boolean left = (y - 1) >= 0 && map[x][y - 1] == 1;
        // 向右
        boolean right = (y + 1) < map[0].length && map[x][y + 1] == 1;
        // 当无路可走
        if (!up && !down && !left && !right) {
            if (count > max) {
                max = count;
            }
            return;
        }
        if (up) {
            map[x - 1][y] = 0;
            dfs(map, x - 1, y, count + 1);
            map[x - 1][y] = 1;
        }
        if (down) {
            map[x + 1][y] = 0;
            dfs(map, x + 1, y, count + 1);
            map[x + 1][y] = 1;
        }
        if (left) {
            map[x][y - 1] = 0;
            dfs(map, x,y - 1, count + 1);
            map[x][y - 1] = 1;
        }
        if (right) {
            map[x][y + 1] = 0;
            dfs(map, x, y + 1, count + 1);
            map[x][y + 1] = 1;
        }
    }
}
