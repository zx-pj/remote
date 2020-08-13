import java.util.concurrent.*;

public class MyThreadPoolDemo {

    public static void main(String[] args) {

        //cpu核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService threadPool =  new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        try {
            for (int i = 1; i <= 15 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }



    }



}
