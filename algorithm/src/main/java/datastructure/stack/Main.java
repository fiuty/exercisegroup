package datastructure.stack;

/**
 * @author zhengdayue
 */
public class Main {

    public static void main(String[] args) {
        DefineStack<Integer> stack = new DefineStack<>();
        stack.put(1);
        stack.put(2);
        stack.put(3);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.put(4);
        System.out.println(stack.pop());
    }
}
