import java.util.concurrent.locks.Lock;

class Bakery implements Lock{
    boolean[] flag;
    int[] label;

    /**
     * Number of threads. Used for interation in the lock
     * method.
     */
    private int n;

    public Bakery(int n){
        flag = new boolean[n];
        label = new int[n];
        this.n = n;
        for(int i = 0; i < n; i++){
            flag[i] = false;
            label[i] = 0;
        }
    }

    public void lock(){
        int me = (int) Thread.currentThread().getId() % n;
        flag[me] = true;
        label[me] = findMax(label) + 1;

        boolean spinwait = true;
        while (spinwait) {
            spinwait = false;
                
            for (int k = 0; k < n; k++) {
                if ((k != me) && flag[k] 
                && ((label[k] < label[me]) || ((label[k] == label[me]) && k < me))){
                    spinwait = true;
                    break;
                }
            }
        }
    }

    public void unlock(){
        int me = (int) Thread.currentThread().getId() % n;
        flag[me] = false;  
    }

    private int findMax(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++)
        {
            if(array[i] > max){
                max = array[i];
            }
        }
        return max;
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
