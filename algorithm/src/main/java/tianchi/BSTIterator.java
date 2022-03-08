package tianchi;

/**
 * 139. 二叉搜索树迭代器
 * @author zhengdayue
 */
class BSTIterator {

    private Node root = new Node();

    private Node current = root;

    public BSTIterator(TreeNode root) {
        in(root);
    }

    public int next() {
        int val = root.next.val;
        root = root.next;
        return val;
    }

    public boolean hasNext() {
        return root.next != null;
    }

    // 中序遍历
    private void in(TreeNode node) {
        if (node != null) {
            in(node.left);
            current.next = new Node(node.val);
            current = current.next;
            in(node.right);
        }
    }
}
