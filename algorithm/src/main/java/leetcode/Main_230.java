package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树中第K小的元素
 * @author zhengdayue
 */
public class Main_230 {

    public static List<Integer> list;

    public int kthSmallest(TreeNode root, int k) {
        //leetcode的判断需要每次在方法里面重新声明，不然不会重置
        list = new ArrayList<>(8);
        in(root);
        return list.get(k-1);
    }

    public static void in(TreeNode treeNode) {
        if (treeNode != null) {
            in(treeNode.left);
            list.add(treeNode.val);
            in(treeNode.right);
        }
    }

}
