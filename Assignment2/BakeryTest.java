import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BakeryTest {

    public static Bakery lock1 = new Bakery(5);
  
    public static void main(String arg[]) {
        BakeryClient[] clients = new BakeryClient[5];
        
        for(int i = 0; i < clients.length; i++)
        {
            clients[i] = new BakeryClient();
            clients[i].start();
        }
    }
  
    private static class BakeryClient extends Thread {
        public void run() {
            System.out.println("Thread with id = " + this.getId() + " acquiring Lock 1");
            lock1.lock();
            System.out.println("Thread with id = " + this.getId() + " Holding Lock 1");
            
            try
            {
                sleep(5000);
            }
            catch(Exception e)
            {
                System.out.println("Error: " + e.getMessage());
                lock1.unlock();
                return;
            }

            System.out.println("Thread with id = " + this.getId() + " Releasing lock");
            lock1.unlock();
        }
    }
}