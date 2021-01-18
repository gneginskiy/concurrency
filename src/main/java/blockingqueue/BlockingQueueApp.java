package blockingqueue;

public class BlockingQueueApp {
    public static void main(String[] args) throws InterruptedException {
        CustomBlockingQueue<Integer> queue = new CustomBlockingQueue<>(4);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 20_000; i++) {
                queue.enqueue(i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                queue.dequeue();
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                queue.dequeue();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(queue);

    }
}
