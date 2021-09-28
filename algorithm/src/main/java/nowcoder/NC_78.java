package nowcoder;

import datastructure.linkedlist.ListNode;

/**
 * NC78 反转链表
 *
 * 记得别让链表断了，所以要先把next取出来
 * @author zhengdayue
 */
public class NC_78 {

    public ListNode ReverseList(ListNode head) {
        ListNode reHead = new ListNode(0);
        while (head != null) {
            ListNode next = head.next;
            head.next = reHead.next;
            reHead.next = head;
            head = next;
        }
        return reHead.next;
    }
}
