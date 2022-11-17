package concurrency.blockingqueue;

public class BlockingQueueApp {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        BlockingQueue<Integer> queue = new CustomBlockingQueue<>(100);


        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                queue.dequeue();
            }
        });

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 20_000; i++) {
                queue.enqueue(i);
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

        System.out.println(System.currentTimeMillis() - start);
        System.out.println(queue);

    }

    private static void waitForMillis(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
