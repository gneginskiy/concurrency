package concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockSample {
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        var instance = new DeadlockSample();
        new Thread(instance::operation1).start();
        new Thread(instance::operation2).start();
    }

    private void operation2() {
        lock1.lock();
        sleep(100);
        lock2.lock();
        lock2.unlock();
        lock1.unlock();
    }


    private void operation1() {
        lock2.lock();
        sleep(100);
        lock1.lock();

        lock1.unlock();
        lock2.unlock();
    }

    private void sleep(int millis)  {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
