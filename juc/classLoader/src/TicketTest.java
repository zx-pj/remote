import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket {

    private int num = 300;

    private Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + '\t' + "卖出第" + (num--) + "张票");
            }
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
}


public class TicketTest {

    public static void main(String[] args) {

        Ticket ticket = new Ticket();

        new Thread(()->{for (int i = 0; i < 400; i++) ticket.sale();},"A").start();
        new Thread(()->{for (int i = 0; i < 400; i++) ticket.sale();},"B").start();
        new Thread(()->{for (int i = 0; i < 400; i++) ticket.sale();},"C").start();

    }
}


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 400; i++) {
//                    ticket.sale();
//                }
//            }
//        }, "A").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 400; i++) {
//                    ticket.sale();
//                }
//            }
//        }, "B").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 400; i++) {
//                    ticket.sale();
//                }
//            }
//        }, "C").start();

