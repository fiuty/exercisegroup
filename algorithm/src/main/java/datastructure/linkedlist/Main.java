package datastructure.linkedlist;

/**
 * @author zhengdayue
 */
public class Main {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        ListNode reverseList = reverse(listNode);
        System.out.print(reverseList.val);
        while (reverseList.next != null) {
            reverseList = reverseList.next;
            System.out.print(reverseList.val);
        }
    }

    public static ListNode reverse(ListNode node) {
        ListNode head = new ListNode();
        //遍历链表
        while (node != null) {
            ListNode currentNode = new ListNode(node.val);
            currentNode.next = head.next;
            head.next = currentNode;
            node = node.next;
        }
        return head.next;
    }
}
