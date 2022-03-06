package tianchi;

import java.util.HashSet;

/**
 * 131. 相交链表
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null
 * @author zhengdayue
 */
public class Main_131 {

    /**
     * 这题没看答案一直觉得挺难的，要O(m+n)的时间复杂度，还有O(1)内存，O(1)内存混淆了我的思路，没想到用hash来实现
     * 用了hash来实现的话就可以定位了。
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>(8);
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }
}
