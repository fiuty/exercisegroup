package datastructure.queue;

/**
 * @author zhengdayue
 */
public class Main {

    public static void main(String[] args) {
        DefineQueue<Integer> queue = new DefineQueue<>();
        queue.put(1);
        queue.put(2);
        queue.put(3);

        System.out.println(queue.pop());
        System.out.println(queue.pop());
        queue.put(4);
        System.out.println(queue.pop());
    }
}
