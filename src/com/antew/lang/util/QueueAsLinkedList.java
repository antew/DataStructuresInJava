package com.antew.lang.util;

import java.util.NoSuchElementException;

import com.antew.lang.AbstractContainer;
import com.antew.lang.Enumeration;
import com.antew.lang.Queue;
import com.antew.lang.Visitor;
import com.antew.lang.exception.ContainerEmptyException;
import com.antew.lang.exception.ContainerFullException;

public class QueueAsLinkedList extends AbstractContainer implements Queue {

    protected LinkedList queue;
    
    public QueueAsLinkedList() {
        this.queue = new LinkedList();
    }
    
    @Override
    public void purge() {
        queue.purge();
        count = 0;
    }

    @Override
    public void accept(Visitor visitor) throws ContainerEmptyException {
        for (LinkedList.Element element = queue.getHead(); element != null && !visitor.isDone(); element = element.getNext()) {
            visitor.visit(element);
        }
    }

    @Override
    public Enumeration getEnumeration() throws ContainerEmptyException {
        return new Enumeration() {
            protected LinkedList.Element element = queue.getHead();
            
            @Override
            public Object nextElement() throws NoSuchElementException {
                if (element == null)
                    throw new NoSuchElementException();
                
                Object data = element.getData();
                element = element.getNext();
                
                return data;
            }
            
            @Override
            public boolean hasMoreElements() {
                return element != null;
            }
        };
    }

    @Override
    public Object getHead() throws ContainerEmptyException, ContainerEmptyException {
        if (count == 0)
            throw new ContainerEmptyException();
        
        return queue.getHead();
    }

    @Override
    public void enqueue(Object object) throws ContainerFullException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Object dequeue() throws ContainerEmptyException {
        // TODO Auto-generated method stub
        return null;
    }

}
