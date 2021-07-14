package leetcode;


import leetcode.bytedance.ListNode;

/**
 * @author zhengdayue
 */
public class Main_24 {

    public static void main(String[] args) {

    }

    //递归方式，对递归的看法：凡事逻辑上有规律的，最后可以终止的，往递归的想法上去靠。
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    //迭代的方式
    public static ListNode swapPairs2(ListNode head) {
        ListNode newHeadd = new ListNode();
        newHeadd.next = head;
        ListNode temp = newHeadd;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = temp.next.next;//也是temp = node1
        }
        return newHeadd.next;
    }

}
