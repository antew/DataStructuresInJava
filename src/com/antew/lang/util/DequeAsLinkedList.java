package com.antew.lang.util;

import com.antew.lang.Deque;
import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;

public class DequeAsLinkedList extends QueueAsLinkedList implements Deque {

    @Override
    public Object getTail() throws ContainerEmptyException {
        if (count == 0)
            throw new ContainerEmptyException();
        
        return queue.getTail();
    }

    @Override
    public void enqueueHead(Object object) throws ContainerFullException {
        queue.prepend(object);
        count++;
    }

    @Override
    public void enqueueTail(Object object) throws ContainerFullException {
        queue.append(object);
        count++;
    }

    @Override
    public Object dequeueHead() throws ContainerEmptyException {
        if (count == 0)
            throw new ContainerEmptyException();
        
        Object retVal = queue.getHead();
        queue.remove(retVal);
        count--;
        
        return retVal;
    }

    @Override
    public Object dequeueTail() throws ContainerEmptyException {
        if (count == 0)
            throw new ContainerEmptyException();
        
        Object retVal = queue.getTail();
        queue.remove(retVal);
        count--;
        
        return retVal;
    }

}
