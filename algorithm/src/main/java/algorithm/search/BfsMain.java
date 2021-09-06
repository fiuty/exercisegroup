package algorithm.search;

import java.util.*;

/**
 * 广度/宽度优先搜索算法(Breadth First Search)：思想是从一个顶点开始，辐射状地优先遍历其周围较广的区域，连通图的一种遍历策略
 * 最典型的例子是走迷宫，从起始点开始，找出到终点的最短路径，很多最短路径的算法是基于广度优先搜索思想成立的。
 * 代码基本流程是：明确起点和终点 --> 把起点加入待排查集合A -->
 * --> 从集合A取出节点，判断取出节点相邻节点是否满足条件，不满足把相邻节点加入集合进行下一轮处理直至集合为空/满足终点条件
 *
 * 广度优先搜索通常需要借助队列，先进先出，深度优先搜索通常需要借助栈，后进先出（其实递归也是栈的实现）。
 * @author zhengdayue
 */
public class BfsMain {

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
        Scanner scanner = new Scanner(System.in);
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
        Node firstNode = new Node(0, 0);
        nodes.add(firstNode);
        //记录步数
        int step = 0;
        //循环检查nodes是否为空
        while (!nodes.isEmpty()) {
            int curentSize = nodes.size();
            ++step;
            //找到终点,跳出while循环
            boolean flag = false;
            for (int i = 0; i < curentSize; i++) {
                Node node = nodes.pop();
                //找到终点
                if (node.x == 4 && node.y == 4) {
                    copy[4][4] = step;
                    flag = true;
                    break;
                }
                copy[node.x][node.y] = step;
                List<Node> aroundNode = getAroundNode(node, array, copy);
                nodes.addAll(aroundNode);
            }
            if (flag) {
                break;
            }
        }
        //再遍历copy从终点往回走,用栈保存,最后遍历栈打印路径
        Stack<Node> stackNodes = new Stack<>();
        LinkedList<Node> backNodes = new LinkedList<>();
        Node node = new Node(4, 4);
        backNodes.add(node);
        while (!backNodes.isEmpty()) {
            Node popNode = backNodes.pop();
            stackNodes.add(popNode);
            int x = popNode.x;
            int y = popNode.y;
            int currentStep = copy[x][y];
            //copy初始值均为0,或者初始化copy为-1即不需要该判断。
            if (x == 0 && y == 0) {
                break;
            }
            //防止多条路径,满足一条退出
            if ((x - 1) >= 0 && copy[x - 1][y] == currentStep - 1) {
                //上
                backNodes.add(new Node(x - 1, y));
            } else if ((x + 1) < 5 && copy[x + 1][y] == currentStep - 1) {
                //下
                backNodes.add(new Node(x + 1, y));
            } else if ((y - 1) >= 0 && copy[x][y - 1] == currentStep - 1) {
                //左
                backNodes.add(new Node(x, y - 1));
            } else if ((y + 1) < 5 && copy[x][y + 1] == currentStep - 1) {
                //右
                backNodes.add(new Node(x, y + 1));
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
        List<Node> nodes = new ArrayList<>(4);
        int x = node.x;
        int y = node.y;
        //上
        if ((x - 1) >= 0 && array[x - 1][y] == 0 && copy[x - 1][y] == 0) {
            Node addNode = new Node(x - 1, y);
            nodes.add(addNode);
        }
        //下
        if ((x + 1) < 5 && array[x + 1][y] == 0 && copy[x + 1][y] == 0) {
            Node addNode = new Node(x + 1, y);
            nodes.add(addNode);
        }
        //左
        if ((y - 1) >= 0 && array[x][y - 1] == 0 && copy[x][y - 1] == 0) {
            Node addNode = new Node(x, y - 1);
            nodes.add(addNode);
        }
        //右
        if ((y + 1) < 5 && array[x][y + 1] == 0 && copy[x][y + 1] == 0) {
            Node addNode = new Node(x, y + 1);
            nodes.add(addNode);
        }
        return nodes;
    }
}

