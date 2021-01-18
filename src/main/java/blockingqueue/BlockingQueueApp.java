package blockingqueue;

public class BlockingQueueApp {
    public static void main(String[] args) throws InterruptedException {
        CustomBlockingQueue<Integer> queue = new CustomBlockingQueue<>(4);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10_000_1; i++) {
                queue.enqueue(i);
            }
            System.out.println(queue);
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10_000_0; i++) {
                queue.dequeue();
            }
            System.out.println(queue);
        });
        thread2.start();

        thread2.join();
        thread1.join();
    }
}
