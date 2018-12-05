package ca.mcgill.ecse420.a3;

import java.util.LinkedList;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class FineGrainedContains<T>
{
    public Node head = new Node();

    public Node tail = new Node();

    public boolean contains(T item){
        // basic locking for hand-over-hand locking
        Node pred = head;
        pred.lock();
        Node curr = pred.next;
        curr.lock();
        int key = item.hashCode();

        try
        {
            while (curr.key <= key)
            {
                // check if the item has been found
                if (curr.item == item)
                {
                    return true;
                }

                // Hand over hand locking
                pred.unlock();
                pred = curr;

                // end if the tail id reached
                if(curr.next != tail)
                {
                    curr = curr.next;
                    curr.lock();
                }
                else
                {
                    curr = curr.next;
                    curr.lock();
                    break;
                }
            }
        }
        finally
        {   
            pred.unlock();
            curr.unlock();
        }

        // nothing was found
        return false;
    }

}