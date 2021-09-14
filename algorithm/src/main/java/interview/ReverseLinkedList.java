package interview;

import datastructure.linkedlist.ListNode;

/**
 * 手写反转链表
 * @author zhengdayue
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        two.next = three;
        one.next = two;
        ListNode reverse = reverse(one);
        while (reverse != null) {
            System.out.println(reverse.val);
            reverse = reverse.next;
        }
    }

    public static ListNode reverse(ListNode listNode) {
        ListNode head = null;
        ListNode current = listNode;
        while (current != null) {
            //一定不要让链表断了，把下一个链表先拿出来，然后当前节点要怎么处理都行。
            ListNode next = current.next;
            current.next = head;
            head = current;
            current = next;
        }
        return head;
    }
}
