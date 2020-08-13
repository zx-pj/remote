import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //到7后，执行方法
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("*******召唤神龙");
        });

        for (int i = 1; i <=7 ; i++) {
            final int tempInt = i;
            //lambda表达式中的变量需要用final修饰的
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+'\t'+"收集到第"+'\t'+ tempInt +"颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
