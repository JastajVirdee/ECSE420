import java.util.concurrent.locks.Lock;

class Filter implements Lock{ 
    private int level[] = new int[n];
    private int victim[] = new int[n];
    
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
    public void Lock(){
        int threadId = ThreadId.get();
        for (int L = 1; L < level.length; L++){
            level[threadId] = L;
            victim[L] = threadId;

            for (int k = 0; k < n; k++) {
                while ((k != threadId) && (level[k] >= L 
                && victim[L] == threadId)){};
            }
        }
    }

    /**
     * Method to release the lock
     */
    public void Release(){
        int threadId = ThreadId.get();
        level[threadId] = 0;
    }
}