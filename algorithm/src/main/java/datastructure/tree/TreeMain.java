package datastructure.tree;

/**
 * 排序二叉树的遍历：前、中、后序遍历是以父节点为基础，前序遍历就是先输出父节点，中序遍历就是父节点在中间输出，后序遍历就是在最后才输出
 * 前序遍历：10 --> 8 --> 7 --> 9 --> 15 --> 14 --> 16
 * 中序遍历：7 --> 8 --> 9 --> 10 --> 14 --> 15 --> 16
 * 后序遍历：7 --> 9 --> 8 --> 14 --> 16 --> 15 --> 10
 *
 * 排序二叉树的节点插入：寻找合适的空的节点，插入即可
 *
 * 排序二叉树的A节点删除：三种情况：
 * 1.删除节点无子节点（直接删除）
 * 2.删除节点只有一个子节点（子节点替换要删除的A节点）
 * 3.删除节点是父节点（寻找A节点右子树最小节点，因为该节点大于A节点左子树的所有节点，小于A节点右子树的所有节点，最后替换掉A节点即可）
 *
 * @author zhengdayue
 */
public class TreeMain {

    /**
     * * * * * * 10 * * * * * *
     * * * * * * * * * * * * *
     * * * * 8 * * * 15 * * * *
     * * * * * * * * * * * * *
     * * 7 * * 9 14 * * * 16 * *
     */
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(9);
        TreeNode node6 = new TreeNode(14);
        TreeNode node7 = new TreeNode(16);
        node2.leftChild = node4;
        node2.rightChild = node5;
        node3.leftChild = node6;
        node3.rightChild = node7;
        node1.leftChild = node2;
        node1.rightChild = node3;
        System.out.print("前序遍历：");
        pre(node1);
        System.out.println();
        System.out.print("中序遍历：");
        in(node1);
        System.out.println();
        System.out.print("后序遍历：");
        post(node1);
        System.out.println();

        //插入节点17后进行前序遍历
        insert(node1, 17);
        System.out.print("插入节点17后进行前序遍历：");
        pre(node1);
        System.out.println();


    }

    //输出当前节点值
    private static void visitNode(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.print(" " + treeNode.num);
        }
    }

    //前序遍历
    public static void pre(TreeNode treeNode) {
        if (treeNode != null) {
            visitNode(treeNode);
            pre(treeNode.leftChild);
            pre(treeNode.rightChild);
        }
    }

    //中序遍历
    public static void in(TreeNode treeNode) {
        if (treeNode != null) {
            in(treeNode.leftChild);
            visitNode(treeNode);
            in(treeNode.rightChild);
        }
    }

    //后序遍历
    public static void post(TreeNode treeNode) {
        if (treeNode != null) {
            post(treeNode.leftChild);
            post(treeNode.rightChild);
            visitNode(treeNode);
        }
    }

    //插入节点,假设输入节点值不重复
    public static TreeNode insert(TreeNode treeNode, int num) {
        TreeNode node = new TreeNode(num);
        if (treeNode == null) {
            return node;
        }
        TreeNode current = treeNode;
        while (true) {
            //左子树
            if (current.num > num) {
                if (current.leftChild == null) {
                    current.leftChild = node;
                    break;
                }
                current = current.leftChild;
            } else {
                //右子树
                if (current.rightChild == null) {
                    current.rightChild = node;
                    break;
                }
                current = current.rightChild;
            }
        }
        return treeNode;
    }
}
