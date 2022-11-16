package concurrency.blockingqueue;

public interface BlockingQueue<T> {
    void enqueue(T item);

    T dequeue();
}