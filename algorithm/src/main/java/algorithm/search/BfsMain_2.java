package algorithm.search;

import java.util.*;

/**
 * 参考BfsMain题目,进阶走迷宫2,自定义迷宫大小,起点左上角,终点右下角
 * 例如
 * 输入
 * 5 5
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
 * @author zhengdayue
 */
public class BfsMain_2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        //地图
        int[][] array = new int[x][y];
        //0代表未访问过
        int[][] copy = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                array[i][j] = scanner.nextInt();
            }
        }
        LinkedList<Node> queues = new LinkedList<>();
        queues.add(new Node(0, 0));
        int step = 0;
        //队列不为空,继续进行下一轮
        while (!queues.isEmpty()) {
            int currentSize = queues.size();
            ++step;
            boolean flag = false;
            for (int i = 0; i < currentSize; i++) {
                Node node = queues.pop();
                //赋值步数
                copy[node.x][node.y] = step;
                //终点,右下角
                if (node.x == array.length - 1 && node.y == array[0].length - 1) {
                    flag = true;
                    break;
                }
                List<Node> aroundNode = getAroundNode(node, array, copy);
                queues.addAll(aroundNode);
            }
            if (flag) {
                break;
            }
        }
        //再遍历copy从终点往回走,用栈保存,最后遍历栈打印路径
        Stack<Node> stackNodes = new Stack<>();
        LinkedList<Node> backNodes = new LinkedList<>();
        backNodes.add(new Node(x - 1, y - 1));
        while (!backNodes.isEmpty()) {
            Node popNode = backNodes.pop();
            stackNodes.add(popNode);
            int currentX = popNode.x;
            int currentY = popNode.y;
            int currentStep = copy[currentX][currentY];
            //copy初始值均为0,或者初始化copy为-1即不需要该判断。
            if (currentX == 0 && currentY == 0) {
                break;
            }
            //防止多条路if else
            if ((currentX - 1) >= 0 && copy[currentX - 1][currentY] == currentStep - 1) {
                //上
                backNodes.add(new Node(currentX - 1, currentY));
            } else if ((currentX + 1) < x && copy[currentX + 1][currentY] == currentStep - 1) {
                //下
                backNodes.add(new Node(currentX + 1, currentY));
            } else if ((currentY - 1) >= 0 && copy[currentX][currentY - 1] == currentStep - 1) {
                //左
                backNodes.add(new Node(currentX, currentY - 1));
            } else if ((currentY + 1) < y && copy[currentX][currentY + 1] == currentStep - 1) {
                //右
                backNodes.add(new Node(currentX, currentY + 1));
            }
        }
        while (!stackNodes.isEmpty()) {
            Node pop = stackNodes.pop();
            System.out.printf("(%s, %s)", pop.x, pop.y);
            System.out.println();
        }
    }

    //获取节点周边 可访问并且未访问过 的节点
    public static List<Node> getAroundNode(Node node, int[][] array, int[][] copy) {
        int x = node.x;
        int y = node.y;
        List<Node> nodes = new ArrayList<>(4);
        //上
        if ((x - 1) >= 0 && array[x - 1][y] == 0 && copy[x - 1][y] == 0) {
            nodes.add(new Node(x - 1, y));
        }
        //下
        if ((x + 1) < array.length && array[x + 1][y] == 0 && copy[x + 1][y] == 0) {
            nodes.add(new Node(x + 1, y));
        }
        //左
        if ((y - 1) >= 0 && array[x][y - 1] == 0 && copy[x][y - 1] == 0) {
            nodes.add(new Node(x, y - 1));
        }
        //右
        if ((y + 1) < array[0].length && array[x][y + 1] == 0 && copy[x][y + 1] == 0) {
            nodes.add(new Node(x, y + 1));
        }
        return nodes;
    }
}