package leetcode.bytedance;


import datastructure.linkedlist.ListNode;

/**
 * 两数相加，这道题有点坑，例子带进坑了，实际上例子也是一个指引，为什么要逆序颠倒数字过来相加然后输出呢？
 * 实际这道题这样也才方便解，比如例子342+465是正常思维的顺序两数相加，可以看到正常的数学逻辑我们是从逆序去相加的
 * 即从个位数2+5=7，然后是4+6=10进位1，最后是3+4+1=8，最后要的结果就是7-0-8，可以看到输入是逆序也满足我们相加的要求，
 * 即第一个点2+5=7，然后4+6进位1，最后是3+4+1=8，即可。
 * @author zhengdayue
 */
public class Main_2 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(6);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(2);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode listNode = addTwoNumbers(l1, l2);
        while (listNode != null) {
            System.out.print(listNode.val);
            listNode = listNode.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

}
