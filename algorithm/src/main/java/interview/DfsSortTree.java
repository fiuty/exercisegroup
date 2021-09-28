package interview;

import datastructure.tree.TreeNode;

import java.util.Stack;

/**
 * 华为一面笔试题10-20分钟手写
 * 非递归实现排序二叉树的前序遍历
 * 利用栈的后进先出特点，先打印父节点，然后添加右子树节点和左子树节点，这样栈下一轮循环先pop出来的就是左节点
 * 达到前序遍历目的，先父节点，再左节点，再右节点
 * 10-8-7-9-15-14-20
 * @author zhengdayue
 */
public class DfsSortTree {

    public static void main(String[] args) {

        //10
        //8 15
        //7 9  14 20
        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(9);
        TreeNode node6 = new TreeNode(14);
        TreeNode node7 = new TreeNode(20);
        node2.leftChild = node4;
        node2.rightChild = node5;
        node3.leftChild = node6;
        node3.rightChild = node7;
        node1.leftChild = node2;
        node1.rightChild = node3;
        pre(node1);
    }

    public static void printValue(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.print(treeNode.num + " ");
        }
    }

    //非递归实现排序二叉树的前序遍历
    public static void pre(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            printValue(pop);
            if (pop.rightChild != null) {
                stack.push(pop.rightChild);
            }
            if (pop.leftChild != null) {
                stack.push(pop.leftChild);
            }
        }
    }
}
