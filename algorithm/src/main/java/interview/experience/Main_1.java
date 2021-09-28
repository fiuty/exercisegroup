package interview.experience;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 水域大小（类似于矩阵，有多少种情况能相连，8个方向）
 * 有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。
 * 由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。
 * 编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
 *
 * 示例：
 * 输入：
 * [
 *   [0,2,1,0],
 *   [0,1,0,1],
 *   [1,1,0,1],
 *   [0,1,0,1]
 * ]
 * 输出： [1,2,4]
 *
 * 提示：
 *     0 < len(land) <= 1000
 *     0 < len(land[i]) <= 1000
 * 解题思路：深度优先搜索，不过和之前的不太一样，这里的解题思路是每走一步的话，进行加1，然后继续深搜，最后把总数返回
 * 并且访问过的节点进行标记，达到遍历矩阵的时候，能走就继续走，然后标记为访问过
 * 最后返回当前遍历的这个节点能连起来多少个数，除去0的情况。
 *
 * */
public class Main_1 {

    //8个方向
    public static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, 1}, {1, -1}, {-1, 1}};

    public static List<Integer> nums = new ArrayList<>(8);

    public static void main(String[] args) {
        int[][] array = {{0,2,1,0}, {0,1,0,1}, {1,1,0,1},{0,1,0,1}};
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[0].length; y++) {
                int dfs = dfs(array, x, y);
                if (dfs != 0) {
                    nums.add(dfs);
                }
            }
        }
        Integer[] integers = nums.toArray(new Integer[0]);
        Arrays.sort(integers);
        System.out.println(Arrays.toString(integers));
    }

    public static int dfs(int[][] array, int x, int y) {
        int count = 0;
        //当前节点不可访问或者数组越界
        if (x < 0 || x >= array.length || y < 0 || y >= array[0].length || array[x][y] != 0) {
            return count;
        }
        ++count;
        //访问方向
        array[x][y] = -1;
        for (int i = 0; i < dir.length; i++) {
            int dirX = dir[i][0];
            int dirY = dir[i][1];
            count += dfs(array, x + dirX, y + dirY);
        }
        return count;
    }
}
