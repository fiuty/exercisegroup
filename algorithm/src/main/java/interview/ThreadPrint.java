package interview;

/**
 * 请用java写出两个线程交替打印奇偶数
 * 注意while放在加互斥锁的上边，通过创建两个线程，这两个线程共享object对象锁，当一个线程打印完一个数字后，会释放对象锁，
 * 另一个线程拿到对象锁，然后判断是否为偶数（奇数），满足条件则打印。
 * @author zhengdayue
 */
public class ThreadPrint {

    private static int count = 0;

    private static final Object object = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            while (count < 10) {
                synchronized (object){
                    if (count % 2 == 0) {
                        System.out.printf("偶数：%s\n", count++);
                    }
                }
            }
        }).start();

        new Thread(()->{
            while (count < 10) {
                synchronized (object){
                    if (count % 2 == 1) {
                        System.out.printf("奇数：%s\n", count++);
                    }
                }
            }
        }).start();
    }
}
