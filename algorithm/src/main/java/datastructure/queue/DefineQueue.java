package datastructure.queue;

/**
 * 自定义链表实现队列功能，先进先出
 * @author zhengdayue
 */
public class DefineQueue <T> {

    private Node<T> head;
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
