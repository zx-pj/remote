import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public  static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+'\t'+"离开教室");
                countDownLatch.countDown();//每次计数减1，从6到0
            },String.valueOf(i)).start();
        }
        //要求：上述6个线程全部执行结束后，再执行下面的语句
        try {
            countDownLatch.await();//必须等到countDownLatch.countDown();从6到0，再唤醒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+'\t'+"班长离开教室");
    }

    public static void closeDoor() {
        for (int i = 1; i <= 6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+'\t'+"离开教室");
            },String.valueOf(i)).start();
        }
        //要求：上述6个线程全部执行结束后，再执行下面的语句
        System.out.println(Thread.currentThread().getName()+'\t'+"班长离开教室");
    }
}
