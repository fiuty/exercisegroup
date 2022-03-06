package tianchi;

import java.util.Arrays;

/**
 * 130. 最小栈
 * @author zhengdayue
 */
public class MinStack {

    int min;

    Node node;

    int count;

    public MinStack() {
    }

    public void push(int val) {
        Node current = new Node();
        current.val = val;
        current.next = node;
        node = current;
        if (count == 0) {
            min = val;
        }
        if (val < min) {
            min = val;
        }
        ++count;
    }

    public void pop() {
        node = node.next;
        --count;
        if (count != 0) {
            int[] array = new int[count];
            int index = 0;
            Node tempNode = node;
            while (tempNode != null) {
                array[index++] = tempNode.val;
                tempNode = tempNode.next;
            }
            Arrays.sort(array);
            min = array[0];
        }
    }

    public int top() {
        return node.val;
    }

    public int getMin() {
        return min;
    }
}

class Node {
    int val;

    Node next;

    Node() {

    }

    Node(int val) {

        this.val = val;
    }

    Node(int val, Node next) {

        this.val = val;
        this.next = next;
    }
}
