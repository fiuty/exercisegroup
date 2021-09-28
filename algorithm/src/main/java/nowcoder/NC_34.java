package nowcoder;

/**
 * NC34 求路径
 * 一个机器人在m×n大小的地图的左上角（起点）。
 * 机器人每次可以向下或向右移动。机器人要到达地图的右下角（终点）。
 * 可以有多少种不同的路径从起点走到终点？
 *
 * 动态规划求解，第一行和第一列都只有一条路径能到达终点，然后其他节点的路径数等于上边和左边的路径之和。
 * @author zhengdayue
 */
public class NC_34 {

    public static void main(String[] args) {
        System.out.println(uniquePaths(1,1));
    }

    public static int uniquePaths (int m, int n) {
        if (m == n && m == 1) {
            return 1;
        }
        int[][] nums = new int[m][n];
        for (int i = 1; i < n; i++) {
            nums[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            nums[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                nums[i][j] = nums[i - 1][j] + nums[i][j - 1];
            }
        }
        return nums[m - 1][n - 1];
    }
}
