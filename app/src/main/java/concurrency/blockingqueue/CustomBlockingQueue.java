package concurrency.blockingqueue;

import java.util.LinkedList;

public class CustomBlockingQueue<T> implements BlockingQueue<T> {
    private final int capacity;
    private final LinkedList<T> list;

    public CustomBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.list = new LinkedList<>();
    }

    @Override
    public synchronized void enqueue(T item) {
        while (list.size() == capacity) waitt();
        list.add(item);
        if (list.size()==1) notifyAll();
    }


    @Override
    public synchronized T dequeue() {
        while (list.size()==0) waitt();
        var val = list.remove(0);
        if (list.size()<capacity) notifyAll();
        return val;
    }

    private void waitt() {
        try { wait(); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
