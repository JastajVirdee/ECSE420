import java.util.concurrent.locks.Lock;
import java.lang.*;

class Filter implements Lock{ 
    private int level[];
    private int victim[];
    
    /**
     * Constructor for the filter class
     * @param n the amount of threads
     */
    public Filter (int n){
        level = new int[n];
        victim = new int[n];

        // Initialize the levels
        for (int i = 0; i < n; i++){
            level[i] = 0;
        }
    }

    /**
     * Method to lock the lock
     */
    public void lock(){
        int threadId = (int) Thread.currentThread().getId() % level.length;
        for (int L = 1; L < level.length; L++){
            level[threadId] = L;
            victim[L] = threadId;

            for (int k = 0; k < level.length; k++) {
                while ((k != threadId) && (level[k] >= L 
                && victim[L] == threadId)){};
            }
        }
    }

    /**
     * Method to release the lock
     */
    public void unlock(){
        int threadId = (int) Thread.currentThread().getId() % level.length;
        level[threadId] = 0;
    }

    /*
    * Abstract methods that need to be defined. They do nothing basically since they 
    * is no the focus of the filter lock
    */
    public java.util.concurrent.locks.Condition newCondition() {
        return null;
    }

    public boolean tryLock(long timeout, java.util.concurrent.TimeUnit unit) throws java.lang.InterruptedException {
        return false;
    }

    public boolean tryLock() {
        return false;
    }

    public void lockInterruptibly() throws java.lang.InterruptedException {
    }
}