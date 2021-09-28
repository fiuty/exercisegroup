package interview.experience;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用三个线程输出ABC顺序10次
 * @author zhengdayue
 */
public class ThreadPrint_2 {

    private static ReentrantLock lock = new ReentrantLock();

    private static Condition cA = lock.newCondition();

    private static Condition cB = lock.newCondition();

    private static Condition cC = lock.newCondition();

    private static CountDownLatch latchB = new CountDownLatch(1);

    private static CountDownLatch latchC = new CountDownLatch(1);

    public static void main(String[] args) {

        new Thread(()->{
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print("A");
                    //唤醒B线程
                    cB.signal();
                    if (i == 0) {
                        latchB.countDown();
                    }
                    cA.await();
                }
                cB.signal();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }finally {
                lock.unlock();
            }
        }).start();

        new Thread(()->{
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print("B");
                    cC.signal();
                    if (i == 0) {
                        latchC.countDown();
                    }
                    cB.await();
                }
                cC.signal();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }finally {
                lock.unlock();
            }
        }).start();


        new Thread(()->{
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("C");
                    cA.signal();
                    cC.await();
                }
                cC.signal();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }finally {
                lock.unlock();
            }
        }).start();
    }
}
