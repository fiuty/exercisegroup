package interview;

import datastructure.tree.TreeNode;

/**
 * 画出排序二叉树，遍历二叉树，插入一个节点，删除一个节点
 * 假设排序二叉树
 *            100
 *        90       120
 *     80    95  105   130
 * @author zhengdayue
 */
public class Dex2 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(100);
        TreeNode node2 = new TreeNode(90);
        TreeNode node3 = new TreeNode(120);
        TreeNode node4 = new TreeNode(80);
        TreeNode node5 = new TreeNode(95);
        TreeNode node6 = new TreeNode(105);
        TreeNode node7 = new TreeNode(130);
        node1.leftChild = node2;
        node1.rightChild = node3;
        node2.leftChild = node4;
        node2.rightChild = node5;
        node3.leftChild = node6;
        node3.rightChild = node7;

        //遍历排序二叉树
        System.out.print("先序遍历 ");
        pre(node1);
        System.out.println();
        System.out.print("中序遍历 ");
        in(node1);
        System.out.println();
        System.out.print("后序遍历 ");
        post(node1);
        System.out.println();

        System.out.print("插入110节点，然后先序遍历：");
        //插入节点110
        inserNode(node1, 110);
        pre(node1);
        System.out.println();

        System.out.print("删除110节点，然后先序遍历：");
        //删除节点110
        deleteNode(node1, 110);
        pre(node1);
        System.out.println();

        System.out.print("删除120节点，然后先序遍历：");
        deleteNode(node1,120);
        pre(node1);
        System.out.println();
    }

    public static void printValue(TreeNode treeNode) {
        System.out.print(treeNode.num + " ");
    }

    //先序遍历
    public static void pre(TreeNode treeNode) {
        if (treeNode != null) {
            printValue(treeNode);
            pre(treeNode.leftChild);
            pre(treeNode.rightChild);
        }
    }

    //中序遍历
    public static void in(TreeNode treeNode) {
        if (treeNode != null) {
            in(treeNode.leftChild);
            printValue(treeNode);
            in(treeNode.rightChild);
        }
    }

    //后序遍历
    public static void post(TreeNode treeNode) {
        if (treeNode != null) {
            post(treeNode.leftChild);
            post(treeNode.rightChild);
            printValue(treeNode);
        }
    }

    //插入节点value，不断遍历二叉树，小于当前节点，则往左子树找，大于当前节点，则往右子树找
    public static void inserNode(TreeNode treeNode, int value) {
        TreeNode node = new TreeNode(value);
        TreeNode current = treeNode;
        while (true) {
            if (current.num > value) {
                if (current.leftChild == null) {
                    current.leftChild = node;
                    break;
                }
                current = current.leftChild;
            } else {
                if (current.rightChild == null) {
                    current.rightChild = node;
                    break;
                }
                current = current.rightChild;
            }
        }
    }

    //删除节点value，删除有3种情况
    //1,删除节点为叶子节点，则直接删除
    //2,删除节点只有一个子节点（子节点替换要删除的A节点）
    //3,删除节点是父节点（寻找A节点右子树最小节点，因为该节点大于A节点左子树的所有节点，小于A节点右子树的所有节点，最后替换掉A节点即可）
    public static void deleteNode(TreeNode treeNode, int value) {
        TreeNode current = treeNode;
        //假设为待删除节点的父亲节点
        TreeNode rootNode = treeNode;
        //假设为待删除节点
        TreeNode deleteNode = null;
        //判断是否有存在删除节点
        boolean flag = false;
        while (current != null) {
            if (current.num == value) {
                flag = true;
                deleteNode = current;
                break;
            } else if (current.num < value) {
                rootNode = current;
                current = current.rightChild;
            } else {
                rootNode = current;
                current = current.leftChild;
            }
        }
        if (flag) {
            //第一种情况
            if (deleteNode.leftChild == null && deleteNode.rightChild == null) {
                //删除左节点
                if (rootNode.leftChild != null && rootNode.leftChild.num == deleteNode.num) {
                    rootNode.leftChild = null;
                } else {
                    //删除右节点
                    rootNode.rightChild = null;
                }
                //第三种情况
            } else if (deleteNode.leftChild != null && deleteNode.rightChild != null) {
                //找出右子树最小节点，即右子树的最左子树为最小节点
                TreeNode tempNode = deleteNode.rightChild;
                //最小节点的父节点，要替换掉删除节点，然后把它置为null
                TreeNode rootTempNode = deleteNode;
                while (tempNode.leftChild != null) {
                    rootTempNode = tempNode;
                    tempNode = tempNode.leftChild;
                    if (tempNode.leftChild == null) {
                        break;
                    }
                }
                deleteNode.num = tempNode.num;
                //当要替换的父节点的左孩子节点不等于要替换的节点，说明要替换的节点只是待删除节点的右孩子，直接替换即可，然后右孩子置为null
                if (rootTempNode.leftChild.num != tempNode.num) {
                    deleteNode.rightChild = null;
                } else {
                    //否则，要替换的节点的父节点的左孩子置为null
                    rootTempNode.leftChild = null;
                }
            } else {
                //第二种情况
                if (deleteNode.leftChild != null) {
                    deleteNode.num = deleteNode.leftChild.num;
                    deleteNode.leftChild = null;
                } else {
                    deleteNode.num = deleteNode.rightChild.num;
                    deleteNode.rightChild = null;
                }
            }
        }
    }
}
