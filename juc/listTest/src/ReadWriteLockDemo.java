import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) throws InterruptedException {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t ---写入数据" + key);
            //暂停线程
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t ---写入完成");
        } catch (Exception e) {

        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public void get(String key) throws InterruptedException {
        readWriteLock.readLock().lock();
        try {
        System.out.println(Thread.currentThread().getName() + "\t ---读取数据");
        //暂停线程
        TimeUnit.MILLISECONDS.sleep(300);
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName() + "\t ---读取完成" + result);
        }catch (Exception e){

        }finally {
            readWriteLock.readLock().unlock();
        }

    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                try {
                    myCache.put(tempInt + "", tempInt + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                try {
                    myCache.get(tempInt + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}