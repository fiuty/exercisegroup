package datastructure.queue;

/**
 * 自定义链表实现队列功能，先进先出
 * 队列需要头指针和当前指针（尾指针），头部指针指向当前队列的头，当前指针指向当前队列的尾巴，
 * 头部指针方便pop出队列，当前指针方便put入队列。
 * head -->  -->    -->     --> current
 * [] --> [] --> [] --> [] --> []
 * @author zhengdayue
 */
public class DefineQueue <T> {

    private Node<T> head;
    //或者描述为tail尾指针
    private Node<T> current;

    public void put(T value) {
        if (head == null) {
            head = new Node<>(value);
            current = head;
            return;
        }
        Node<T> currentNode = new Node<>(value);
        current.next = currentNode;
        current = currentNode;
    }

    public T pop() {
        if (head == null) {
            return null;
        }
        T value = head.value;
        head = head.next;
        return value;
    }
}
