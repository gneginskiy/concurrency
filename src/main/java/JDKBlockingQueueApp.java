import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class JDKBlockingQueueApp {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 20_000; i++) {
                queue.offer(i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                queue.poll();
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                queue.poll();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(start - System.currentTimeMillis());
        System.out.println(queue);

    }
}
