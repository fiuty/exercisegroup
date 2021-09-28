package leetcode;


import datastructure.linkedlist.ListNode;

/**
 * @author zhengdayue
 */
public class Main_25 {

    public static void main(String[] args) {

    }

    //	#25 K 个一组翻转链表
    //先判断是否满足k个节点，满足的话，对其进行反转，不满足的话接上原有节点然后退出。
    public static ListNode reverseKGroup2(ListNode head, int k) {
        ListNode reverseHead = new ListNode();
        ListNode currentHead = reverseHead;
        boolean flag = false;
        boolean noFull = false;
        while (true) {
            //指向反转头节点
            ListNode currentNodeHead = new ListNode();
            //指向反转结尾节点
            ListNode currentNodeTail = new ListNode();
            //判断是否满足k个节点
            ListNode temp = new ListNode();
            temp.next = head;
            for(int i=0;i<k;i++){
                temp = temp.next;
                if (temp == null) {
                    noFull = true;
                    currentHead.next = head;
                    break;
                }
            }
            if (noFull) {
                break;
            }
            for (int i = 0; i < k; i++) {
                if (i == 0) {
                    //保留反转结尾节点
                    currentNodeTail.next = head;
                }
                ListNode next = head.next;
                head.next = currentNodeHead.next;
                currentNodeHead.next = head;
                head = next;
                if (head == null) {
                    flag = true;
                    break;
                }
            }
            currentHead.next = currentNodeHead.next;
            currentHead = currentNodeTail.next;
            if (flag) {
                break;
            }
        }
        return reverseHead.next;
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
