package interview;

import datastructure.tree.TreeNode;

import java.util.LinkedList;

/**
 * 10-20分钟手写
 * 对二叉排序树做广度优先搜索
 * @author zhengdayue
 */
public class BfsSortTree {
    public static void main(String[] args) {

        //10
        //8 15
        //7 9  14 20
        //3 4
        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(9);
        TreeNode node6 = new TreeNode(14);
        TreeNode node7 = new TreeNode(20);
        TreeNode node8 = new TreeNode(3);
        TreeNode node9 = new TreeNode(4);
        node2.leftChild = node4;
        node2.rightChild = node5;
        node3.leftChild = node6;
        node3.rightChild = node7;
        node1.leftChild = node2;
        node1.rightChild = node3;
        node4.leftChild = node8;
        node4.rightChild = node9;

        //队列，先进先出
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        //先添加父节点
        treeNodes.add(node1);
        //当队列不为空，持续pop出来
        while (!treeNodes.isEmpty()) {
            //pop出当前节点，打印，当当前节点的左节点和右节点存在时，继续add到队列中去，做下一轮打印
            TreeNode pop = treeNodes.pop();
            System.out.print(pop.num +" ");
            if (pop.leftChild != null) {
                treeNodes.add(pop.leftChild);
            }
            if (pop.rightChild != null) {
                treeNodes.add(pop.rightChild);
            }
        }
    }
}
