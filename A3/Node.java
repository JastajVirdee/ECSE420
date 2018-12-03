import java.util.concurrent.locks.ReentrantLock;

public class Node<T>{
    
    public ReentrantLock nodeLock = new ReentrantLock();

    public T item;
    public int key;
    public volatile Node next;

    public void lock()
    {
        this.nodeLock.lock();
        System.out.println("Node with key = " + key + " locked");
    }

    public void unlock()
    {
        System.out.println("Node with key = " + key + " unlocked");
        this.nodeLock.unlock();
    }

}