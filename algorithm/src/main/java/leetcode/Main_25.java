package leetcode;


import datastructure.linkedlist.ListNode;

/**
 * @author zhengdayue
 */
public class Main_25 {

    public static void main(String[] args) {

    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode hari = new ListNode();
        hari.next = head;
        ListNode pre = hari;
        while (head != null) {
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return hari.next;
                }
            }
            ListNode next = tail.next;
            ListNode[] revers = revers(head, tail);
            head = revers[0];
            tail = revers[1];
            pre.next = head;
            tail.next = next;
            pre = tail;
            head = tail.next;
        }
        return hari.next;
    }

    //反转，记得最后连接下一个k组的头节点，需要在起始节点做好连接
    public static ListNode[] revers(ListNode head, ListNode tail) {
        ListNode pre = tail.next;
        ListNode current = head;
        while (pre != tail) {
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return new ListNode[]{tail, head};
    }
}
