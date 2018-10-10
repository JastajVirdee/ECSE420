package ca.mcgill.ecse420.a1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class to simulate deadlock 2 locks are initialized and 2 threads are run.
 * Boths threads try to acquire both locks and this leads to deadlock as the
 * threads are holding the others lock.
 */
public class DeadLock {
  public static ReentrantLock lock1 = new ReentrantLock();

  public static ReentrantLock lock2 = new ReentrantLock();

  public static void main(String arg[]) {
    DeadLockThread dead = new DeadLockThread();
    DeadLockThread2 dead2 = new DeadLockThread2();

    // dead will acquire lock1 and then attempt to acquire lock2
    dead.start();

    // dead2 will acquire lock2 and then attempt to acquire lock1
    dead2.start();

    // Due to the acquisition order of the locks, this will always lead
    // to a deadlock situation.
  }

  private static class DeadLockThread extends Thread {
    public DeadLockThread() {

    }

    public void run() {
      System.out.println("Thread with id = " + this.getId() + " acquiring Lock 1");
      lock1.lock();
      System.out.println("Thread with id = " + this.getId() + " Holding Lock 1");

      System.out.println("Thread with id = " + this.getId() + " Acquiring Lock 2");
      lock2.lock();
      System.out.println("Thread with id = " + this.getId() + " Holding Lock 2");

      System.out.println("Thread with id = " + this.getId() + " Releasing locks");
      lock2.unlock();
      lock1.unlock();

      System.out.println("Thread with id = " + this.getId() + " Done thread execution");
    }
  }

  private static class DeadLockThread2 extends Thread {
    public DeadLockThread2() {

    }

    public void run() {
      System.out.println("Thread with id = " + this.getId() + " Acquiring Lock 2");
      lock2.lock();
      System.out.println("Thread with id = " + this.getId() + " Holding Lock 2");

      System.out.println("Thread with id = " + this.getId() + " Acquiring Lock 1");
      lock1.lock();
      System.out.println("Thread with id = " + this.getId() + " Holding Lock 1");

      System.out.println("Thread with id = " + this.getId() + " Releasing locks");
      lock2.unlock();
      lock1.unlock();

      System.out.println("Thread with id = " + this.getId() + " Done thread execution");
    }
  }
}
