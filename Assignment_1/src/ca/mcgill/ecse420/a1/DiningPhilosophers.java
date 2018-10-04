package ca.mcgill.ecse420.a1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers
{
  // Lock to avoid deadlock and starvation
  // Avoids starvation as the reetrant lock is fair
  private static ReentrantLock lock = new ReentrantLock(true);

  private static int totalEats = 0;

  public static void main(String[] args)
  {
		int numberOfPhilosophers = 5;
    Philosopher[] philosophers = new Philosopher[numberOfPhilosophers];
    Object[] chopsticks = new Object[numberOfPhilosophers];

    // Initialize the chopsticks
    for (int i = 0; i < chopsticks.length; i++)
    {
      chopsticks[i] = new Object();
    }

    // Initialize each philosopher by setting their chopsticks
    for(int i = 0; i < philosophers.length; i++)
    {
      philosophers[i] = new Philosopher(chopsticks[i], chopsticks[(i+1)%chopsticks.length]);

      Thread philosophThread = new Thread(philosophers[i]);
      philosophThread.start();
    }
	}

  public static class Philosopher implements Runnable
  {
    private Object LeftChopstick;

    private Object RightChopstick;

    public int eatingAmount = 0;

    public Philosopher(Object leftChopstick, Object rightChopstick)
    {
      this.LeftChopstick = leftChopstick;
      this.RightChopstick = rightChopstick;
    }

		@Override
    public void run()
    {
      while(true)
      {
        try
        {
          System.out.println("Philosopher " + Thread.currentThread().getId() + " is hungry. Attempting to acquire chopsticks.");

          // Add the lock to avoid Deadlock and starvation!
          lock.lock();

          synchronized(this.LeftChopstick)
          {
            System.out.println("Philosopher " + Thread.currentThread().getId() + " picked up left chopstick.");

            synchronized(this.RightChopstick)
            {
              System.out.println("Philosopher " + Thread.currentThread().getId() + " picked up Right chopstick. Eating now.");

              // Simulate eating
              Thread.sleep(1);
            }
          }
          System.out.println("Philosopher " + Thread.currentThread().getId() + " finished using the chopsticks. Going back to thinking.");

          // Simulate thinking.
          //Thread.sleep(500);
        }
        catch(InterruptedException e)
        {
          System.out.println(e.getMessage());
          return;
        }
        finally
        {
          // Release the lock so that no one else will be blocked if thread dies
          lock.unlock();
        }
      }
    }
	}
}
