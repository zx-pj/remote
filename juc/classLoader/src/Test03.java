import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Num2 {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void abs() {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + (--number));
            condition.signalAll();

        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void add() {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + (++number));
            condition.signalAll();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

}

public class Test03 {
    public static void main(String[] args) {
        Num2 num = new Num2();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                num.add();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                num.add();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                num.abs();
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                num.abs();
            }
        }, "D").start();
    }
}

