package tianchi;

/**
 * 20. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * @author zhengdayue
 */
public class Main_20 {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode root = new ListNode();
        ListNode current = root;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                current.next = list2;
                list2 = list2.next;
            } else {
                current.next = list1;
                list1 = list1.next;
            }
            current = current.next;
        }
        while (list1 != null) {
            current.next = list1;
            current = current.next;
            list1 = list1.next;
        }
        while (list2 != null) {
            current.next = list2;
            current = current.next;
            list2 = list2.next;
        }
        return root.next;
    }
}
