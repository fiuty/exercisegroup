package leetcode;


import datastructure.linkedlist.ListNode;

/**
 * @author zhengdayue
 */
public class Main_206 {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        ListNode reverseList = reverseList(listNode);
        System.out.print(reverseList.val);
        while (reverseList.next != null) {
            reverseList = reverseList.next;
            System.out.print(reverseList.val);
        }
    }

    //遍历一次listNode，要有一个节点保留上个节点，然后当前节点指向上个节点。
    public static ListNode reverseListMain(ListNode head) {
        ListNode pre = null;
        ListNode current = head;
        //遍历一次，每次保留上一个节点，下个节点指向上一个节点
        while (current != null) {
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }
    //最笨方法，遍历3次listNode
    public static ListNode reverseList(ListNode head) {
        int count = 1;
        ListNode temp = head;
        while (temp.next != null) {
            ++count;
            temp = temp.next;
        }
        int[] nums = new int[count];
        nums[0] = head.val;
        for (int i = 1; i < count; i++) {
            head = head.next;
            nums[i] = head.val;
        }
        ListNode listNode = new ListNode();
        ListNode tempNode = listNode;
        for (int i = count - 1; i >= 0; i--) {
            tempNode.next = new ListNode(nums[i]);
            tempNode = tempNode.next;
        }
        return listNode.next;
    }
}
