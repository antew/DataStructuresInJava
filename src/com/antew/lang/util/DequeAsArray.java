package com.antew.lang.util;

import com.antew.lang.Deque;
import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;

public class DequeAsArray extends QueueAsArray implements Deque {

    public DequeAsArray(int size) {
        super(size);
    }

    @Override
    public Object getTail() throws ContainerEmptyException {
        if (count == 0)
            throw new ContainerEmptyException();
        
        return array[tail];
    }

    @Override
    public Object getHead() throws ContainerEmptyException {
        return super.getHead();
    }
    
    @Override
    public void enqueueHead(Object object) throws ContainerFullException {
        if (count == array.length)
            throw new ContainerFullException();
        
        if (head-- == 0)
            head = array.length - 1;
        
        array[head] = object;
        count++;
    }

    @Override
    public void enqueueTail(Object object) throws ContainerFullException {
        enqueue(object);
    }

    @Override
    public Object dequeueHead() throws ContainerEmptyException {
        return dequeue();
    }

    @Override
    public Object dequeueTail() {
        if (count == 0)
            return new ContainerEmptyException();
        
        Object result = array[tail];
        array[tail] = null;
        if (tail-- == 0)
            tail = array.length - 1;
        count--;
        
        return result;
    }

}
