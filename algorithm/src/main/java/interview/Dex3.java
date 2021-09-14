package interview;


import datastructure.linkedlist.ListNode;

/**
 * 画出单向链表，并且遍历、插入、删除
 *
 * 假设链表默认为 1-->2-->3-->4
 * 遍历输出：1、2、3、4
 * 插入5最后输出：1、2、3、4、5
 * 删除头节点1，最后输出2、3、4、5
 * @author zhengdayue
 */
public class Dex3 {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        //遍历链表
        printValue(listNode1);

        //尾部插入5，并遍历链表
        tailInsert(listNode4, 5);
        printValue(listNode1);

        //删除头节点，并遍历链表
        printValue(deleteHeadNode(listNode1));
    }

    //遍历链表并且打印值
    public static void printValue(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val+"、");
            listNode = listNode.next;
            //最后一个
            if (listNode.next == null) {
                System.out.println(listNode.val);
                break;
            }
        }
    }

    //插入节点，尾插法必须要有尾指针，故需要引入尾节点；如果是头插法就不用，但是头插法顺序会跟插入节点顺序相反
    public static void tailInsert(ListNode tail, int value) {
        ListNode insertNode = new ListNode(value);
        tail.next = insertNode;
    }

    //删除节点，这里选择删除头节点，头节点直接指向下一个节点即可
    //如果删除尾部节点，需要先查找尾部的前驱节点（也可以在每个节点加前驱节点指针，这样可以直接删除）
    public static ListNode deleteHeadNode(ListNode listNode) {
        listNode = listNode.next;
        return listNode;
    }
}
