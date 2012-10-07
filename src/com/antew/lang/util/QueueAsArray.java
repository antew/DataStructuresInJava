package com.antew.lang.util;

import java.util.NoSuchElementException;

import com.antew.lang.AbstractContainer;
import com.antew.lang.Enumeration;
import com.antew.lang.Queue;
import com.antew.lang.Visitor;
import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;

public class QueueAsArray extends AbstractContainer implements Queue {
    protected Object array[];
    protected int head;
    protected int tail;
    
    public QueueAsArray(int size) {
        array = new Object[size];
        head = 0;
        tail = size - 1;
    }
    @Override
    public void purge() {
        while (count > 0) {
            array[head] = null;
            if (++head == array.length)
                head = 0;
            
            count--;
                    
        }
        
    }

    @Override
    public void accept(Visitor visitor) {
        for (int i = 0; i < count && !visitor.isDone(); i++) {
            visitor.visit(array[i]);
        }
    }

    @Override
    public Enumeration getEnumeration() throws ContainerEmptyException {
        return new Enumeration() {
            
            protected int position = 0;
            
            @Override
            public Object nextElement() throws NoSuchElementException {
                if (position > getCount())
                    throw new NoSuchElementException();

                return array[position++];
            }
            
            @Override
            public boolean hasMoreElements() {
                return position < getCount();
            }
        };
    }

    @Override
    public Object getHead() throws ContainerEmptyException {
        if (count == 0) 
            throw new ContainerEmptyException();
        
        return array[head];
    }

    @Override
    public void enqueue(Object object) throws ContainerFullException {
        if (count == array.length)
            throw new ContainerFullException();
        
        if (++tail == array.length)
            tail = 0;
        
        array[tail] = object;
        count++;
    }

    @Override
    public Object dequeue() throws ContainerEmptyException {
        if (count == 0)
            throw new ContainerEmptyException();
        
        Object obj = array[head];
        array[head] = null;
        
        if (++head == array.length)
            head = 0;
        
        count--;
        return obj;
    }

}
