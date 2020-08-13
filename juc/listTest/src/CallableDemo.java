import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("哈哈哈哈哈哈");
        TimeUnit.SECONDS.sleep(4);
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();  //同用一个futureTask，再次访问会直接返回缓存内容

        System.out.println(Thread.currentThread().getName()+"****计算完成lalalalaldddd");
        System.out.println(futureTask.get());

    }
}

