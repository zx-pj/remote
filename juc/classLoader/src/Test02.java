
class Num {
    private int number = 0;

    public synchronized void abs() {
        while (number == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + (--number));
        this.notifyAll();
    }

    public synchronized void add() {
        while (number != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + (++number));
        this.notifyAll();
    }
}

public class Test02 {

    public static void main(String[] args) {
        Num num = new Num();
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
