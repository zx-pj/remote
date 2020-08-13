import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 会出现各种结果
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        //用线程安全的Vector或者Collections.synchronizedList()或者CopyOnWriteArrayList

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }
}
