package datastructure.stack;


/**
 * 自定义链表方式实现栈功能，后进先出
 * @author zhengdayue
 */
public class DefineStack <T>{

    private Node<T> head;

    public void put(T num) {
        if (head == null) {
            head = new Node<>(num);
            return;
        }
        Node<T> currentNode = new Node<>(num);
        currentNode.next = head;
        head = currentNode;
    }

    public T pop(){
        if (head == null) {
            return null;
        }
        T value = head.value;
        head = head.next;
        return value;
    }
}
