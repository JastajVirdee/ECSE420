import java.util.LinkedList;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class FineGrainedContains<T>
{
    public Node head = new Node();

    public boolean contains(T item)
    {
        Node pred = head;
        pred.lock();
        Node curr = pred.next;
        curr.lock();
        int key = item.hashCode();

        try
        {

            while (curr.key <= key)
            {
                // check if they are equal
                if (curr.item == item)
                {
                    return true;
                }

                // Hand over hand locking
                pred.unlock();
                pred = curr;
                if(curr.next != null)
                {
                    curr = curr.next;
                    curr.lock();
                }
                else
                {
                    curr = null;
                    break;
                }
            }

        }
        finally
        {   
            pred.unlock();
            if (curr != null)
            {
                curr.unlock();
            }
        }

        // nothing was found
        return false;
    }

}