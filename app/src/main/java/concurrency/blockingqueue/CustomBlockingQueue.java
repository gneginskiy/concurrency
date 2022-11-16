package concurrency.blockingqueue;

import java.util.Arrays;

public class CustomBlockingQueue<T> implements BlockingQueue<T> {
    volatile T[] array;
    volatile int size = 0;
    final int capacity;
    volatile int head = 0;
    volatile int tail = 0;

    @SuppressWarnings("unchecked")
    public CustomBlockingQueue(int capacity) {
        array = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    @Override
    public synchronized void enqueue(T item) {
        while (size == capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        array[tailPostIncrement(tail)] = item;
        size++;

        if (size == 1) {
            notifyAll();
        }
    }

    @Override
    public synchronized T dequeue() {
        while (size == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        int head = headPostIncrement(this.head);
        T item = array[head];
        array[head] = null;
        size--;

        if (size == capacity - 1) {
            notifyAll();
        }
        return item;
    }


    private int tailPostIncrement(int tail) {
        this.tail = (tail + 1) % capacity;
        return tail;
    }

    private int headPostIncrement(int head) {
        this.head = (this.head + 1) % capacity;
        return head;
    }


    @Override
    public String toString() {
        return Arrays.asList(array).toString();
    }
}
