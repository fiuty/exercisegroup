package nowcoder;

import datastructure.tree.TreeNode;

import java.util.Arrays;

/**
 * NC45 实现二叉树先序，中序和后序遍历
 * @author zhengdayue
 */
public class NC_45 {

    public static StringBuilder s1;

    public static StringBuilder s2;

    public static StringBuilder s3;

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.leftChild = new TreeNode(2);
        treeNode.rightChild = new TreeNode(3);
        int[][] ints = threeOrders(treeNode);
        for (int i = 0; i < 3; i++) {
            System.out.println(Arrays.toString(ints[i]));
        }
    }

    public static int[][] threeOrders (TreeNode root) {
        if (root == null) {
            return new int [3][0];
        }
        s1 = new StringBuilder();
        s2 = new StringBuilder();
        s3 = new StringBuilder();
        pre(root);
        mid(root);
        post(root);
        String[] num1 = s1.toString().split(" ");
        int[][] re = new int[3][num1.length];
        for (int i = 0; i < num1.length; i++) {
            re[0][i] = Integer.parseInt(num1[i]);
        }
        String[] num2 = s2.toString().split(" ");
        for (int i = 0; i < num2.length; i++) {
            re[1][i] = Integer.parseInt(num2[i]);
        }
        String[] num3 = s3.toString().split(" ");
        for (int i = 0; i < num3.length; i++) {
            re[2][i] = Integer.parseInt(num3[i]);
        }
        return re;
    }


    //先序遍历
    public static void pre(TreeNode treeNode) {
        if (treeNode != null) {
            s1.append(treeNode.num).append(" ");
            pre(treeNode.leftChild);
            pre(treeNode.rightChild);
        }
    }

    //中序遍历
    public static void mid(TreeNode treeNode) {
        if (treeNode != null) {
            mid(treeNode.leftChild);
            s2.append(treeNode.num).append(" ");
            mid(treeNode.rightChild);
        }
    }

    //后序遍历
    public static void post(TreeNode treeNode) {
        if (treeNode != null) {
            post(treeNode.leftChild);
            post(treeNode.rightChild);
            s3.append(treeNode.num).append(" ");
        }
    }

}
