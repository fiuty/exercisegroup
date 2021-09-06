package algorithm.search;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * 优化BfsMain走迷宫题，精简代码，上下左右用数据存着，然后循环遍历该方向数组。（最终代码也没精简多少，可读性变差了。）
 * @author zhengdayue
 */
public class BfsMainBetter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //方向 上下左右 精简代码
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        //地图
        int[][] array = new int[5][5];
        //0代表未访问过
        int[][] copy = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                array[i][j] = scanner.nextInt();
            }
        }
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(new Node(0, 0));
        int step = 0;
        boolean flag = false;
        //不为空
        while (!nodes.isEmpty()) {
            ++step;
            int currentSize = nodes.size();
            for (int i = 0; i < currentSize; i++) {
                Node pop = nodes.pop();
                copy[pop.x][pop.y] = step;
                if (pop.x == 4 && pop.y == 4) {
                    flag = true;
                    break;
                }
                //优化点，其实也差不多，代码没精简多少，代码可读性反而没那么好
                for (int j = 0; j < 4; j++) {
                    int nextX = pop.x + dir[j][0];
                    int nextY = pop.y + dir[j][1];
                    //上下左右节点是否满足条件，可以加入下一节点访问
                    boolean isNext = nextX >= 0 && nextX < 5 && nextY >= 0 && nextY < 5 && array[nextX][nextY] == 0 && copy[nextX][nextY] == 0;
                    if (isNext) {
                        nodes.add(new Node(nextX, nextY));
                    }
                }
            }
            if (flag) {
                break;
            }
        }
        LinkedList<Node> backNodes = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        backNodes.add(new Node(4, 4));
        while (!backNodes.isEmpty()) {
            Node pop = backNodes.pop();
            stack.add(pop);
            if (pop.x == 0 && pop.y == 0) {
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = pop.x + dir[i][0];
                int nextY = pop.y + dir[i][1];
                boolean isNext = nextX >= 0 && nextX < 5 && nextY >= 0 && nextY < 5 && copy[nextX][nextY] == copy[pop.x][pop.y] - 1;
                if (isNext) {
                    backNodes.add(new Node(nextX, nextY));
                    break;
                }
            }
        }
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            System.out.printf("(%s, %s)", pop.x, pop.y);
            System.out.println();
        }
    }
}
