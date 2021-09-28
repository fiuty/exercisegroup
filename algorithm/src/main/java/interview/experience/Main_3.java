package interview.experience;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * 练习广度优先搜索 走迷宫最短路径
 * @author zhengdayue
 */
public class Main_3 {

    /**
     * 迷宫问题，POJ3984，http://poj.org/problem?id=3984
     * 输入5*5迷宫，从左上角到右下角的最短路径，输出路径
     * 例：
     * 0 1 0 0 0
     * 0 1 0 1 0
     * 0 0 0 0 0
     * 0 1 1 1 0
     * 0 0 0 1 0
     * 输出
     * (0, 0)
     * (1, 0)
     * (2, 0)
     * (2, 1)
     * (2, 2)
     * (2, 3)
     * (2, 4)
     * (3, 4)
     * (4, 4)
     */
    public static void main(String[] args) {
        int[][] map = new int[5][5];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = scanner.nextInt();
            }
        }
        //上、下、左、右方向
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        LinkedList<MapNode> nodeQueue = new LinkedList<>();
        //起始点加入
        nodeQueue.add(new MapNode(0, 0));
        //标记起始点步数，每次添加节点，对新节点的num进行+1操作，然后标记，这里以2开始，防止输出路径跟1墙重复
        int num = 2;
        map[0][0] = num;
        //开始一步一步往前走，每pop出一个节点，判断是否是终点，是的话终止，否则继续判断是否有路可以走，有就加入队列
        while (!nodeQueue.isEmpty()) {
            MapNode pop = nodeQueue.pop();
            if (pop.x == map.length - 1 && pop.y == map[0].length - 1) {
                break;
            }
            //判断是否可以向前走，往队列加节点
            for (int i = 0; i < 4; i++) {
                int dirX = pop.x + dir[i][0];
                int dirY = pop.y + dir[i][1];
                if (0 <= dirX && dirX < map.length && 0 <= dirY && dirY < map[0].length && map[dirX][dirY] == 0) {
                    map[dirX][dirY] = map[pop.x][pop.y]+1;
                    nodeQueue.add(new MapNode(dirX, dirY));
                }
            }
        }
        MapNode lastNode = new MapNode(map.length - 1, map[0].length - 1);
        //用队列往回走最后用栈保存路径
        LinkedList<MapNode> backQueue = new LinkedList<>();
        backQueue.add(lastNode);
        //利用栈输出路径，从终点往回退
        Stack<MapNode> nodeStack = new Stack<>();
        while (!backQueue.isEmpty()) {
            MapNode pop = backQueue.pop();
            nodeStack.add(pop);
            if (pop.x == 0 && pop.y == 0) {
                break;
            }
            int backNum = map[pop.x][pop.y];
            //判断前后左右是否有比当前backNum小于1的，有就加入队列
            for (int i = 0; i < 4; i++) {
                int dirX = pop.x + dir[i][0];
                int dirY = pop.y + dir[i][1];
                if (0 <= dirX && dirX < map.length && 0 <= dirY && dirY < map[0].length && map[dirX][dirY] == backNum - 1) {
                    backQueue.add(new MapNode(dirX, dirY));
                    //跳出循环
                    break;
                }
            }
        }
        while (!nodeStack.isEmpty()) {
            MapNode pop = nodeStack.pop();
            System.out.println("(" + pop.x + "," + pop.y + ")");
        }
    }


}
//地图坐标
class MapNode{
    public int x;
    public int y;
    public MapNode(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
