package interview;

import java.util.HashSet;

/**
 * 最大连接色块，在一个5*3的颜色矩阵中，有4种颜色，每个格子相邻的8个格子都可以连起来，同种颜色才能连，求最大色块连接数
 * 例子，用1-4代替例题四种颜色
 * 色块：
 * 1 2 2
 * 2 3 1
 * 2 2 3
 * 4 4 4
 * 1 2 3
 * 输出5
 *
 * 深搜的题目可以优化一下递归退出条件，之前做法是判断当前是否还有路径可以走，这样会有多余的判断！
 * 可以换成判断当前是否是满足条件的，如果已经发生数组坐标越界或者是错误的不可走的，直接返回结果，否则继续往下走。
 * 区别在于结果，当前可用走，结果做加1操作，如果下一步是错的，则直接返回上一个传入的结果即可！
 * @author zhengdayue
 */
public class DfsMaxBlock {

    private static int sum = -1;

    public static void main(String[] args) {
        new HashSet<>();
        System.out.println(sum);
    }

    public static void dfs(int[][] array, int i, int j, int count, int[][] temp) {
        //当8个方向无路可走，则比较结果大小
        //下
        boolean flag1 = i+1>= 5;

        //当8个方向有可以走的，继续深搜走下去
    }

}
