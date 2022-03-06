package tianchi;

import java.util.HashMap;

/**
 * 70. 删除排序链表中的重复元素 II
 * @author zhengdayue
 */
public class Main_70 {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode root = new ListNode();
        ListNode current = root;
        // key为节点值，value为节点次数
        HashMap<Integer, Integer> map = new HashMap<>(8);
        ListNode temp = head;
        while (temp != null) {
            map.merge(temp.val, 1, Integer::sum);
            temp = temp.next;
        }
        while (head != null) {
            Integer value = map.get(head.val);
            if (value == 1) {
                current.next = new ListNode(head.val);
                current = current.next;
            }
            head = head.next;
        }
        return root.next;
    }
}
